package br.com.mercado.model;

public class Anime {
    private int idAnime;
    private String nome;

    public Anime(int idAnime, String nome) {
        this.idAnime = idAnime;
        this.nome = nome;
    }
    public  Anime(){
    }

    public int getIdAnime() {
        return idAnime;
    }

    public String getNome() {
        return nome;
    }

    public void setIdAnime(int idAnime) {
        this.idAnime = idAnime;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

