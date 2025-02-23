package desafio.conquista.setup.itau.models;

public class Cliente {
    private String cpf;
    private String nomeCompleto;
    private String telefone;
    private String email;
    private String dataNascimento;
    private String cep;
    private String nomeMae;

    public Cliente(String cpf, String nomeCompleto, String telefone, String email, String dataNascimento, String cep, String nomeMae){
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.nomeMae = nomeMae;
    }

    public Cliente(String cpf) {
        this.cpf = cpf;
    }

    public Cliente() {
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getNomeCompleto(){
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto){
        this.nomeCompleto = nomeCompleto;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getDataNascimento(){
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public String getCep(){
        return cep;
    }

    public void setCep(String cep){
        this.cep = cep;
    }

    public String getNomeMae(){
        return nomeMae;
    }

    public void setNomeMae(String nomeMae){
        this.nomeMae = nomeMae;
    }

}
