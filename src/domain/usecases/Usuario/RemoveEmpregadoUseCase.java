package domain.usecases.Usuario;

import domain.entities.Usuario.Empregado;
import domain.utils.EntityNotFoundException;

public class RemoveEmpregadoUseCase {
    private final EmpregadoDAO empregadoDAO;

    public RemoveEmpregadoUseCase(EmpregadoDAO empregadoDAO) {
        this.empregadoDAO = empregadoDAO;
    }
    public boolean remove(Integer id){
        if(id == null || empregadoDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Empregado não encontrado.");

        return empregadoDAO.deleteByKey(id);
    }

    public boolean remove(Empregado empregado){
        if(empregado == null || empregadoDAO.findOne(empregado.getId()).isEmpty())
            throw new EntityNotFoundException("Empregado não encontrado.");
        return empregadoDAO.delete(empregado);
    }
}
