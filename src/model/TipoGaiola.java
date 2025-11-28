package model;

public enum TipoGaiola {
    VIVEIRO("Viveiro"),
    CRIADEIRA("Criadeira"),
    SUSPENSA("Suspensa"),
    NORMAL("Normal");

    private final String nome;

    TipoGaiola(String nome){
        this.nome = nome;
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
