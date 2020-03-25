  

# Linux Cluster Monitoring Agent

  

## Introduction

The Linux Cluster Monitoring Agent is an internal tool that records the hardware specifications of each node and monitor node resource usages in realtime. This allows the user to respond to anomalities efficiently and keep updated on cluster usages. All the information is persisted in a docker-provisioning PostgreSQL database which is hosted on one of the nodes. The bash agents hosted on each node are responsible to update the resource usage every minute.

  

  

## Architecture and Design

![Architecture demo](https://github.com/jarviscanada/jarvis_data_eng_pearl/blob/README/linux_sql/assets/monitor_cluster_demo.jpg)

As shown in architecture diagram above, the Bash (Host) Agent scripts are run on each node in the Linux Cluster and maintain the PostgreSQL database hosted on Server 1 by inserting the resource usage data to the tables every minute.

  

#### Databases and Tables

The database, `host_agent` consists of two tables `host_info` and `host_usage`.

  

1. The `host_info` table records the hardware specifications of each node. The data preserved in the `host_info` is only inserted once by `host_info.sh` since the assumption is made that the hardware specifications will remain static:

	* `id`: The unique id number representing each node is the primary key and auto-incremented by PostgreSQL
	
	* `hostname`: The hostname of each node, assumed to be unique

	* `cpu_number`: Number of cores in the CPU

	* `cpu_architecture`: The CPU's architecture

	* `cpu_model`: The manufacture model of CPU

	* `cpu_mhz`: The transmission speed of electronic devices

	* `L2_cache`: Size of L2 cache, measured in KB

	* `total_mem`: Total amount of memory of each server, measured in KB

	* `timestamp`: Time when the data is collected in UTC timezone

  

2. The `host_usage` table collects all the information from the node every minute, in order to monitor resource usage information in realtime.

	* `timestamp`: Time when the data is collected in UTC timezone

	* `host_id`: The unique id number representing each node, foreign key to the `id` field in the `host_info` table

	* `memory_free`: Amount of idle memory, measured in MB

	* `cpu_idle`: Percentage of total CPU time spent idle

	* `cpu_kernel`: Percentage of total CPU time spent on kernel

	* `disk_io`: Number of disk I/O

	* `disk_available`: Root directory available disk, measured in MB

  

#### Script Description

1. `/scripts/host_info.sh` collects the host hardware info and insert it into the `host_info` table in the `host_agent` database. It will be run only once at the installation time.

2. `/scripts/host_usage.sh` collects the current host usage (CPU and Memory) and then insert into the `host_usage` table. It will be run every minute managed by `crontab` job.

3. `/scripts/psql_docker.sh` is used to provision PostgreSQL with docker. It is used to start/stop the PostgreSQL docker container.

4. `/sql/ddl.sql` creates `host_agent` database and tables in necessary.

5. `/sql/queries.sql` contains two SQL queries which reflects the information about resource usage and can be used for future server usage planning

	* Group hosts by CPU number of cores and sort by their memory size in descending order

	* Average used memory in percentage over 5 mins interval for each host

  

  

## Usage

1. how to init database and tables

* `./scripts/psql_docker.sh start|stop [password]` to start/stop psql container instance.

*	`psql -h localhost -p 5432 -U postgres -W -f sql/ddl.sql` to init database and tables.

2. `host_info.sh` usage

	`./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password`

3. `host_usage.sh` usage

	`./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password`

4. crontab setup

```
#to run host_usage every minute using crontab, open the terminal
#run this to edit crontab job
crontab -e

#add this to crontab
* * * * * bash /home/centos/dev/jarvis_data_eng_pearl/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log

#list crontab jobs
crontab -l
  
#validate your result in psql instance
#or by checking the log details
cat /tmp/host_usage.log
```
  

  


  

## Improvements

  

1. Detect node failures and overloaded servers:  
Send out alerts if node failures if detected. Similarly, if a server is used consistently high volume, also send out alerts to the team in order to indicate that rebalance the workload distribution is necessary.

2. Handle hardware updates: 
The `host_info` table in the database should be noticed and updated once a hardware replacement happens (ex. CPU replacement).

3. Create backups of `host_agent` database:  
Currently our whole database is designed to only rely on one server in our architecture which has a high risk of server failure. A backup database is recommended to be hosted on another server.

