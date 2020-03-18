

--create a `host_agent` database if not exist
CREATE DATABASE host_agent; 


--switch to `host_agent`
\c host_agent;

--create `host_info` table if not exist
DROP TABLE IF EXISTS public.host_info CASCADE;
CREATE TABLE PUBLIC.host_info 
      ( 
         id               SERIAL NOT NULL, 
         hostname         VARCHAR NOT NULL, 
         cpu_number       INT2 NOT NULL, 
         cpu_architecture VARCHAR NOT NULL, 
         cpu_model        VARCHAR NOT NULL, 
         cpu_mhz          FLOAT8 NOT NULL, 
         l2_cache         INT4 NOT NULL, 
         "timestamp"      TIMESTAMP NULL, 
         total_mem        INT4 NULL, 
         CONSTRAINT host_info_pk PRIMARY KEY (id), 
         CONSTRAINT host_info_un UNIQUE (hostname) 
      ); 
--Sample INSERT statement
--INSERT INTO host_info (id, hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, "timestamp", total_mem) VALUES(1, 'jrvs-remote-desktop-centos7-6.us-central1-a.c.spry-framework-236416.internal', 1, 'x86_64', 'Intel(R) Xeon(R) CPU @ 2.30GHz', 2300, 256, '2019-05-29 17:49:53.000', 601324);
    

--create `host_usage` table if not exist    
DROP TABLE IF EXISTS public.host_usage CASCADE; 
CREATE TABLE PUBLIC.host_usage 
      ( 
         "timestamp"    TIMESTAMP NOT NULL, 
         host_id        SERIAL NOT NULL, 
         memory_free    INT4 NOT NULL, 
         cpu_idel       INT2 NOT NULL, 
         cpu_kernel     INT2 NOT NULL, 
         disk_io        INT4 NOT NULL, 
         disk_available INT4 NOT NULL, 
         CONSTRAINT host_usage_host_info_fk FOREIGN KEY (host_id) REFERENCES host_info(id) 
      ); 
--Sample INSERT statement
--INSERT INTO host_usage ("timestamp", host_id, memory_free, cpu_idel, cpu_kernel, disk_io, disk_available) VALUES('2019-05-29 15:48:47.000', 1, 123, 123, 123, 123, 123);



