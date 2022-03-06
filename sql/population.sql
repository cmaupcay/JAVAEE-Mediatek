/* UTILISATEURS */
/* Bibliothecaire */
INSERT INTO mediatek.utilisateurs (nom, mdp, bibliothecaire) VALUES ("biblio", "biblio", 1);
/* Abonnés */
INSERT INTO mediatek.utilisateurs (nom, mdp, bibliothecaire, age, abonnement_actif) VALUES ("clement.m", "clem", 0, 20, 1); /* Abonné régulier */
INSERT INTO mediatek.utilisateurs (nom, mdp, bibliothecaire, age, abonnement_actif) VALUES ("clement.p", "clem", 0, 19, 0); /* Abonnement inactif */
INSERT INTO mediatek.utilisateurs (nom, mdp, bibliothecaire, age, abonnement_actif) VALUES ("enfant", "enfant", 0, 7, 1);   /* Abonné mineur */

/* DOCUMENTS */
/* TOFIX Problème d'affichage des caractères spéciaux dans les titres. */
/* DVD */
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "Star Wars - Un nouvel espoir", "Georges Lucas", 0);
/* DVD pour adulte */
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "La Bibliothecaire", "Jacquie & Michel", 1);
/* Blu-Ray */
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (1, "Star Wars - La Menace fantome", "Georges Lucas", 0);

/* Population dans un ordre aléatoire. */
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "OSS 117 - Le Caire, nid d'espions", "Michel Hazanavicius", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "Star Wars - L'Empire contre-attaque", "Irvin Kershner", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (1, "Star Wars - Les Derniers Jedi", "Rian Johnson", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "L'Ecoliere", "Jacquie & Michel", 1);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (1, "Star Wars - L'Ascension de Skywalker", "J. J. Abrams", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "Star Wars - Le Retour du Jedi", "Richard Marquand", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "OSS 117 - Rio ne repond plus", "Michel Hazanavicius", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (1, "Star Wars - La Revanche des Sith", "Georges Lucas", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "Star Wars - La Menace fantome", "Georges Lucas", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (1, "Star Wars - L'Attaque des clones", "Georges Lucas", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "Star Wars - L'Attaque des clones", "Georges Lucas", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "OSS 117 - Alerte rouge en Afrique noire", "Nicolas Bedos", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "Star Wars - La Revanche des Sith", "Georges Lucas", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (1, "Star Wars - Le Reveil de la Force", "J. J. Abrams", 0);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (0, "La Secretaire", "Jacquie & Michel", 1);
INSERT INTO mediatek.documents (type, titre, auteur, adulte) VALUES (1, "OSS 117 - Alerte rouge en Afrique noire", "Nicolas Bedos", 0);

/* EMPRUNTS */
/* Clément M emprunte les 3 DVD OSS 117 : */
UPDATE mediatek.documents SET emprunteur = "clement.m" WHERE id = 4;
UPDATE mediatek.documents SET emprunteur = "clement.m" WHERE id = 10;
UPDATE mediatek.documents SET emprunteur = "clement.m" WHERE id = 15;
/* Clément P emprunte le Blu-Ray de Star Wars I : */
UPDATE mediatek.documents SET emprunteur = "clement.m" WHERE id = 3;

COMMIT;