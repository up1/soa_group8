INSERT INTO reservation values(
  null,
  '2017-01-10',
  '2017-01-12',
  1,
  1,
  1,
  1,
  1,
  "Mr.",
  "Adisorn Sripakpaisit",
  "example@example.com",
  "0123456789",
  "Thailand",
  "Thai",
  "1234567890",
  "2020-02-29",
  "1234"
);

INSERT INTO reservation_status (status_id, status_description) values
  (1, "waiting"),
  (2, "completed"),
  (3, "cancel");

INSERT INTO payment_type (payment_type_id, payment_type_description) values
  (1, "credit card"),
  (2, "counter"),
  (3, "bank account");