DROP TABLE IF EXISTS ROOMS;

CREATE TABLE ROOMS
(
    room_id int(11) NOT NULL AUTO_INCREMENT,
    room_detail varchar(100) NOT NULL,
    room_status int(1) NOT NULL,
    room_type_id int(1) NOT NULL,
    PRIMARY KEY (room_id)
);