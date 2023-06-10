package devandroid.jeff.controledeprodutos.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import devandroid.jeff.controledeprodutos.R;
import devandroid.jeff.controledeprodutos.database.FirebaseHelper;
import devandroid.jeff.controledeprodutos.view.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_login_conta;
    private EditText et_login_email;
    private EditText et_login_senha;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciaComponentes();

        configCliques();
    }

    public void logar(View view) {
        String email = et_login_email.getText().toString().trim();
        String senha = et_login_senha.getText().toString();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {
                progressBar.setVisibility(View.VISIBLE);

                validaLogin(email, senha);
            } else {
                et_login_senha.requestFocus();
                et_login_senha.setError("Informe a senha.");
            }
        } else {
            et_login_email.requestFocus();
            et_login_email.setError("Informe o e-mail.");
        }
    }

    private void validaLogin(String email, String senha){
        FirebaseHelper.getAuth().signInWithEmailAndPassword(
               email, senha
        ).addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               finish();
               startActivity(new Intent(this, MainActivity.class));
           }else {
               String error = task.getException().getMessage();
               Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
               progressBar.setProgress(View.GONE);
           }
        });
    }

    private void configCliques() {
        tv_login_conta.setOnClickListener(view -> {
            startActivity(new Intent(this, CriarContaActivity.class));
        });
    }

    private void iniciaComponentes() {
        tv_login_conta = findViewById(R.id.tv_login_conta);
        et_login_email = findViewById(R.id.et_login_email);
        et_login_senha = findViewById(R.id.et_login_senha);
        progressBar = findViewById(R.id.progressBar);
    }
}