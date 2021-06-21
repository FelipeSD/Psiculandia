package domain.usecases.Estoque;

import domain.entities.Estoque.Estoque;

import java.util.List;
import java.util.Optional;

public class FindEstoqueUseCase {
    private final EstoqueDAO estoqueDAO;

    public FindEstoqueUseCase(EstoqueDAO estoqueDAO) {
        this.estoqueDAO = estoqueDAO;
    }

    public Optional<Estoque> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null.");
        return estoqueDAO.findOne(id);
    }

    public List<Estoque> findAll() {
        return estoqueDAO.findAll();
    }
}
