package domain.entities.Fornecedor;

public class Fornecedor {
    private String cnpj;
    private String nome;
    private String enedereco;
    private String[] produtos;
    private String tempoEntrega;

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

    public String getEnedereco() {
        return enedereco;
    }

    public void setEnedereco(String enedereco) {
        this.enedereco = enedereco;
    }

    public String[] getProdutos() {
        return produtos;
    }

    public void setProdutos(String[] produtos) {
        this.produtos = produtos;
    }

    public String getTempoEntrega() {
        return tempoEntrega;
    }

    public void setTempoEntrega(String tempoEntrega) {
        this.tempoEntrega = tempoEntrega;
    }
}
