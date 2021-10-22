package com.example.tcc_estoque_20_09.Produto;

import java.io.Serializable;

public class Produtos implements Serializable {

    private int _id;
    private String nome;
    private String unidade;
    private String perecivel;
    private int quantidade;
    private int dataValidade;

    public Produtos(int _id, String nome,int dataValidade, int quantidade) {
        this._id = _id;
        this.nome = nome;
        this.dataValidade = dataValidade;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.perecivel = perecivel;
    }


    public int get_id() {
        return _id;
    }

    public String getNome() {
        return nome;
    }
    public String getUnidade(){
        return unidade;
    }
    public String getPerecivel(){
        return perecivel;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getDataValidade()
    {
        return dataValidade;
    }


   @Override
    public  String toString() {
       return "\n" + "Nome: " + nome + "\n Data de validade: " + dataValidade + "\n Quantidade : " + quantidade + "\n Unidade: " + unidade + "\n Perecivel: " + perecivel;
   }

}

