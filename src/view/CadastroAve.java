package view;

import controller.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CadastroAve extends JDialog{
    private final PlantelController plantelController;
    private final EspecieController especieController;
    private final GaiolaController gaiolaController;

    private JTextField txtAnilha;
    private JComboBox<Especie> cmbEspecie;
    private JComboBox<Sexo> cmbSexo;
    private JTextField txtCor;
    private JComboBox<Gaiola> cmbGaiola;
    private JComboBox<Status> cmbStatus;

    public CadastroAve(Frame parent,
                              PlantelController plantelCtrl,
                              EspecieController especieCtrl,
                              GaiolaController gaiolaCtrl) {

        super(parent, "Cadastrar Nova Ave", true);

        this.plantelController = plantelCtrl;
        this.especieController = especieCtrl;
        this.gaiolaController = gaiolaCtrl;
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        add(criarPainelFormulario(), BorderLayout.CENTER);

        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridLayout(0, 2, 10, 10)); // 0=auto-rows, 2 colunas
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painel.add(new JLabel("Anilha:"));
        txtAnilha = new JTextField();
        painel.add(txtAnilha);

        painel.add(new JLabel("Espécie:"));
        Vector<Especie> especies = new Vector<>(especieController.getListaEspecies());
        cmbEspecie = new JComboBox<>(especies);
        painel.add(cmbEspecie);

        painel.add(new JLabel("Sexo:"));
        cmbSexo = new JComboBox<>(Sexo.values());
        painel.add(cmbSexo);

        painel.add(new JLabel("Cor/Mutação:"));
        txtCor = new JTextField();
        painel.add(txtCor);

        painel.add(new JLabel("Gaiola Atual:"));
        Vector<Gaiola> gaiolas = new Vector<>(gaiolaController.getListaGaiolas());
        cmbGaiola = new JComboBox<>(gaiolas);
        painel.add(cmbGaiola);

        painel.add(new JLabel("Status:"));
        cmbStatus = new JComboBox<>(Status.values());
        painel.add(cmbStatus);

        return painel;
    }


    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> acaoSalvar());

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        painel.add(btnSalvar);
        painel.add(btnCancelar);
        return painel;
    }


    private void acaoSalvar() {
        try {
            String anilha = txtAnilha.getText();
            Especie especie = (Especie) cmbEspecie.getSelectedItem();
            Sexo sexo = (Sexo) cmbSexo.getSelectedItem();
            String cor = txtCor.getText();
            Gaiola gaiola = (Gaiola) cmbGaiola.getSelectedItem();
            Status status = (Status) cmbStatus.getSelectedItem();

            plantelController.cadastrarAve(anilha, especie, sexo, cor, gaiola, status);

            JOptionPane.showMessageDialog(this,
                    "Ave (Anilha: " + anilha + ") cadastrada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
