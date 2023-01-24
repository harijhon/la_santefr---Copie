package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Maladie extends MetaData {

    public Maladie(int id, String nom) {
        super(id, nom);
    }

    public Maladie() {
    }

    @Override
    MetaData[] getDataFromDB(Connection con) throws Exception {
        String rqt = "select * from " + this.getClass().getSimpleName();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(rqt);
        Vector data = new Vector<Maladie>();
        while (rs.next()) {
            Maladie temp = new Maladie();
            temp.setId(rs.getInt("id_maladie"));
            temp.setNom(rs.getString("nom_maladie"));
            data.add(temp);
        }
        Maladie[] ret = new Maladie[data.size()];
        data.copyInto(ret);
        return ret;
    }

    public static Maladie[] getAllMaladie(Connection con) throws Exception {
        Maladie m = new Maladie();
        return (Maladie[]) m.getDataFromDB(con);
    }

    public static Medicament[] getMedicamentByMaladie(Connection con, int id) throws Exception {
        String rqt = "select * from medicament_data where id_medicament in (select distinct id_medicament from maladie_medicament where id_maladie="
                + id + ")";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(rqt);
        Vector data = new Vector<Medicament>();
        while (rs.next()) {
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
        Medicament[] ret = new Medicament[data.size()];
        data.copyInto(ret);
        return ret;
    }
}
