package com.creativematrix.noteapp.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

public class FireBaseToken extends FirebaseMessagingService {

    String token;

    public FireBaseToken() {

        setToken(FirebaseInstanceId.getInstance().getToken());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
