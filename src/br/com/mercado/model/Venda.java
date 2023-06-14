package br.com.mercado.model;

import java.sql.Date;

public class Venda {
    private int id;
    private int idCliente;
    private int idVendedor;
    private int idFormaPagamento;
    private Date dataVenda;
    private double valorFinal;
    private double porcDesconto;
    private boolean statusPago; 


    public Venda() {
        // Construtor vazio
    }

    public Venda(int id, int idCliente, int idVendedor, int idFormaPagamento, Date dataVenda, double valorFinal, double porcDesconto, boolean statusPago) {
        this.id = id;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.idFormaPagamento = idFormaPagamento;
        this.dataVenda = dataVenda;
        this.valorFinal = valorFinal;
        this.porcDesconto = porcDesconto;
        this.statusPago = statusPago;
    }

    public Venda(int idCliente, int idVendedor, int idFormaPagamento, Date dataVenda, double valorFinal, double porcDesconto, boolean statusPago) {
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.idFormaPagamento = idFormaPagamento;
        this.dataVenda = dataVenda;
        this.valorFinal = valorFinal;
        this.porcDesconto = porcDesconto;
        this.statusPago = statusPago;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public int getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public double getPorcDesconto() {
        return porcDesconto;
    }

    public boolean getStatusPago() {
        return statusPago;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public void setIdFormaPagamento(int idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public void setData(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void setPorcDesconto(double porcDesconto) {
        this.porcDesconto = porcDesconto;
    }

    public void setStatusPago(boolean statusPago) {
        this.statusPago = statusPago;
    }
}
