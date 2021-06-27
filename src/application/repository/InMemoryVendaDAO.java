package application.repository;

import domain.entities.Venda.Venda;
import domain.usecases.Venda.VendaDAO;

import java.util.*;

public class InMemoryVendaDAO implements VendaDAO {
    private static final Map<Integer, Venda> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Venda venda) {
        idCounter++;
        venda.setId(idCounter);
        db.put(idCounter, venda);
        return idCounter;
    }

    @Override
    public Optional<Venda> findOne(Integer key) {
        if (db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Venda> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Venda venda) {
        Integer id = venda.getId();
        if(db.containsKey(id)) {
            db.replace(id, venda);
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
    public boolean delete(Venda venda) {
        return deleteByKey(venda.getId());
    }
}
