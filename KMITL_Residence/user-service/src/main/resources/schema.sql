DROP TABLE IF EXISTS user_account;

CREATE TABLE user_account (
  username VARCHAR (20) PRIMARY KEY ,
  hash_password VARCHAR (64),
  status VARCHAR (45),
  role_id INT
);

DROP TABLE IF EXISTS staff;

CREATE TABLE staff(
  th_prename VARCHAR (10) CHARACTER  SET utf8 COLLATE utf8_general_ci,
  th_name VARCHAR (45) CHARACTER  SET utf8 COLLATE utf8_general_ci,
  en_prename VARCHAR (10),
  en_name VARCHAR (45),
  email VARCHAR (45),
  username VARCHAR (20)
);

DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role(
  role_id INT PRIMARY KEY,
  th_role VARCHAR (45) CHARACTER  SET utf8 COLLATE utf8_general_ci,
  en_role VARCHAR (45)
);
