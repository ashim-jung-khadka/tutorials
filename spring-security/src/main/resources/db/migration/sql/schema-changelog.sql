--liquibase formatted sql

-- -----------------------------------------------------
-- Table app_user
-- -----------------------------------------------------
-- changeset ashim:tbl_app_user

CREATE TABLE app_user (
	id SERIAL,
	user_name varchar(30) NOT NULL,
	password varchar(100) NOT NULL,
	first_name varchar(30) NOT NULL,
	last_name varchar(30) NOT NULL,
	email varchar(30) NOT NULL,
	status varchar(30) NOT NULL
);

-- rollback drop table app_user;

-- -----------------------------------------------------
-- Table user_profile
-- -----------------------------------------------------
-- changeset ashim:tbl_user_profile

CREATE TABLE user_profile (
	id SERIAL,
	profile_type varchar(30) NOT NULL
);

-- rollback drop table user_profile;

-- -----------------------------------------------------
-- Table app_user_user_profile
-- -----------------------------------------------------
-- changeset ashim:tbl_app_user_user_profile

CREATE TABLE app_user_user_profile (
	user_id int NOT NULL,
	user_profile_id int NOT NULL
);

-- rollback DROP TABLE app_user_user_profile;
