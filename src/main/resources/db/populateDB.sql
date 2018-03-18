DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
  (100000, '2018-03-18 10:00:00', 'Breakfast', 500),
  (100000, '2018-03-18 14:00:00', 'Lunch', 1000),
  (100000, '2018-03-18 20:00:00', 'Dinner', 700),
  (100000, '2018-03-17 10:00:00', 'Breakfast', 300),
  (100000, '2018-03-17 14:00:00', 'Lunch', 800),
  (100000, '2018-03-17 20:00:00', 'Dinner', 900);
