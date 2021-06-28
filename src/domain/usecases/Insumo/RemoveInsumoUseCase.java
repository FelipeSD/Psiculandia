package domain.usecases.Insumo;

import domain.entities.Estoque.Estoque;
import domain.entities.Insumo.Insumo;
import domain.entities.Tanque.Tanque;
import domain.usecases.Estoque.EstoqueDAO;
import domain.usecases.Tanque.TanqueDAO;
import domain.utils.EntityNotFoundException;

public class RemoveInsumoUseCase {
    private final InsumoDAO insumoDAO;
    private final EstoqueDAO estoqueDAO;

    public RemoveInsumoUseCase(InsumoDAO insumoDAO, EstoqueDAO estoqueDAO) {
        this.insumoDAO = insumoDAO;
        this.estoqueDAO = estoqueDAO;
    }

    public boolean remove(Integer id){
        if(id == null || insumoDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Insumo não encontrado.");

        insumoDAO.deleteByKey(id);
        return true;
    }

    public boolean remove(Insumo insumo, Estoque estoque){
        if(insumo == null || insumoDAO.findOne(insumo.getId()).isEmpty())
            throw new EntityNotFoundException("Insumo não encontrado.");

        insumoDAO.delete(insumo);
        estoque.removerInsumo(insumo);
        estoqueDAO.update(estoque);
        return true;
    }
}
