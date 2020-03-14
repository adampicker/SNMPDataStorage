INSERT INTO public.mib(
	id, description, oid, telnet_shortcut, unit)
	VALUES (0 ,'average system load', '.1.3.6.1.4.1.2021.10.1.3.1', '1 min load', '1minload');

INSERT INTO public.mib(
	id, description, oid, telnet_shortcut, unit)
	VALUES (1, 'average system load', '.1.3.6.1.4.1.2021.10.1.3.2', '5 min load', '5minload');

INSERT INTO public.mib(
	id, description, oid, telnet_shortcut, unit)
	VALUES (2, 'average system load','.1.3.6.1.4.1.2021.10.1.3.3', '15 min load', '15minload');

INSERT INTO public.mib(
	id, description, oid, telnet_shortcut, unit)
	VALUES (3, 'time when it is not being used by any program.', '.1.3.6.1.4.1.2021.11.53.0', 'rawIdle','seconds');


INSERT INTO public.configuration(
	id)
	VALUES (0);

INSERT INTO public.configuration(
	id)
	VALUES (1);


INSERT INTO public.clients(
	id, mac_address, status, configuration)
	VALUES (100, 'samplemac', 0, 0);

INSERT INTO public.clients(
    id, mac_address, status, configuration)
    VALUES (101, 'samplemac2', 0, 1);

INSERT INTO public.configuration_mib(
	configuration_id, mib_id)
	VALUES (0, 0);

INSERT INTO public.configuration_mib(
	configuration_id, mib_id)
	VALUES (0, 1);
INSERT INTO public.configuration_mib(
	configuration_id, mib_id)
	VALUES (0, 2);

	INSERT INTO public.configuration_mib(
	configuration_id, mib_id)
	VALUES (0, 3);

INSERT INTO public.configuration_mib(
	configuration_id, mib_id)
	VALUES (1, 3);

SELECT * FROM public.mib_value m;