package connection;

import java.sql.*;

public class MyCon {
    public static Connection getOracleCon() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "jean", "jean");
        return con;
    }

    /**
     * Fonction permettant de me connecter à Postgre.
     * 
     * @param databaseName Nom de la base de donnée.
     * @param usrName      Nom de l'utilisateur.
     * @param psswrd       Mots de passe de l'utilisateur.
     * @return Un variable de type Connection.
     * @throws Exception Les Exceptions venant de la connection.
     */
    public static Connection getPostgreCon(String databaseName, String usrName, String psswrd) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + databaseName,
                usrName,
                psswrd);
        return con;
    }
}