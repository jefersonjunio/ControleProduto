package devandroid.jeff.controledeprodutos.database;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseHelper {

    private static FirebaseAuth firebaseAuth;

    public static FirebaseAuth getAuth(){
        if(firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

}
