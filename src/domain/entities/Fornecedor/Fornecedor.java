package domain.entities.Fornecedor;

public class Fornecedor {
    private int id;
    private String cnpj;
    private String nome;
    private String endereco;
    private String[] produtos;
    private int tempoEntrega;

    public Fornecedor() {
    }

    public Fornecedor(String nome) {
        this.nome = nome;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String[] getProdutos() {
        return produtos;
    }

    public void setProdutos(String[] produtos) {
        this.produtos = produtos;
    }

    public int getTempoEntrega() {
        return tempoEntrega;
    }

    public void setTempoEntrega(int tempoEntrega) {
        this.tempoEntrega = tempoEntrega;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
