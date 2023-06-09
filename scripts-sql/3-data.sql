SET search_path TO projet;


-- Supprime toutes les données
DELETE FROM amitie;
DELETE FROM emprunt;
DELETE FROM ouvrage;
DELETE FROM personne;
DELETE FROM editeur;
DELETE FROM auteur;
DELETE FROM categorie;
DELETE FROM role;
DELETE FROM compte;


-- Insère les données

-- Compte

-- Insertion des comptes
INSERT INTO compte (idcompte, pseudo, motdepasse, email)
VALUES
  (1, 'geek', 'geek', 'geek@3il.fr'),
  (2, 'lee', 'lee', 'lee@3il.fr'),
  (3, 'chang', 'chang', 'chang@3il.fr'),
  (4, 'garcia', 'garcia', 'garcia@3il.fr'),
  (5, 'Lopez', 'lopez', 'lopez@3il.fr'),
  (6, 'martin', 'martin', 'martin@3il.fr'),
  (7, 'nguyen', 'nguyen', 'nguyen@3il.fr'),
  (8, 'kumar', 'kumar', 'kumar@3il.fr'),
  (9, 'rossi', 'rossi', 'rossi@3il.fr'),
  (10, 'bianchi', 'bianchi', 'bianchi@3il.fr'),
  (11, 'smith', 'smith', 'smith@3il.fr'),
  (12, 'dupont', 'dupont', 'dupont@3il.fr');
  
 ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 13;


-- Role

INSERT INTO role (idcompte, role)
VALUES
  (1, 'ADMINISTRATEUR'),
  (1, 'UTILISATEUR'),
  (2, 'UTILISATEUR'),
  (3, 'UTILISATEUR'),
  (4, 'UTILISATEUR'),
  (5, 'UTILISATEUR'),
  (6, 'UTILISATEUR'),
  (7, 'UTILISATEUR'),
  (8, 'UTILISATEUR'),
  (9, 'UTILISATEUR'),
  (10, 'UTILISATEUR'),
  (11, 'UTILISATEUR'),
  (12, 'UTILISATEUR');


  
-- Categorie

INSERT INTO categorie (idcategorie, libelle)
VALUES
  (1, 'Biographie'),
  (2, 'Horreur'),
  (3, 'Action'),
  (4, 'Romance'),
  (5, 'Slice of Life'),
  (6, 'Science-Fiction'),
  (7, 'Fantasy'),
  (8, 'Drame');

ALTER TABLE categorie ALTER COLUMN idcategorie RESTART WITH 9;
  


-- Auteur

INSERT INTO auteur (idauteur, nom)
VALUES
  (1, 'James Bond'),
  (2, 'Sam Smith'),
  (3, 'P.K. Green'),
  (4, 'Emma Watson'),
  (5, 'Antoine Dupont'),
  (6, 'Sophie Martin'),
  (7, 'Daniel Garcia'),
  (8, 'Isabella Lopez'),
  (9, 'Liam Nguyen'),
  (10, 'Ava Kumar'),
  (11, 'Luca Rossi'),
  (12, 'Giulia Bianchi');
  
 ALTER TABLE auteur ALTER COLUMN idauteur RESTART WITH 13;

 
 
-- Editeur

INSERT INTO editeur (idediteur, nom)
VALUES
  (1, 'Mike & Co'),
  (2, 'William''s Edition'),
  (3, 'Femmes d''Afrique'),
  (4, 'Red Phoenix Publishing'),
  (5, 'Golden Books'),
  (6, 'Silver Publishing'),
  (7, 'Rainbow Press'),
  (8, 'Blue Moon Editions'),
  (9, 'Greenleaf Publishing'),
  (10, 'Purple Ink');

ALTER TABLE editeur ALTER COLUMN idediteur RESTART WITH 11;



-- Personne

INSERT INTO personne (idpersonne, idcompte, nom, prenom, datenaissance)
VALUES
  (1,1, 'Geek', 'Admin', {d '1992-06-10'}),
  (2, 2, 'Lee', 'Sophia', {d '1992-07-21'}),
  (3, 3, 'Chang', 'Daniel', {d '1991-11-09'}),
  (4, 4, 'Garcia', 'Isabella', {d '1993-04-28'}),
  (5, 5, 'Lopez', 'Mateo', {d '1994-09-12'}),
  (6, 6, 'Martin', 'Emma', {d '1997-02-05'}),
  (7, 7, 'Nguyen', 'Liam', {d '1999-10-18'}),
  (8, 8, 'Kumar', 'Ava', {d '1996-12-07'}),
  (9, 9, 'Rossi', 'Luca', {d '1998-05-24'}),
  (10, 10, 'Bianchi', 'Giulia', {d '1990-08-30'}),
  (11, 11, 'Smith', 'Oliver', {d '1993-11-02'}),
  (12, 12, 'Dupont', 'John', {d '1995-03-15'});

