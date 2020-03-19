#!/bin/bash

#check the arguments of the script
if [ $# -gt 2 ] || [ $# -lt 1 ]
then
	echo "Invalid number of arugments"
	echo "Usage: ./psql_docker.sh start|stop [postgres_password]"
	exit 1
fi

if [ $1 == "start" ]
then
	#su centos
	#check if docker is running. If not, start docker.
	systemctl status docker || systemctl start docker

	#pull the latest image named postgres from docker
	docker pull postgres

	#create a new volume if not exists
	docker volume create pgdata

	#check if user put their own password, if not, use the default
	export PGPASSWORD='password'
	if [ $# -eq 2 ]
	then
		pgpassword=$2
	fi

	#check if the container `jrvs-psql` is created or not
	#check if the computer `jrvs-psql` is installed or not
	jpCreate=`docker container ls -a -f name="jrvs-psql" | wc -l`
		
	
	#if the jrvs-psql is not created, create one
	if [ $jpCreate -eq 1 ]
	then
		docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	fi

	#check if `jrvs-psql` container is running
	#analogy: check `jrvs-psql` is powered on
	jpRunning=`docker ps -f name="jrvs-psql" | wc -l`

	#if the jrvs-psql is off, turn on it
	if [ $jpRunning -eq 1 ]
	then
		docker container start jrvs-psql
	fi


elif [ $1 == "stop" ]
then
	docker container stop jrvs-psql


else 
	echo 'Invalid command read.'
	echo 'Usage: ./psql_docker.sh start|stop [postgres_password]'
	exit 1
fi


