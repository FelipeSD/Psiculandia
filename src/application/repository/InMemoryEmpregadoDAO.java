package application.repository;

import domain.entities.Usuario.Empregado;
import domain.usecases.Empregado.EmpregadoDAO;

import java.util.*;

public class InMemoryEmpregadoDAO implements EmpregadoDAO {
    private static final Map<Integer, Empregado> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Empregado empregado) {
        idCounter++;
        empregado.setId(idCounter);
        db.put(idCounter, empregado);
        return idCounter;
    }

    @Override
    public Optional<Empregado> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Empregado> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Empregado empregado) {
        Integer id = empregado.getId();
        if(db.containsKey(id)) {
            db.replace(id, empregado);
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
    public boolean delete(Empregado empregado) {
        return deleteByKey(empregado.getId());
    }
}
