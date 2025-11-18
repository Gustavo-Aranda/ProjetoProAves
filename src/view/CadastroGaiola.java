package view;

import controller.GaiolaController;
import model.TipoGaiola;

import javax.swing.*;
import java.awt.*;


public class CadastroGaiola extends JDialog {
    private final GaiolaController gaiolaController;

    private JTextField txtNumero;
    private JComboBox<TipoGaiola> cmbTipo;

    public CadastroGaiola(Frame parent, GaiolaController gaiolaCtrl) {
        super(parent, "Cadastrar Nova Gaiola", true);

        this.gaiolaController = gaiolaCtrl;
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        add(criarPainelFormulario(), BorderLayout.CENTER);

        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridLayout(0, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painel.add(new JLabel("Número/Código:"));
        txtNumero = new JTextField();
        painel.add(txtNumero);

        painel.add(new JLabel("Tipo de Gaiola:"));
        cmbTipo = new JComboBox<>(TipoGaiola.values());
        painel.add(cmbTipo);

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
            String codigo = txtNumero.getText();
            TipoGaiola tipo = (TipoGaiola) cmbTipo.getSelectedItem();

            gaiolaController.adicionarGaiola(codigo, tipo);

            JOptionPane.showMessageDialog(this,
                    "Gaiola (" + codigo + ") cadastrada com sucesso!",
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
