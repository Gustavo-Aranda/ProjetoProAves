package controller;

import model.Especie;
import java.util.ArrayList;

public class EspecieController {
    private ArrayList<Especie> listaEspecies;

    public EspecieController(){
        this.listaEspecies = new ArrayList<>();
        inicializarEspeciesPadrao();
    }

    private void inicializarEspeciesPadrao(){
        try{
            adicionarEspecie("Ring Neck");
            adicionarEspecie("Loris");
            adicionarEspecie("Cabeça-de-Ameixa");
            adicionarEspecie("Rosela");
        } catch (Exception e) {
            System.err.println("Erro ao inicializar as espécies padrão: " + e.getMessage());
        }
    }

    public void adicionarEspecie(String nome) throws Exception {
        if (nome == null || nome.trim().isEmpty()){
            throw new Exception("O nome não pode estar vazio.");
        }

        Especie especieNova = new Especie(nome);

        if (this.listaEspecies.contains(especieNova)){
            throw new Exception("A espécie '" + nome + "' já está cadastrada!");
        }

        this.listaEspecies.add(especieNova);
    }

    public void removerEspecie(Especie especie) throws Exception{
        if (especie == null){
            throw new Exception("Nenhuma espécie selecionada para remoção.");
        }

        // (TODO verificar se não contém aves dessa espécie)

        if (!this.listaEspecies.contains(especie)){
            throw new Exception("A espécie '"+ especie.getNome() + "' não está cadastrada");
        }

        this.listaEspecies.remove(especie);
    }

    public ArrayList<Especie> getListaEspecies() {
        return this.listaEspecies;
    }
}
