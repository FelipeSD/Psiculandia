package domain.usecases.Estoque;

import domain.entities.Estoque.Estoque;

public class CreateEstoqueUseCase {
    private final EstoqueDAO estoqueDAO;

    public CreateEstoqueUseCase(EstoqueDAO estoqueDAO) {
        this.estoqueDAO = estoqueDAO;
    }

    public boolean insert(Estoque estoque){
        estoqueDAO.create(estoque);
        return true;
    }
}
