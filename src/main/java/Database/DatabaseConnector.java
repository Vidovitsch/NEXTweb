/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author David
 */
public class DatabaseConnector {
    
    private static Connection con;
    private static ResultSet myRs;
    private static String connectionstring = "jdbc:mysql://studmysql01.fhict.local/dbi329413";
    private static String user = "dbi329413";
    private static String pass = "Fontys123";

    private static DatabaseConnector instance = null;
    
    public DatabaseConnector() {
        try {
            con = DriverManager.getConnection(connectionstring, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return con;
    }
}
