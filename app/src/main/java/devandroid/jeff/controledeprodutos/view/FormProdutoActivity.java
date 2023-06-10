package devandroid.jeff.controledeprodutos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import devandroid.jeff.controledeprodutos.R;
import devandroid.jeff.controledeprodutos.database.ProdutoDAO;
import devandroid.jeff.controledeprodutos.model.Produto;

public class FormProdutoActivity extends AppCompatActivity {

    private EditText edit_produto;
    private EditText edit_quantidade;
    private EditText edit_valor;
    private Button btn_salvar;
    private ProdutoDAO produtoDAO;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produto);

        produtoDAO = new ProdutoDAO(this);

        edit_produto = this.findViewById(R.id.edit_produto);
        edit_quantidade = this.findViewById(R.id.edit_quantidade);
        edit_valor = this.findViewById(R.id.edit_valor);
        btn_salvar = this.findViewById(R.id.btn_salvar);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            produto = (Produto) bundle.getSerializable("produto");
            editProduto();
        }

        btn_salvar.setOnClickListener(view -> {
            salvarProduto();
        });


    }

    private void editProduto(){
        edit_produto.setText(produto.getNome());
        edit_quantidade.setText(String.valueOf(produto.getEstoque()));
        edit_valor.setText(String.valueOf(produto.getValor()));
    }

    public void salvarProduto() {
        String nomeProduto = edit_produto.getText().toString();
        String quantidadeProduto = edit_quantidade.getText().toString();
        String valor = edit_valor.getText().toString();

        //validando informacoes
        if(!nomeProduto.isEmpty()) {
            if(!quantidadeProduto.isEmpty()){
                int qtd = Integer.parseInt(quantidadeProduto);

                if(qtd >= 1){
                    if(!valor.isEmpty()) {
                        double valorProduto = Double.parseDouble(valor);
                        if(valorProduto > 0){

                            if(produto == null) produto = new Produto();
                            produto.setNome(nomeProduto);
                            produto.setEstoque(qtd);
                            produto.setValor(valorProduto);

                            if(produto.getId() != 0){
                                produtoDAO.atualizaProduto(produto);
                            }else {
                                produtoDAO.salvarProduto(produto);
                            }
                            finish();


                        } else {
                            edit_valor.requestFocus();
                            edit_valor.setError("Informe um valor maior que zero.");
                        }

                    }else {
                        edit_valor.requestFocus();
                        edit_valor.setError("Informe o valor do produto.");
                    }

                }else {
                    edit_quantidade.requestFocus();
                    edit_quantidade.setError("Informer um valor maior que 0");
                }

            }else {
                edit_quantidade.requestFocus();
                edit_quantidade.setError("Informe a quantidade.");
            }

        } else {
            edit_produto.requestFocus();
            edit_produto.setError("Informe o nome do produto.");
        }
    }
}