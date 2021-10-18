package com.example.tcc_estoque_20_09.BancoDeDados;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc_estoque_20_09.model.Produtos;
import com.example.tcc_estoque_20_09.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter <AdapterRecycler.myViewHolder> {

    private List<Produtos> testeLista;

    public AdapterRecycler(List<Produtos> teste){

        this.testeLista = teste;
    }


    @Override
    public int getItemCount() {
        return testeLista.size();
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new myViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        Produtos teste = testeLista.get(position);
        holder.nome.setText(teste.getNome());
        holder.quantidade.setText("" + teste.getQuantidade());
        holder.dataValidade.setText("" + teste.getDataValidade());

    }


    class myViewHolder extends RecyclerView.ViewHolder {


        TextView nome;
        TextView quantidade;
        TextView dataValidade;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nome);
            quantidade = itemView.findViewById(R.id.Quantidade);
            dataValidade = itemView.findViewById(R.id.dataValidade);

            SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter ( "NN/NN/NNNN" );
            MaskTextWatcher maskTextWatcher = new MaskTextWatcher ( dataValidade, simpleMaskFormatter );
            dataValidade.addTextChangedListener ( maskTextWatcher );




        }
    }
}
