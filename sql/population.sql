/* UTILISATEURS */
/* Bibliothecaire */
INSERT INTO utilisateurs (nom, mdp, bibliothecaire) VALUES ("admin", "admin", 1);
/* Abonnés */
INSERT INTO utilisateurs (nom, mdp, bibliothecaire, age, abonnement_actif) VALUES ("jean", "jean", 0, 21, 1);
INSERT INTO utilisateurs (nom, mdp, bibliothecaire, age, abonnement_actif) VALUES ("jeanne", "jeanne", 0, 24, 1);
INSERT INTO utilisateurs (nom, mdp, bibliothecaire, age, abonnement_actif) VALUES ("jeannette", "jeannette", 0, 4, 1);
INSERT INTO utilisateurs (nom, mdp, bibliothecaire, age, abonnement_actif) VALUES ("john", "john", 0, 20, 0);

/* DOCUMENTS */
/* DVD */
INSERT INTO documents (type, titre, auteur, adulte) VALUES (0, "Star Wars - Le retour du Jedi", "Georges Lucas", 0);
INSERT INTO documents (type, titre, auteur, adulte) VALUES (0, "OSS 117 - Le Caire, nid d'espions", "Michel Hazanivicius", 0);
INSERT INTO documents (type, titre, auteur, adulte) VALUES (0, "La Bibliothécaire", "Jacquie & Michel", 1);

COMMIT;