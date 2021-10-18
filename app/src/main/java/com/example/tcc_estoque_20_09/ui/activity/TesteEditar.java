package com.example.tcc_estoque_20_09.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tcc_estoque_20_09.BancoDeDados.BancoDados;
import com.example.tcc_estoque_20_09.model.Produtos;
import com.example.tcc_estoque_20_09.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;

public class TesteEditar extends AppCompatActivity {

    private TextView txtNome;
    private Button btnEditarProduto;
    private Produtos produtoAtual;
    private Produtos produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_editar);

        txtNome = findViewById(R.id.textProduto);
        btnEditarProduto = findViewById(R.id.editarProduto);
        EditText mudarNome = findViewById(R.id.mudarNome);
        EditText mudarQuantidade = findViewById(R.id.mudarQuantidade);
        EditText mudarDataValidade = findViewById(R.id.mudarDataValidade);
        BancoDados bd = new BancoDados(getApplicationContext(),1);


        produtoAtual = (Produtos) getIntent().getSerializableExtra("produtoSelecionado");

        //Adicionando máscaras
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(mudarDataValidade, smf);
        mudarDataValidade.addTextChangedListener(mtw);

        btnEditarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bd.atualizar(txtNome.getText().toString(),mudarNome.getText().toString(),Integer.parseInt(mudarQuantidade.getText().toString()),
                        Integer.parseInt(mudarDataValidade.getText().toString().replace("/", "")))){
                    Toast.makeText(TesteEditar.this, "Atualizado", Toast.LENGTH_SHORT).show();
                    finish ();

                }else {
                    Toast.makeText(TesteEditar.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            }
        });




        if (produtoAtual != null){
            txtNome.setText(produtoAtual.getNome());
        }

        }



    }



