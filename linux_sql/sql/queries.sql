

--Group hosts by CPU number and sort by their memory size in descending order(within each cpu_number group)
SELECT
	cpu_number, id, total_mem
FROM	PUBLIC.host_info
ORDER BY max(total_mem) OVER (PARTITION BY cpu_number) DESC,
		cpu_number DESC;


--Average used memory in percentage over 5 mins interval for each host. (used memory = total memory - free memory).
SELECT
	hu.host_id, hi.hostname,
	ROUND(avg((hi.total_mem-hu.memory_free*1024) * 100 / hi.total_mem), 2) AS usedMemoryPercentage,
	date_trunc('hour', hu.timestamp) + date_part('minute', hu.timestamp)::int / 5 * interval '5 min' AS fiveMinuteInterval
FROM public.host_usage hu JOIN public.host_info hi
ON hu.host_id=hi.id
GROUP BY fiveMinuteInterval, hu.host_id, hi.hostname;


--Detect node failure. Find out when a node failed to write usage data to DB three times in a row.




