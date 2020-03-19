
## Introduction
Cluster Monitor Agent is an internal tool that records the hardware specifications of each node and monitor node resource usages in realtime. The collected data should be stored in an RDBMS database. It helps the infrastructure team to generate some reports for furture resourse planning purpose. 


## Architecture and Design
* `host_info` table records the hardware specifications of each node
* `host_usage` table records the resource usage of each node every minute 
* `host_info.sh` collects the host hardware info and insert it into the database. It will be run only once at the installation time.
* `host_usage.sh` collects the current host usage (CPU and Memory) and then insert into the database. It will be triggered by the `crontab` job every minute.


## Usage
* how to init database and tables
	1. `./scripts/psql_docker.sh start|stop [password]` to start/stop psql container instance.
	2. `psql -h localhost -p 5432 -U postgres -W -f sql/ddl.sql` to init database and tables
*  `host_info.sh` usage
	`./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password`
* `host_usage.sh` usage
	`./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password`
*  crontab setup
	```to run host_usage every minute using crontab, open the terminal
	crontab -e
	#add this to crontab
	* * * * * bash /home/centos/dev/jrvs/bootcamp/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log

	#list crontab jobs
	crontab -l

	#validate your result in psql instance```


## Improvements 
1. Detect node failures
2. Handle hardware updates
3. Detect overloaded servers and alarm it to team


