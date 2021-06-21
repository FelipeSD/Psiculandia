package domain.usecases.Cliente;

import domain.entities.Cliente.Cliente;

public class CreateClienteUseCase {
    private final ClienteDAO clienteDAO;

    public CreateClienteUseCase(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public boolean insert(Cliente cliente){
        clienteDAO.create(cliente);
        return true;
    }
}
