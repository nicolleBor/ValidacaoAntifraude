package desafio.conquista.setup.itau.models;

public class Cliente {
    private String cpf;
    private String nomeCompleto;
    private String telefone;
    private String email;
    private String dataNascimento;
    private Endereco endereco;
    private String nomeMae;

    public Cliente(String cpf, String nomeCompleto, String telefone, String email, String dataNascimento, Endereco endereco, String nomeMae){
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
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

    public Endereco getEndereco(){
        return endereco;
    }

    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }

    public String getNomeMae(){
        return nomeMae;
    }

    public void setNomeMae(String nomeMae){
        this.nomeMae = nomeMae;
    }

}
