DROP TABLE IF EXISTS mediatek.documents;
DROP TABLE IF EXISTS mediatek.utilisateurs;
DROP DATABASE IF EXISTS mediatek;

CREATE DATABASE mediatek CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE mediatek.utilisateurs (
    nom VARCHAR(32),
    mdp VARCHAR(256) NOT NULL,
    bibliothecaire BOOLEAN NOT NULL,
    age INT,
    abonnement_actif BOOLEAN,
    PRIMARY KEY(nom)
);

CREATE TABLE mediatek.documents (
    id INT AUTO_INCREMENT,
    emprunteur VARCHAR(32),
    type INT NOT NULL,
    titre VARCHAR(64),
    auteur VARCHAR(64),
    adulte BOOLEAN,
    PRIMARY KEY(id),
    FOREIGN KEY(emprunteur) REFERENCES mediatek.utilisateurs(nom) ON DELETE SET NULL
);

COMMIT;