CREATE SCHEMA IF NOT EXISTS ORDER_SERVICE;

CREATE TABLE IF NOT EXISTS ORDER_SERVICE.USERS (
ID bigint not null, 
ENABLED bit, 
PASSWORD varchar(255), 
USERNAME varchar(255), 
PERSON_ID bigint,
CREATED_AT timestamp,
primary key (id)
);

CREATE TABLE IF NOT EXISTS ORDER_SERVICE.ROLES (
id bigint not null, 
name varchar(255), 
ENABLED bit,
CREATED_AT timestamp,
primary key (id)
);

CREATE TABLE IF NOT EXISTS ORDER_SERVICE.USER_ROLES (
USER_ID INT NOT NULL,
ROLE_ID INT NOT NULL,
CONSTRAINT PK_UR PRIMARY KEY (USER_ID, ROLE_ID),
FOREIGN KEY (USER_ID) REFERENCES USERS (ID),
FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ID)
);

CREATE TABLE IF NOT EXISTS ORDER_SERVICE.PRODUCTS (
id bigint not null, 
name varchar(255), 
PRICE DECIMAL(19,10),
description varchar(255),
thumbnail_image varchar(255),
measurement_type varchar(255),
category varchar(255),
fragile bit,
ENABLED bit,
CREATED_AT timestamp,
primary key (id)
);

CREATE TABLE IF NOT EXISTS ORDER_SERVICE.ORDERS (
id bigint not null, 
name varchar(255), 
USER_ID bigint,
state varchar(255),
ENABLED bit,
CREATED_AT timestamp,
primary key (id)
);
