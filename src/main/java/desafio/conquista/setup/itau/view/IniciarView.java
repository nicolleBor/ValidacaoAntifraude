package desafio.conquista.setup.itau.view;

import javax.swing.*;

public class IniciarView extends JFrame{
    private JPanel Rodape;
    private JLabel Cabecalho;
    private JPanel Painel;
    private JButton IniciarButton;
    private JButton SairButton;

    public IniciarView(JFrame frame) {
        addActionListeners(frame);
    }

    public void addActionListeners(JFrame frame){
        IniciarButton.addActionListener(e -> {
            JFrame frame2 = new JFrame();
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ValidacaoView menu = new ValidacaoView(frame2);
            frame2.setContentPane(menu.getMainPanel());
            frame2.setSize(700, 550);
            frame2.setResizable(false);
            frame2.setLocationRelativeTo(null);
            frame2.setVisible(true);
        });


    }

    private void createUIComponents(){

    }

    public JPanel getMainPanel() {
        return Painel;
    }

}

