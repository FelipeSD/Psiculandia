package domain.usecases.Venda;

import domain.entities.Venda.Venda;

public class CreateVendaUseCase {
    private final VendaDAO vendaDAO;

    public CreateVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public boolean insert(Venda venda){
        vendaDAO.create(venda);
        return true;
    }
}
