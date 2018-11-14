DROP TABLE IF EXISTS BOOKS CASCADE ;
DROP TABLE IF EXISTS SALES CASCADE ;
DROP TABLE IF EXISTS REGULAR_CUSTOMERS CASCADE;
DROP TABLE IF EXISTS USERS CASCADE;
DROP TABLE IF EXISTS GENRES CASCADE;

CREATE TABLE GENRES (
  ID SERIAL PRIMARY KEY,
  GENRE varchar(40)
);

CREATE TABLE BOOKS (
  ISBN varchar(13) PRIMARY KEY NOT NULL UNIQUE,
  TITLE varchar(50),
  AUTHOR varchar(50),
  RELEASE_DATE integer NOT NULL DEFAULT 2018,
  PUBLISHER varchar(50),
  GENRE integer NOT NULL references GENRES,
  PAGES integer,
  PRICE integer,
  IS_NEW boolean DEFAULT TRUE,
  COVER varchar(120) NOT NULL
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

INSERT INTO GENRES VALUES (1,'Regény');
INSERT INTO GENRES VALUES (2,'Életrajzok, visszaemlékezések');
INSERT INTO GENRES VALUES (3,'Szakirodalom');
INSERT INTO GENRES VALUES (4, 'Szakácskönyv');
INSERT INTO GENRES VALUES (5, 'Humor');
INSERT INTO GENRES VALUES (6, 'Hobbi');
INSERT INTO GENRES VALUES (7, 'Újság');
INSERT INTO GENRES VALUES (8, 'Vers');
INSERT INTO GENRES VALUES (9, 'Történelem');

INSERT INTO BOOKS VALUES ('9789634065906', 'Eredet', 'Dan Brown', 2018, 'Gabo', 1, 573, 1000, true, 'https://s03.static.libri.hu/cover/e0/5/4325023_5.jpg');
INSERT INTO BOOKS VALUES ('9789636893453', 'A Da Vinci-kód', 'Dan Brown', 2010, 'Gabo', 1, 648, 1500, true, 'https://s03.static.libri.hu/cover/18/5/675238_5.jpg');
INSERT INTO BOOKS VALUES ('9789634065449', 'Angyalok és démonok', 'Dan Brown', 2010, 'Gabo', 1, 708, 2000, true, 'https://s01.static.libri.hu/cover/12/b/819752_3.jpg');
INSERT INTO BOOKS VALUES ('9780552172134', 'Inferno', 'Dan Brown', 2016, 'Gabo', 1, 620, 1890, true, 'https://s01.static.libri.hu/cover/67/6/881712_3.jpg');

INSERT INTO BOOKS VALUES ('9789634059578', 'Diana utolsó nyara', 'Antonio Caprarica', 2018, 'Európa', 2, 200, 2060, true, 'https://s01.static.libri.hu/cover/2a/b/4877987_3.jpg');
INSERT INTO BOOKS VALUES ('9789633245989', 'Íróportrék', 'Szegő András', 2018, 'Central Kiadói Csoport Kft.', 2, 216, 2999, true, 'https://s01.static.libri.hu/cover/dd/5/4847142_3.jpg');
INSERT INTO BOOKS VALUES ('9789631437713', 'Napló', 'Radnóti Miklós', 2018, 'Gabo', 2, 480, 4888, true, 'https://s01.static.libri.hu/cover/7c/1/4874604_3.jpg');
INSERT INTO BOOKS VALUES ('9789631365283', 'A magányos város', 'Olivia Laing', 2018, 'Corvina Kiadó Kft.', 2, 278, 1616, true, 'https://s01.static.libri.hu/cover/1a/0/4812238_3.jpg');
INSERT INTO BOOKS VALUES ('9789632785561', 'Kortalan kortársaink', 'Kun Zsuzsa', 2018, 'Cser Kiadó Kft.', 2, 192, 2145, true, 'https://s01.static.libri.hu/cover/cf/e/4887985_3.jpg');
INSERT INTO BOOKS VALUES ('9789634791515', 'Az ismeretlen Kimi Räikkönen', 'Kari Hotakainen', 2018, 'Helikon Kiadó Kft.', 2, 264, 3349, true, 'https://s01.static.libri.hu/cover/8f/5/4700892_3.jpg');
INSERT INTO BOOKS VALUES ('9786150022024', 'Hétköznapi hősök', 'Aszalós Szilvia', 2018, 'Hit Rádió', 2, 165, 3599, true, 'https://s01.static.libri.hu/cover/14/9/4672969_3.jpg');

INSERT INTO BOOKS VALUES ('9786155477447', 'KEZDŐ HACKEREK KÉZIKÖNYVE', 'Fehér Krisztián', 2016, 'BBS-INFO Kft.', 3, 223, 1999, true, 'https://s01.static.libri.hu/cover/e1/b/3498044_3.jpg');
INSERT INTO BOOKS VALUES ('9786155477614', 'ALKALMAZÁSFEJLESZTÉS ANDROID STUDIO RENDSZERBEN', 'Fehér Krisztián', 2018, 'BBS-INFO Kft.', 3, 151, 2599, true, 'https://s02.static.libri.hu/cover/71/3/4374500_3.jpg');
INSERT INTO BOOKS VALUES ('9786155477607', 'DIRECT2D', 'Fehér Krisztián', 2018, 'BBS-INFO Kft.', 3, 150, 1799, true, 'https://s01.static.libri.hu/cover/aa/3/4374501_3.jpg');
INSERT INTO BOOKS VALUES ('9789630992640', 'Ogilvy a reklámról a digitális korban', 'Miles Young ', 2018, 'Kossuth Kiadó Zrt..', 3, 288, 2800, true, 'https://s04.static.libri.hu/cover/31/8/4576283_3.jpg');
INSERT INTO BOOKS VALUES ('9789632520247', 'Isteni színjáték', 'Dante Alighieri', 2011, 'Talentum Diákkönyvtár', 8, 544,1200,true,'https://s06.static.libri.hu/cover/7f/3/712638_5.jpg');
INSERT INTO BOOKS VALUES ('9789634058328', 'A rózsa neve', 'Umberto Eco', 2017, 'Európa Könyvkiadó Kft.', 1, 748,4000,true,'https://s03.static.libri.hu/cover/d3/3/4174484_5.jpg');
INSERT INTO BOOKS VALUES ('9789630793735', 'A prágai temető', 'Umberto Eco', 2012, 'Európa Könyvkiadó Kft.', 1, 564,3200,true,'https://s04.static.libri.hu/cover/0e/4/836351_5.jpg');
INSERT INTO BOOKS VALUES ('9789630795760', 'A Foucault-inga', 'Umberto Eco', 2015, 'Európa Könyvkiadó Kft.', 1, 820,4058,true,'https://s03.static.libri.hu/cover/df/6/2225954_5.jpg');

INSERT INTO BOOKS VALUES ('9789630796606', 'A legendás földek és helyek története', 'Umberto Eco', 2013, 'Európa Könyvkiadó Kft.', 1, 478,7999,true,'https://s04.static.libri.hu/cover/e5/9/887429_5.jpg');
INSERT INTO BOOKS VALUES ('9789634067221', 'A katedrális', 'Ken Follett', 2017, 'Gabo Kiadó', 1, 973, 4486, true,'https://s03.static.libri.hu/cover/89/e/4037692_5.jpg');
INSERT INTO BOOKS VALUES ('9789634065296', 'Az idők végezetéig', 'Ken Follett', 2017, 'Gabo Kiadó', 1, 200, 3900, true,'https://s03.static.libri.hu/cover/0d/4/4037691_5.jpg');
INSERT INTO BOOKS VALUES ('9789636892302', 'A Tű a szénakazalban', 'Ken Follett', 2008, 'Gabo Kiadó', 1, 376,2800, true,'https://s01.static.libri.hu/cover/b5/0/734160_4.jpg');

INSERT INTO BOOKS VALUES ('9789632520667', 'Goriot apó', 'Honoré de Balzac', 2012, 'Akkord Kiadó KFT.', 1,356,1500,true,'https://s05.static.libri.hu/cover/9a/e/847739_5.jpg');
INSERT INTO BOOKS VALUES ('9789633292983', 'Hivatalnokok', 'Honoré de Balzac', 2011, 'Fapadoskönyv Kiadó', 1, 240,3120,true,'https://s01.static.libri.hu/cover/25/4/829319_5.jpg');
INSERT INTO BOOKS VALUES ('9789630595032', 'Az első világháború', 'John Keegan', 2014, 'Akadémiai Kiadó',9 , 658,4500,true,'https://s06.static.libri.hu/cover/64/b/1170443_5.jpg');
INSERT INTO BOOKS VALUES ('9789634064541', 'Rövid útmutató a boldog kutyához', 'Cesar Millan', 2013, 'Gabo Kiadó', 6, 206,980,true,'https://s01.static.libri.hu/cover/4a/4/869008_4.jpg');

INSERT INTO BOOKS VALUES ('9789635904747', 'A világ legnagyobb kutyája', 'Dave Nesser', 2012, 'Totem Plusz Könyvkiadó', 6, 268,2400,true,'https://s03.static.libri.hu/cover/e3/b/859799_5.jpg');
INSERT INTO BOOKS VALUES ('9789633490365', 'Anyám tyúkja', 'Petőfi Sándor', 2017, 'HOLNAP KIADÓ KFT.', 8, 66,2500,true,'https://s03.static.libri.hu/cover/88/f/852895_5.jpg');
INSERT INTO BOOKS VALUES ('9789632445519', 'Arany Lacinak', 'Petőfi Sándor', 2015, 'SCOLAR KFT.',8, 24,1400,true,'https://s01.static.libri.hu/cover/2f/b/2411435_4.jpg');
INSERT INTO BOOKS VALUES ('9789639429031', 'Ady Endre válogatott versek', 'Ady Endre', 2001, 'Talentum Diákkönyvtár', 8, 222,808,true,'https://s01.static.libri.hu/cover/e1/a/712658_4.jpg');

INSERT INTO BOOKS VALUES ('9789631437218', 'A hobbit', 'J. R. R. Tolkien', 2018, 'MAGVETŐ KFT.', 1, 306,3500,true,'https://s03.static.libri.hu/cover/ab/b/4533028_5.jpg');
INSERT INTO BOOKS VALUES ('9789634058397', 'A Gyűrűk Ura I-III.', 'J. R. R. Tolkien', 2018, 'Európa Könyvkiadó Kft.', 1, 1836,12398,true,'https://s03.static.libri.hu/cover/50/e/4173195_5.jpg');
INSERT INTO BOOKS VALUES ('9789632785301', 'Hímzés kereten - 20 modern hímzésötlet', 'Cristin Morgan', 2018, 'CSER KÖNYVKIADÓ ÉS KER. Kft.',6 , 128,2200,true,'https://s03.static.libri.hu/cover/61/9/4870061_5.jpg');
INSERT INTO BOOKS VALUES ('9789632799964', 'WTF? - Miért rajtunk múlik, hogy mit hoz a jövő?', 'Tim O Reilly', 2018, 'Typotex Elektronikus Kiadó Kft.', 3, 473,3750,true,'https://s03.static.libri.hu/cover/75/a/4843285_5.jpg');

