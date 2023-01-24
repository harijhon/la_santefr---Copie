package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Marque extends MetaData {
    public Marque(int id, String nom) {
        super(id, nom);
    }

    public Marque() {
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
        Vector data = new Vector<Marque>();
        while (rs.next()) {
            Marque temp = new Marque();
            temp.setId(rs.getInt(1));
            temp.setNom(rs.getString(2));
            data.add(temp);
        }
        Marque[] ret = new Marque[data.size()];
        data.copyInto(ret);
        return ret;
    }

    public static Marque[] getAllMarque(Connection con) throws Exception {
        Marque m = new Marque();
        return (Marque[]) m.getDataFromDB(con);
    }

    public static Medicament[] getMedicamentByMarque(Medicament[] data, int id) {
        Medicament[] ret = null;
        Vector v = new Vector<>();
        for (Medicament each : data) {
            if (each.getMarque().getId() == id) {
                v.add(each);
            }
        }
        ret = new Medicament[v.size()];
        v.copyInto(ret);
        return ret;
    }

}
