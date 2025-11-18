package model;

public enum Status {
    ATIVA("Ativa"),
    VENDIDA("Vendida"),
    OBITO("Óbito");

    private final String displayValue;

    Status(String displayValue) {
        this.displayValue = displayValue;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
