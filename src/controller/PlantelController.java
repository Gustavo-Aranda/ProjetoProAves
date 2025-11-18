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
        if (especie == null) {
            throw new Exception("A espécie deve ser selecionada.");
        }
        if (sexo == null) {
            throw new Exception("O sexo deve ser selecionado.");
        }
        if (cor == null || cor.trim().isEmpty()) {
            throw new Exception("A cor não pode ser vazia.");
        }
        if (gaiola == null) {
            throw new Exception("A gaiola não pode ser vazia.");
        }
        if (status == null) {
            throw new Exception("O status deve ser selecionado.");
        }

        for (Ave ave : this.plantel){
            if (ave.getAnilha().equalsIgnoreCase(anilha.trim())){
                throw new Exception("Erro (RF003): A anilha '" + anilha + "' já está cadastrada.");
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
}
