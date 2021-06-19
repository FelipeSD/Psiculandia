package domain.entities.Cliente;

public class ClienteValidator {
    public boolean validar(Cliente cliente){
        return (
                validaCNPJ(cliente.getCnpj()) && validaNome(cliente.getNome())
        );
    }

    public boolean validaCNPJ(String cnpj){
        return cnpj.length() == 14;
    }

    public boolean validaNome(String nome){
        return nome.length() != 0;
    }
}
