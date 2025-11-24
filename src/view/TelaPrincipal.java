package view;

import controller.EspecieController;
import controller.GaiolaController;
import controller.PlantelController;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private final PlantelController plantelController;
    private final GaiolaController gaiolaController;
    private final EspecieController especieController;

    public TelaPrincipal(PlantelController plantelCtrl, GaiolaController gaiolaCtrl, EspecieController especieCtrl) {
        this.plantelController = plantelCtrl;
        this.gaiolaController = gaiolaCtrl;
        this.especieController = especieCtrl;

        configurarJanela();
        configurarComponentes();
    }

    private void configurarJanela() {
        setTitle("ProAves - Gerenciador de Plantel v1.0");
        setLayout(new GridLayout(3, 3, 10, 10));
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void configurarComponentes() {
        add(criarBotao("Adicionar Ave", e -> acaoAdicionarAve()));
        add(criarBotao("Remover Ave", e -> acaoRemoverAve()));

        add(criarBotao("Gerenciar Gaiolas", e -> acaoGerenciarGaiolas()));

        add(criarBotao("Gerenciar Espécies", e -> acaoGerenciarEspecies()));

        add(criarBotao("Visualizar Plantel", e -> acaoVisualizarPlantel()));
        add(criarBotao("Sair", e -> acaoSair()));
    }

    private JButton criarBotao(String texto, java.awt.event.ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.addActionListener(acao);
        return botao;
    }

    private void acaoAdicionarAve() {
        CadastroAve dialogo = new CadastroAve(
                this,
                plantelController,
                especieController,
                gaiolaController
        );
        dialogo.setVisible(true);
    }

    private void acaoVisualizarPlantel() {
        String relatorio = plantelController.getPlantelFormatado();
        JTextArea area = new JTextArea(relatorio, 25, 60);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(this,
                scroll,
                "Relatório do Plantel",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void acaoGerenciarGaiolas() {
        GerenciadorGaiola dialogo = new GerenciadorGaiola(this, gaiolaController, plantelController);
        dialogo.setVisible(true);
    }

    private void acaoGerenciarEspecies() {
        GerenciadorEspecie dialogo = new GerenciadorEspecie(this, especieController, plantelController);
        dialogo.setVisible(true);
    }

    private void acaoRemoverAve() {
        if (plantelController.getPlantel().isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Não há aves no plantel a serem removidas.",
                    "Plantel vazio",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        RemoverAve dialogo = new RemoverAve(this, plantelController);
        dialogo.setVisible(true);
    }

    private void acaoSair() {
        dispose();
    }
}
