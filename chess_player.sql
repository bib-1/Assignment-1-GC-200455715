-- Using the databse Bibek200455715
USE Bibek200455715;

-- Creating checc_player table
CREATE TABLE chess_player (
id int NOT NULL AUTO_INCREMENT,
name varchar(60) NOT NULL,
country varchar(45) NOT NULL,
sex varchar(1) NOT NULL,
rating int NOT NULL,
PRIMARY KEY(id)
);

-- to check the table properties
Show CREATE TABLE chess_player; 

-- to insert the a row in the table
INSERT INTO chess_player(id, name, country, sex, rating) VALUES(1, "Bibek Poudel", "Nepal", "M", 1200);

-- delete a row from the table
-- DELETE FROM chess_player WHERE id = 2;

-- to select all data from the table
SELECT id, name, country, sex, rating FROM chess_player;



