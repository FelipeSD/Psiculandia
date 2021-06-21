package domain.usecases.Insumo;

import domain.entities.Insumo.Insumo;

public class CreateInsumoUseCase {
    private final InsumoDAO insumoDAO;

    public CreateInsumoUseCase(InsumoDAO insumoDAO) {
        this.insumoDAO = insumoDAO;
    }

    public boolean insert(Insumo insumo){
        insumoDAO.create(insumo);
        return true;
    }
}
