package com.example.tcc_estoque_20_09.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tcc_estoque_20_09.AdapterRecycler;
import com.example.tcc_estoque_20_09.BancoDeDados.BancoDados;
import com.example.tcc_estoque_20_09.MainActivity;
import com.example.tcc_estoque_20_09.R;
import com.example.tcc_estoque_20_09.databinding.FragmentDashboardBinding;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private Spinner spinner_Unidade;
    private Spinner spinner_Perecivel;
    private DashboardViewModel dashboardViewModel;
    //private FragmentDashboardBinding binding;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        //Criação do objeto da classe BancoDados
        BancoDados bd = new BancoDados(getActivity(),1);
        view = inflater.inflate(R.layout.fragment_dashboard,container,false);



        //Referencias dos componentes usados no layout
        EditText editTextnomeProduto = view.findViewById(R.id.editText_nomeProduto);
        EditText editTextdataValidade = view.findViewById(R.id.editText_dataValidade);
        EditText editText_Quantidade = view.findViewById(R.id.editText_Quantidade);
        spinner_Unidade = view.findViewById(R.id.spinnerUnidade);
        spinner_Perecivel = view.findViewById(R.id.spinner_Perecivel);
        Button button_cadastrarProduto = view.findViewById(R.id.button_cadastrarProduto);

        //Adicionando máscaras para o campo de data
        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter ( "NN/NN/NNNN" );
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher ( editTextdataValidade, simpleMaskFormatter );
        editTextdataValidade.addTextChangedListener ( maskTextWatcher );

        //Botão para cadastrar produtos no banco de dados
        button_cadastrarProduto.setOnClickListener( view -> {
            String produto = editTextnomeProduto.getText().toString().trim();
            String dataValidade = editTextdataValidade.getText().toString();
            String quantidade = editText_Quantidade.getText().toString();
            //Validações para cadastrar os produtos
            if(produto.equals("") || dataValidade.equals("") || quantidade.equals("")) {
                Toast.makeText(view.getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }else if(produto.length() > 30) {
               Toast.makeText(view.getContext(), "O campo produto não pode ter mais que 30 caracteres", Toast.LENGTH_SHORT).show();
               return;
           }
        if (bd.cadastrarProduto(produto,Integer.parseInt(dataValidade.replace("/", "")),
             Integer.parseInt(quantidade))){
           Toast.makeText(view.getContext(), "Produto Cadastrado", Toast.LENGTH_SHORT).show();
          }else {
             Toast.makeText(view.getContext(), "Erro", Toast.LENGTH_SHORT).show();
          }
       });

        return view;
    }

}