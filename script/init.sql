DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS SALES;
DROP TABLE IF EXISTS REGULAR_CUSTOMERS;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS GENRES;

CREATE TABLE GENRES (
  ID SERIAL PRIMARY KEY,
  GENRE varchar(20)
);

CREATE TABLE BOOKS (
  ISBN varchar(13) PRIMARY KEY NOT NULL UNIQUE,
  TITLE varchar(50),
  AUTHOR varchar(30),
  RELEASE_DATE integer NOT NULL DEFAULT 2018,
  PUBLISHER varchar(30),
  GENRE integer NOT NULL references GENRES,
  PAGES integer,
  IS_NEW boolean DEFAULT TRUE
);

CREATE TABLE USERS (
  USERNAME varchar(20) PRIMARY KEY UNIQUE NOT NULL,
  FIRST_NAME varchar(15),
  LAST_NAME varchar(15),
  EMAIL varchar(30) UNIQUE,
  PASSW varchar(200)
);

CREATE TABLE SALES (
  ID SERIAL PRIMARY KEY,
  USERNAME varchar (20) NOT NULL references USERS,
  PURCHASE_DATE DATE NOT NULL DEFAULT CURRENT_DATE,
  ADDRESS varchar(60),
  PURCHASED_DATE varchar(13),
  RETURNED_BOOKS int DEFAULT 0
);

CREATE TABLE REGULAR_CUSTOMERS (
  ID SERIAL PRIMARY KEY,
  USERNAME varchar (20) NOT NULL references USERS,
  LEVEL integer NOT NULL DEFAULT 1
);

INSERT INTO GENRES VALUES (1, 'action');

INSERT INTO BOOKS VALUES ('9789634065906', 'Eredet', 'Dan Brown', 2018, 'Gabo', 1, 573);
