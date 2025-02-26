package desafio.conquista.setup.itau.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import desafio.conquista.setup.itau.controller.ValidacaoAntifraudeController;
import desafio.conquista.setup.itau.models.Cliente;
import desafio.conquista.setup.itau.models.Endereco;
import desafio.conquista.setup.itau.controller.IntegracaoAPIController;
import desafio.conquista.setup.itau.utils.Utilities;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.MaskFormatter;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Locale;

public class ValidacaoView extends JFrame {
    public Container Painel;
    private JLabel insiraOsDadosPessoaisLabel;
    private JTextField InsereNomeCompleto;
    private JLabel nomeCompletoLabel;
    private JFormattedTextField InsereCEP;
    private JFormattedTextField InsereCPF;
    private JFormattedTextField InsereDataNascimento;
    private JTextField InsereNomeMae;
    private JFormattedTextField InsereTelefone;
    private JTextField InsereEmail;
    private JTextField CapturaLogradouro;
    private JTextField InsereNumero;
    private JTextField InsereComplemento;
    private JTextField CapturaBairro;
    private JTextField CapturaCidade;
    private JTextField CapturaUF;
    private JButton LimparButton;
    private JPanel Validacao;
    private JButton ValidarButton;
    private JButton VoltarButton;
    private JFrame frame;

    private ValidacaoAntifraudeController validacaoAntifraudeController = new ValidacaoAntifraudeController();

    public ValidacaoView(JFrame frame) {
        this.frame = frame;
        $$$setupUI$$$();
        addActionListeners();
    }

