package desafio.conquista.setup.itau.view;

import javax.swing.*;

public class IniciarView extends JFrame{
    private JPanel Rodape;
    private JLabel Cabecalho;
    private JPanel Painel;
    private JButton IniciarButton;
    private JButton SairButton;

    private JFrame frame;

    public IniciarView(JFrame frame) {
        this.frame = frame;
        addActionListeners();
    }

    public void addActionListeners(){
        IniciarButton.addActionListener(e -> abrirValidacao());
        SairButton.addActionListener(e -> encerrarValidacao());
    }

    private void abrirValidacao(){
        ValidacaoView validacao = new ValidacaoView(frame);
        frame.setContentPane(validacao.getMainPanel());
        frame.revalidate();
        frame.repaint();
        frame.setSize(700,550);
    }

    private void encerrarValidacao(){
        System.exit(0);
    }


    public JPanel getMainPanel() {
        return Painel;
    }

}

