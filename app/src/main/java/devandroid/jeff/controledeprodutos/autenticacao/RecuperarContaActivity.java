package devandroid.jeff.controledeprodutos.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import devandroid.jeff.controledeprodutos.R;
import devandroid.jeff.controledeprodutos.database.FirebaseHelper;

public class RecuperarContaActivity extends AppCompatActivity {

    private EditText et_recuperar_email;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_conta);

        inicializaComponentes();
    }

    private void inicializaComponentes() {
        et_recuperar_email = findViewById(R.id.et_recuperar_email);
        progressBar = findViewById(R.id.progressBar);
    }

    public void recuperarSenha(View view) {
        String email = et_recuperar_email.getText().toString().trim();

        if (!email.isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);
            enviaEmail(email);
        } else {
            et_recuperar_email.requestFocus();
            et_recuperar_email.setError("Informe o e-mail.");
        }
    }

    private void enviaEmail(String email) {
        FirebaseHelper.getAuth().sendPasswordResetEmail(email).addOnCompleteListener(task ->{
            if(task.isSuccessful()){
                Toast.makeText(this, "E-mail de recuperação enviado com sucesso!", Toast.LENGTH_LONG).show();
            }else{
                String error = task.getException().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);

        });
    }
}