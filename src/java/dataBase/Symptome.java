package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import view.Produit;

public class Symptome extends MetaData {

    public Symptome(int id, String nom) {
        super(id, nom);
    }

    public Symptome() {
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    MetaData[] getDataFromDB(Connection con) throws Exception {
        String rqt = "select * from " + this.getClass().getSimpleName();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(rqt);
        Vector data = new Vector<Symptome>();
        while (rs.next()) {
            Symptome temp = new Symptome();
            temp.setId(rs.getInt(1));
            temp.setNom(rs.getString(2));
            data.add(temp);
        }
        Symptome[] ret = new Symptome[data.size()];
        data.copyInto(ret);
        return ret;
    }

    public static Symptome[] getAllSymptome(Connection con) throws Exception {
        Symptome m = new Symptome();
        return (Symptome[]) m.getDataFromDB(con);
    }

    public static Produit[] getMedicamentBySymptome(Connection con, int id) throws Exception {
        String rqt = "select * from mixted_data where id_medicament in (select distinct id_medicament from symptome_medicament where id_symptome= "
                + id + ")";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(rqt);
        Vector data = new Vector<Produit>();
        while (rs.next()) {
            Produit prod = new Produit();
            prod.setIdProduit(rs.getInt(0));
            prod.setIdMedicament(rs.getInt("id_medicament"));
            prod.setIdProduit(rs.getInt(1));
            prod.setLienPhoto(rs.getString("lien_img"));
            prod.setNomProduit(rs.getString(1));
            Medicament temp = new Medicament();
            temp.setId(rs.getInt("id_medicament"));
            temp.setNom(rs.getString("nom_medicament"));
            temp.setLienImg(rs.getString("lien_img"));
            temp.setNotice(rs.getString("notice"));
            temp.setPourAllaitement(rs.getBoolean("pour_allaitement"));
            temp.setPourEnceinte(rs.getBoolean("pour_enceinte"));
            temp.setDose(rs.getString("dose"));
            Administration admin = new Administration(rs.getInt("id_admin"), rs.getString("nom_admin"));
            temp.setForme(new Forme(rs.getInt("id_forme"), rs.getString("nom_forme"), admin));
            temp.setMarque(new Marque(rs.getInt("id_marque"), rs.getString("nom_marque")));
            temp.setTypeMed(new TypeMed(rs.getInt("id_type"), rs.getString("nom_type")));
            data.add(temp);
        }
        Produit[] ret = new Produit[data.size()];
        data.copyInto(ret);
        return ret;
    }

}
