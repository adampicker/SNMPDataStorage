INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (0, 'average system load', '.1.3.6.1.4.1.2021.10.1.3.1', '1 min load', '1minload');

INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (1, 'average system load', '.1.3.6.1.4.1.2021.10.1.3.2', '5 min load', '5minload');

INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (2, 'average system load', '.1.3.6.1.4.1.2021.10.1.3.3', '15 min load', '15minload');

INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (3, 'time when it is not being used by any program.', '.1.3.6.1.4.1.2021.11.53.0', 'rawIdle', 'seconds');


INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (4, 'total size of free swap mem. in kb', '.1.3.6.1.4.1.2021.4.4.0', 'freeswap', 'kb');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (5, 'total size of swap mem. in kb.', '.1.3.6.1.4.1.2021.4.3.0', 'totalswap', 'kb');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (6, 'total ram size', '.1.3.6.1.4.1.2021.4.5.0', 'totalram', 'kb');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (7, 'total used ram', '.1.3.6.1.4.1.2021.4.6.0', 'usedram', 'kb');

INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (8, 'free ram', '.1.3.6.1.4.1.2021.4.11.0', 'freeram', 'kb');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (9, 'total shared ram', '.1.3.6.1.4.1.2021.4.13.0', 'sharedram', 'kb');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (10, 'total buffered ram', '.1.3.6.1.4.1.2021.4.14.0', 'bufferedram', 'kb');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (11, 'total cached memory', '.1.3.6.1.4.1.2021.4.15.0', 'cachedmemory', 'kb');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (12, 'mount point', '.1.3.6.1.4.1.2021.9.1.2.1', 'mountpoint', 'string');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (13, 'mount device for the partition', '.1.3.6.1.4.1.2021.9.1.3.1', 'mountdevice', 'kb');

INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (14, 'total partition size', '.1.3.6.1.4.1.2021.9.1.6.1', 'totalpartition', 'kb');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (15, 'Free disk space', '.1.3.6.1.4.1.2021.9.1.7.1', 'freedisk', 'kb');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (16, 'used disk space', '.1.3.6.1.4.1.2021.9.1.8.1', 'useddisk', 'kb');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (17, 'Disk usage percentage', '.1.3.6.1.4.1.2021.9.1.9.1', 'diskusagep', 'percentage');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (18, 'Uptime in seconds', '.1.3.6.1.2.1.25.1.1.0', 'uptime', 'seconds');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (19, 'snmp uptime in seconds', '.1.3.6.1.2.1.1.3.0', 'snmpuptime', 'seconds');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (20, 'percentage of user CPU time', '.1.3.6.1.4.1.2021.11.9.0', 'usercpu', 'percentage');

INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (21, 'raw user cpu time', '.1.3.6.1.4.1.2021.11.50.0', 'rawusercpu', 'seconds');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (22, 'percentage of system cpu time', '.1.3.6.1.4.1.2021.11.10.0', 'usercpu', 'percentage');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (23, 'raw system cpu time', '.1.3.6.1.4.1.2021.11.52.0', 'rawsystemcpu', 'seconds');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (24, 'percentages of idle CPU time', '.1.3.6.1.4.1.2021.11.11.0', 'idlecpu', 'percentage');
INSERT INTO public.mib(
id, description, oid, telnet_shortcut, unit)
VALUES (25, 'nice cpu time', '.1.3.6.1.4.1.2021.11.51.0', 'nicecpu', 'seconds');


INSERT INTO public.configuration(
id, configuration_name, default_configuration)
VALUES (100, 'konfiguracja1', TRUE);

INSERT INTO public.configuration(
id, configuration_name, default_configuration)
VALUES (101, 'konfiguracja2', FALSE);

INSERT INTO public.clients(
id, mac_address, status, configuration, type, port, pid, user_name)
VALUES (100, 'samplemac', 0, 100, 'desktop', '1601', 323, 'snmp');

INSERT INTO public.clients(
id, mac_address, status, configuration, type, port, pid, user_name)
VALUES (101, 'samplemac2', 1, 101, 'desktop', '1601', 323, 'snmp');

INSERT INTO public.clients(
id, mac_address, status, configuration, type, port, pid, user_name)
VALUES (102, 'samplemac3', 2, 100, 'desktop', '1621', 323, 'snmp4');

INSERT INTO public.clients(
id, mac_address, status, configuration, type, port, pid, user_name)
VALUES (103, 'samplemac4', 3, 101, 'desktop', '1621', 323, 'snmp5');

INSERT INTO public.configuration_mib(
configuration_id, mib_id)
VALUES (100, 0);

INSERT INTO public.configuration_mib(
configuration_id, mib_id)
VALUES (100, 1);
INSERT INTO public.configuration_mib(
configuration_id, mib_id)
VALUES (100, 2);

INSERT INTO public.configuration_mib(
configuration_id, mib_id)
VALUES (100, 3);

INSERT INTO public.configuration_mib(
configuration_id, mib_id)
VALUES (101, 3);

SELECT *
FROM public.mib_value m;