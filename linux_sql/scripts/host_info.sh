#!/bin/bash

if [ $# != 5 ]
then
	echo 'Invalid number of arguments read'
	echo 'Usage: ./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password'
	exit 1
fi


#assign CLI arguments to variables
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5


#parse host hardware specifications using bash cmds
lscpu_out=`lscpu`
hostname=`hostname -f`
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep '^Model name:' | awk -F ':' '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep '^CPU MHz:' | awk -F ':' '{print $2}' | xargs)
L2_cache=$(echo "$lscpu_out" | egrep '^L2 cache:' | awk -F ':' '{print $2}' | grep -o -E '[0-9]+' | xargs)
total_mem=$(grep MemTotal /proc/meminfo | awk '{print $2}' | xargs)
curtime=$(date +%Y-%m-%d' '%T)


#construct the INSERT statement
insert_stmt="INSERT INTO host_info (hostname,cpu_number,cpu_architecture,cpu_model,cpu_mhz,L2_cache,"timestamp") VALUES ("$hostname", $cpu_number, "$cpu_architecture", "$cpu_model", "$cpu_mhz", $L2_cache, $total_mem, $curtime)"


#execute the INSERT statement through psql CLI tool
export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -U $psql_user -d $db_name << EOF
INSERT INTO PUBLIC.host_info 
	(hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, "timestamp") 
VALUES 
	('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $L2_cache, $total_mem, '$curtime');
EOF

exit 0

