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
public class FBConnector implements IDatabase {

    private static FBConnector instance = null;
    private Firebase firebase = null;
    
    public static FBConnector getInstance() {
        if (instance == null) {
            instance = new FBConnector();
        }
        return instance;
    }
    
    @Override
    public void connect() {
        String firebase_url = "https://nextweek-b9a58.firebaseio.com/";
        firebase = new Firebase(firebase_url);
    }

    @Override
    public Object getConnectionObject() throws NullPointerException {
        if (firebase != null) {
            return firebase;
        } else {
            throw new NullPointerException("There is no connection with the database");
        }
    }

    @Override
    public boolean checkConnection() {
        //Is changeable
        return firebase != null;
    }
}