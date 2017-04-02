INSERT INTO user_role (role_id, th_role, en_role) values
  (1, "แอดมิน", "Admin"), (2, "พนักงาน", "Staff");

INSERT INTO user_account(username, hash_password, status,  role_id) VALUES
("admin", "b9c950640e1b3740e98acb93e669c65766f6670dd1609ba91ff41052ba48c6f3", "Active", 1);

INSERT INTO staff (th_prename, th_name, en_prename, en_name, email, username) VALUES
("admin", "admin", "admin", "admin", "admin@admin.com", "admin");