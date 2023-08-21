DROP DATABASE IF EXISTS test_friend  ;
CREATE DATABASE test_friend ;
USE test_friend ;

DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
                       ID int NOT NULL AUTO_INCREMENT,
                       Username VARCHAR(255) NOT NULL UNIQUE,
                       Email VARCHAR(60) NOT NULL UNIQUE,
                       Password_hash VARCHAR(255) NOT NULL,
                       Is_administrator BOOLEAN NOT NULL DEFAULT FALSE,
                       Date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
                       PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS FRIENDS;

CREATE TABLE FRIENDS(
                        ID int NOT NULL AUTO_INCREMENT,
                        User_ID int NOT NULL,
                        Friend_ID int NOT NULL,
                        PRIMARY KEY(ID),

                        CONSTRAINT un unique (User_ID, Friend_ID),
                        FOREIGN KEY (User_ID) REFERENCES USERS(ID) ON DELETE CASCADE,
                        FOREIGN KEY (Friend_ID) REFERENCES USERS(ID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS FRIEND_REQS;

CREATE TABLE FRIEND_REQS(
                            ID int NOT NULL AUTO_INCREMENT,
                            Reciever_ID int NOT NULL,
                            Sender_ID int NOT NULL,
                            PRIMARY KEY(ID),
                            CONSTRAINT un unique (Reciever_ID, Sender_ID),
                            FOREIGN KEY (Sender_ID) REFERENCES USERS(ID) ON DELETE CASCADE,
                            FOREIGN KEY (Reciever_ID) REFERENCES USERS(ID) ON DELETE CASCADE

);

