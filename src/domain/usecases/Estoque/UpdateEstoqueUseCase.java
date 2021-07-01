package domain.usecases.Estoque;

import domain.entities.Estoque.Estoque;
import domain.utils.EntityNotFoundException;

public class UpdateEstoqueUseCase {
    private final EstoqueDAO estoqueDAO;

    public UpdateEstoqueUseCase(EstoqueDAO estoqueDAO) {
        this.estoqueDAO = estoqueDAO;
    }

    public boolean update(Estoque estoque){
        int id = estoque.getId();
        if(estoqueDAO.findOne(id).isEmpty()){
            throw new EntityNotFoundException("Estoque não encontrado.");
        }

        estoqueDAO.update(estoque);
        return true;
    }
}
