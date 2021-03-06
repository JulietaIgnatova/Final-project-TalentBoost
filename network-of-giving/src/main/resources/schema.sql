DROP TABLE IF EXISTS Users;

DROP TABLE IF EXISTS Charities;

DROP TABLE IF EXISTS Participants;

DROP TABLE IF EXISTS Donators;

DROP TABLE IF EXISTS Useractions;

CREATE TABLE IF NOT EXISTS Users(
    id INT IDENTITY PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    username VARCHAR(128) NOT NULL UNIQUE,
    age INT NOT NULL,
    gender VARCHAR(1),
    location VARCHAR(128),
    password VARCHAR(128),
    role VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS Charities(
    id INT IDENTITY PRIMARY KEY,
    creator_id INT NOT NULL,
    title VARCHAR(128) NOT NULL UNIQUE,
    description VARCHAR(4096) NOT NULL,
    budget_required DECIMAL  NOT NULL,
    amount_collected DECIMAL NOT NULL ,
    volunteers_required INT NOT NULL,
    volunteers_signed_up INT NOT NULL,
    image BYTEA
);

CREATE TABLE IF NOT EXISTS Participants(
    user_id INT,
    charity_id INT,
    participant_date timestamp default now()
);

CREATE TABLE IF NOT EXISTS Donators(
    user_id INT,
    charity_id INT,
    donated_money DECIMAL,
    donation_date timestamp default now()
);


CREATE TABLE IF NOT EXISTS Useractions(
   user_id INT,
   description VARCHAR(4096),
   charity_title VARCHAR(128)
);

--ALTER TABLE Charities ADD CONSTRAINT FK_USER_ID FOREIGN
--KEY (id) REFERENCES Users(id)  ON DELETE CASCADE;
