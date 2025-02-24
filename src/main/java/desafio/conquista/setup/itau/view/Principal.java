package desafio.conquista.setup.itau.view;

import desafio.conquista.setup.itau.controller.ValidacaoAntifraudeController;
import desafio.conquista.setup.itau.models.Cliente;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) throws Exception {
        int nota;
        Cliente cliente = new Cliente();
//        cliente.setCpf(JOptionPane.showInputDialog("Digite CPF: "));
//        cliente.setNomeCompleto(JOptionPane.showInputDialog("Digite Nome Completo: "));
//        cliente.setTelefone(JOptionPane.showInputDialog("Digite o Telefone: "));
//        cliente.setEmail(JOptionPane.showInputDialog("Digite email: "));
//        cliente.setDataNascimento(JOptionPane.showInputDialog("Digite data de nascimento: "));
//        cliente.setCep(JOptionPane.showInputDialog("Digite o CEP do seu Endereço: "));
//        cliente.setNomeMae(JOptionPane.showInputDialog("Digite o nome da sua mãe: "));
//
//        ValidacaoAntifraudeController validacao = new ValidacaoAntifraudeController();
//        nota = validacao.validacaoAntifraude(cliente);
//
//        JOptionPane.showMessageDialog(null, "Nota: " + nota);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        IniciarView menu = new IniciarView(frame);
        frame.setContentPane(menu.getMainPanel());
        frame.setSize(300, 125);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
