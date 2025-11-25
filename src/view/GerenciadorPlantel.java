package view;

import controller.EspecieController;
import controller.GaiolaController;
import controller.PlantelController;
import model.Ave;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GerenciadorPlantel extends JDialog {
    private final PlantelController plantelController;
    private final EspecieController especieController;
    private final GaiolaController gaiolaController;

    private JList<Ave> listaAvesVisual;
    private DefaultListModel<Ave> listModel;

    public GerenciadorPlantel(Frame parent, PlantelController plantelCtrl, EspecieController especieCtrl,
                              GaiolaController gaiolaCtrl){
        super(parent, "Gerenciador Plantel", true);
        this.plantelController = plantelCtrl;
        this.especieController = especieCtrl;
        this.gaiolaController = gaiolaCtrl;

        setSize(500,400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        add(criarPainelLista(), BorderLayout.CENTER);
        JLabel lblDica = new JLabel("Clique duas vezes na ave para editar.", JLabel.CENTER);
        lblDica.setFont(new Font("Arial", Font.ITALIC, 14));
        add(lblDica, BorderLayout.NORTH);

        atualizarListaVisual();
    }

    private JScrollPane criarPainelLista(){
        listModel = new DefaultListModel<>();
        listaAvesVisual = new JList<>(listModel);
        listaAvesVisual.setFont(new Font("Arial", Font.PLAIN, 14));

        listaAvesVisual.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Ave aveSelecionada = listaAvesVisual.getSelectedValue();
                    if (aveSelecionada != null) {
                        abrirEdicao(aveSelecionada);
                    }
                }
            }
        });

        return new JScrollPane(listaAvesVisual);
    }

    private void atualizarListaVisual(){
        listModel.clear();
        ArrayList<Ave> plantel = plantelController.getPlantel();
        for (Ave ave : plantel){
            listModel.addElement(ave);
        }
    }

    private void abrirEdicao(Ave ave){
        EditarAve dialogo = new EditarAve((Frame) getOwner(), plantelController, especieController, gaiolaController, ave);
        dialogo.setVisible(true);

        atualizarListaVisual();
    }
}
