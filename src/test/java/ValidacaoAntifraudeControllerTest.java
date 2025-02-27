import desafio.conquista.setup.itau.controller.ValidacaoAntifraudeController;
import desafio.conquista.setup.itau.models.Endereco;
import org.junit.Assert;
import org.junit.Test;

public class ValidacaoAntifraudeControllerTest {

    ValidacaoAntifraudeController validacao = new ValidacaoAntifraudeController();

    @Test
    public void testValidaCPFCorreto(){
        Assert.assertTrue(validacao.validaCpf("711.410.580-06"));
    }

    @Test
    public void testValidaCPFIncorreto(){
        Assert.assertFalse(validacao.validaCpf("711.410.580-04"));
    }

    @Test
    public void testNomeCompletoCorreto(){
        Assert.assertTrue(validacao.validaNomeCompleto("Camila Mendes da Silva", "Vanessa Mendes"));
    }

    @Test
    public void testNomeCompletoIncorreto(){
        Assert.assertFalse(validacao.validaNomeCompleto("camila mendes da silva", "Vanessa Mendes"));
        Assert.assertFalse(validacao.validaNomeCompleto("Camila", "Vanessa Mendes"));
        Assert.assertFalse(validacao.validaNomeCompleto("Vanessa Mendes", "Vanessa Mendes"));

    }

    @Test
    public void testDatadeNascimentoCorreto(){
        Assert.assertTrue(validacao.validaDataNascimento("26/02/1999"));
    }

    @Test
    public void testDatadeNascimentoIncorreto(){
        Assert.assertFalse(validacao.validaDataNascimento("30/02/1999"));
        Assert.assertFalse(validacao.validaDataNascimento("31/04/1999"));
        Assert.assertFalse(validacao.validaDataNascimento("31/06/1999"));
        Assert.assertFalse(validacao.validaDataNascimento("31/09/1999"));
        Assert.assertFalse(validacao.validaDataNascimento("31/11/1999"));
        Assert.assertFalse(validacao.validaDataNascimento("30/02/2512"));
        Assert.assertFalse(validacao.validaDataNascimento("30/02/1801"));
    }

    @Test
    public void testTelefoneCorreto(){
        Assert.assertTrue(validacao.validaTelefone("(11)95520-4018"));
        Assert.assertTrue(validacao.validaTelefone("(11)05520-4018"));

    }

    @Test
    public void testTelefoneIncorreto(){
        Assert.assertFalse(validacao.validaTelefone("(11)454002-18922"));
        Assert.assertFalse(validacao.validaTelefone("(20)95520-4018"));
    }

    @Test
    public void testEnderecoCorreto() throws Exception {
        Assert.assertTrue(validacao.validaEndereco(mockEndereco()));
    }

    @Test
    public void testEnderecoIncorreto() throws Exception {
        Assert.assertFalse(validacao.validaEndereco(new Endereco()));
    }

    @Test
    public void testEmailCorreto(){
        Assert.assertTrue(validacao.validaEmail("nicolle@itau.com"));
    }

    @Test
    public void testEmailIncorreto(){
        Assert.assertFalse(validacao.validaEmail("nicolle@com"));
        Assert.assertFalse(validacao.validaEmail("nicolle.com"));
    }

    @Test
    public void testNomeMaeCorreto(){
        Assert.assertTrue(validacao.validaNomeMae("Camila Mendes da Silva", "Bruno Mendes"));
    }

    @Test
    public void testNomeMaeIncorreto(){
        Assert.assertFalse(validacao.validaNomeMae("Camila", "Bruno Mendes"));
        Assert.assertFalse(validacao.validaNomeMae("camila mendes da silva", "Bruno Mendes"));
        Assert.assertFalse(validacao.validaNomeMae("Camila Mendes", "Camila Mendes"));
    }


    private Endereco mockEndereco(){
        Endereco mockEndereco = new Endereco();

        mockEndereco.setCep("07601-115");
        mockEndereco.setUnidade("71");
        mockEndereco.setComplemento("N/A");
        mockEndereco.setLocalidade("Mairiporã");
        mockEndereco.setLogradouro("Rua Vinte e Quatro");
        mockEndereco.setUf("SP");
        mockEndereco.setBairro("Olho D'Água");

        return mockEndereco;
    }

}
