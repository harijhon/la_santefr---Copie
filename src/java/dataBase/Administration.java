package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Administration extends MetaData {

    public Administration(int id, String nom) {
        super(id, nom);
    }

    public Administration() {
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
        Vector data = new Vector<Administration>();
        while (rs.next()) {
            Administration temp = new Administration();
            temp.setId(rs.getInt("id_admin"));
            temp.setNom(rs.getString("nom_admin"));
            data.add(temp);
        }
        Administration[] ret = new Administration[data.size()];
        data.copyInto(ret);
        return ret;
    }

    public static Administration[] getAllAdministrations(Connection con) throws Exception {
        Administration m = new Administration();
        return (Administration[]) m.getDataFromDB(con);
    }

    public static Medicament[] getMedicamentByAdmin(Medicament[] data, int id) {
        Medicament[] ret = null;
        Vector v = new Vector<>();
        for (Medicament each : data) {
            if (each.getForme().getAdmin().getId() == id) {
                v.add(each);
            }
        }
        ret = new Medicament[v.size()];
        v.copyInto(ret);
        return ret;
    }

}
