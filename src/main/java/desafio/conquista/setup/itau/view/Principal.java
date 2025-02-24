package desafio.conquista.setup.itau.view;

import desafio.conquista.setup.itau.controller.ValidacaoAntifraudeController;
import desafio.conquista.setup.itau.models.Cliente;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        IniciarView menu = new IniciarView(frame);
        frame.setContentPane(menu.getMainPanel());
        //frame.setSize(300, 125);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
