CREATE KEYSPACE Rent
WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 3}
AND durable_writes = true;

use rent;

CREATE TABLE rent(
id uuid,
price decimal,
vehicle text,
brand text,
PRIMARY KEY (id)
);