package br.com.mercado.model;

public class ItemVenda {
    private int id;
    private int idVenda;
    private int idProduto;
    private int quantidade;
    private double preco;

    public ItemVenda() {
        // Construtor vazio
    }

    public ItemVenda(int idVenda, int idProduto, int quantidade, double preco) {
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItemVenda(int id, int idVenda, int idProduto, int quantidade, double preco) {
        this.id = id;
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItemVenda(int idProduto, int quantidade, double preco){
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
