package com.gyngro.vanbora;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gyngro.vanbora.Group;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexandre Lara on 22/07/2016.
 */

public class FirebaseUtil {
    static DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    public static void saveCurrentUser(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("provider", "password");
        map.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        map.put("displayName", FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        ref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(map);
    }

    public static void saveNewGroup(Group group){
        Map<String, Object> map = new HashMap<String, Object>();

        map.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), true);
        group.setMembers(map);

        ref.child("groups").push().setValue(group);
    }
}
