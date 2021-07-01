package application.repository.inmemory;

import domain.entities.Peixe.Peixe;
import domain.usecases.Peixe.PeixeDAO;

import java.util.*;

public class InMemoryPeixeDAO implements PeixeDAO {
    private static final Map<Integer, Peixe> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Peixe peixe) {
        idCounter++;
        peixe.setId(idCounter);
        db.put(idCounter, peixe);
        return idCounter;
    }

    @Override
    public Optional<Peixe> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public Optional<Peixe> findByEspecie(String especie) {
        for(Map.Entry<Integer, Peixe> peixe : db.entrySet()){
            if(peixe.getValue().getNome().equals(especie)){
                return Optional.of(db.get(peixe.getKey()));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Peixe> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Peixe peixe) {
        Integer id = peixe.getId();
        if(db.containsKey(id)) {
            db.replace(id, peixe);
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
    public boolean delete(Peixe peixe) {
        return deleteByKey(peixe.getId());
    }
}
