package controller;
import model.*;
import java.util.ArrayList;

public class PlantelController {
    private ArrayList<Ave> plantel;

    public PlantelController(){
        this.plantel = new ArrayList<>();
    }

    public void cadastrarAve(String anilha, Especie especie, Sexo sexo, String cor, Gaiola gaiola, Status status) throws Exception{
        if (anilha == null || anilha.trim().isEmpty()) {
            throw new Exception("A anilha não pode ser vazia.");
        }
        if (cor == null || cor.trim().isEmpty()) {
            throw new Exception("A cor não pode ser vazia.");
        }

        for (Ave ave : this.plantel){
            if (ave.getAnilha().equalsIgnoreCase(anilha.trim())){
                throw new Exception("Erro: A anilha '" + anilha + "' já está cadastrada.");
            }
        }

        Ave ave = new Ave(anilha.trim(), especie, sexo, cor.trim(), gaiola, status);
        this.plantel.add(ave);
    }

    public void removerAve(Ave ave) throws Exception {
        if (ave == null) {
            throw new Exception("Nenhuma ave selecionada para remoção.");
        }
        if (!this.plantel.contains(ave)) {
            throw new Exception("Esta ave não existe no plantel.");
        }

        this.plantel.remove(ave);
    }

    public String getPlantelFormatado(){
        if (this.plantel.isEmpty()){
            return "Nenhuma ave encontrada no plantel.";
        }

        StringBuilder sb = new StringBuilder("--- PLANTEL DE AVES ---\n\n");
        for (Ave ave : this.plantel){
            sb.append(String.format(
                    "Anilha: %s\n" +
                            "Espécie: %s\n" +
                            "Sexo: %s\n" +
                            "Cor: %s\n" +
                            "Gaiola: %s (Tipo: %s)\n" +
                            "Status: %s\n" +
                            "-------------------------------\n",
                    ave.getAnilha(),
                    ave.getEspecie(),
                    ave.getSexo(),
                    ave.getCor(),
                    ave.getGaiola().getCodigo(),
                    ave.getGaiola().getTipo(),
                    ave.getStatus()
            ));
        }
        return sb.toString();
    }

    public ArrayList<Ave> getPlantel(){
        return new ArrayList<>(this.plantel);
    }

    public ArrayList<Ave> getAvesPorGaiola(Gaiola gaiola){
        ArrayList<Ave> avesNaGaiola = new ArrayList<>();
        if (gaiola == null) return avesNaGaiola;

        for (Ave ave : this.plantel){
            if (ave.getGaiola().equals(gaiola)){
                avesNaGaiola.add(ave);
            }
        }

        return avesNaGaiola;
    }

    public boolean verificarEspecieEmUso(Especie especie) {
        for (Ave ave : this.plantel) {
            if (ave.getEspecie().equals(especie)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarGaiolaEmUso(Gaiola gaiola) {
        for (Ave ave : this.plantel) {
            if (ave.getGaiola().equals(gaiola)) {
                return true;
            }
        }
        return false;
    }
}
