package application.repository;

import domain.entities.Cliente.Cliente;
import domain.usecases.Cliente.ClienteDAO;

import java.util.*;

public class InMemoryClienteDAO implements ClienteDAO {
    private static final Map<Integer, Cliente> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Cliente cliente) {
        idCounter++;
        cliente.setId(idCounter);
        db.put(idCounter, cliente);
        return idCounter;
    }

    @Override
    public Optional<Cliente> findOne(Integer key) {
        if (db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Cliente cliente) {
        Integer id = cliente.getId();
        if(db.containsKey(id)) {
            db.replace(id, cliente);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        if(db.containsKey(key)){
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Cliente cliente) {
        return deleteByKey(cliente.getId());
    }
}
