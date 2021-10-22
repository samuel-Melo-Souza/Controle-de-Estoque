package com.example.tcc_estoque_20_09.model;

import java.io.Serializable;

public class Produtos implements Serializable {

    public Long get_id() {
        return _id;
    }

    private Long _id;
    private String nome;
    private int quantidade;
    private int dataValidade;
    private String unidade;

    public void set_id(Long _id) {
        this._id = _id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setDataValidade(int dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setUnidade(String unidade) { this.unidade = unidade; }


    public Produtos(Long _id, String nome,int dataValidade, int quantidade) {
        this._id = _id;
        this.nome = nome;
        this.dataValidade = dataValidade;
        this.quantidade = quantidade;
    }



    public String getNome() {

        return nome;
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
       return "\n" + "Nome: " + nome + "\n Data de validade: " + dataValidade + "\n Quantidade: " + quantidade + "\n Unidade: " + "\n";
   }

}

