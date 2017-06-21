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
 *
 * @author Youri van der Ceelen
 */
public class DBAssignmentModifier implements IModAssignment {

    private static DatabaseReference firebase;
    private String groupId;

    public DBAssignmentModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (DatabaseReference) connector.getConnectionObject();
    }

    @Override
    public void insertSubmission(String uid, String link, String name, String email) {
        String gId = getGroup(uid);
        DatabaseReference nameRef = firebase.child("Assignment").child(name).child("Submissions").child(gId).child("Name");
        DatabaseReference linkRef = firebase.child("Assignment").child(name).child("Submissions").child(gId).child("Link");
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
