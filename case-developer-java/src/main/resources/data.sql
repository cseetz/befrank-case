INSERT INTO deelnemer(id, voornaam, tussenvoegsel, achternaam, straat, huisnummer, postcode, woonplaats, email, geboortedatum, rekening_nummer) VALUES(1, 'Casper', null, 'Seetz', 'Spaklerweg', 4, '1096BA', 'Amsterdam', 'casper@voorbeeld.nl', '1962-12-27', 'rekening-1');

INSERT INTO werkgever(id, naam) VALUES(1, 'BeFrank');

INSERT INTO werknemer(id, deelnemer_id, werkgever_id) VALUES(1, 1, 1);

INSERT INTO dienstverband(id, fulltime_salaris, parttime_percentage, franchise, premie_percentage, werknemer_id, werkgever_id) VALUES(1, 60000, 80, 15599, 5, 1, 1);
