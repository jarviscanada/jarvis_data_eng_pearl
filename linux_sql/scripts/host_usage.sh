#!/bin/bash

if [ $# -ne 5 ]
then
	echo 'Invalid number of arguments read.'
	echo 'Usage: ./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password'
	exit 1
fi


#parse server CPU and memory usage data using bash scripts
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

curtime=$(date +%Y-%m-%d' '%T)
export PGPASSWORD=$psql_password
hostname=`hostname -f`
host_id=`psql -h $psql_host -p $psql_port -U $psql_user -d $db_name -t -c "SELECT id FROM PUBLIC.host_info WHERE hostname='$hostname';"`
memory_free=$(vmstat -t --unit M | awk '{print $4}' | tail -n1 | xargs)
cpu_idle=$(vmstat -t | awk '{print $15}' | tail -n1 | xargs)
cpu_kernel=$(vmstat -t | awk '{print $15}' | tail -n1 | xargs)
disk_io=$(vmstat --unit M | awk '{print $9}' | tail -n1 | xargs)
disk_available=$(df -BM / | awk '{print $4}' | tail -n1 | xargs | grep -o -E '[0-9]+')


#execute the INSERT statement
psql -h $psql_host -p $psql_port -U $psql_user -d $db_name -c "INSERT INTO PUBLIC.host_usage (host_id, memory_free, cpu_idel, cpu_kernel, disk_io, disk_available, "timestamp") VALUES ($host_id, $memory_free, $cpu_idle, $cpu_kernel, $disk_io, $disk_available, '$curtime');"

exit 0

