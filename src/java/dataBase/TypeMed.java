package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class TypeMed extends MetaData {

    public TypeMed(int id, String nom) {
        super(id, nom);
    }

    public TypeMed() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    MetaData[] getDataFromDB(Connection con) throws Exception {
        String rqt = "select * from type_medicament";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(rqt);
        Vector data = new Vector<TypeMed>();
        while (rs.next()) {
            TypeMed temp = new TypeMed();
            temp.setId(rs.getInt(1));
            temp.setNom(rs.getString(2));
            data.add(temp);
        }
        TypeMed[] ret = new TypeMed[data.size()];
        data.copyInto(ret);
        return ret;
    }

    public static TypeMed[] getAllTypeMed(Connection con) throws Exception {
        TypeMed m = new TypeMed();
        return (TypeMed[]) m.getDataFromDB(con);
    }

    public static Medicament[] getMedicamentByType(Medicament[] data, int id) {
        Medicament[] ret = null;
        Vector v = new Vector<>();
        for (Medicament each : data) {
            if (each.getTypeMed().getId() == id) {
                v.add(each);
            }
        }
        ret = new Medicament[v.size()];
        v.copyInto(ret);
        return ret;
    }
}
