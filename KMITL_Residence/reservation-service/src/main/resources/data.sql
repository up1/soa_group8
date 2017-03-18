-- INSERT INTO reservation values(
--   null,
--   '2017-01-10',
--   '2017-01-12',
--   1,
--   1,
--   1,
--   1,
--   1,
--   "Mr.",
--   "Adisorn Sripakpaisit",
--   "example@example.com",
--   "0123456789",
--   "Thailand",
--   "Thai",
--   "1234567890",
--   "2020-02-29",
--   "1234"
-- );
INSERT INTO reservation (reservation_date, reservation_checkout, reservation_adults, reservation_children, reservation_status,
room_type, customer_title_name, customer_full_name, customer_email, customer_tel, customer_country, customer_nation,
credit_card_id, credit_card_expired_date, credit_card_cvv)
VALUES ('2017-01-01', '2017-01-03', 2, 0, 1, 1, 'Mr.', 'Adisorn Sripakpaisit', 'example@example.com', '0891234567', 'Thailand',
'Thai', '1234567890', '2020-01-01', '123');

INSERT INTO reservation_status (status_id, status_description) values
  (1, "waiting"),
  (2, "completed"),
  (3, "cancel");