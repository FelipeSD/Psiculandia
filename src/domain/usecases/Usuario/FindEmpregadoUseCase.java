package domain.usecases.Usuario;

import domain.entities.Usuario.Empregado;

import java.util.List;
import java.util.Optional;

public class FindEmpregadoUseCase {
    private final EmpregadoDAO empregadoDAO;

    public FindEmpregadoUseCase(EmpregadoDAO empregadoDAO) {
        this.empregadoDAO = empregadoDAO;
    }

    public Optional<Empregado> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null.");
        return empregadoDAO.findOne(id);
    }

    public List<Empregado> findAll() {
        return empregadoDAO.findAll();
    }
}
