package domain.usecases.Insumo;

import domain.entities.Estoque.Estoque;
import domain.entities.Insumo.Insumo;
import domain.usecases.Estoque.EstoqueDAO;
import domain.utils.EntityNotFoundException;

public class UpdateInsumoUseCase {
    private final InsumoDAO insumoDAO;
    private final EstoqueDAO estoqueDAO;

    public UpdateInsumoUseCase(InsumoDAO insumoDAO, EstoqueDAO estoqueDAO) {
        this.insumoDAO = insumoDAO;
        this.estoqueDAO = estoqueDAO;
    }

    public boolean update(Insumo insumo, Estoque estoque){
        int id = insumo.getId();
        if(insumoDAO.findOne(id).isEmpty()){
            throw new EntityNotFoundException("Insumo não encontrado.");
        }

        insumoDAO.update(insumo);
        estoque.atualizarInsumo(insumo);
        estoqueDAO.update(estoque);
        return true;
    }
}
