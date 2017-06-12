/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.firebase.client.Firebase;

/**
 * This method is used as a connection between the application and the firebase
 * specifically the Assignment branch
 * @author Youri van der Ceelen
 */
public class DBAssignmentModifier implements IModAssignment {

    private static Firebase firebase;
    private Object lock;
    private boolean done = false;

    /**
     * The constructor of the DBAssignmentModifier class, The method takes no arguments.
     * It initiates the field firebase by creating a connection using the FBConnector class
     */
    public DBAssignmentModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (Firebase) connector.getConnectionObject();
    }

    /**
     * This method is used to to add a new Submission for an assignment to the Firebase database.
     * As parameters it takes the uid(who submitted), link(the link to their material),
     * name(assignemnt name) and email(email of the person who handed in the assignment)
     * @param uid not null
     * @param link not null
     * @param name not null
     * @param email not null
     */
    @Override
    public void insertSubmission(String uid, String link, String name, String email) {
        if(uid.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried to add a submision to firebase without a uid");
        }else if(link.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried to add a submision to firebase without a link");
        }else if(name.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried to add a submision to firebase without a name");
        }else if(email.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried to add a submision to firebase without a email");
        }
        Firebase nameRef = firebase.child("Assignment").child(name).child("Submissions").child(uid).child("Name");
        Firebase linkRef = firebase.child("Assignment").child(name).child("Submissions").child(uid).child("Link");
        linkRef.setValue(link);
        nameRef.setValue(email);

    }

}
