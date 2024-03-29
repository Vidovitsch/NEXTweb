/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Utility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This method is used as a connection between the application and the firebase
 * specifically the Assignment branch
 * @author Youri van der Ceelen
 */
public class DBAssignmentModifier implements IModAssignment {

    private static DatabaseReference firebase;
    private String groupId;

    /**
     * The constructor of the DBAssignmentModifier class, The method takes no arguments.
     * It initiates the field firebase by creating a connection using the FBConnector class
     */
    public DBAssignmentModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (DatabaseReference) connector.getConnectionObject();
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
        DatabaseReference nameRef = firebase.child("Assignment").child(name).child("Submissions").child(uid).child("Name");
        DatabaseReference linkRef = firebase.child("Assignment").child(name).child("Submissions").child(uid).child("Link");
        String gId = getGroup(uid);

        linkRef.setValue(link);
        nameRef.setValue(email);
    }
    
    private String getGroup(String uid) {
        DatabaseReference ref = firebase.child("User/" + uid + "/GroupID");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                groupId = String.valueOf(ds.getValue());
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
                
        Utility.lockFXThread();
        return groupId;
    }
}
