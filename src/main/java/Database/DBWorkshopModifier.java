/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Models.Workshop;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author David
 */
public class DBWorkshopModifier {
    
    private static Connection con;
    private static ResultSet myRs;
    
    public DBWorkshopModifier() {
        con = DatabaseConnector.getConnection();
    }
    
    public ArrayList<Workshop> getWorkshops() {
        try {
            ArrayList<Workshop> workshops = new ArrayList();
            
            String query = "SELECT CONCAT_WS(';', Title, Description, Image, ScheduledOn, MaxStudents) AS Fields FROM workshop";
            Statement statement = con.createStatement();
            myRs = statement.executeQuery(query);
            
            String values[];
            while (myRs.next()){
                values = myRs.getString("Fields").split(";");
                String name = values[0];
                String description = values[1];
                String image = values[2];
                String date = values[3];
                int maxStudents = Integer.valueOf(values[4]);
                
                workshops.add(new Workshop(name, description, date, maxStudents, image));
            }
            
            return workshops;
        } catch (SQLException ex) {
            Logger.getLogger(DBWorkshopModifier.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void insertWorkshop(Workshop workshop) {
        try {
            String title = workshop.getName();
            String description = workshop.getDescription();
            String image = workshop.getImage();
            String date = workshop.getDate();
            int maxStudents = workshop.getMaxStudents();
            
            String query = "INSERT INTO workshop (Title, Description, Image, ScheduledOn, MaxStudents) VALUES ('" + title + "', '" +
                    description + "', '" + image + "', '" + date + "', " + maxStudents + ");";
            Statement statement = con.createStatement();          
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBWorkshopModifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
