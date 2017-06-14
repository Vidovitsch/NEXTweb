/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


/**
 *
 * @author Youri van der Ceelen
 */
public class DBAssignmentModifier implements IModAssignment {

    private static DatabaseReference firebase;
    private Object lock;
    private boolean done = false;

    public DBAssignmentModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (DatabaseReference) connector.getConnectionObject();
    }

    @Override
    public void insertSubmission(String uid, String link, String name, String email) {
        DatabaseReference nameRef = firebase.child("Assignment").child(name).child("Submissions").child(uid).child("Name");
        DatabaseReference linkRef = firebase.child("Assignment").child(name).child("Submissions").child(uid).child("Link");
        linkRef.setValue(link);
        nameRef.setValue(email);
    }
}
