CREATE TABLE Genre (
	id_genre serial NOT NULL,
	nom_genre varchar(255) NOT NULL,
	CONSTRAINT Genre_pk PRIMARY KEY (id_genre)
);



CREATE TABLE User (
	id_user serial NOT NULL,
	id_genre integer NOT NULL,
	prenom varchar(255) NOT NULL,
	nom varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	mot_de_passe varchar(255) NOT NULL,
	email_parrain varchar(255) NOT NULL,
	date_naissance DATE(255) NOT NULL,
	newsletter BOOLEAN NOT NULL,
	CONSTRAINT User_pk PRIMARY KEY (id_user)

);



CREATE TABLE Panier (
	id_panier serial NOT NULL,
	id_user integer NOT NULL,
	CONSTRAINT Panier_pk PRIMARY KEY (id_panier)

);



CREATE TABLE Contenu (
	id_contenu serial NOT NULL,
	id_medicament integer NOT NULL,
	quantite integer NOT NULL,
	prix integer NOT NULL,
	id_panier integer NOT NULL,
	CONSTRAINT Contenu_pk PRIMARY KEY (id_contenu)

);



CREATE TABLE Symptome (
	id_symptome serial NOT NULL,
	nom_symptome varchar(255) NOT NULL,
	CONSTRAINT Symptome_pk PRIMARY KEY (id_symptome)

);

CREATE TABLE Type_Med (
	id_type serial NOT NULL,
	nom_type varchar(255) NOT NULL,
	CONSTRAINT Type_Med_pk PRIMARY KEY (id_type)

);

CREATE TABLE Marque (
	id_marque serial NOT NULL,
	nom_marque varchar(255) NOT NULL,
	CONSTRAINT Marque_pk PRIMARY KEY (id_marque)

);



CREATE TABLE Forme (
	id_forme serial NOT NULL,
	nom_forme varchar(255) NOT NULL,
	CONSTRAINT Forme_pk PRIMARY KEY (id_forme)

);



CREATE TABLE unite_dose (
	id_unite_dose serial NOT NULL,
	unite_dose varchar(255) NOT NULL,
	CONSTRAINT unite_dose_pk PRIMARY KEY (id_unite_dose)

);



CREATE TABLE Medicament (
	id_medicament serial NOT NULL,
	id_marque integer NOT NULL,
	nom_medicament varchar(255) NOT NULL,
	dose integer NOT NULL,
	id_unite_dose integer,
	id_administration integer NOT NULL,
	prix float NOT NULL,
	date_entree DATE NOT NULL,
	date_peremption DATE NOT NULL,
	enceinte BOOLEAN NOT NULL,
	allaitement BOOLEAN NOT NULL,
	notice TEXT NOT NULL,
	img varchar(255), 
	id_type integer,
	id_forme integer,
	CONSTRAINT Medicament_pk PRIMARY KEY (id_medicament)

);



CREATE TABLE Symptome_Medicament (
	id_symptome integer NOT NULL,
	id_medicament integer NOT NULL

);



CREATE TABLE administration (
	id_administration serial NOT NULL,
	nom_administration varchar(255) NOT NULL,
	CONSTRAINT administration_pk PRIMARY KEY (id_administration)

);




ALTER TABLE User ADD CONSTRAINT User_fk0 FOREIGN KEY (id_genre) REFERENCES Genre(id_genre);

ALTER TABLE Panier ADD CONSTRAINT Panier_fk0 FOREIGN KEY (id_user) REFERENCES User(id_user);

ALTER TABLE Contenu ADD CONSTRAINT Contenu_fk0 FOREIGN KEY (id_panier) REFERENCES Panier(id_panier);





ALTER TABLE Medicament ADD CONSTRAINT Medicament_fk0 FOREIGN KEY (id_marque) REFERENCES Marque(id_marque);
ALTER TABLE Medicament ADD CONSTRAINT Medicament_fk1 FOREIGN KEY (unite_dose) REFERENCES unite_dose(id_unite_dose);
ALTER TABLE Medicament ADD CONSTRAINT Medicament_fk2 FOREIGN KEY (id_administration) REFERENCES administration(id_administration);
ALTER TABLE Medicament ADD CONSTRAINT Medicament_fk3 FOREIGN KEY (id_type) REFERENCES Type_Med(id_type);


ALTER TABLE Symptome_Medicament ADD CONSTRAINT Symptome_Medicament_fk0 FOREIGN KEY (id_symptome) REFERENCES Symptome(id_symptome);
ALTER TABLE Symptome_Medicament ADD CONSTRAINT Symptome_Medicament_fk1 FOREIGN KEY (id_medicament) REFERENCES Medicament(id_medicament);













