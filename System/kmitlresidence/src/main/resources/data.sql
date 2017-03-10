-- -----------------------------------------------------
-- Data for table `kmitlresidence`.`room_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `kmitlresidence`;
INSERT INTO `kmitlresidence`.`room_type` (`room_type_id`, `name`, `description`, `maximum_guest`, `price`) VALUES (1, 'ONE BEDROOM DELUXE APARTMENT 70SQM', 'Located in Tower 39, these One Bedroom Deluxe Apartments 70sqm provide plenty of space and comfort for couples and single travelers. The neutral colors, wooden flooring and contemporary furnishings allow guests to relax from the moment they arrive. The bedroom features one double bed or two single beds, and an LED TV with a wide range of international channels.', 2, 2900);
INSERT INTO `kmitlresidence`.`room_type` (`room_type_id`, `name`, `description`, `maximum_guest`, `price`) VALUES (2, 'ONE BEDROOM DELUXE APARTMENT 75SQM', 'Enjoy panoramic views of the stunning Sukhumvit skyline from this spacious 75 sqm apartment ideal for couples and single travelers. These luxury city apartments have larger living areas offering extra space for relaxation. The elegant interiors have neutral colors, wooden flooring and contemporary furnishings allowing guests to unwind from the moment they arrive. The bedroom features one double bed or two single beds, and an LED TV with a wide range of international channels.', 2, 3200);
INSERT INTO `kmitlresidence`.`room_type` (`room_type_id`, `name`, `description`, `maximum_guest`, `price`) VALUES (3, 'TWO BEDROOM DELUXE APARTMENT', 'nvite the family to stay in this spacious 170 sqm apartment located in Tower 39. There are two comfortable bedrooms each with their own television for entertainment. The master bedroom has a double bed and a private en-suite bathroom with bathtub and rain shower, while the guest room features twin beds and a second private bathroom.', 4, 4500);
INSERT INTO `kmitlresidence`.`room_type` (`room_type_id`, `name`, `description`, `maximum_guest`, `price`) VALUES (4, 'THREE BEDROOM DELUXE APARTMENT', 'The forest themed Tower 41 is home to our collection of luxury Three Bedroom Deluxe Apartments. Sleeping up to six adults, these large city apartments have everything families need for a home away from home experience. The stylish interiors have wooden floors throughout, contemporary furnishings and the latest technology facilities including LAN Internet access at each work station in every room.', 5, 5500);

COMMIT;


-- -----------------------------------------------------
-- Data for table `kmitlresidence`.`room`
-- -----------------------------------------------------
START TRANSACTION;
USE `kmitlresidence`;
INSERT INTO `kmitlresidence`.`room` (`room_id`, `floor`, `room_type_id`) VALUES (1, 1, 1);
INSERT INTO `kmitlresidence`.`room` (`room_id`, `floor`, `room_type_id`) VALUES (2, 1, 1);
INSERT INTO `kmitlresidence`.`room` (`room_id`, `floor`, `room_type_id`) VALUES (3, 1, 1);
INSERT INTO `kmitlresidence`.`room` (`room_id`, `floor`, `room_type_id`) VALUES (4, 2, 2);
INSERT INTO `kmitlresidence`.`room` (`room_id`, `floor`, `room_type_id`) VALUES (5, 2, 2);
INSERT INTO `kmitlresidence`.`room` (`room_id`, `floor`, `room_type_id`) VALUES (6, 2, 2);
INSERT INTO `kmitlresidence`.`room` (`room_id`, `floor`, `room_type_id`) VALUES (7, 3, 3);

COMMIT;