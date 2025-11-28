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
        setLayout(new BorderLayout(10, 10));
        setSize(800, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void configurarComponentes() {
        JLabel lblBanner = new JLabel("Bem-Vindo ao ProAves!", SwingConstants.CENTER);
        lblBanner.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblBanner.setOpaque(true);
        lblBanner.setBackground(new Color(0, 102, 204));
        lblBanner.setForeground(Color.WHITE);
        lblBanner.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel painelBotoes = new JPanel(new GridLayout(3, 3, 15, 15));

        painelBotoes.add(criarBotao("Adicionar Ave", e -> acaoAdicionarAve()));
        painelBotoes.add(criarBotao("Adicionar Gaiola", e -> acaoGerenciarGaiolas()));
        painelBotoes.add(criarBotao("Gerenciar Espécies", e -> acaoGerenciarEspecies()));
        painelBotoes.add(criarBotao("Remover Ave", e -> acaoRemoverAve()));
        painelBotoes.add(criarBotao("Visualizar/Editar Plantel", e -> acaoVisualizarPlantel()));
        painelBotoes.add(criarBotao("Sair", e -> acaoSair()));

        add(lblBanner, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
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
        GerenciadorPlantel dialogo = new GerenciadorPlantel(this, plantelController, especieController, gaiolaController);
        dialogo.setVisible(true);
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
