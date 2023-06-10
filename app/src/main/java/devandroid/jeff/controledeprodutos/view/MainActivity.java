package devandroid.jeff.controledeprodutos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

import devandroid.jeff.controledeprodutos.autenticacao.LoginActivity;
import devandroid.jeff.controledeprodutos.database.FirebaseHelper;
import devandroid.jeff.controledeprodutos.database.ProdutoDAO;
import devandroid.jeff.controledeprodutos.model.Produto;
import devandroid.jeff.controledeprodutos.R;
import devandroid.jeff.controledeprodutos.adapter.AdapterProduto;

public class MainActivity extends AppCompatActivity implements AdapterProduto.OnClick {

    private AdapterProduto adapterProduto;
    private SwipeableRecyclerView rvProdutos;
    private ImageButton imgBtn_add;
    private ImageButton imgBtn_ver_mais;
    private TextView tv_info;
    private ProdutoDAO produtoDAO;
    private List<Produto> produtoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        produtoDAO = new ProdutoDAO(this);
        produtoList = produtoDAO.getListProdutos();

        iniciaComponentes();
        ouvinteCliques();
    }


    @Override
    protected void onStart() {
        super.onStart();
        configRecyclerView();
    }

    private void iniciaComponentes(){
        rvProdutos = findViewById(R.id.rv_listaProdutos);
        imgBtn_add = findViewById(R.id.imgBtn_add);
        imgBtn_ver_mais = findViewById(R.id.imgBtn_ver_mais);
        tv_info = findViewById(R.id.tv_info);
    }
    private void ouvinteCliques() {
        imgBtn_add.setOnClickListener(view -> {
            Toast.makeText(this, "Clicou em add", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, FormProdutoActivity.class));
        });
        
        imgBtn_ver_mais.setOnClickListener(view -> {

            PopupMenu popupMenu = new PopupMenu(this, imgBtn_ver_mais);
            popupMenu.getMenuInflater().inflate(R.menu.menu_toolbar, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if(menuItem.getItemId() == R.id.menu_sobre) {
                    Toast.makeText(this, "Sobre", Toast.LENGTH_SHORT).show();
                }else if (menuItem.getItemId() == R.id.menu_sair) {
                    FirebaseHelper.getAuth().signOut();
                    startActivity(new Intent(this, LoginActivity.class));
                }

                return true;
            });

            popupMenu.show();
        });
    }

    private void configRecyclerView(){
        produtoList.clear();
        produtoList = produtoDAO.getListProdutos();

        verificaQtdLista();

        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setHasFixedSize(true);
        adapterProduto = new AdapterProduto(produtoList, this);
        rvProdutos.setAdapter(adapterProduto);

        rvProdutos.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) {

                Produto produto = produtoList.get(position);
                produtoDAO.deleteProduto(produto);
                produtoList.remove(produto);
                //regarrega o adapter sem o item removido
                adapterProduto.notifyItemRemoved(position);

                verificaQtdLista();

            }
        });
    }

    private void verificaQtdLista() {
        if(produtoList.size() == 0){
            tv_info.setVisibility(View.VISIBLE);
        }else {
            tv_info.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClickListener(Produto produto) {
        Intent intent = new Intent(this, FormProdutoActivity.class);
        intent.putExtra("produto", produto);
        startActivity(intent);
    }

}