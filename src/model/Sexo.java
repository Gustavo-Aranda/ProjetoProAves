package model;

public enum Sexo {
    MACHO("Macho"),
    FEMEA("Fêmea"),
    INDEFINIDO("Indefinido");

    private final String displayValue;

    Sexo(String displayValue) {
        this.displayValue = displayValue;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