    public void addActionListeners() {

        ValidarButton.addActionListener(e -> validaDados());

        InsereCEP.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String cep = InsereCEP.getText().replaceAll("[^0-9]", "");

                if (cep.length() == 8) {
                    preencherEndereco(cep);
                }
            }
        });

        VoltarButton.addActionListener(e -> voltarParaIniciar());

        LimparButton.addActionListener(e -> limparCampos());

    }

    private void validaDados() {
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();

        cliente.setCpf(Utilities.limpaCaractere(InsereCPF.getText()));
        cliente.setNomeCompleto(InsereNomeCompleto.getText());
        cliente.setEmail(InsereEmail.getText());
        cliente.setTelefone(InsereTelefone.getText());
        cliente.setNomeMae(InsereNomeMae.getText());
        cliente.setDataNascimento(InsereDataNascimento.getText());

        endereco.setCep(InsereCEP.getText());
        endereco.setBairro(CapturaBairro.getText());
        endereco.setLogradouro(CapturaLogradouro.getText());
        endereco.setUnidade(InsereNumero.getText());
        endereco.setComplemento(InsereComplemento.getText());
        endereco.setLocalidade(CapturaCidade.getText());
        endereco.setUf(CapturaUF.getText());
        cliente.setEndereco(endereco);

        try {
            int nota = validacaoAntifraudeController.validacaoAntifraude(cliente);
            JOptionPane.showMessageDialog(frame, "GRAU DE CONFIABILIDADE: " + nota);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void limparCampos() {
        InsereNomeCompleto.setText("");
        InsereCPF.setValue(null);
        InsereCPF.setText("");
        InsereTelefone.setValue(null);
        InsereTelefone.setText("");
        InsereNomeMae.setText("");
        InsereEmail.setText("");
        InsereDataNascimento.setValue(null);
        InsereDataNascimento.setText("");
        InsereCEP.setValue(null);
        InsereCEP.setText("");
        InsereNumero.setText("");
        InsereComplemento.setText("");
        CapturaLogradouro.setText("");
        CapturaCidade.setText("");
        CapturaBairro.setText("");
        CapturaUF.setText("");
    }

    private void preencherEndereco(String cep) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    Endereco endereco = IntegracaoAPIController.buscaCep(cep);

                    if (endereco != null) {
                        CapturaLogradouro.setText(endereco.getLogradouro());
                        CapturaBairro.setText(endereco.getBairro());
                        CapturaCidade.setText(endereco.getLocalidade());
                        CapturaUF.setText(endereco.getUf());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar o CEP!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        };
        worker.execute();
    }

    private void voltarParaIniciar() {
        IniciarView iniciar = new IniciarView(frame);
        frame.setContentPane(iniciar.getMainPanel());
        frame.revalidate();
        frame.repaint();
        frame.pack();
    }

    private void createUIComponents() {
        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            InsereCPF = new JFormattedTextField(cpfMask);

            MaskFormatter cepMask = new MaskFormatter("#####-###");
            cepMask.setPlaceholderCharacter('_');
            InsereCEP = new JFormattedTextField(cepMask);

            MaskFormatter telefoneMask = new MaskFormatter("(##)#####-####");
            telefoneMask.setPlaceholderCharacter('_');
            InsereTelefone = new JFormattedTextField(telefoneMask);

            MaskFormatter dataNascimentoMask = new MaskFormatter("##/##/####");
            dataNascimentoMask.setPlaceholderCharacter('_');
            InsereDataNascimento = new JFormattedTextField(dataNascimentoMask);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public JPanel getMainPanel() {
        return Validacao;
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        Validacao = new JPanel();
        Validacao.setLayout(new GridLayoutManager(7, 8, new Insets(0, 0, 0, 0), -1, -1));
        Validacao.setBackground(new Color(-1977648));
        Font ValidacaoFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, Validacao.getFont());
        if (ValidacaoFont != null) Validacao.setFont(ValidacaoFont);
        Validacao.setForeground(new Color(-1977648));
        Validacao.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        insiraOsDadosPessoaisLabel = new JLabel();
        insiraOsDadosPessoaisLabel.setBackground(new Color(-40448));
        Font insiraOsDadosPessoaisLabelFont = this.$$$getFont$$$("Ubuntu Condensed", Font.BOLD, 21, insiraOsDadosPessoaisLabel.getFont());
        if (insiraOsDadosPessoaisLabelFont != null) insiraOsDadosPessoaisLabel.setFont(insiraOsDadosPessoaisLabelFont);
        insiraOsDadosPessoaisLabel.setForeground(new Color(-40448));
        insiraOsDadosPessoaisLabel.setText("Insira os dados pessoais mandatórios para validação:");
        Validacao.add(insiraOsDadosPessoaisLabel, new GridConstraints(1, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1977648));
        panel1.setForeground(new Color(-1977648));
        Validacao.add(panel1, new GridConstraints(2, 2, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-10989987));
        label1.setText("Nome Completo:");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        InsereNomeCompleto = new JTextField();
        InsereNomeCompleto.setBackground(new Color(-459777));
        Font InsereNomeCompletoFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, InsereNomeCompleto.getFont());
        if (InsereNomeCompletoFont != null) InsereNomeCompleto.setFont(InsereNomeCompletoFont);
        InsereNomeCompleto.setForeground(new Color(-10989987));
        InsereNomeCompleto.setHorizontalAlignment(0);
        InsereNomeCompleto.setText("");
        panel1.add(InsereNomeCompleto, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(205, 34), null, 0, false));
        nomeCompletoLabel = new JLabel();
        Font nomeCompletoLabelFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, nomeCompletoLabel.getFont());
        if (nomeCompletoLabelFont != null) nomeCompletoLabel.setFont(nomeCompletoLabelFont);
        nomeCompletoLabel.setForeground(new Color(-10989987));
        nomeCompletoLabel.setText("CPF:");
        panel1.add(nomeCompletoLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        InsereCPF.setBackground(new Color(-459777));
        Font InsereCPFFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, InsereCPF.getFont());
        if (InsereCPFFont != null) InsereCPF.setFont(InsereCPFFont);
        InsereCPF.setForeground(new Color(-10989987));
        InsereCPF.setHorizontalAlignment(0);
        InsereCPF.setText("");
        InsereCPF.setVerifyInputWhenFocusTarget(true);
        panel1.add(InsereCPF, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(205, 34), null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-10989987));
        label2.setText("Data de Nascimento:");
        panel1.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        InsereDataNascimento.setBackground(new Color(-459777));
        Font InsereDataNascimentoFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, InsereDataNascimento.getFont());
        if (InsereDataNascimentoFont != null) InsereDataNascimento.setFont(InsereDataNascimentoFont);
        InsereDataNascimento.setForeground(new Color(-10989987));
        InsereDataNascimento.setHorizontalAlignment(0);
        InsereDataNascimento.setText("");
        panel1.add(InsereDataNascimento, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(205, 34), null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-10989987));
        label3.setText("Nome da Mãe:");
        panel1.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        InsereNomeMae = new JTextField();
        InsereNomeMae.setBackground(new Color(-459777));
        Font InsereNomeMaeFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, InsereNomeMae.getFont());
        if (InsereNomeMaeFont != null) InsereNomeMae.setFont(InsereNomeMaeFont);
        InsereNomeMae.setForeground(new Color(-10989987));
        InsereNomeMae.setHorizontalAlignment(0);
        InsereNomeMae.setText("");
        panel1.add(InsereNomeMae, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(205, 34), null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-10989987));
        label4.setText("Telefone:");
        panel1.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        InsereTelefone.setBackground(new Color(-459777));
        Font InsereTelefoneFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, InsereTelefone.getFont());
        if (InsereTelefoneFont != null) InsereTelefone.setFont(InsereTelefoneFont);
        InsereTelefone.setForeground(new Color(-10989987));
        InsereTelefone.setHorizontalAlignment(0);
        InsereTelefone.setText("");
        InsereTelefone.setToolTipText("Para números fixos, colocar 0 no primeiro dígito.");
        panel1.add(InsereTelefone, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(205, 34), null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-10989987));
        label5.setText("E-mail:");
        panel1.add(label5, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        InsereEmail = new JTextField();
        InsereEmail.setBackground(new Color(-459777));
        Font InsereEmailFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, InsereEmail.getFont());
        if (InsereEmailFont != null) InsereEmail.setFont(InsereEmailFont);
        InsereEmail.setForeground(new Color(-10989987));
        InsereEmail.setHorizontalAlignment(0);
        InsereEmail.setText("");
        panel1.add(InsereEmail, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(205, 34), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(4, 6, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1977648));
        Validacao.add(panel2, new GridConstraints(4, 2, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-10989987));
        label6.setText("          Endereço:");
        panel2.add(label6, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(115, 17), null, 0, false));
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setForeground(new Color(-10989987));
        label7.setHorizontalAlignment(0);
        label7.setHorizontalTextPosition(0);
        label7.setText("CEP:");
        panel2.add(label7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(77, 15), null, 0, false));
        InsereCEP.setBackground(new Color(-459777));
        Font InsereCEPFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, InsereCEP.getFont());
        if (InsereCEPFont != null) InsereCEP.setFont(InsereCEPFont);
        InsereCEP.setForeground(new Color(-10989987));
        InsereCEP.setHorizontalAlignment(0);
        InsereCEP.setText("");
        panel2.add(InsereCEP, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(146, 34), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setBackground(new Color(-10989987));
        Font label8Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setForeground(new Color(-10989987));
        label8.setText("Logradouro:");
        panel2.add(label8, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CapturaLogradouro = new JTextField();
        CapturaLogradouro.setBackground(new Color(-459777));
        CapturaLogradouro.setDisabledTextColor(new Color(-10989987));
        CapturaLogradouro.setEditable(true);
        CapturaLogradouro.setEnabled(false);
        Font CapturaLogradouroFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, CapturaLogradouro.getFont());
        if (CapturaLogradouroFont != null) CapturaLogradouro.setFont(CapturaLogradouroFont);
        CapturaLogradouro.setForeground(new Color(-15067365));
        CapturaLogradouro.setHorizontalAlignment(0);
        CapturaLogradouro.setSelectedTextColor(new Color(-10989987));
        CapturaLogradouro.setText("");
        CapturaLogradouro.setToolTipText("Informe o CEP para o preenchimento automático do campo.");
        panel2.add(CapturaLogradouro, new GridConstraints(1, 3, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(148, 34), null, 0, false));
        final JLabel label9 = new JLabel();
        Font label9Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label9.getFont());
        if (label9Font != null) label9.setFont(label9Font);
        label9.setForeground(new Color(-10989987));
        label9.setHorizontalAlignment(0);
        label9.setHorizontalTextPosition(0);
        label9.setText("Número:");
        panel2.add(label9, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(77, 15), null, 0, false));
        InsereNumero = new JTextField();
        InsereNumero.setBackground(new Color(-459777));
        InsereNumero.setEnabled(true);
        Font InsereNumeroFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, InsereNumero.getFont());
        if (InsereNumeroFont != null) InsereNumero.setFont(InsereNumeroFont);
        InsereNumero.setForeground(new Color(-10989987));
        InsereNumero.setHorizontalAlignment(0);
        InsereNumero.setText("");
        InsereNumero.setToolTipText("");
        panel2.add(InsereNumero, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(146, 34), null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setBackground(new Color(-10989987));
        Font label10Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label10.getFont());
        if (label10Font != null) label10.setFont(label10Font);
        label10.setForeground(new Color(-10989987));
        label10.setText("Complemento:");
        panel2.add(label10, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        InsereComplemento = new JTextField();
        InsereComplemento.setBackground(new Color(-459777));
        InsereComplemento.setEditable(true);
        InsereComplemento.setEnabled(true);
        Font InsereComplementoFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, InsereComplemento.getFont());
        if (InsereComplementoFont != null) InsereComplemento.setFont(InsereComplementoFont);
        InsereComplemento.setForeground(new Color(-10989987));
        InsereComplemento.setHorizontalAlignment(0);
        InsereComplemento.setText("");
        InsereComplemento.setToolTipText("Caso não tenha complemento, coloque \"N/A\"");
        panel2.add(InsereComplemento, new GridConstraints(2, 3, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(148, 34), null, 0, false));
        final JLabel label11 = new JLabel();
        Font label11Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label11.getFont());
        if (label11Font != null) label11.setFont(label11Font);
        label11.setForeground(new Color(-10989987));
        label11.setHorizontalAlignment(0);
        label11.setHorizontalTextPosition(0);
        label11.setText("Bairro");
        panel2.add(label11, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(77, 15), null, 0, false));
        CapturaBairro = new JTextField();
        CapturaBairro.setBackground(new Color(-459777));
        CapturaBairro.setDisabledTextColor(new Color(-10989987));
        CapturaBairro.setEditable(true);
        CapturaBairro.setEnabled(false);
        Font CapturaBairroFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, CapturaBairro.getFont());
        if (CapturaBairroFont != null) CapturaBairro.setFont(CapturaBairroFont);
        CapturaBairro.setForeground(new Color(-15067365));
        CapturaBairro.setHorizontalAlignment(0);
        CapturaBairro.setOpaque(true);
        CapturaBairro.setRequestFocusEnabled(true);
        CapturaBairro.setSelectedTextColor(new Color(-10989987));
        CapturaBairro.setSelectionColor(new Color(-13745298));
        CapturaBairro.setText("");
        CapturaBairro.setToolTipText("Informe o CEP para o preenchimento automático do campo.");
        panel2.add(CapturaBairro, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(146, 34), null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setBackground(new Color(-10989987));
        Font label12Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label12.getFont());
        if (label12Font != null) label12.setFont(label12Font);
        label12.setForeground(new Color(-10989987));
        label12.setText("Cidade:");
        panel2.add(label12, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CapturaCidade = new JTextField();
        CapturaCidade.setBackground(new Color(-459777));
        CapturaCidade.setDisabledTextColor(new Color(-10989987));
        CapturaCidade.setEditable(true);
        CapturaCidade.setEnabled(false);
        Font CapturaCidadeFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, CapturaCidade.getFont());
        if (CapturaCidadeFont != null) CapturaCidade.setFont(CapturaCidadeFont);
        CapturaCidade.setForeground(new Color(-15067365));
        CapturaCidade.setHorizontalAlignment(0);
        CapturaCidade.setSelectedTextColor(new Color(-10989987));
        CapturaCidade.setText("");
        CapturaCidade.setToolTipText("Informe o CEP para o preenchimento automático do campo.");
        panel2.add(CapturaCidade, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(146, 34), null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setBackground(new Color(-10989987));
        Font label13Font = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, label13.getFont());
        if (label13Font != null) label13.setFont(label13Font);
        label13.setForeground(new Color(-10989987));
        label13.setText("UF:");
        panel2.add(label13, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CapturaUF = new JTextField();
        CapturaUF.setBackground(new Color(-459777));
        CapturaUF.setDisabledTextColor(new Color(-10989987));
        CapturaUF.setEditable(true);
        CapturaUF.setEnabled(false);
        Font CapturaUFFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, CapturaUF.getFont());
        if (CapturaUFFont != null) CapturaUF.setFont(CapturaUFFont);
        CapturaUF.setForeground(new Color(-15067365));
        CapturaUF.setHorizontalAlignment(0);
        CapturaUF.setSelectedTextColor(new Color(-10989987));
        CapturaUF.setText("");
        CapturaUF.setToolTipText("Informe o CEP para o preenchimento automático do campo.");
        panel2.add(CapturaUF, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 34), null, 0, false));
        final Spacer spacer1 = new Spacer();
        Validacao.add(spacer1, new GridConstraints(2, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        Validacao.add(spacer2, new GridConstraints(4, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        Validacao.add(spacer3, new GridConstraints(5, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        Validacao.add(spacer4, new GridConstraints(1, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        Validacao.add(spacer5, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        Validacao.add(spacer6, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        Validacao.add(spacer7, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        Validacao.add(spacer8, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        Validacao.add(spacer9, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        Validacao.add(spacer10, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        Validacao.add(spacer11, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        Validacao.add(spacer12, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        Validacao.add(spacer13, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        Validacao.add(spacer14, new GridConstraints(3, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer15 = new Spacer();
        Validacao.add(spacer15, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer16 = new Spacer();
        Validacao.add(spacer16, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer17 = new Spacer();
        Validacao.add(spacer17, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer18 = new Spacer();
        Validacao.add(spacer18, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer19 = new Spacer();
        Validacao.add(spacer19, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer20 = new Spacer();
        Validacao.add(spacer20, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        VoltarButton = new JButton();
        VoltarButton.setBackground(new Color(-10989987));
        Font VoltarButtonFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, VoltarButton.getFont());
        if (VoltarButtonFont != null) VoltarButton.setFont(VoltarButtonFont);
        VoltarButton.setForeground(new Color(-657156));
        VoltarButton.setHorizontalTextPosition(0);
        VoltarButton.setText("Voltar");
        Validacao.add(VoltarButton, new GridConstraints(5, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 34), null, 0, false));
        LimparButton = new JButton();
        LimparButton.setBackground(new Color(-10989987));
        Font LimparButtonFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, LimparButton.getFont());
        if (LimparButtonFont != null) LimparButton.setFont(LimparButtonFont);
        LimparButton.setForeground(new Color(-657156));
        LimparButton.setHorizontalTextPosition(0);
        LimparButton.setText("Limpar");
        Validacao.add(LimparButton, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 34), null, 0, false));
        ValidarButton = new JButton();
        ValidarButton.setBackground(new Color(-10989987));
        Font ValidarButtonFont = this.$$$getFont$$$("Ubuntu Condensed", -1, 16, ValidarButton.getFont());
        if (ValidarButtonFont != null) ValidarButton.setFont(ValidarButtonFont);
        ValidarButton.setForeground(new Color(-657156));
        ValidarButton.setHorizontalTextPosition(0);
        ValidarButton.setText("Validar");
        Validacao.add(ValidarButton, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 34), null, 0, false));
        final Spacer spacer21 = new Spacer();
        Validacao.add(spacer21, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer22 = new Spacer();
        Validacao.add(spacer22, new GridConstraints(6, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer23 = new Spacer();
        Validacao.add(spacer23, new GridConstraints(6, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer24 = new Spacer();
        Validacao.add(spacer24, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer25 = new Spacer();
        Validacao.add(spacer25, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer26 = new Spacer();
        Validacao.add(spacer26, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer27 = new Spacer();
        Validacao.add(spacer27, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer28 = new Spacer();
        Validacao.add(spacer28, new GridConstraints(6, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer29 = new Spacer();
        Validacao.add(spacer29, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer30 = new Spacer();
        Validacao.add(spacer30, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return Validacao;
    }

}
