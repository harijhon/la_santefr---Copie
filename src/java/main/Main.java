package main;

import java.sql.Connection;

import connection.MyCon;
import dataBase.*;
import navabar.Category;
import view.Produit;

public class Main {
    public static void main(String[] args) {
        try {
            Connection con = MyCon.getPostgreCon("la_sante", "postgres", "root");
            for (Category each : Category.getAllDatas(con)) {
                System.out.println(each.getTitle());
                for (String item : each.getOutPutTxt()) {
                    System.out.println(item);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
