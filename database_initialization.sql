
CREATE TABLE user (
	uid BIGINT NOT NULL AUTO_INCREMENT,
	fname VARCHAR(15) NOT NULL,
	lname VARCHAR(15) NOT NULL,
	username VARCHAR(10) NOT NULL UNIQUE,
	password VARCHAR(10) NOT NULL,
	PRIMARY KEY (uid)
);

CREATE TABLE user_interest_tag (
	uid BIGINT NOT NULL AUTO_INCREMENT,
	tag VARCHAR(10),
	FOREIGN KEY (uid) REFERENCES user (uid)
);

CREATE TABLE matches (
	uid1 BIGINT NOT NULL,
	uid2 BIGINT NOT NULL,
	tstamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (uid1) REFERENCES user (uid),
	FOREIGN KEY (uid2) REFERENCES user (uid)
);

CREATE TABLE message_instance (
	uidfrom BIGINT NOT NULL,
	uidto BIGINT NOT NULL,
	content VARCHAR(50),
	FOREIGN KEY (uidfrom) REFERENCES user (uid),
	FOREIGN KEY (uidto) REFERENCES user (uid)
);

CREATE TABLE community (
	cid BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(10),
	imagepath VARCHAR(50),
	description VARCHAR(50),
	PRIMARY KEY (cid)
);

CREATE TABLE community_tags (
	cid BIGINT NOT NULL,
	tag VARCHAR(10),
	FOREIGN KEY (cid) REFERENCES community (cid)
);

CREATE TABLE community_post (
	uid BIGINT NOT NULL,
	cid BIGINT NOT NULL,
	tstamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	content VARCHAR(100),
	FOREIGN KEY (uid) REFERENCES user (uid),
	FOREIGN KEY (cid) REFERENCES community (cid)
);

CREATE TABLE community_user_profile (
	uid BIGINT NOT NULL,
	cid BIGINT NOT NULL,
	description VARCHAR(50),
	FOREIGN KEY (uid) REFERENCES user (uid),
	FOREIGN KEY (cid) REFERENCES community (cid)
);

CREATE TABLE user_community_profile_image (
	uid BIGINT NOT NULL,
	cid BIGINT NOT NULL,
	imagepath VARCHAR(50),
	FOREIGN KEY (uid) REFERENCES user (uid),
	FOREIGN KEY (cid) REFERENCES community (cid)	
);
