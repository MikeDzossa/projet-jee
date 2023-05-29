SET search_path TO projet;


-- Supprime toutes les données
DELETE FROM emprunter;
DELETE FROM posseder;
DELETE FROM etreami;
DELETE FROM ouvrage;
DELETE FROM editeur;
DELETE FROM auteur;
DELETE FROM categorie;
DELETE FROM role;
DELETE FROM compte;


-- Insère les données

-- Compte

INSERT INTO compte (idcompte, pseudo, nom, prenom, motdepasse, email ) VALUES 
( 1, 'geek', 'Force', 'Geek', 'geek', 'geek@jfox.fr' ),
( 2, 'chef', 'Cha', 'Chef', 'chef', 'chef@jfox.fr' ),
( 3, 'job', 'Doe', 'Job','job', 'job@jfox.fr' );

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


-- Ouvrage

INSERT INTO ouvrage (idouvrage, titre, idcategorie, idediteur, idauteur) VALUES 
(1, 'Au dela des mers', 1, 1, 1),
(2, 'Big bang', 2, 2, 2);

ALTER TABLE ouvrage ALTER COLUMN idouvrage RESTART WITH 3;


-- EtreAmi

INSERT INTO etreAmi (iddemandeur, idrecepteur, statut) VALUES
(1, 2, 'P'),
(2, 1, 'P'),
(1, 3, 'V'),
(3, 1, 'V');


-- Posseder

INSERT INTO posseder (idcompte, idouvrage) VALUES
(2, 2),
(1, 2),
(1, 1);


-- Emprunter

INSERT INTO emprunter (idemprunteur, idouvrage, idproprietaire) VALUES
(3, 1, 1);
 
