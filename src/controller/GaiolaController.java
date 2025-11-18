package controller;

import model.Gaiola;
import model.TipoGaiola;

import java.util.ArrayList;

public class GaiolaController {
    private ArrayList<Gaiola> listaGaiolas;

    public GaiolaController(){
        this.listaGaiolas = new ArrayList<>();
        inicializarGaiolas();
    }

    private void inicializarGaiolas(){
        try{
            adicionarGaiola("G-01", TipoGaiola.VIVEIRO);
            adicionarGaiola("C-01", TipoGaiola.CRIADEIRA);
            adicionarGaiola("S-01", TipoGaiola.SUSPENSA);
            adicionarGaiola("N-01", TipoGaiola.NORMAL);
        } catch (Exception e){
            System.err.println("Erro ao inicializar gaiolas: " +e.getMessage());
        }
    }

    public void adicionarGaiola(String codigo, TipoGaiola tipo) throws Exception{
        if (codigo == null || codigo.trim().isEmpty()){
            throw new Exception("O código da gaiola não pode ser vazio!");
        }
        if (tipo == null ){
            throw new Exception("O tipo da gaiola não pode ser vazio!");
        }

        Gaiola gaiolaNova = new Gaiola(codigo.trim(), tipo);
        if (this.listaGaiolas.contains(gaiolaNova)){
            throw new Exception("A gaiola '" + codigo + "' já existe!");
        }

        this.listaGaiolas.add(gaiolaNova);
    }

    public void removerGaiola(Gaiola gaiola) throws Exception {
        if (gaiola == null) {
            throw new Exception("Nenhuma gaiola selecionada para remoção.");
        }

        // (TODO verificar se a gaiola não contém aves)

        if (!this.listaGaiolas.contains(gaiola)) {
            throw new Exception("Esta gaiola não existe.");
        }

        this.listaGaiolas.remove(gaiola);
    }

    public String getGaiolasFormatado() {
        if (this.listaGaiolas.isEmpty()) {
            return "Nenhuma gaiola cadastrada.";
        }

        StringBuilder sb = new StringBuilder("--- GAIOLAS CADASTRADAS ---\n\n");
        for (Gaiola g : this.listaGaiolas) {
            sb.append(String.format(
                    "Número: %s\nTipo: %s\n--------------------------\n",
                    g.getCodigo(),
                    g.getTipo()
            ));
        }
        return sb.toString();
    }

    public ArrayList<Gaiola> getListaGaiolas() {
        return this.listaGaiolas;
    }
}
