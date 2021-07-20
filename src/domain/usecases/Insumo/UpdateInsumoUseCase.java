package domain.usecases.Insumo;

import domain.entities.Estoque.Estoque;
import domain.entities.Insumo.Insumo;
import domain.usecases.Estoque.EstoqueDAO;
import domain.utils.EntityNotFoundException;

public class UpdateInsumoUseCase {
    private final InsumoDAO insumoDAO;

    public UpdateInsumoUseCase(InsumoDAO insumoDAO) {
        this.insumoDAO = insumoDAO;
    }

    public boolean update(Insumo insumo){
        int id = insumo.getId();
        if(insumoDAO.findOne(id).isEmpty()){
            throw new EntityNotFoundException("Insumo não encontrado.");
        }

        insumoDAO.update(insumo);
        return true;
    }
}
