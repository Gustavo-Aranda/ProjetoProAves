import controller.EspecieController;
import controller.GaiolaController;
import controller.PlantelController;
import view.TelaPrincipal;

import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Não foi possível definir o Look and Feel.");
        }

        EspecieController especieCtrl = new EspecieController();
        GaiolaController gaiolaCtrl = new GaiolaController();
        PlantelController plantelCtrl = new PlantelController();

        TelaPrincipal tela = new TelaPrincipal(plantelCtrl, gaiolaCtrl, especieCtrl);

        java.awt.EventQueue.invokeLater(() -> {
            tela.setVisible(true);
        });
    }
}
