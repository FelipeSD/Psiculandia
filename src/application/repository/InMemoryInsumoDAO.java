package application.repository;

import domain.entities.Insumo.Insumo;
import domain.usecases.Insumo.InsumoDAO;

import java.util.*;

public class InMemoryInsumoDAO implements InsumoDAO  {
    private static final Map<Integer, Insumo> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Insumo insumo) {
        idCounter++;
        insumo.setId(idCounter);
        db.put(idCounter, insumo);
        return idCounter;
    }

    @Override
    public Optional<Insumo> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Insumo> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Insumo insumo) {
        Integer id = insumo.getId();
        if(db.containsKey(id)) {
            db.replace(id, insumo);
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
    public boolean delete(Insumo insumo) {
        return deleteByKey(insumo.getId());
    }
}
