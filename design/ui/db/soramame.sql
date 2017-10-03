CREATE DATABASE soramame
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'ja_JP.UTF-8'
       LC_CTYPE = 'ja_JP.UTF-8'
       CONNECTION LIMIT = -1;
       
/* Drop Tables */

DROP TABLE IF EXISTS user_access_counter;
DROP TABLE IF EXISTS participation_hope_counter;
DROP TABLE IF EXISTS numbering;
DROP TABLE IF EXISTS type_industry_mst;
DROP TABLE IF EXISTS location_mst;
DROP TABLE IF EXISTS recruitment_area_mst;
DROP TABLE IF EXISTS years_experience_mst;
DROP TABLE IF EXISTS age_group_mst;
DROP TABLE IF EXISTS user_career_overview;
DROP TABLE IF EXISTS user_base;
DROP TABLE IF EXISTS job_category_mst;
DROP TABLE IF EXISTS transmission_history;
DROP TABLE IF EXISTS project_access_counter;
DROP TABLE IF EXISTS user_scout_counter;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS affiliation;




/* Create Tables */

CREATE TABLE user_access_counter
(
	user_name varchar(32) NOT NULL,
	user_access_count int DEFAULT 0 NOT NULL,
	access_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (user_name)
) WITHOUT OIDS;


CREATE TABLE participation_hope_counter
(
	project_id varchar(12) NOT NULL,
	participation_hope_count int DEFAULT 0 NOT NULL,
	participation_hope_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (project_id)
) WITHOUT OIDS;


CREATE TABLE numbering
(
	-- creear_id：01／project_id：02
	numbering_code varchar(2) NOT NULL,
	id varchar(12) DEFAULT '000000000000' NOT NULL,
	PRIMARY KEY (numbering_code)
) WITHOUT OIDS;


CREATE TABLE type_industry_mst
(
	type_industry_code varchar(7) NOT NULL,
	type_industry_name varchar(256) NOT NULL,
	sort_number int DEFAULT 0 NOT NULL,
	PRIMARY KEY (type_industry_code)
) WITHOUT OIDS;


CREATE TABLE location_mst
(
	location_code varchar(3) NOT NULL,
	location_name varchar(256) NOT NULL,
	sort_number int DEFAULT 0 NOT NULL,
	PRIMARY KEY (location_code)
) WITHOUT OIDS;


CREATE TABLE recruitment_area_mst
(
	recruitment_area_code varchar(7) NOT NULL,
	recruitment_area_name varchar(256) NOT NULL,
	sort_number int DEFAULT 0 NOT NULL,
	PRIMARY KEY (recruitment_area_code)
) WITHOUT OIDS;


CREATE TABLE years_experience_mst
(
	years_experience_code varchar(2) NOT NULL,
	years_experience_name varchar(24) NOT NULL,
	sort_number int DEFAULT 0 NOT NULL,
	PRIMARY KEY (years_experience_code)
) WITHOUT OIDS;


CREATE TABLE age_group_mst
(
	age_group_code varchar(2) NOT NULL,
	age_group_name varchar(24) NOT NULL,
	sort_number int DEFAULT 0 NOT NULL,
	PRIMARY KEY (age_group_code)
) WITHOUT OIDS;


CREATE TABLE user_career_overview
(
	career_id varchar(12) NOT NULL,
	user_name varchar(32) NOT NULL,
	affiliation_name varchar(32) NOT NULL,
	job_category_code varchar(7) NOT NULL,
	-- yyyy/mm/dd
	period_from varchar(10) DEFAULT '1970/01/01' NOT NULL,
	-- yyyy/mm/dd
	period_to varchar(10) DEFAULT '1970/01/01' NOT NULL,
	over_view varchar(400) NOT NULL,
	register_name varchar(64) NOT NULL,
	register_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	update_name varchar(64) NOT NULL,
	update_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (career_id)
) WITHOUT OIDS;


