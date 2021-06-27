package domain.usecases.Venda;

import domain.entities.Venda.Venda;
import domain.utils.EntityNotFoundException;

public class RemoveVendaUseCase {
    private final VendaDAO vendaDAO;

    public RemoveVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }
    public boolean remove(Integer id){
        if(id == null || vendaDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Venda não encontrada.");

        return vendaDAO.deleteByKey(id);
    }

    public boolean remove(Venda venda){
        if(venda == null || vendaDAO.findOne(venda.getId()).isEmpty())
            throw new EntityNotFoundException("Empregado não encontrado.");
        return vendaDAO.delete(venda);
    }
}
