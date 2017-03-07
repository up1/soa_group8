DROP TABLE IF EXISTS ROOMS;
DROP TABLE IF EXISTS ROOMS_TYPE;
DROP TABLE IF EXISTS ROOMSCHECKER;

CREATE TABLE Room_Type
(
    type_id int PRIMARY KEY,
    type_name_th VARCHAR(45),
    type_name_en VARCHAR(80),
    type_details_th VARCHAR(2000),
    type_details_en VARCHAR(2000),
    type_price DOUBLE not null,
    type_adult_limit INT not null,
    type_children_limit INT not null
);

CREATE TABLE Rooms
(
    room_id int PRIMARY KEY,
    room_details VARCHAR(500),
    room_type_id int,
    FOREIGN KEY (room_type_id) REFERENCES Room_Type(type_id)
);

CREATE TABLE RoomsChecker
(
    reservation_id INT PRIMARY KEY,
    checkin DATE NOT NULL,
    checkout DATE,
    room_id INT,
    FOREIGN KEY (room_id) REFERENCES Rooms(room_id)
);