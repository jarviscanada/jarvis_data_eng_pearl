

--create a `host_agent` database if not exist
DROP DATABASE IF EXISTS host_agent;
CREATE DATABASE host_agent; 


--switch to `host_agent`
\c host_agent

--create `host_info` table if not exist
DROP TABLE IF EXISTS public.host_info CASCADE;
CREATE TABLE PUBLIC.host_info 
      ( 
         id               SERIAL PRIMARY KEY, 
         hostname         VARCHAR NOT NULL UNIQUE, 
         cpu_number       INT NOT NULL, 
         cpu_architecture VARCHAR NOT NULL, 
         cpu_model        VARCHAR NOT NULL, 
         cpu_mhz          FLOAT8 NOT NULL, 
         l2_cache         INT NOT NULL, 
         "timestamp"      TIMESTAMP NOT NULL, 
         total_mem        INT NOT NULL 
         --CONSTRAINT host_info_pk PRIMARY KEY (id), 
         --CONSTRAINT host_info_un UNIQUE (hostname) 
      ); 
--Sample INSERT statement
--INSERT INTO host_info (id, hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, "timestamp", total_mem) VALUES(1, 'jrvs-remote-desktop-centos7-6.us-central1-a.c.spry-framework-236416.internal', 1, 'x86_64', 'Intel(R) Xeon(R) CPU @ 2.30GHz', 2300, 256, '2019-05-29 17:49:53.000', 601324);
    

--create `host_usage` table if not exist    
DROP TABLE IF EXISTS public.host_usage CASCADE; 
CREATE TABLE PUBLIC.host_usage 
      ( 
         "timestamp"    TIMESTAMP NOT NULL, 
         host_id        SERIAL NOT NULL REFERENCES public.host_info(id), 
         memory_free    INT NOT NULL, 
         cpu_idel       INT NOT NULL, 
         cpu_kernel     INT NOT NULL, 
         disk_io        INT NOT NULL,
	 disk_available INT NOT NULL
);



