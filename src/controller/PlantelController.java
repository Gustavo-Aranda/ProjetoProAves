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

    public void atualizarAve(Ave aveOriginal, String novaAnilha, Especie novaEspecie, Sexo novoSexo, String novaCor, Gaiola novaGaiola, Status novoStatus) throws Exception{
        validarDados(novaAnilha, novaEspecie, novoSexo, novaCor, novaGaiola, novoStatus);

        for (Ave ave : this.plantel){
            if (!ave.equals(aveOriginal) && ave.getAnilha().equalsIgnoreCase(novaAnilha.trim())){
                throw new Exception("A anilha "+novaAnilha+" já pertence à outra ave.");
            }
        }

        aveOriginal.setAnilha(novaAnilha.trim());
        aveOriginal.setCor(novaCor);
        aveOriginal.setEspecie(novaEspecie);
        aveOriginal.setStatus(novoStatus);
        aveOriginal.setSexo(novoSexo);
        aveOriginal.setGaiola(novaGaiola);
    }

    private void validarDados(String anilha, Especie especie, Sexo sexo, String cor, Gaiola gaiola, Status status) throws Exception {
        if (anilha == null || anilha.trim().isEmpty()) throw new Exception("A anilha não pode ser vazia.");
        if (especie == null) throw new Exception("A espécie deve ser selecionada.");
        if (sexo == null) throw new Exception("O sexo deve ser selecionado.");
        if (cor == null || cor.trim().isEmpty()) throw new Exception("A cor não pode ser vazia.");
        if (gaiola == null) throw new Exception("A gaiola não pode ser vazia.");
        if (status == null) throw new Exception("O status deve ser selecionado.");
    }
}
