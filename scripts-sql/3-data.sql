SET search_path TO projet;


-- Supprime toutes les données
DELETE FROM emprunt;
DELETE FROM amitie;
DELETE FROM ouvrage;
DELETE FROM personne;
DELETE FROM editeur;
DELETE FROM auteur;
DELETE FROM categorie;
DELETE FROM role;
DELETE FROM compte;


-- Insère les données

-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES 
( 1, 'geek', 'geek', 'geek@jfox.fr' ),
( 2, 'chef', 'chef', 'chef@jfox.fr' ),
( 3, 'job', 'job', 'job@jfox.fr' );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;


-- Role

INSERT INTO role (idcompte, role) VALUES 
( 1, 'ADMINISTRATEUR' ),
( 1, 'UTILISATEUR' ),
( 2, 'UTILISATEUR' ),
( 3, 'UTILISATEUR' );


-- Categorie

INSERT INTO categorie (idcategorie, libelle) VALUES 
(1, 'Romance'),
(2, 'Horreur');

ALTER TABLE categorie ALTER COLUMN idcategorie RESTART WITH 3;


-- Auteur

INSERT INTO auteur (idauteur, nom) VALUES 
(1, 'Mike Carlington'),
(2, 'Emilie Mayoum');

ALTER TABLE auteur ALTER COLUMN idauteur RESTART WITH 3;


-- Editeur

INSERT INTO editeur (idediteur, nom) VALUES 
(1, 'La MAIF'),
(2, 'Femmes Afrique');

ALTER TABLE editeur ALTER COLUMN idediteur RESTART WITH 3;

-- Personne

INSERT INTO personne (idpersonne, nom, prenom, datenaissance, idcompte) VALUES 
(1, 'Force', 'Geek', {d '2001-01-28' }, 1),
(2, 'Cha', 'Chef', {d '2010-08-31' }, 2),
(3, 'Doe', 'Job', {d '2008-03-11' }, 3);

ALTER TABLE personne ALTER COLUMN idpersonne RESTART WITH 4;

-- Ouvrage

INSERT INTO ouvrage (idouvrage, titre, idcategorie, idediteur, idauteur, idproprietaire) VALUES 
(1, 'Au dela des mers', 1, 1, 1, 1),
(2, 'Big bang', 2, 2, 2, 2);

ALTER TABLE ouvrage ALTER COLUMN idouvrage RESTART WITH 3;


-- Amitie

INSERT INTO amitie (idutilisateur, idami) VALUES
(1, 2),
(2, 1),
(1, 3),
(3, 1),
(2, 3);


-- Emprunt

INSERT INTO emprunt (idemprunteur, idouvrage) VALUES
(3, 1),
(3, 2);
 
