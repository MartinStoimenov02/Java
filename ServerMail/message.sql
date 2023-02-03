CREATE DATABASE messages;
USE messages;

CREATE TABLE usr(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL,
    usrName VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    friends VARCHAR(255),
    lastseen VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE(usrName),
    UNIQUE(number)
);

INSERT INTO usr (id, name, number, usrName, pass, friends, lastseen)
VALUES (0, "01.01.2023", "0000000000", "mymailteam", "0000000000", "0", "0");

Select * from usr where usrName="krumsultov14";

SELECT * FROM messages.usr;

DELETE FROM usr WHERE id=11;

Drop table usr;