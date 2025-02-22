package desafio.conquista.setup.itau.Interface;

public interface IValidacaoAntifraudeController {
    boolean validaCpf(String cpf);
    boolean validaNomeCompleto(String nomeCompleto);
    boolean validaTelefone(String telefone);
    boolean validaEmail(String email);
    boolean validaDataNascimento(String dataNascimento);
    boolean validaEndereco(String endereco);
    boolean validaNomeMae(String nomeMae);
}
