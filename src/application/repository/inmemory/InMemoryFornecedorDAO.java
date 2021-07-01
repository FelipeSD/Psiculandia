package application.repository.inmemory;

import domain.entities.Fornecedor.Fornecedor;
import domain.usecases.Fornecedor.FornecedorDAO;

import java.util.*;

public class InMemoryFornecedorDAO implements FornecedorDAO {
    private static final Map<Integer, Fornecedor> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Fornecedor fornecedor) {
        idCounter++;
        fornecedor.setId(idCounter);
        db.put(idCounter, fornecedor);
        return idCounter;
    }

    @Override
    public Optional<Fornecedor> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Fornecedor> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Fornecedor fornecedor) {
        Integer id = fornecedor.getId();
        if(db.containsKey(id)) {
            db.replace(id, fornecedor);
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
    public boolean delete(Fornecedor fornecedor) {
        return deleteByKey(fornecedor.getId());
    }
}
