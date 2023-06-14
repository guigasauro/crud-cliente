package br.com.mercado.model;

public class Vendedor {
    private int id;
    private String nome;

    public Vendedor() {
        // Construtor vazio
    }

    public Vendedor(String nome) {
        this.nome = nome;
    }

    public Vendedor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}
