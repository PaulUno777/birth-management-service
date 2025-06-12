INSERT INTO "city_halls" ("name","description","address_id")
VALUES
('MANGELICA','voluptate eiusmod voluptate do pariatur reprehenderit dolor', (SELECT id FROM addresses WHERE tag = 'CITY_HALL' ORDER BY street DESC LIMIT 1)),
('DATACATOR','culpa esse ea voluptate est dolore fugiat', (SELECT id FROM addresses WHERE tag = 'CITY_HALL' ORDER BY street ASC LIMIT 1)),
('SAVVY','laborum anim sunt nulla ad do amet', (SELECT id FROM addresses WHERE tag = 'CITY_HALL' ORDER BY street DESC LIMIT 1)),
('SLAMBDA','irure dolor mollit amet esse culpa non', (SELECT id FROM addresses WHERE tag = 'CITY_HALL' ORDER BY street ASC LIMIT 1)),
('SARASONIC','duis quis irure amet sit irure consequat', (SELECT id FROM addresses WHERE tag = 'CITY_HALL' ORDER BY street DESC LIMIT 1)),
('INDEXIA','incididunt minim magna anim elit culpa esse', (SELECT id FROM addresses WHERE tag = 'CITY_HALL' ORDER BY street ASC LIMIT 1)),
('ACLIMA','velit dolore cupidatat magna in non excepteur', (SELECT id FROM addresses WHERE tag = 'CITY_HALL' ORDER BY street DESC LIMIT 1)),
('CENTURIA','in dolor enim adipisicing esse aliqua deserunt', (SELECT id FROM addresses WHERE tag = 'CITY_HALL' ORDER BY street ASC LIMIT 1)),
('PHOLIO','irure laboris anim eu commodo et aute', (SELECT id FROM addresses WHERE tag = 'CITY_HALL' ORDER BY street DESC LIMIT 1)),
('IPLAX','exercitation mollit duis anim ullamco laboris consequat', (SELECT id FROM addresses WHERE tag = 'CITY_HALL' ORDER BY street ASC LIMIT 1));