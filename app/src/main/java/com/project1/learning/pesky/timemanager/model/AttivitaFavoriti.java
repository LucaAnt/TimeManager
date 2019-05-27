package com.project1.learning.pesky.timemanager.model;

public class AttivitaFavoriti implements Comparable {

    private String nomeAttivita;
    private boolean favorito;

    public AttivitaFavoriti(String nomeAttivita, boolean favorito) {
        this.nomeAttivita = nomeAttivita;
        this.favorito = favorito;
    }

    public String getNomeAttivita() {
        return nomeAttivita;
    }

    public void setNomeAttivita(String nomeAttivita) {
        this.nomeAttivita = nomeAttivita;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public  void toggleFavorito()
    {
        this.favorito = ! this.favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }


    // Serve per la gestione dei preferiti
    @Override
    public int compareTo(Object o) {
        //return this.getNomeAttivita().compareTo(((AttivitaFavoriti) o).getNomeAttivita());
        return Boolean.compare(this.favorito,((AttivitaFavoriti) o).isFavorito());
    }
}
