package view;

import controller.GaiolaController;
import controller.PlantelController;
import model.Ave;
import model.Gaiola;
import model.TipoGaiola;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GerenciadorGaiola extends JDialog {
    private final GaiolaController gaiolaController;
    private final PlantelController plantelController;

    private JList<Gaiola> listaGaiolaVisual;
    private DefaultListModel<Gaiola> listModel;

    private JTextField txtNumero;
    private JComboBox<TipoGaiola> cmbTipo;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JButton btnFechar;

    public GerenciadorGaiola(Frame parent, GaiolaController gaiolaCtrl, PlantelController plantelCtrl){
        super(parent, "Gerenciar Gaiolas", true);
        this.gaiolaController = gaiolaCtrl;
        this.plantelController = plantelCtrl;

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        add(criarPainelLista(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);

        atualizarListaVisual();

        JLabel lblDica = new JLabel("Clique duas vezes na gaiola para ver as aves.", JLabel.CENTER);
        lblDica.setFont(new Font("Arial", Font.ITALIC, 14));
        add(lblDica, BorderLayout.NORTH);
    }


    private JScrollPane criarPainelLista(){
        listModel = new DefaultListModel<>();
        listaGaiolaVisual = new JList<>(listModel);

        listaGaiolaVisual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaGaiolaVisual.setFont(new Font("Arial", Font.PLAIN, 14));

        listaGaiolaVisual.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Gaiola gaiolaSelecionada = listaGaiolaVisual.getSelectedValue();
                    if (gaiolaSelecionada != null) {
                        mostrarAvesDaGaiola(gaiolaSelecionada);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(listaGaiolaVisual);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0,10));
        return scrollPane;
    }

    private void mostrarAvesDaGaiola(Gaiola gaiola) {
        ArrayList<Ave> aves = plantelController.getAvesPorGaiola(gaiola);

        StringBuilder sb = new StringBuilder();
        sb.append("Gaiola: ").append(gaiola.getCodigo()).append("\n");
        sb.append("Tipo: ").append(gaiola.getTipo()).append("\n\n");

        if (aves.isEmpty()) {
            sb.append("Gaiola vazia.");
        } else {
            sb.append("Aves presentes (" + aves.size() + "):\n");
            for (Ave ave : aves) {
                sb.append("- ").append(ave.getAnilha())
                        .append(" (").append(ave.getEspecie()).append(")")
                        .append(" - ").append(ave.getSexo())
                        .append("\n");
            }
        }

        JTextArea area = new JTextArea(sb.toString(), 10, 30);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JOptionPane.showMessageDialog(this, new JScrollPane(area),
                "Detalhes da Gaiola", JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel criarPainelBotoes(){
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

    private void atualizarListaVisual(){
        listModel.clear();
        ArrayList<Gaiola> gaiolas = gaiolaController.getListaGaiolas();
        for (Gaiola gaiola : gaiolas){
            listModel.addElement(gaiola);
        }
    }

    private JPanel criarPainelFormulario(){
        JPanel painel = new JPanel(new GridLayout(0, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem interna

        painel.add(new JLabel("Número/Código:"));
        txtNumero = new JTextField();
        painel.add(txtNumero);

        painel.add(new JLabel("Tipo de Gaiola:"));
        cmbTipo = new JComboBox<>(TipoGaiola.values());
        painel.add(cmbTipo);

        return painel;
    }

    private void acaoAdicionar(){
        JPanel painelFormulario = criarPainelFormulario();

        int resultado = JOptionPane.showConfirmDialog(this,
                painelFormulario,
                "Adicionar Nova Gaiola",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                String codigo = txtNumero.getText();
                TipoGaiola tipo = (TipoGaiola) cmbTipo.getSelectedItem();

                gaiolaController.adicionarGaiola(codigo, tipo);

                JOptionPane.showMessageDialog(this,
                        "Gaiola (" + codigo + ") cadastrada com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                atualizarListaVisual();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        e.getMessage(),
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void acaoRemover(){
        try{
            Gaiola gaiolaSelecionada = listaGaiolaVisual.getSelectedValue();

            if (gaiolaSelecionada == null){
                throw new Exception("Por favor, selecione uma gaiola da lista para removê-la");
            }

            if (plantelController.verificarGaiolaEmUso(gaiolaSelecionada)) {
                JOptionPane.showMessageDialog(this,
                        "Não é possível remover a gaiola '" + gaiolaSelecionada.getCodigo() + "' pois ela contém aves.\n" +
                                "Mova ou remova as aves desta gaiola antes de excluí-la.",
                        "Operação Negada",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int conf = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja remover a gaiola: "+gaiolaSelecionada+"?",
                    "Confirmar Remoção",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (conf != JOptionPane.YES_OPTION){
                return;
            }
            gaiolaController.removerGaiola(gaiolaSelecionada);
            atualizarListaVisual();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Erro ao remover",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
