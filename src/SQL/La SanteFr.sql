create table administration(
    id_admin serial primary key,
    nom_admin varchar(50)
);

create table marque(
    id_marque serial primary key,
    nom_marque varchar(50)
);

create table type_medicament(
    id_type serial primary key,
    nom_type varchar(50)
);

create table forme(
    id_forme serial primary key,
    nom_forme varchar(50),
    id_admin int,
    foreign key (id_admin) references administration(id_admin)
);

create table maladie(
    id_maladie serial primary key,
    nom_maladie varchar(50)
);

create table symptome(
    id_symptome serial primary key,
    nom_symptome varchar(50)
);

create table ageConsommateur(
    id_age serial primary key,
    age varchar(25)
);

create table medicament(
    id_medicament serial primary key,
    nom_medicament varchar(80),
    id_marque int,
    id_forme int,
    id_type int,
    id_age int,
    dose varchar(80),
    pour_enceinte boolean,
    pour_allaitement boolean,
    notice varchar(400),
    lien_img varchar(40),
    need_ordonnance boolean,
    foreign key (id_marque) references marque(id_marque),
    foreign key (id_forme) references forme(id_forme),
    foreign key (id_type) references type_medicament(id_type),
    foreign key(id_age) references ageConsommateur
);

create table maladie_medicament(
    id_maladie int,
    id_medicament int,
    foreign key (id_maladie) references maladie (id_maladie),
    foreign key (id_medicament) references medicament (id_medicament)
);

create table symptome_medicament(
    id_symptome int,
    id_medicament int,
    foreign key(id_symptome) references symptome(id_symptome),
    foreign key (id_medicament) references medicament(id_medicament)
);
create table symptome_maladie(
    id_maladie int,
    id_symptome int,
    foreign key (id_maladie) references maladie (id_maladie),
    foreign key (id_symptome) references symptome(id_symptome)
);

-- vue pour les donnees des medicament
create view medicament_data as (
    select nom_medicament,nom_forme,nom_type,nom_marque,
    medicament.id_medicament,forme.id_forme,type_medicament.id_type,marque.id_marque,
    nom_admin,administration.id_admin,
    notice,pour_allaitement,pour_enceinte,lien_img,dose
    from medicament
    join forme on medicament.id_forme = forme.id_forme
    join type_medicament on medicament.id_type=type_medicament.id_type
    join marque on medicament.id_marque=marque.id_marque
    join administration on forme.id_admin=administration.id_admin
);


-- Table et vue pour la gestion des medicaments
create table produit(
    id_produit serial primary key,
    id_medicament int,
    quantite_medicament int,
    prix_unitaire float,
    foreign key (id_medicament) references medicament(id_medicament)
);

create table mouvement_produit(
    id_produit int,
    date_mouvement date,
    date_peremption date,
    is_entry boolean,
    prix_unitaire int,
    quantite_produit int,
    foreign key (id_produit) references produit(id_produit)
);

create table stock(
    id_produit int,
    date_modif date,
    quantite_en_stock int,
    foreign key (id_produit) references produit(id_produit)
);

-- view pour la gestion du stock des produits
create view stock_par_produit as (
    select * from stock where date_modif in (
        select max(date_modif) from stock group by id_produit 
    )
);

-- Table et vue pour l'achat d'un produit
create table user_table(
    id_user serial primary key,
    nom_user varchar(80),
    genre int,
    adresse varchar(80),
    contact varchar(80),
    news_letter boolean,
    mdp varchar
);

create table panier(
    id_panier serial primary key,
    id_user int,
    is_valider boolean default false,
    foreign key (id_user) references user_table(id_user)  
);

create table achat(
    id_panier int,
    id_produit int,
    quantite_produit int,
    foreign key (id_panier) references panier(id_panier),
    foreign key (id_produit) references produit(id_produit)
);
create table livraison(
    id_panier int,
    id_user int,
    date_livraison date,
    is_valider boolean default false,
    foreign key (id_panier) references panier(id_panier),
    foreign key (id_user) references user_table(id_user)
);

-- detail produit


-- current price of one product
create view current_price as (
    select id_produit,prix_unitaire 
    from mouvement_produit 
    where date_mouvement in  (
        select  MAX(date_mouvement) 
        from mouvement_produit 
        where is_entry is true group by id_produit 
    )
);


-- NavBar View 
select nom_maladie,nom_symptome,symptome.id_symptome
from 


-- build navbar data
create view Navbar_view as (
    select nom_symptome,nom_maladie,symptome.id_symptome,symptome_maladie.id_maladie
    from symptome_maladie 
    join symptome on symptome_maladie.id_symptome = symptome.id_symptome
    join maladie on symptome_maladie.id_maladie = maladie.id_maladie
    order by symptome_maladie.id_maladie
);

select id_maladie,id_symptome from symptome_maladie group by id_maladie


create view mixted_data as (
    select nom_medicament,nom_forme,nom_type,nom_marque,
    produit.id_medicament,id_forme,id_type,id_marque,
    nom_admin,id_admin,
    notice,pour_allaitement,pour_enceinte,lien_img,dose,id_produit,prix_unitaire,quantite_medicament
    from produit 
    join medicament_data 
    on produit.id_medicament = medicament_data.id_medicament
);