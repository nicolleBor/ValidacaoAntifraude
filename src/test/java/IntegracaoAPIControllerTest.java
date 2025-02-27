import desafio.conquista.setup.itau.controller.IntegracaoAPIController;
import desafio.conquista.setup.itau.models.Endereco;
import org.junit.Assert;
import org.junit.Test;

public class IntegracaoAPIControllerTest {

    @Test
    public void testBuscaCEPCorreto() throws Exception {
        Endereco endereco = IntegracaoAPIController.buscaCep("04344-902");

        Assert.assertNotNull(endereco);
        Assert.assertEquals(endereco.getCep(), "04344-902");
        Assert.assertEquals(endereco.getLogradouro(), "Praça Alfredo Egydio de Souza Aranha");
        Assert.assertEquals(endereco.getBairro(), "Parque Jabaquara");
        Assert.assertEquals(endereco.getLocalidade(), "São Paulo");
        Assert.assertEquals(endereco.getUf(), "SP");

    }

    @Test
    public void testBuscaCEPIncorreto() throws Exception {
        Endereco endereco = IntegracaoAPIController.buscaCep("99999-904");

        Assert.assertNotNull(endereco);
        Assert.assertNull(endereco.getCep());
    }

}
