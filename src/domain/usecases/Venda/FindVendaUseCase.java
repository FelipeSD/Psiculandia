package domain.usecases.Venda;

import domain.entities.Venda.Venda;

import java.util.List;
import java.util.Optional;

public class FindVendaUseCase {
    private final VendaDAO vendaDAO;

    public FindVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public Optional<Venda> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null.");
        return vendaDAO.findOne(id);
    }

    public List<Venda> findAll() {
        return vendaDAO.findAll();
    }
}