CREATE TABLE user_base
(
	user_name varchar(32) NOT NULL,
	password varchar(1024) NOT NULL,
	-- 10:Player/20:Manager
	role varchar(2) NOT NULL,
	-- yyyy/mm/dd
	birth_day varchar(10) DEFAULT '1970/01/01' NOT NULL,
	email varchar(256) NOT NULL,
	live_code varchar(7) NOT NULL,
	register_name varchar(64) NOT NULL,
	register_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	update_name varchar(64) NOT NULL,
	update_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (user_name)
) WITHOUT OIDS;


CREATE TABLE job_category_mst
(
	job_category_code varchar(7) NOT NULL,
	job_category_name varchar(256) NOT NULL,
	sort_number int DEFAULT 0 NOT NULL,
	PRIMARY KEY (job_category_code)
) WITHOUT OIDS;


CREATE TABLE transmission_history
(
	history_id varchar(12) NOT NULL,
	transmission_type varchar(2) NOT NULL,
	-- 送信者名称
	senders_name varchar(32) NOT NULL,
	-- 受信者名称
	recipient_name varchar(32) NOT NULL,
	register_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (history_id)
) WITHOUT OIDS;


CREATE TABLE project_access_counter
(
	project_id varchar(12) NOT NULL,
	project_access_count int DEFAULT 0 NOT NULL,
	access_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (project_id)
) WITHOUT OIDS;


CREATE TABLE user_scout_counter
(
	user_name varchar(32) NOT NULL,
	user_scout_count int DEFAULT 0 NOT NULL,
	scout_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (user_name)
) WITHOUT OIDS;


CREATE TABLE project
(
	project_id varchar(12) NOT NULL,
	user_name varchar(32) NOT NULL,
	title varchar(128) NOT NULL,
	recruitment_area_code varchar(7) NOT NULL,
	over_view varchar(400) NOT NULL,
	register_name varchar(64) NOT NULL,
	register_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	update_name varchar(64) NOT NULL,
	update_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (project_id)
) WITHOUT OIDS;


CREATE TABLE affiliation
(
	user_name varchar(32) NOT NULL,
	affiliation_name varchar(32),
	address varchar(256),
	type_industry_code varchar(7),
	activities varchar(400),
	register_name varchar(64) NOT NULL,
	register_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	update_name varchar(64) NOT NULL,
	update_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (user_name)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE user_career_overview
	ADD FOREIGN KEY (user_name)
	REFERENCES user_base (user_name)
	ON UPDATE CASCADE
	ON DELETE CASCADE
;

ALTER TABLE affiliation
	ADD FOREIGN KEY (user_name)
	REFERENCES user_base (user_name)
	ON UPDATE CASCADE
	ON DELETE CASCADE
;

ALTER TABLE project
	ADD FOREIGN KEY (user_name)
	REFERENCES user_base (user_name)
	ON UPDATE CASCADE
	ON DELETE CASCADE
;

ALTER TABLE user_access_counter
	ADD FOREIGN KEY (user_name)
	REFERENCES user_base (user_name)
	ON UPDATE CASCADE
	ON DELETE CASCADE
;

ALTER TABLE user_scout_counter
	ADD FOREIGN KEY (user_name)
	REFERENCES user_base (user_name)
	ON UPDATE CASCADE
	ON DELETE CASCADE
;

ALTER TABLE project_access_counter
	ADD FOREIGN KEY (project_id)
	REFERENCES project (project_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
;

ALTER TABLE participation_hope_counter
	ADD FOREIGN KEY (project_id)
	REFERENCES project (project_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
;


/* Comments */

COMMENT ON COLUMN numbering.numbering_code IS 'creear_id：01／project_id：02';
COMMENT ON COLUMN user_career_overview.period_from IS 'yyyy/mm/dd';
COMMENT ON COLUMN user_career_overview.period_to IS 'yyyy/mm/dd';
COMMENT ON COLUMN user_base.role IS '10:Player/20:Manager';
COMMENT ON COLUMN user_base.birth_day IS 'yyyy/mm/dd';
COMMENT ON COLUMN transmission_history.senders_name IS '送信者名称';
COMMENT ON COLUMN transmission_history.recipient_name IS '受信者名称';



