/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import com.firebase.client.Firebase;


/**
 *
 * @author David
 */
public class DBWorkshopModifier {
    
    private static Firebase firebase;

    public DBWorkshopModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (Firebase) connector.getConnectionObject();
    }
}
