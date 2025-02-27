package desafio.conquista.setup.itau.view;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        IniciarView menu = new IniciarView(frame);
        frame.setContentPane(menu.getMainPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
