

--Group hosts by CPU number and sort by their memory size in descending order(within each cpu_number group)
SELECT
	cpu_number, id, total_mem
FROM	host_info
GROUP BY cpu_number
ORDER BY total_mem DESC
;


--Average used memory in percentage over 5 mins interval for each host. (used memory = total memory - free memory).
SELECT 
	hu.host_id, hu.hostname, 
	TIMESTAMP WITH TIME ZONE 'epoch' + INTERVAL '1 second' * round(extract('epoch' from timestamp) / 300) * 300 as timestamp, 
	((hi.total_mem-avg(hu.memory_free)*1024)/hi.total_mem*100) AS avg_used_mem_percentage
FROM host_usage hu JOIN host_info hi
ON hu.host_id=hi.id
GROUP BY hu.host_id, round(extract('epoch' from timestamp) / 300)
;


--Detect node failure. Find out when a node failed to write usage data to DB three times in a row.




