package devandroid.jeff.controledeprodutos.autenticacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import devandroid.jeff.controledeprodutos.R;
import devandroid.jeff.controledeprodutos.database.FirebaseHelper;
import devandroid.jeff.controledeprodutos.model.Usuario;
import devandroid.jeff.controledeprodutos.view.MainActivity;

public class CriarContaActivity extends AppCompatActivity {

    private EditText et_criar_conta_nome;
    private EditText et_criar_conta_email;
    private EditText et_criar_conta_senha;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        iniciaComponentes();
        configCliques();

    }

    private void configCliques(){
        findViewById(R.id.imgBtn_voltar).setOnClickListener(view -> finish());
    }

    private void iniciaComponentes() {
        et_criar_conta_nome = findViewById(R.id.et_criar_conta_nome);
        et_criar_conta_email = findViewById(R.id.et_criar_conta_email);
        et_criar_conta_senha = findViewById(R.id.et_criar_conta_senha);
        progressBar = findViewById(R.id.progressBar);

        TextView tv_titulo = findViewById(R.id.tv_titulo);
        tv_titulo.setText("Criar conta");
    }

    public void validaDados(View view){
        String nome = et_criar_conta_nome.getText().toString();
        String email = et_criar_conta_email.getText().toString();
        String senha = et_criar_conta_senha.getText().toString();

        if(!nome.isEmpty()){
            if(!email.isEmpty()){
                if(!senha.isEmpty()){

                    progressBar.setVisibility(View.VISIBLE);

                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    salvarCadastro(usuario);
                }else{
                    et_criar_conta_senha.requestFocus();
                    et_criar_conta_senha.setError("Informe sua senha.");
                }
            }else{
                et_criar_conta_email.requestFocus();
                et_criar_conta_email.setError("Informe seu e-mail.");
            }
        }else{
            et_criar_conta_nome.requestFocus();
            et_criar_conta_nome.setError("Informe seu nome.");
        }
    }

    private void salvarCadastro(Usuario usuario){
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                String id = task.getResult().getUser().getUid();
                usuario.setId(id);

                finish();

                startActivity(new Intent(this, MainActivity.class));

            }
        });
    }

}