package domain.usecases.Insumo;

import domain.entities.Insumo.Insumo;

public class CreateInsumoUseCase {
    private final InsumoDAO insumoDAO;
//    private final EstoqueDAO estoqueDAO;

    public CreateInsumoUseCase(InsumoDAO insumoDAO
//                               EstoqueDAO estoqueDAO
    ) {
        this.insumoDAO = insumoDAO;
//        this.estoqueDAO = estoqueDAO;
    }

    public boolean insert(Insumo insumo
//                          Estoque estoque
    ){
        insumoDAO.create(insumo);
//        estoque.adicionarInsumo(insumo);
//        estoqueDAO.update(estoque);
        return true;
    }
}
