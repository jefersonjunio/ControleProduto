package devandroid.jeff.controledeprodutos.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {

    private static FirebaseAuth firebaseAuth;
    private static DatabaseReference databaseReference;


    public static String getIdFirebase(){
        return getAuth().getUid();
    }
    public static DatabaseReference getDatabaseReference(){
        if(databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        return databaseReference;
    }

    public static FirebaseAuth getAuth(){
        if(firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

    public static boolean getAutenticado(){
        return getAuth().getCurrentUser() != null;
    }

}
