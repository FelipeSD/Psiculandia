package domain.usecases.Peixe;

import domain.entities.Peixe.Peixe;

import java.util.List;
import java.util.Optional;

public class FindPeixeUseCase {
    private final PeixeDAO peixeDAO;

    public FindPeixeUseCase(PeixeDAO peixeDAO) {
        this.peixeDAO = peixeDAO;
    }
    public Optional<Peixe> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null.");
        return peixeDAO.findOne(id);
    }

    public List<Peixe> findAll() {
        return peixeDAO.findAll();
    }
}
