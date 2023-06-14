package br.com.mercado.model;

public class Cliente {
    private int idCliente;
    private String nome;
    private int telefone;
    private int idAnime;
    private int idCidade;
    private int idTimeTorcedor;
    private boolean possuiDesconto;

    public Cliente() {
        // Construtor vazio
    }

    public Cliente(String nome, int telefone, int idAnime, int idCidade, int idTimeTorcedor) {
        this.nome = nome;
        this.telefone = telefone;
        this.idAnime = idAnime;
        this.idCidade = idCidade;
        this.idTimeTorcedor = idTimeTorcedor;

        if (idAnime == 2 || idCidade == 1 || idTimeTorcedor == 2) {
            this.possuiDesconto = true;
        } else {
            this.possuiDesconto = false;
        }
    }

    public Cliente(String nome, int telefone, int idAnime, int idCidade, int idTimeTorcedor, boolean possuiDesconto){
        this.nome = nome;
        this.telefone = telefone;
        this.idAnime = idAnime;
        this.idCidade = idCidade;
        this.idTimeTorcedor = idTimeTorcedor;
        this.possuiDesconto = possuiDesconto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getIdAnime() {
        return idAnime;
    }

    public void setIdAnime(int idAnime) {
        this.idAnime = idAnime;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public int getIdTimeTorcedor() {
        return idTimeTorcedor;
    }

    public void setIdTimeTorcedor(int idTimeTorcedor) {
        this.idTimeTorcedor = idTimeTorcedor;
    }

    public boolean isPossuiDesconto() {
        return possuiDesconto;
    }

    public void setPossuiDesconto(boolean possuiDesconto) {
        this.possuiDesconto = possuiDesconto;
    }
}

