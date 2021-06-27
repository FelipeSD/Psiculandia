package domain.usecases.Venda;

import domain.entities.Venda.Venda;
import domain.utils.EntityNotFoundException;

public class UpdateVendaUseCase {
    private VendaDAO vendaDAO;
    public UpdateVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }
    public boolean update(Venda venda){
        int id = venda.getId();
        if(vendaDAO.findOne(id).isEmpty()){
            throw new EntityNotFoundException("Empregado não encontrado");
        }

        vendaDAO.update(venda);
        return true;
    }
}
