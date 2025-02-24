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

    //public void addActionListeners(JFrame frame){
        //IniciarButton.addActionListener(e -> {
            //JFrame frame2 = new JFrame();
            //frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //ValidacaoView menu = new ValidacaoView(frame2);
            //frame2.setContentPane(menu.getMainPanel());
            //frame2.setSize(700, 550);
            //frame2.pack();
            //frame2.setResizable(false);
            //frame2.setLocationRelativeTo(null);
            //frame2.setVisible(true);
        //});


    //}

    public JPanel getMainPanel() {
        return Painel;
    }

}

