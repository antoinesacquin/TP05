/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stag
 */
public class Singleton {

    /**
     * URL de connection
     */
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "JEETP5";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
    /**
     * Nom du user
     */
    private static final String USER = "ldnr";
    /**
     * Mot de pass e du user
     */
    private static final String PASSWORD = "ldnr";
    /**
     * Objet Connection
     */
    private static Connection connection = null;

    /**
     * Méthode qui va nous retourner notre instance et la créer si elle n'existe
     * pas...
     *
     * @return la connexion vers la base de donnée ou null
     */
    public static Connection getInstance() {
       try{ Class.forName("com.mysql.jdbc.Driver");
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException ex) {
                Logger.getLogger(Singleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}catch(Exception e){
            
        }
        System.out.println("connection = " + connection);
        return connection;
    }
}
