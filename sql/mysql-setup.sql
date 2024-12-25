create database test;

use test;

create table dbaas_catalog (
    id integer not null auto_increment primary key, 
    service varchar(100),
    method varchar(100),
    version varchar(30),
    full_sql varchar(16000),
    created timestamp default now(), 
    updated timestamp default now() on update now()
);

INSERT INTO dbaas_catalog(service,method,version,full_sql) VALUES ('sample','catalog','v1','select VERSION()');
	 
INSERT INTO dbaas_catalog(service,method,version,full_sql) VALUES ('sample','schemas','v1','select SCHEMA_NAME FROM information_schema.schemata order by 1');
	 
INSERT INTO dbaas_catalog(service,method,version,full_sql) VALUES ('sample','tables','v1','select TABLE_SCHEMA ,TABLE_NAME, engine  FROM information_schema.TABLES order by 1,2');

INSERT INTO dbaas_catalog(service,method,version,full_sql) VALUES ('sample','tables-by-schema','v1','select TABLE_SCHEMA ,TABLE_NAME, engine FROM information_schema.TABLES WHERE TABLE_SCHEMA=? order by 1,2');
 