ALTER TABLE personne ALTER COLUMN idpersonne RESTART WITH 13;



-- Ouvrage

INSERT INTO ouvrage (idouvrage, idcategorie, idediteur, idauteur, idproprietaire, titre)
VALUES
  (1, 1, 1, 1, 1, 'Au-delà des mers'),
  (2, 2, 2, 2, 1, 'Bigbang Theory'),
  (3, 3, 3, 3, 2, 'Martial Peak'),
  (4, 4, 4, 4, 3, 'Romance éternelle'),
  (5, 5, 5, 5, 4, 'Le Secret de l''Amour'),
  (6, 6, 6, 6, 5, 'L''Énigme du passé'),
  (7, 7, 7, 7, 6, 'Le Chemin du Destin'),
  (8, 8, 8, 8, 7, 'La Puissance de l''Esprit'),
  (9, 2, 9, 9, 8, 'L''Aube des Ténèbres'),
  (10, 4, 10, 10, 9, 'Arcane Sniper'),
  (11, 6, 3, 1, 10, 'SSS Class Trash Hero'),
  (12, 2, 1, 1, 11, 'Demon Slayer'),
  (13, 7, 4, 10, 12, 'Haykuu'),
  (14, 1, 2, 3, 9, 'Tutorial Tower'),
  (15, 2, 5, 2, 2, 'Leveling up by Eating'),
  (16, 5, 7, 2, 3, 'Tales of Demons and Gods'),
  (17, 6, 8, 5, 3, 'Leveling with the Gods'),
  (18, 8, 3, 2, 2, 'Nano Machines'),
  (19, 7, 9, 5, 1, 'My wife is a Demon'),
  (20, 3, 10, 10, 7, 'Lecteur Omniscient'),
  (21, 3, 1, 7, 7, 'Overgeared'),
  (22, 2, 4, 7, 7, 'Les Chroniques de Narnia'),
  (23, 4, 3, 8, 8, 'Player'),
  (24, 5, 7, 8, 9, 'Sword King''s Survival'),
  (25, 7, 7, 9, 9, 'Joueur dès Aujourd''hui'),
  (26, 1, 8, 2, 7, 'Retour du Maitre'),
  (27, 2, 9, 2, 1, 'Solo Max Level Newbie'),
  (28, 6, 9, 4, 1, 'Solo Necromancy'),
  (29, 7, 9, 4, 2, 'Tales of Scribe'),
  (30, 5, 3, 5, 2, 'SSS Class Suicide hunter'),
  (31, 8, 3, 6, 4, 'Taming Master'),
  (32, 5, 4, 10,4, 'The Live'),
  (33, 4, 5, 4, 10, 'Dragon Ball'),
  (34, 4, 3, 8, 10, 'Le Monde Apres La Fin'),
  (35, 2, 10, 11, 8, 'Warior''s Return'),
  (36, 3, 10, 11, 8, 'Tomb Raider King'),
  (37, 3, 10, 12, 11, 'Blue Lock'),
  (38, 8, 2, 12, 11, 'Mercenary Enrollment'),
  (39, 3, 2, 11, 12, 'High School of the Dead'),
  (40, 2, 1, 11, 10, 'Another'),
  (41, 1, 3, 11, 6, 'God of HighSchool'),
  (42, 1, 5, 10, 8, 'Bug Player'),
  (43, 4, 5, 5, 7, 'Jujutsu Kaisen'),
  (44, 6, 6, 7, 7, 'Heavenly Demon Instructor'),
  (45, 7, 6, 3, 1, 'Solo Leveling');

ALTER TABLE ouvrage ALTER COLUMN idouvrage RESTART WITH 46;



-- Emprunt

INSERT INTO emprunt (idemprunt, idemprunteur, idouvrage, valider) VALUES
(1, 3, 1, true),
(2, 3, 2, true),
(3, 1, 3, true),
(4, 1, 5, true),
(5, 1, 6, false),
(6, 2, 1, false),
(7, 5, 2, true),
(8, 1, 8, false);

ALTER TABLE emprunt ALTER COLUMN idemprunt RESTART WITH 9;



-- Amitie

INSERT INTO amitie (idutilisateur, idami) VALUES
(1, 2),
(2, 1),
(1, 3),
(3, 1),
(2, 3);



 
