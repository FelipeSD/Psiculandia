package domain.usecases.Cliente;

import domain.entities.Cliente.Cliente;
import domain.utils.EntityNotFoundException;

public class UpdateClienteUseCase {
    private final ClienteDAO clienteDAO;

    public UpdateClienteUseCase(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public boolean update(Cliente cliente){
        int id = cliente.getId();
        if(clienteDAO.findOne(id).isEmpty()){
            throw new EntityNotFoundException("Cliente não encontrado.");
        }

        clienteDAO.update(cliente);
        return true;
    }
}
