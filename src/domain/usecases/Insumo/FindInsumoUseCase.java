package domain.usecases.Insumo;

import domain.entities.Insumo.Insumo;
import domain.entities.Usuario.Empregado;
import domain.usecases.Usuario.EmpregadoDAO;

import java.util.List;
import java.util.Optional;

public class FindInsumoUseCase {
    private final InsumoDAO insumoDAO;

    public FindInsumoUseCase(InsumoDAO insumoDAO) {
        this.insumoDAO = insumoDAO;
    }

    public Optional<Insumo> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null.");

        return insumoDAO.findOne(id);
    }

    public List<Insumo> findAll() {
        return insumoDAO.findAll();
    }
}
