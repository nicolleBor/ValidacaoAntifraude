package desafio.conquista.setup.itau.view;

import desafio.conquista.setup.itau.controller.ValidacaoAntifraudeController;
import desafio.conquista.setup.itau.models.Cliente;
import desafio.conquista.setup.itau.models.Endereco;
import desafio.conquista.setup.itau.service.IntegracaoAPI;
import desafio.conquista.setup.itau.utils.Utilities;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ValidacaoView extends JFrame{
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
        addActionListeners();
    }

    public void addActionListeners(){

        ValidarButton.addActionListener(e -> {
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

        });

        InsereCEP.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String cep = InsereCEP.getText().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos

                if (cep.length() == 8) {
                    preencherEndereco(cep);
                }
            }
        });

        VoltarButton.addActionListener(e -> voltarParaIniciar());

        LimparButton.addActionListener(e -> limparCampos());

    }

    private void limparCampos(){
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
                    Endereco endereco = IntegracaoAPI.buscaCep(cep);

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

    private void voltarParaIniciar(){
        IniciarView iniciar = new IniciarView(frame);
        frame.setContentPane(iniciar.getMainPanel());
        frame.revalidate();
        frame.repaint();
        frame.pack();
    }

    private void createUIComponents(){
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

    public JPanel getMainPanel(){
        return Validacao;
    }


}
