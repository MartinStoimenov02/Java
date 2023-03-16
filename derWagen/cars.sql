CREATE DATABASE cars;
USE cars;

CREATE TABLE usr(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL,
    usrName VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    savedAds VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE(usrName),
    UNIQUE(number)
);

INSERT INTO usr (id, name, number, usrName, pass)
VALUES (1, "Martin Stoimenov", "0884462531", "martincho", "1234567");

INSERT INTO usr (name, number, usrName, pass)
VALUES ("Krum Sultov", "0987654321", "krumsultov14", "1234567");

DROP TABLE usr;

SELECT * FROM cars.usr;
SELECT * FROM car;

select * from usr where usrName="martincho" and pass="1234567";
select * from usr where usrName="krumtheidol" and pass="-273423278";
select * from usr where usrName="krumsultov14" and pass="-273423278";

Update usr set savedAds = "" where id=7;

Update car set price = "15000" where id=21;

DELETE FROM usr WHERE id=28;



CREATE TABLE car(
	id INT NOT NULL AUTO_INCREMENT,
    date DATE,
    mark VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    engine VARCHAR(255) NOT NULL,
    transmission VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    year INT NOT NULL,
    power INT NOT NULL,
    kilometres INT NOT NULL,
    colour VARCHAR(255) NOT NULL,
    coupeType VARCHAR (255) NOT NULL, 
    euroCategory INT NOT NULL, 
    city VARCHAR(255) NOT NULL, 
    description VARCHAR (255) NOT NULL,
    idUsr INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (idUsr) REFERENCES usr(id)
);

INSERT INTO car (date, mark, model, price, engine, transmission, state, year, power, kilometres, colour, coupeType, euroCategory, city, description, idUsr)
VALUES (date_add('2022-10-20', INTERVAL 0 YEAR), "Ford", "Focus", 18000, "Fuel", "Manual", "old", 2019, 115, 90000, "gold", "combi", 5, "Sofia", "old but really gold", 6);

INSERT INTO car (date, mark, model, price, engine, transmission, state, year, power, kilometres, colour, coupeType, euroCategory, city, description, idUsr)
VALUES (date_add('2020-9-21', INTERVAL 0 YEAR), "Ford", "Focus", 8000, "Fuel", "Manual", "old", 2014, 98, 346000, "blue", "combi", 4, "Sofia", "old but gold", 6);

INSERT INTO car (date, mark, model, price, engine, transmission, state, year, power, kilometres, colour, coupeType, euroCategory, city, description, idUsr)
VALUES (date_add('2021-1-14', INTERVAL 0 YEAR), "Opel", "Astra", 10000, "Diesel", "Automatic", "old", 2010, 120, 176000, "red", "sedan", 3, "Pernik", "bokluk", 6);

INSERT INTO car (date, mark, model, price,engine, transmission, state, year, power, kilometres, colour, coupeType, euroCategory, city, description, idUsr)
VALUES ('2022-12-13', 'Vw', 'Golf 4', 3000.0, 'fuel', 'manual', 'old', 2001, 115, 170000, 'grey', 'hatchback', 4, 'Sofia', 'Perniks future car!', 9);

DROP TABLE car;

SELECT * FROM cars.car;

select * from car order by price;

select * from car where mark = "Ford" and price >= "5000" and year >= "2010" and power >= "60";

select * from car where price >= "5000" and price <= "10000" and year >= "2010" and year <= "2020" and power >= "70" and power <= "120";

select * from car where mark = "Ford" and price>="10000" and state = "old" and city = "sofia";

select * from car where id=7 or id=8 or id=9;

SELECT COUNT(*) from car where mark = "Ford";

select * from car where mark = "ford" and model = "focus" and engine = "fuel" and transmission = "manual" and state = "old" and kilometres = "100000" and colour = "400000" and coupeType = "combi" and euro = "4" and city = "sofia"; 

DELETE FROM car WHERE id=6;