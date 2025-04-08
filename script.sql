CREATE DATABASE webdyncredit;
USE webdyncredit;

CREATE TABLE webdyncredit_utilisateur (
    idutilisateur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50),
    mdp VARCHAR(50)
);

CREATE TABLE webdyncredit_credit (
    idcredit INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100),
    montant FLOAT,
    datedebut DATE,
    datefin DATE
);

CREATE TABLE webdyncredit_depense (
    iddepense INT AUTO_INCREMENT PRIMARY KEY,
    idutilisateur INT REFERENCES webdyncredit_utilisateur(idutilisateur),
    idcredit INT REFERENCES webdyncredit_credit(idcredit),
    montantdepense FLOAT,
    datedepense DATE
);

INSERT INTO webdyncredit_utilisateur(nom, mdp) VALUES ('web', 'web');