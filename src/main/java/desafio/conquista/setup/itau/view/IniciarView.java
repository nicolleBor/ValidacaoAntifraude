package desafio.conquista.setup.itau.view;



import javax.swing.*;

public class IniciarView {
    private JPanel topPanel;
    private JLabel titleLabel;
    private JPanel Painel;

    private JButton iniciarButton;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Tela Inicial");

        IniciarView iniciar = new IniciarView();
        frame.setContentPane(iniciar.Painel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

