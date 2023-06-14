package br.com.mercado.model;

public class ClienteView {


    private int idCliente;
    private String nome;
    private int telefone;
    private String nomeAnime;
    private String nomeCidade;
    private String nomeTimeTorcedor;

    public ClienteView(int idCliente, String nome, int telefone, String nomeAnime, String nomeCidade, String nomeTimeTorcedor) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.telefone = telefone;
        this.nomeAnime = nomeAnime;
        this.nomeCidade = nomeCidade;
        this.nomeTimeTorcedor = nomeTimeTorcedor;
    }
    public ClienteView(){

    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public String getNomeAnime() {
        return nomeAnime;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public String getNomeTimeTorcedor() {
        return nomeTimeTorcedor;
    }

}

