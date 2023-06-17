package devandroid.jeff.controledeprodutos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import devandroid.jeff.controledeprodutos.R;
import devandroid.jeff.controledeprodutos.autenticacao.LoginActivity;
import devandroid.jeff.controledeprodutos.database.FirebaseHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(this::verificaLogin, 3000);
    }

    private void verificaLogin() {
        if (FirebaseHelper.getAutenticado()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}