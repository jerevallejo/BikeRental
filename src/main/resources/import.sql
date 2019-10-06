
INSERT INTO clients (name, last_name, create_at) VALUES ('Jeremias1', 'Vallejo', '2001-01-01');
INSERT INTO clients (name, last_name, create_at) VALUES ('Juan', 'Alonzo', '2002-02-02');
INSERT INTO clients (name, last_name, create_at) VALUES ('Pedro', 'Suarez', '2003-03-03');
INSERT INTO clients (name, last_name, create_at) VALUES ('Ana', 'Liza', '2004-04-04');
INSERT INTO clients (name, last_name, create_at) VALUES ('Micaela', 'Moreno', '2005-05-05');
INSERT INTO clients (name, last_name, create_at) VALUES ('Javier', 'Rodriguez', '2006-06-06');
INSERT INTO clients (name, last_name, create_at) VALUES ('Marta', 'Martinez', '2007-07-07');
INSERT INTO clients (name, last_name, create_at) VALUES ('Ramon', 'Rojas', '2008-08-08');
INSERT INTO clients (name, last_name, create_at) VALUES ('Florencia', 'Fernandez', '2009-09-09');
INSERT INTO clients (name, last_name, create_at) VALUES ('Pablo', 'Gomez', '2010-10-10');
INSERT INTO clients (name, last_name, create_at) VALUES ('Franco', 'Garcia', '2011-11-11');

INSERT INTO services (name, duration, price, create_at) VALUES ('per hour', 'hour', 5, NOW());
INSERT INTO services (name, duration, price, create_at) VALUES ('a day', 'day', 20, NOW());
INSERT INTO services (name, duration, price, create_at) VALUES ('a week', 'week', 60, NOW());

/* Create invoices */
INSERT INTO invoices (client_id, create_at) VALUES (1, NOW());
INSERT INTO invoices_items (quantity, service_id, invoice_id) VALUES (2, 1, 1);
INSERT INTO invoices_items (quantity, service_id, invoice_id) VALUES (1, 2, 1);

INSERT INTO invoices (client_id, create_at) VALUES (2, NOW());
INSERT INTO invoices_items (quantity, service_id, invoice_id) VALUES (3, 3, 2);
INSERT INTO invoices_items (quantity, service_id, invoice_id) VALUES (1, 2, 2);