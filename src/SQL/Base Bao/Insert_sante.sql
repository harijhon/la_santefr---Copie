--INsertion symptomes
INSERT INTO Symptome (nom_symptome) VALUES ('Etat Grippal');
INSERT INTO Symptome (nom_symptome) VALUES ('Grippe');
INSERT INTO Symptome (nom_symptome) VALUES ('Etat Grippal');

--Insertion maladie
INSERT INTO maladie (nom_maladie) VALUES ('Douleur et Fievre');
INSERT INTO maladie (nom_maladie) VALUES ('Maux de gorges et Toux');
INSERT INTO maladie (nom_maladie) VALUES ('Maux de bouche');
INSERT INTO maladie (nom_maladie) VALUES ('Medicament Bebe');
INSERT INTO maladie (nom_maladie) VALUES ('Dermatologie');
INSERT INTO maladie (nom_maladie) VALUES ('Sevrage Tabagique');
INSERT INTO maladie (nom_maladie) VALUES ('Muscles et Articulations');
INSERT INTO maladie (nom_maladie) VALUES ('Troubles Veineux');
INSERT INTO maladie (nom_maladie) VALUES ('Premier soins');
INSERT INTO maladie (nom_maladie) VALUES ('Nez et oreilles');
INSERT INTO maladie (nom_maladie) VALUES ('Digestion et nausees');
INSERT INTO maladie (nom_maladie) VALUES ('Forme et vitalite');
INSERT INTO maladie (nom_maladie) VALUES ('Yeux');




--Insertion type
INSERT INTO type_medicament (nom_type) VALUES ('Anti-inflammatoires');
INSERT INTO type_medicament (nom_type) VALUES ('Antibiotiques');
INSERT INTO type_medicament (nom_type) VALUES ('Antiviraux');
INSERT INTO type_medicament (nom_type) VALUES ('Dermatologie');
INSERT INTO type_medicament (nom_type) VALUES ('Anti-douleur');
INSERT INTO type_medicament (nom_type) VALUES ('Vasculoprotecteur');
INSERT INTO type_medicament (nom_type) VALUES ('Antitussif');
INSERT INTO type_medicament (nom_type) VALUES ('Antiseptique iodé');

--Insertion marque
INSERT INTO Marque (nom_marque) VALUES ('Doliprane');
INSERT INTO Marque (nom_marque) VALUES ('Ginkor');
INSERT INTO Marque (nom_marque) VALUES ('Toplexil');
INSERT INTO Marque (nom_marque) VALUES ('Betadine');

--Insertion administration
INSERT INTO administration (nom_admin) VALUES ('Voie orale');
INSERT INTO administration (nom_admin) VALUES ('Voie cutanée');


--Insertion Forme
INSERT INTO Forme (nom_forme,id_admin) VALUES ('Comprimés',1);
INSERT INTO Forme (nom_forme,id_admin) VALUES ('Gellule',1);
INSERT INTO Forme (nom_forme,id_admin) VALUES ('Ampoule buvable',1);
INSERT INTO Forme (nom_forme,id_admin) VALUES ('Gel',2);
INSERT INTO Forme (nom_forme,id_admin) VALUES ('Solution buvable',1);
INSERT INTO Forme (nom_forme,id_admin) VALUES ('Solution',2);

--Insertion medicament
INSERT INTO Medicament(
    id_marque,
    nom_medicament,
    dose,
    pour_enceinte,
    pour_allaitement,
    notice,
    lien_img,
    id_type,
    id_forme
    ) 
VALUES(
        1,
        'Doliprane Paracétamol',
        '1000 mg',
        true,
        true,
        'Sans ordonnance, ce médicament est indiqué en cas de douleur et/ou fièvre : maux de tête, états grippaux, douleurs dentaires, courbatures, règles douloureuses. Il peut également être prescrit en cas d''arthrose.',
        'img/doliprane.png',
        5,
        1
);

INSERT INTO Medicament(
    id_marque,
    nom_medicament,
    dose,
    pour_enceinte,
    pour_allaitement,
    notice,
    lien_img,
    id_type,
    id_forme
    ) VALUES(2,'Ginkor Fort','120 mg',false,false,
'Médicament préconisé dans les troubles de la circulation veineuse des jambes (jambes lourdes, douleurs...) et dans le traitement des symptômes liés à la crise hémorroïdaire.',
'img/ginkor_fort.png',6,2);

-- Insert into symptome_medicament
INSERT into symptome_medicament (id_medicament,id_symptome) VALUES(1,1);
INSERT into symptome_medicament (id_medicament,id_symptome) VALUES(1,2);
INSERT into symptome_medicament (id_medicament,id_symptome) VALUES(2,1);
INSERT into symptome_medicament (id_medicament,id_symptome) VALUES(2,2);

-- Insert into maladie_medicament
INSERT into maladie_medicament (id_medicament,id_maladie) VALUES(1,1);
INSERT into maladie_medicament (id_medicament,id_maladie) VALUES(1,2);
INSERT into maladie_medicament (id_medicament,id_maladie) VALUES(2,1);
INSERT into maladie_medicament (id_medicament,id_maladie) VALUES(2,2);

-- insertion produit :
insert into produit (id_medicament,quantite_medicament) VALUES (1,20);
insert into produit (id_medicament,quantite_medicament) VALUES (1,10);

-- insert mouvement : 
insert into mouvement_produit(id_produit,date_mouvement,date_peremption,is_entry,prix_unitaire,quantite_produit)
VALUES (1,'01-01-2023','05-10-2023',true,10000,100);
insert into mouvement_produit(id_produit,date_mouvement,date_peremption,is_entry,prix_unitaire,quantite_produit)
VALUES (1,'02-01-2023','05-10-2023',false,10000,50);

insert into mouvement_produit(id_produit,date_mouvement,date_peremption,is_entry,prix_unitaire,quantite_produit)
VALUES (2,'31-12-2022','29-11-2023',true,15000,75);
insert into mouvement_produit(id_produit,date_mouvement,date_peremption,is_entry,prix_unitaire,quantite_produit)
VALUES (2,'04-01-2023','23-11-2023',false,15000,50);


--insert stock :
insert into stock(id_produit,date_modif,quantite_en_stock) VALUES(1,'01-01-2023',100);
insert into stock(id_produit,date_modif,quantite_en_stock) VALUES(1,'02-01-2023',100);

insert into stock(id_produit,date_modif,quantite_en_stock) VALUES(2,'31-12-2022',75);
insert into stock(id_produit,date_modif,quantite_en_stock) VALUES(2,'04-11-2023',50);




-- insertion maladie_symptome
INSERT INTO symptome_maladie(id_maladie,id_symptome) VALUES();