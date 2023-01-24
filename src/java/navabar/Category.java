package navabar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Category {
    String title;
    String[] outPutTxt;
    int[] outPutId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getOutPutTxt() {
        return outPutTxt;
    }

    public void setOutPutTxt(String[] outPutTxt) {
        this.outPutTxt = outPutTxt;
    }

    public int[] getOutPutId() {
        return outPutId;
    }

    public void setOutPutId(int[] outPutId) {
        this.outPutId = outPutId;
    }

    public static String[][] getAllMaladiInNavBar(Connection con) throws Exception {
        String requete = "SELECT DISTINCT id_maladie,nom_maladie FROM Navbar_view ORDER BY id_maladie";
        ResultSet rs = con.createStatement().executeQuery(requete);
        Vector v = new Vector<>();
        String[][] ret = null;
        while (rs.next()) {
            String[] temp = { rs.getString(1), rs.getString(2) };
            v.add(temp);
        }
        ret = new String[v.size()][];
        v.copyInto(ret);
        return ret;
    }

    public static Category geNavbarDatasByIdMaladie(Connection con, String idMaladie, String title) throws Exception {
        String rqt = "SELECT * FROM Navbar_view WHERE id_maladie=" + idMaladie;
        ResultSet rs = con.createStatement().executeQuery(rqt);
        Category ret = new Category();
        ret.setTitle(title);
        Vector outPutTxt = new Vector<>();
        Vector outPutID = new Vector<>();
        while (rs.next()) {
            outPutTxt.add(rs.getString(1));
            outPutID.add(rs.getString(3));
        }
        String[] outPutTxtStrings = new String[outPutTxt.size()];
        int[] outPutIDInt = new int[outPutID.size()];
        outPutTxt.copyInto(outPutTxtStrings);
        String[] temp = new String[outPutID.size()];
        outPutID.copyInto(temp);
        int idOutPutID = 0;
        for (String each : temp) {
            outPutIDInt[idOutPutID] = Integer.parseInt(each);
        }
        ret.setOutPutId(outPutIDInt);
        ret.setOutPutTxt(outPutTxtStrings);
        return ret;
    }

    public static Category[] getAllDatas(Connection con) throws Exception {
        String[][] dataStrings = Category.getAllMaladiInNavBar(con);
        Category[] ret = new Category[dataStrings.length];
        int idRet = 0;
        for (String[] each : dataStrings) {
            ret[idRet] = Category.geNavbarDatasByIdMaladie(con, each[0], each[1]);
            idRet++;
        }
        return ret;
    }

}
