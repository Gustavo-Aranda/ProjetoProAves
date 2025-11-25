package model;

import model.Especie;
import model.Gaiola;

public class Ave {

    private String anilha;
    private Especie especie;
    private String cor;
    private Gaiola gaiola;
    private Status status;
    private Sexo sexo;

    public Ave(String anilha, Especie especie, Sexo sexo, String cor, Gaiola gaiola, Status status) {
        this.anilha = anilha.trim();
        this.especie = especie;
        this.sexo = sexo;
        this.cor = cor.trim();
        this.gaiola = gaiola;
        this.status = status;
    }

    public String getAnilha() {
        return anilha;
    }

    public Especie getEspecie() {
        return especie;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getCor() {
        return cor;
    }

    public Gaiola getGaiola() {
        return gaiola;
    }

    public Status getStatus() {
        return status;
    }

    public void setAnilha(String anilha) {
        this.anilha = anilha;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo.");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return this.anilha + " (" + this.especie.getNome() + ")";
    }
}

