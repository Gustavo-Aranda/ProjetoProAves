package view;

import controller.PlantelController;
import model.Ave;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class RemoverAve extends JDialog {
    private final PlantelController plantelController;

    private JList<Ave> listaAvesVisual;
    private DefaultListModel<Ave> listModel;
    private JButton btnRemover;
    private JButton btnFechar;

    public RemoverAve(Frame parent, PlantelController plantelCtrl){
        super(parent, "Remover Ave", true);
        this.plantelController = plantelCtrl;

        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        add(criarPainelLista(), BorderLayout.CENTER);

        add(criarPainelBotoes(), BorderLayout.SOUTH);

        atualizarListaVisual();

        JLabel lblDica = new JLabel("Selecione uma ave na lista para remover.", JLabel.CENTER);
        lblDica.setFont(new Font("Arial", Font.ITALIC, 14));
        add(lblDica, BorderLayout.NORTH);
    }

    private JScrollPane criarPainelLista(){
        listModel = new DefaultListModel<>();
        listaAvesVisual = new JList<>(listModel);

        listaAvesVisual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaAvesVisual.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(listaAvesVisual);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        return scrollPane;
    }

    private JPanel criarPainelBotoes(){
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnRemover = new JButton("Remover Selecionado");
        btnRemover.addActionListener(e -> acaoRemover());

        btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        painel.add(btnRemover);
        painel.add(btnFechar);

        return painel;
    }

    private void atualizarListaVisual(){
        listModel.clear();
        ArrayList<Ave> plantel = plantelController.getPlantel();

        if (plantel.isEmpty()){
            btnRemover.setEnabled(false);
        } else {
            btnRemover.setEnabled(true);
            for (Ave ave : plantel){
                listModel.addElement(ave);
            }
        }
    }

    private void acaoRemover(){
        try {
            Ave aveSelecionada = listaAvesVisual.getSelectedValue();

            if (aveSelecionada == null) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, selecione uma ave da lista.",
                        "Seleção Necessária",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int conf = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja remover permanentemente a ave:\n" +
                            "Anilha: " + aveSelecionada.getAnilha() + "\n" +
                            "Espécie: " + aveSelecionada.getEspecie(),
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (conf == JOptionPane.YES_OPTION) {
                plantelController.removerAve(aveSelecionada);

                JOptionPane.showMessageDialog(this, "Ave removida com sucesso.");

                atualizarListaVisual();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Erro ao Remover",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
