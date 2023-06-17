package devandroid.jeff.controledeprodutos.model;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

import devandroid.jeff.controledeprodutos.database.FirebaseHelper;

public class Produto implements Serializable {

    private String nome;
    private int estoque;
    private double valor;
    private String id;

    public Produto() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference();
        this.setId(reference.push().getKey());
    }
    public void salvarProduto() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference()
                .child("produtos")
                .child(FirebaseHelper.getIdFirebase())
                .child(this.id);

        reference.setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
