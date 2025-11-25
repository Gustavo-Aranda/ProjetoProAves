package view;

import controller.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class EditarAve extends JDialog{
    private final PlantelController plantelController;
    private final Ave aveOriginal;

    private JTextField txtAnilha;
    private JComboBox<Especie> cmbEspecie;
    private JComboBox<Sexo> cmbSexo;
    private JTextField txtCor;
    private JComboBox<Gaiola> cmbGaiola;
    private JComboBox<Status> cmbStatus;

    public EditarAve(Frame parent, PlantelController plantelCtrl, EspecieController especieCtrl,
                     GaiolaController gaiolaCtrl, Ave ave){
        super(parent, "Editar Ave - " + ave.getAnilha(), true);
        this.plantelController = plantelCtrl;
        this.aveOriginal = ave;

        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        add(criarPainelFormulario(especieCtrl, gaiolaCtrl), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);

        preencherDados();
    }

    private JPanel criarPainelFormulario(EspecieController especieCtrl, GaiolaController gaiolaCtrl){
        JPanel painel = new JPanel(new GridLayout(0, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painel.add(new JLabel("Anilha:"));
        txtAnilha = new JTextField();
        painel.add(txtAnilha);

        painel.add(new JLabel("Espécie:"));
        cmbEspecie = new JComboBox<>(new Vector<>(especieCtrl.getListaEspecies()));
        painel.add(cmbEspecie);

        painel.add(new JLabel("Sexo:"));
        cmbSexo = new JComboBox<>(Sexo.values());
        painel.add(cmbSexo);

        painel.add(new JLabel("Cor/Mutação:"));
        txtCor = new JTextField();
        painel.add(txtCor);

        painel.add(new JLabel("Gaiola Atual:"));
        cmbGaiola = new JComboBox<>(new Vector<>(gaiolaCtrl.getListaGaiolas()));
        painel.add(cmbGaiola);

        painel.add(new JLabel("Status:"));
        cmbStatus = new JComboBox<>(Status.values());
        painel.add(cmbStatus);

        return painel;
    }

    private JPanel criarPainelBotoes(){
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnSalvar = new JButton("Salvar Alterações");
        btnSalvar.addActionListener(e -> acaoSalvar());

        JButton btnCancelar = new JButton("Cancelar Alterações");
        btnCancelar.addActionListener(e -> dispose());

        painel.add(btnSalvar);
        painel.add(btnCancelar);

        return painel;
    }

    private void preencherDados() {
        txtAnilha.setText(aveOriginal.getAnilha());
        cmbEspecie.setSelectedItem(aveOriginal.getEspecie());
        cmbSexo.setSelectedItem(aveOriginal.getSexo());
        txtCor.setText(aveOriginal.getCor());
        cmbGaiola.setSelectedItem(aveOriginal.getGaiola());
        cmbStatus.setSelectedItem(aveOriginal.getStatus());
    }

    private void acaoSalvar(){
        try{
            plantelController.atualizarAve(
                    aveOriginal,
                    txtAnilha.getText(),
                    (Especie) cmbEspecie.getSelectedItem(),
                    (Sexo) cmbSexo.getSelectedItem(),
                    txtCor.getText(),
                    (Gaiola) cmbGaiola.getSelectedItem(),
                    (Status) cmbStatus.getSelectedItem()
            );

            JOptionPane.showMessageDialog(this, "Ave atualizada com sucesso!");
            dispose();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Erro ao Atualizar",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
