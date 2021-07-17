package domain.entities.Cliente;

public class Cliente {
    private int id;
    private String cnpj;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(){

    }

    public Cliente(int id, String cnpj, String nome, String telefone, String email){
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Cliente(String cnpj, String nome, String telefone, String email){
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
