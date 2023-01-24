package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Medicament extends MetaData {
    public TypeMed typeMed;
    public Forme forme;
    public Marque marque;
    public String dose, notice, lienImg;
    public boolean pourAllaitement, pourEnceinte;

    // Getters + Setters
    public TypeMed getTypeMed() {
        return typeMed;
    }

    public void setTypeMed(TypeMed typeMed) {
        this.typeMed = typeMed;
    }

    public Forme getForme() {
        return forme;
    }

    public void setForme(Forme forme) {
        this.forme = forme;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getLienImg() {
        return lienImg;
    }

    public void setLienImg(String lienImg) {
        this.lienImg = lienImg;
    }

    public boolean isPourAllaitement() {
        return pourAllaitement;
    }

    public void setPourAllaitement(boolean pourAllaitement) {
        this.pourAllaitement = pourAllaitement;
    }

    public boolean isPourEnceinte() {
        return pourEnceinte;
    }

    public void setPourEnceinte(boolean pourEnceinte) {
        this.pourEnceinte = pourEnceinte;
    }

    // constructor
    public Medicament(int id, String nom, TypeMed typeMed, Forme forme, Marque marque, String dose, String notice,
            String lienImg, boolean pourAllaitement, boolean pourEnceinte) {
        super(id, nom);
        this.typeMed = typeMed;
        this.forme = forme;
        this.marque = marque;
        this.dose = dose;
        this.notice = notice;
        this.lienImg = lienImg;
        this.pourAllaitement = pourAllaitement;
        this.pourEnceinte = pourEnceinte;
    }

    public Medicament(TypeMed typeMed, Forme forme, Marque marque, String dose, String notice, String lienImg,
            boolean pourAllaitement, boolean pourEnceinte) {
        this.typeMed = typeMed;
        this.forme = forme;
        this.marque = marque;
        this.dose = dose;
        this.notice = notice;
        this.lienImg = lienImg;
        this.pourAllaitement = pourAllaitement;
        this.pourEnceinte = pourEnceinte;
    }

    public Medicament() {
    }

    @Override
    MetaData[] getDataFromDB(Connection con) throws Exception {
        String rqt = "select * from medicament_data";
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

    public static Medicament[] getAllMedicament(Connection con) throws Exception {
        Medicament test = new Medicament();
        MetaData[] datas = test.getDataFromDB(con);
        Medicament[] ret = (Medicament[]) datas;
        return ret;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + " Dose :" + this.getDose() + " Pour Allaitement:" + this.isPourAllaitement()
                + " Pour enceinte:" + this.isPourEnceinte() + " lien img:" + this.getLienImg() + " Notice:"
                + this.getNotice() + " " + this.getForme().toString() + " " + this.getTypeMed().toString() + " "
                + this.getMarque().toString();
    }

}
