package devandroid.jeff.controledeprodutos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    private List<Produto> produtoList;
    private OnClick onClick;

    public AdapterProduto(List<Produto> produtoList, OnClick onClick) {
        this.produtoList = produtoList;
        this.onClick =  onClick;
    }

    /*
        Passa o layout que iremos usar ex: item_produto
        */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produto produto = produtoList.get(position);

        holder.tv_produto.setText(produto.getNome());
        holder.tv_estoque.setText("Estoque: " + produto.getEstoque());
        holder.tv_valor.setText("Valor: " + produto.getValor());

        holder.itemView.setOnClickListener(vkiew -> onClick.onClickListener(produto));
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    public interface OnClick{
        void onClickListener(Produto produto);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_produto, tv_estoque, tv_valor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_produto = itemView.findViewById(R.id.tv_produto);
            tv_estoque = itemView.findViewById(R.id.tv_estoque);
            tv_valor = itemView.findViewById(R.id.tv_valor);
        }
    }

}
