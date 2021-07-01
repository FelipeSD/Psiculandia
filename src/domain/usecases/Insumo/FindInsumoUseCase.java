package domain.usecases.Insumo;

import domain.entities.Insumo.Insumo;
import domain.usecases.Estoque.EstoqueDAO;

import java.util.List;
import java.util.Optional;

public class FindInsumoUseCase {
    private final InsumoDAO insumoDAO;
    private final EstoqueDAO estoqueDAO;

    public FindInsumoUseCase(InsumoDAO insumoDAO, EstoqueDAO estoqueDAO) {
        this.insumoDAO = insumoDAO;
        this.estoqueDAO = estoqueDAO;
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
