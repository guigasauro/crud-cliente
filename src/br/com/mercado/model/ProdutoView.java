package br.com.mercado.model;

public class ProdutoView {
    private int idProduto;
    private String nome;
    private double preco;
    private String nomeCategoria;
    private String nomeFabricante;
    private String enderecoFabricante;
    private int quantidade;

    public ProdutoView(int idProduto, String nome, double preco, String nomeCategoria, String nomeFabricante, String enderecoFabricante, int quantidade) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
        this.nomeCategoria = nomeCategoria;
        this.nomeFabricante = nomeFabricante;
        this.enderecoFabricante = enderecoFabricante;
        this.quantidade = quantidade;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public String getEnderecoFabricante() {
        return enderecoFabricante;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getNomeFabricante() {
        return nomeFabricante;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public void setNomeFabricante(String nomeFabricante) {
        this.nomeFabricante = nomeFabricante;
    }

    public void setEnderecoFabricante(String enderecoFabricante) {
        this.enderecoFabricante = enderecoFabricante;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
