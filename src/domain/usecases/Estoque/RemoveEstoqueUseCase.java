package domain.usecases.Estoque;

import domain.entities.Estoque.Estoque;
import domain.utils.EntityNotFoundException;

public class RemoveEstoqueUseCase {
    private final EstoqueDAO estoqueDAO;

    public RemoveEstoqueUseCase(EstoqueDAO estoqueDAO) {
        this.estoqueDAO = estoqueDAO;
    }

    public boolean remove(Integer id){
        if(id == null || estoqueDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Estoque não encontrado.");

        return estoqueDAO.deleteByKey(id);
    }

    public boolean remove(Estoque estoque){
        if(estoque == null || estoqueDAO.findOne(estoque.getId()).isEmpty())
            throw new EntityNotFoundException("Estoque não encontrado.");

        return estoqueDAO.delete(estoque);
    }
}
