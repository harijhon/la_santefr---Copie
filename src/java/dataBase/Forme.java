package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Forme extends MetaData {
    private Administration admin;

    // getters + setters
    public Administration getAdmin() {
        return admin;
    }

    void setAdmin(Administration admin) {
        this.admin = admin;
    }

    // Constructor
    public Forme(int id, String nom, Administration admin) {
        super(id, nom);
        this.admin = admin;
    }

    public Forme() {
        super();
    }

    // inherited method redefinition

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + "  " + this.getAdmin().toString();
    }

    @Override
    MetaData[] getDataFromDB(Connection con) throws Exception {
        String rqt = "select * from forme join administration on forme.id_admin=administration.id_admin";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(rqt);
        Vector data = new Vector<Forme>();
        while (rs.next()) {
            Forme temp = new Forme();
            temp.setId(rs.getInt("id_forme"));
            temp.setNom(rs.getString("nom_forme"));
            Administration admin = new Administration(rs.getInt("id_admin"), rs.getString("nom_admin"));
            temp.setAdmin(admin);
            data.add(temp);
        }
        Forme[] ret = new Forme[data.size()];
        data.copyInto(ret);
        return ret;
    }

    public static Forme[] getAllForme(Connection con) throws Exception {
        Forme f = new Forme();
        return (Forme[]) f.getDataFromDB(con);
    }

    public static Medicament[] getMedicamentByForme(Medicament[] data, int id) {
        Medicament[] ret = null;
        Vector v = new Vector<>();
        for (Medicament each : data) {
            if (each.getForme().getId() == id) {
                v.add(each);
            }
        }
        ret = new Medicament[v.size()];
        v.copyInto(ret);
        return ret;
    }
}
