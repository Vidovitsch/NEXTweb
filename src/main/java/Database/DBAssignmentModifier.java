/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.firebase.client.Firebase;

/**
 *
 * @author Youri van der Ceelen
 */
public class DBAssignmentModifier implements IModAssignment {

    private static Firebase firebase;
    private Object lock;
    private boolean done = false;

    public DBAssignmentModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (Firebase) connector.getConnectionObject();
    }

    @Override
    public void insertSubmission(String uid, String link, String name) {
        Firebase assignmentRef = firebase.child("Assignment").child(name).child("Submissions").child(uid);

        assignmentRef.setValue(link);

    }

}
