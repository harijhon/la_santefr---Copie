package dataBase;

import java.lang.reflect.Method;
import java.sql.Connection;

import connection.MyCon;
import dataBase.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection con = MyCon.getPostgreCon("la_sante", "postgres", "root");
            Medicament[] data = Medicament.getAllMedicament(con);
            // TypeMed[] test2 = TypeMed.getAllTypeMed(con);
            // for (TypeMed each : test2) {
            // System.out.println(each.toString());
            // }
            Medicament[] test = Maladie.getMedicamentByMaladie(con, 1);
            for (Medicament each : test) {
                System.out.println(each.getNom());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
