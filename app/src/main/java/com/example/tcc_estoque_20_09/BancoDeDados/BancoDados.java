package com.example.tcc_estoque_20_09.BancoDeDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.tcc_estoque_20_09.model.Produtos;

import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {
    SQLiteDatabase escreve;

    Produtos produtos;

    //Criação do banco, determinando as caracteristicas da table

    private final String tabelaProduto = "CREATE TABLE produtos (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(60), " +
            "datavalidade INTERGER," +
            "quantidade INTERGER);";

    public BancoDados(@Nullable Context context, int version) {
        super(context, "BancoProduto", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {sqLiteDatabase.execSQL(tabelaProduto);}
    //Método para cadastrar produto na table



    public boolean cadastrarProduto(String nome,int datavalidade, int quantidade){

        SQLiteDatabase conexao = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("datavalidade", datavalidade);
        values.put("quantidade", quantidade);


        if(conexao.insert("produtos",null, values) != -1){
            return true;
        }else {
            return false;
        }
    }


    //Método para listar todos os produtos na table
    public List<Produtos> buscarProdutos(){
        SQLiteDatabase conexao = getWritableDatabase();
        Cursor result = conexao.query(
                "produtos",
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Produtos> lista = new ArrayList<>();

        if (result.moveToFirst()){
            do {
                Long _id = result.getLong(0);
                String nome = result.getString(1);
                int dataValidade = result.getInt(2);
                int quantidade = result.getInt(3);

                Produtos p = new Produtos(_id, nome,dataValidade, quantidade);

                lista.add(p);
            }while (result.moveToNext());
        }
        return lista;
    }

    //Método para remover um produto da table
    public boolean remover (Produtos produtos){
        SQLiteDatabase conexao = getWritableDatabase();
       /* try {
            String[] args = {produtos.get_id().toString()};
            conexao.delete(tabelaProduto,"id=?",args);
            Log.i("INFO","Produto removido com sucesso");
        }catch (Exception e ){
            Log.e("INFO","Erro ao remover produto " + e.getMessage());
            return false;
        }
        return true;*/


        try {

            if(conexao.delete("produtos","nome =?",
                    new String[]{produtos.getNome()}) !=0){
                Log.i("INFO", "Produto salvo com sucesso");

            }
        }catch (Exception e){
            Log.e("INFO","Erro ao remover");
            return false;
        }
        return true;
    }



    //Método para atualizar um produto na table
    public boolean atualizar(String pegarNome,String nome,int quantidade,int datavalidade){
        SQLiteDatabase conexao = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("nome",nome);
        values.put("quantidade",quantidade);
        values.put("datavalidade",datavalidade);
        //values.put("quantidade", quantidade);
        if (conexao.update(
                "produtos",
                values,
                "nome=?",
                new String[]{pegarNome}
        )!= 0){
            return true;
        }else return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
