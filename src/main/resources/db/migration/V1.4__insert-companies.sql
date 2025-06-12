INSERT INTO "companies" ("name","description", "address_id")
VALUES
('EZENT','minim cillum officia nostrud elit irure occaecat', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street DESC LIMIT 1)),
('WAAB','amet magna adipisicing officia duis minim et', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street ASC LIMIT 1)),
('TASMANIA','nulla voluptate id consectetur eiusmod labore veniam', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street DESC LIMIT 1)),
('ZUVY','magna dolor sint ad dolore pariatur pariatur', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street ASC LIMIT 1)),
('SHOPABOUT','laborum est voluptate nisi sit duis excepteur', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street DESC LIMIT 1)),
('FUELTON','aliquip cillum mollit laboris aliquip ad voluptate', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street ASC LIMIT 1)),
('ACCUFARM','labore velit reprehenderit dolor labore sint enim', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street DESC LIMIT 1)),
('EXOBLUE','elit ex ea adipisicing irure tempor Lorem', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street ASC LIMIT 1)),
('HAIRPORT','laboris proident reprehenderit quis irure non quis', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street DESC LIMIT 1)),
('DRAGBOT','nisi duis proident id tempor aliquip veniam', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street ASC LIMIT 1)),
('BEDLAM','nulla veniam adipisicing eiusmod nisi sit consequat', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street DESC LIMIT 1)),
('BIOHAB','deserunt exercitation magna dolor consectetur ut nostrud', (SELECT id FROM addresses WHERE tag = 'COMPANY' ORDER BY street ASC LIMIT 1));