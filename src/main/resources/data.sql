INSERT INTO activities (id, name)
VALUES (1, 'Petting'),
       (2, 'Walking'),
       (3, 'Feeding'),
       (4, 'Medicating'),
       (5, 'Shaving');

INSERT INTO pet_types (id, name)
VALUES (1, 'CAT'),
       (2, 'DOG'),
       (3, 'LIZARD'),
       (4, 'BIRD'),
       (5, 'FISH'),
       (6, 'SNAKE'),
       (7, 'OTHER');

INSERT INTO pet_type_activities (pet_type_id, activity_id)
VALUES (1, 3),
       (1, 4),
       (2, 1);