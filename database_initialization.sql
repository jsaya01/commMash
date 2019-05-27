
CREATE TABLE cm.user (
	uid BIGINT NOT NULL AUTO_INCREMENT,
	fname VARCHAR(20) NOT NULL,
	lname VARCHAR(20) NOT NULL,
	username VARCHAR(20) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL,
	description VARCHAR(244),
	PRIMARY KEY (uid)
);

CREATE TABLE userInterestTag (
	tid BIGINT NOT NULL AUTO_INCREMENT
	uid BIGINT NOT NULL,
	tag VARCHAR(20),
	FOREIGN KEY (uid) REFERENCES cm.user (uid),
	PRIMARY KEY (tid)
);

CREATE TABLE cm.matches (
	mid BIGINT NOT NULL AUTO_INCREMENT,
	uid1 BIGINT NOT NULL,
	uid2 BIGINT NOT NULL,
	tstamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (uid1) REFERENCES cm.user (uid),
	FOREIGN KEY (uid2) REFERENCES cm.user (uid),
	PRIMARY KEY (mid)
);

CREATE TABLE messageInstance (
	iid BIGINT NOT NULL AUTO_INCREMENT,
	uidfrom BIGINT NOT NULL,
	uidto BIGINT NOT NULL,
	content VARCHAR(300),
	FOREIGN KEY (uidfrom) REFERENCES cm.user (uid),
	FOREIGN KEY (uidto) REFERENCES cm.user (uid),
	PRIMARY KEY (iid)
);

CREATE TABLE cm.community (
	cid BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20),
	imagepath VARCHAR(50),
	description VARCHAR(50),
	PRIMARY KEY (cid)
);

CREATE TABLE communityTags (
	tid BIGINT NOT NULL AUTO_INCREMENT,
	cid BIGINT NOT NULL,
	tag VARCHAR(20),
	FOREIGN KEY (cid) REFERENCES cm.community (cid),
	PRIMARY KEY (tid)
);

CREATE TABLE communityPost (
	pid BIGINT NOT NULL AUTO_INCREMENT,
	uid BIGINT NOT NULL,
	cid BIGINT NOT NULL,
	tstamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	content VARCHAR(250),
	FOREIGN KEY (uid) REFERENCES cm.user (uid),
	FOREIGN KEY (cid) REFERENCES cm.community (cid),
	PRIMARY KEY (pid)
);

CREATE TABLE communityUserProfile (
	uid BIGINT NOT NULL,
	cid BIGINT NOT NULL,
	description VARCHAR(250),
	FOREIGN KEY (uid) REFERENCES cm.user (uid),
	FOREIGN KEY (cid) REFERENCES cm.community (cid)
);

-- CREATE TABLE userCommunityProfileImage (
-- 	pid BIGINT NOT NULL AUTO_INCREMENT,
-- 	uid BIGINT NOT NULL,
-- 	cid BIGINT NOT NULL,
-- 	imagepath VARCHAR(50),
-- 	FOREIGN KEY (uid) REFERENCES cm.user (uid),
-- 	FOREIGN KEY (cid) REFERENCES cm.community (cid),
-- 	PRIMARY KEY (pid)
-- );
