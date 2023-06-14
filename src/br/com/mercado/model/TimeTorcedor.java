package br.com.mercado.model;

public class TimeTorcedor {
    private int idTimeTorcedor;
    private String nome;

    public TimeTorcedor(int idTimeTorcedor, String nome) {
        this.idTimeTorcedor = idTimeTorcedor;
        this.nome = nome;
    }
    public TimeTorcedor(){

    }

    public int getIdTimeTorcedor() {
        return idTimeTorcedor;
    }

    public void setIdTimeTorcedor(int idTimeTorcedor) {
        this.idTimeTorcedor = idTimeTorcedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

