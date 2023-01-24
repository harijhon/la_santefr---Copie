package dataBase;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Vector;

public abstract class MetaData {
    private int id;
    private String nom;

    // Getters + setters
    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    void setNom(String nom) {
        this.nom = nom;
    }

    // Constructor
    public MetaData(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public MetaData() {
    }

    abstract MetaData[] getDataFromDB(Connection con) throws Exception;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " id :" + this.getId() + " nom:" + this.getNom();
    }

    public static Method findThisGetters(Class cl, String nomAttr) {
        Method ret = null;
        String nom = "get" + nomAttr;
        for (Method each : cl.getMethods()) {
            if (each.getName().toLowerCase().equals(nom.toLowerCase())) {
                ret = each;
            }
        }
        return ret;
    }

}
