package model;
import java.util.Objects;

public class Especie {
    private String nome;


    public Especie(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da espécie não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da espécie não pode ser vazio.");
        }
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Especie especie = (Especie) o;
        return Objects.equals(nome.toLowerCase(), especie.nome.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }
}
