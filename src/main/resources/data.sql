DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  user_name VARCHAR(250) DEFAULT NULL,
  is_active BIT DEFAULT 1
);

INSERT INTO users (id,first_name, last_name, user_name,is_active) VALUES
  (1000,'Aliko', 'Dangote', 'bono',0),
  (20000,'Bill', 'Gates', 'Bbill',0),
  (300000,'Guy', 'Peleg', 'guypeleg',1),
  (40000,'Folrunsho', 'Alakija', 'agent',1),
  (500000,'Yael', 'Steinberger', 'yaelsteinberger',1);