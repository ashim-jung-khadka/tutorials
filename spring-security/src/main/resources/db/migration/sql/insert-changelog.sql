-- liquibase formatted sql

-- -----------------------------------------------------
-- Table app_user
-- -----------------------------------------------------
-- changeset ashim:ins_app_user

INSERT INTO app_user(user_name, password, first_name, last_name, email, status)
VALUES ('ashim','$2a$10$jhlv3GZ8V0VR.Q37usrpkeM8uP42xVUZLfK88zzHZaLdb/jMxJMoy', 'Ashim','Khadka','ashim@gmail.com', 'active');

INSERT INTO app_user(user_name, password, first_name, last_name, email, status)
VALUES ('niraj','$2a$10$jhlv3GZ8V0VR.Q37usrpkeM8uP42xVUZLfK88zzHZaLdb/jMxJMoy', 'Niraj','lama','niraj@gmail.com', 'active');

INSERT INTO app_user(user_name, password, first_name, last_name, email, status)
VALUES ('sumit','$2a$10$jhlv3GZ8V0VR.Q37usrpkeM8uP42xVUZLfK88zzHZaLdb/jMxJMoy', 'Sumit','Kc','sumit@gmail.com', 'active');

INSERT INTO app_user(user_name, password, first_name, last_name, email, status)
VALUES ('pukar','$2a$10$jhlv3GZ8V0VR.Q37usrpkeM8uP42xVUZLfK88zzHZaLdb/jMxJMoy', 'Pukar','Thapaliya','pukar@gmail.com', 'inactive');

INSERT INTO app_user(user_name, password, first_name, last_name, email, status)
VALUES ('pawan','$2a$10$jhlv3GZ8V0VR.Q37usrpkeM8uP42xVUZLfK88zzHZaLdb/jMxJMoy', 'Pawan','Maharjan','pawan@gmail.com', 'inactive');

-- rollback TRUNCATE TABLE app_user;

-- -----------------------------------------------------
-- Table user_profile
-- -----------------------------------------------------
-- changeset ashim:ins_user_profile

INSERT INTO user_profile(profile_type)
VALUES ('ADMIN');

INSERT INTO user_profile(profile_type)
VALUES ('USER');

-- rollback TRUNCATE TABLE user_profile;

-- -----------------------------------------------------
-- Table app_user_user_profile
-- -----------------------------------------------------
-- changeset ashim:ins_app_user_user_profile

INSERT INTO app_user_user_profile (user_id, user_profile_id)
SELECT u.id, profile.id FROM app_user u, user_profile profile
	WHERE u.user_name='ashim' AND profile.profile_type='ADMIN';

INSERT INTO app_user_user_profile (user_id, user_profile_id)
SELECT u.id, profile.id FROM app_user u, user_profile profile
	WHERE u.user_name='niraj' AND profile.profile_type='ADMIN';

INSERT INTO app_user_user_profile (user_id, user_profile_id)
SELECT u.id, profile.id FROM app_user u, user_profile profile
	WHERE u.user_name='sumit' AND profile.profile_type='USER';

INSERT INTO app_user_user_profile (user_id, user_profile_id)
SELECT u.id, profile.id FROM app_user u, user_profile profile
	WHERE u.user_name='pukar' AND profile.profile_type='USER';

INSERT INTO app_user_user_profile (user_id, user_profile_id)
SELECT u.id, profile.id FROM app_user u, user_profile profile
	WHERE u.user_name='pawan' AND profile.profile_type='USER';

-- rollback TRUNCATE TABLE app_user_user_profile;
