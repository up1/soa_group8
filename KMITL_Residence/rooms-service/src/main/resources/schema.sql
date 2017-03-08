DROP TABLE IF EXISTS ROOMS;
DROP TABLE IF EXISTS ROOM_TYPE;
DROP TABLE IF EXISTS ROOMSCHECKER;

CREATE TABLE Room_Type
(
    type_id int PRIMARY KEY,
    type_name_th VARCHAR(45) CHARACTER SET utf8 COLLATE utf8_general_ci,
    type_name_en VARCHAR(80),
    type_details_th VARCHAR(2000) CHARACTER SET utf8 COLLATE utf8_general_ci,
    type_details_en VARCHAR(2000),
    type_price DOUBLE not null,
    type_adult_limit INT not null,
    type_children_limit INT not null
);

CREATE TABLE Rooms
(
    room_id int PRIMARY KEY,
    room_details VARCHAR(500) CHARACTER  SET utf8 COLLATE utf8_general_ci,
    room_type_id int NOT NULL,
    room_availability int
);

CREATE TABLE RoomsChecker
(
    reservation_id INT PRIMARY KEY,
    checkin DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    checkout DATETIME,
    room_id INT NOT NULL
);