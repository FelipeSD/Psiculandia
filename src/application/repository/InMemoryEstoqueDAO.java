package application.repository;

import domain.entities.Estoque.Estoque;
import domain.usecases.Estoque.EstoqueDAO;

import java.util.*;

public class InMemoryEstoqueDAO implements EstoqueDAO {
    private static final Map<Integer, Estoque> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Estoque estoque) {
        idCounter++;
        estoque.setId(idCounter);
        db.put(idCounter, estoque);
        return idCounter;
    }

    @Override
    public Optional<Estoque> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Estoque> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Estoque estoque) {
        Integer id = estoque.getId();
        if(db.containsKey(id)) {
            db.replace(id, estoque);
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
    public boolean delete(Estoque estoque) {
        return deleteByKey(estoque.getId());
    }
}
