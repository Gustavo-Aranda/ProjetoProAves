package view;

import controller.EspecieController;
import controller.PlantelController;
import model.Especie;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GerenciadorEspecie extends JDialog {
    private final EspecieController especieController;
    private final PlantelController plantelController;

    private JList<Especie> listaEspeciesVisual;
    private DefaultListModel<Especie> listModel;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JButton btnFechar;

    public GerenciadorEspecie(Frame parent, EspecieController especieCtrl, PlantelController plantelCtrl) {
        super(parent, "Gerenciar Espécies", true);
        this.especieController = especieCtrl;
        this.plantelController = plantelCtrl;

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        add(criarPainelLista(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);

        atualizarListaVisual();
    }

    private JScrollPane criarPainelLista() {
        listModel = new DefaultListModel<>();
        listaEspeciesVisual = new JList<>(listModel);

        listaEspeciesVisual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaEspeciesVisual.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(listaEspeciesVisual);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        return scrollPane;
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnAdicionar = new JButton("Adicionar Nova");
        btnAdicionar.addActionListener(e -> acaoAdicionar());

        btnRemover = new JButton("Remover Selecionada");
        btnRemover.addActionListener(e -> acaoRemover());

        btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        painel.add(btnAdicionar);
        painel.add(btnRemover);
        painel.add(btnFechar);
        return painel;
    }

    private void atualizarListaVisual() {
        listModel.clear();
        ArrayList<Especie> especies = especieController.getListaEspecies();
        for (Especie esp : especies) {
            listModel.addElement(esp);
        }
    }

    private void acaoAdicionar() {
        try {
            String nome = JOptionPane.showInputDialog(this,
                    "Digite o nome da nova espécie:",
                    "Adicionar Espécie",
                    JOptionPane.PLAIN_MESSAGE);

            if (nome == null || nome.trim().isEmpty()) {
                return;
            }

            especieController.adicionarEspecie(nome);
            JOptionPane.showMessageDialog(this,
                    "Espécie (" + nome + ") cadastrada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            atualizarListaVisual();

            for (int i = 0; i < listModel.getSize(); i++) {
                if(listModel.get(i).getNome().equalsIgnoreCase(nome.trim())) {
                    listaEspeciesVisual.setSelectedIndex(i);
                    break;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Erro ao Adicionar",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void acaoRemover() {
        try {
            Especie especieSelecionada = listaEspeciesVisual.getSelectedValue();

            if (especieSelecionada == null) {
                throw new Exception("Por favor, selecione uma espécie da lista para remover.");
            }

            if (plantelController.verificarEspecieEmUso(especieSelecionada)) {
                JOptionPane.showMessageDialog(this,
                        "Não é possível remover a espécie '" + especieSelecionada + "' pois existem aves cadastradas com ela.\n" +
                                "Remova ou altere as aves desta espécie antes de excluí-la.",
                        "Operação Negada",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int conf = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja remover a espécie: " + especieSelecionada.getNome() + "?",
                    "Confirmar Remoção",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (conf != JOptionPane.YES_OPTION) {
                return;
            }

            especieController.removerEspecie(especieSelecionada);
            atualizarListaVisual();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Erro ao Remover",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}