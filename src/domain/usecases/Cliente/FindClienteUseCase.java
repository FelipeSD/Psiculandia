package domain.usecases.Cliente;

import domain.entities.Cliente.Cliente;

import java.util.List;
import java.util.Optional;

public class FindClienteUseCase {
    private final ClienteDAO clienteDAO;

    public FindClienteUseCase(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public Optional<Cliente> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null.");
        return clienteDAO.findOne(id);
    }

    public List<Cliente> findAll() {
        return clienteDAO.findAll();
    }
}
