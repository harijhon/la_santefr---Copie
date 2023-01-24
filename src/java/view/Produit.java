package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class Produit {
    int idProduit;
    int idMedicament;
    int quantite, prixUnitaire;
    String lienPhoto, nomProduit;

    // getters + setters
    public int getIdProduit() {
        return idProduit;
    }

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getLienPhoto() {
        return lienPhoto;
    }

    public void setLienPhoto(String lienPhoto) {
        this.lienPhoto = lienPhoto;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    // constructor
    public Produit(int idProduit, int idMedicament, int quantite, String lienPhoto, String nomProduit) {
        this.idProduit = idProduit;
        this.idMedicament = idMedicament;
        this.quantite = quantite;
        this.lienPhoto = lienPhoto;
        this.nomProduit = nomProduit;
    }

    public Produit() {
    }

    public static Produit[] getAllProduct(Connection con) throws Exception {
        String rqt = "select * from product_data";
        ResultSet rs = con.createStatement().executeQuery(rqt);
        Vector data = new Vector<>();
        while (rs.next()) {
            Produit temp = new Produit();
            temp.setIdMedicament(rs.getInt("id_medicament"));
            temp.setIdProduit(rs.getInt("id_produit"));
            temp.setNomProduit(rs.getString("nom_medicament"));
            temp.setLienPhoto(rs.getString("lien_img"));
            temp.setPrixUnitaire(rs.getInt("prix_unitaire"));
            temp.setQuantite(rs.getInt("quantite_medicament"));
            data.add(temp);
        }
        Produit[] ret = new Produit[data.size()];
        data.copyInto(ret);
        return ret;
    }

    public void ajouterPanier(Connection con, int idPanier) throws Exception {

    }

}
