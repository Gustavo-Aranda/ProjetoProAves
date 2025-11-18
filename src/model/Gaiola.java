package model;

import java.util.Objects;

public class Gaiola {
    private String codigo;
    private TipoGaiola tipo;

    public Gaiola(String codigo, TipoGaiola tipo){
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoGaiola getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return codigo + " | " + tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gaiola gaiola = (Gaiola) o;
        return Objects.equals(codigo.toLowerCase(), gaiola.codigo.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo.toLowerCase());
    }

}
