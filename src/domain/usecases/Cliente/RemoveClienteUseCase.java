package domain.usecases.Cliente;

import domain.entities.Cliente.Cliente;
import domain.utils.EntityNotFoundException;

public class RemoveClienteUseCase {
    private final ClienteDAO clienteDAO;

    public RemoveClienteUseCase(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public boolean remove(Integer id){
        if(id == null || clienteDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Cliente não encontrado.");

        return clienteDAO.deleteByKey(id);
    }

    public boolean remove(Cliente cliente){
        if(cliente == null || clienteDAO.findOne(cliente.getId()).isEmpty())
            throw new EntityNotFoundException("Cliente não encontrado.");

        return clienteDAO.delete(cliente);
    }
}
