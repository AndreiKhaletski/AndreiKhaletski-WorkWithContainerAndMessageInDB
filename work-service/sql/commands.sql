CREATE ROLE work_app WITH
	LOGIN
	NOSUPERUSER
	CREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT -1
	PASSWORD '855312';

CREATE DATABASE containersdb
	WITH
	OWNER=work_app
	ENCODING='UTF8'
	CONNECTION LIMIT=-1
	IS_TEMPLATE=False;

CREATE SCHEMA app AUTHORIZATION "work_app";

CREATE TABLE app.container (
    id BIGSERIAL PRIMARY KEY
);

ALTER TABLE app.container
    OWNER TO "work_app";

CREATE TABLE app.message (
    id BIGSERIAL PRIMARY KEY,
    text VARCHAR(10) UNIQUE NOT NULL,
    container_id BIGINT,
    CONSTRAINT fk_container
        FOREIGN KEY (container_id)
        REFERENCES app.container (id)
        ON DELETE CASCADE
);

ALTER TABLE app.message
    OWNER TO "work_app";