package application.repository;

import domain.entities.Tanque.Tanque;
import domain.usecases.Tanque.TanqueDAO;

import java.util.*;

public class InMemoryTanqueDAO implements TanqueDAO {
    private static final Map<Integer, Tanque> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Tanque tanque) {
        idCounter++;
        tanque.setId(idCounter);
        db.put(idCounter, tanque);
        return idCounter;
    }

    @Override
    public Optional<Tanque> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Tanque> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Tanque tanque) {
        Integer id = tanque.getId();
        if(db.containsKey(id)) {
            db.replace(id, tanque);
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
    public boolean delete(Tanque tanque) {
        return deleteByKey(tanque.getId());
    }
}
