package com.example.tcc_estoque_20_09.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc_estoque_20_09.BancoDeDados.AdapterRecycler;
import com.example.tcc_estoque_20_09.BancoDeDados.BancoDados;
import com.example.tcc_estoque_20_09.MainActivity;
import com.example.tcc_estoque_20_09.events.RecyclerItemClickListener;
import com.example.tcc_estoque_20_09.model.Produtos;
import com.example.tcc_estoque_20_09.R;
import com.example.tcc_estoque_20_09.ui.activity.TesteEditar;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private View view;
    private String valorpesquisado;
    private RecyclerView recyclerView;
    private List<Produtos> rvLista = new ArrayList<>();
    private Produtos produtoSelecionado;

     //Variavel auxilar para dexar a lista visivel e invisivel
    int lis = 1;


    @Override
    public void onResume() {
        super.onResume ();
        //Criação do objeto da classe BancoDados
        BancoDados bd = new BancoDados(getActivity(),1);

        rvLista = bd.buscarProdutos();


        //Referencias dos componentes usados no layout
        recyclerView = view.findViewById(R.id.recyclerTeste);
        ListView ls = view.findViewById(R.id.listaProdutos);



        AdapterRecycler a1 = new AdapterRecycler(rvLista);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(a1);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Produtos produtoSelecionado =rvLista.get(position);


                                Intent intent = new Intent(getActivity(), TesteEditar.class);
                                intent.putExtra("produtoSelecionado", produtoSelecionado);

                                startActivity(intent);

                                //Toast.makeText(getContext(), "Teste", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongClickItem(View view, int position ) {

                                produtoSelecionado = rvLista.get(position);


                                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());

                                //Configura titulo e mensagem
                                dialog.setTitle("Confirmar exclusao");
                                dialog.setMessage("Deseja excluir o produto: " + produtoSelecionado.getNome() + " ?");

                                dialog.setPositiveButton("Sim", (dialogInterface, i) -> {


                                    if (bd.remover(produtoSelecionado)){
                                        rvLista.remove (position); //remove da lista
                                        a1.notifyItemRemoved (position); //remove do adapter
                                        a1.notifyDataSetChanged (); //atualiza o recyclerview

                                        Toast.makeText(getContext(), "Excluido", Toast.LENGTH_SHORT).show();

                                    }else {
                                        Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
                                    }
                                } );
                                dialog.setNegativeButton("Não", null);

                                dialog.create();
                                dialog.show();



                            }

                            @Override
                            public void onClick(View view) {

                            }
                        }
                )
        );
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        view = inflater.inflate(R.layout.fragment_home, container, false);



        ImageButton btnListar = view.findViewById(R.id.btnListar);



        //Botão para deixar a lista visivel e invisivel
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lis ==1){
                    recyclerView.setVisibility(View.VISIBLE);
                    lis++;

                }else if(lis>1){
                    recyclerView.setVisibility(View.GONE);
                    lis=1;
                }

            }
        });


        //Colocando no adapter o método de listar os produtos através do objeto bd.
        /*ArrayAdapter<Produtos> adapter = new ArrayAdapter<Produtos>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                bd.buscarProdutos()
        );
        //Colocando no componente ListView o adapter.
        ls.setAdapter(adapter);*/



        return view;
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;



    }
}