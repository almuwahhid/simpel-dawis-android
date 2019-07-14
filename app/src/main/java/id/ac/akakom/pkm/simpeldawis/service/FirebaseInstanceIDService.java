package id.ac.akakom.pkm.simpeldawis.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by rizmualana on 9/13/16.
 */
public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        Log.d("FirebaseInstance", FirebaseInstanceId.getInstance().getToken());
    }
}
