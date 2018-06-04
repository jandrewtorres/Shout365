CREATE TABLE user
  (
     uid         INT,
     name        VARCHAR(50) NOT NULL UNIQUE,
     date_joined DATETIME NOT NULL,
     PRIMARY KEY (uid)
  );

CREATE TABLE business
  (
     bid         INT,
     name        VARCHAR(100) NOT NULL,
     address     VARCHAR(100) NOT NULL,
     city        VARCHAR(100) NOT NULL,
     state       VARCHAR(100) NOT NULL,
     postal_code VARCHAR(100) NOT NULL,
     latitude    FLOAT NOT NULL,
     longitude   FLOAT NOT NULL,
     PRIMARY KEY (bid)
  );

CREATE TABLE review
  (
     rid        INT,
     bid        INT NOT NULL,
     uid        VARCHAR(20) NOT NULL,
     stars      INT NOT NULL,
     date       DATETIME NOT NULL,
     text       TEXT(20000),
     was_useful INT,
     PRIMARY KEY (rid),
     FOREIGN KEY (bid) REFERENCES business(bid),
     FOREIGN KEY (uid) REFERENCES user(uid)
  );

CREATE TABLE category
  (
     cid      INT,
     bid      INT NOT NULL,
     category VARCHAR(100) NOT NULL,
     PRIMARY KEY (cid),
     FOREIGN KEY (bid) REFERENCES business (bid)
  );

CREATE TABLE checkin
  (
     ciid INT,
     bid  INT NOT NULL,
     uid  INT NOT NULL,
     time DATETIME NOT NULL,
     PRIMARY KEY (ciid),
     FOREIGN KEY (bid) REFERENCES business (bid),
     FOREIGN KEY (uid) REFERENCES user (uid)
  );

CREATE TABLE friend
  (
     fid       INT,
     user_id   INT NOT NULL,
     friend_id INT NOT NULL,
     PRIMARY KEY (fid),
     FOREIGN KEY (user_id) REFERENCES user (uid),
     FOREIGN KEY (friend_id) REFERENCES user (uid)
  );
