package domain.usecases.Insumo;

import domain.entities.Insumo.Insumo;
import domain.entities.Tanque.Tanque;
import domain.usecases.Tanque.TanqueDAO;
import domain.utils.EntityNotFoundException;

public class RemoveInsumoUseCase {
    private final InsumoDAO insumoDAO;

    public RemoveInsumoUseCase(InsumoDAO insumoDAO) {
        this.insumoDAO = insumoDAO;
    }

    public boolean remove(Integer id){
        if(id == null || insumoDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Insumo não encontrado.");

        return insumoDAO.deleteByKey(id);
    }

    public boolean remove(Insumo insumo){
        if(insumo == null || insumoDAO.findOne(insumo.getId()).isEmpty())
            throw new EntityNotFoundException("Insumo não encontrado.");
        return insumoDAO.delete(insumo);
    }
}
