package domain.usecases.Empregado;

import domain.entities.Usuario.Empregado;
import domain.utils.EntityNotFoundException;

import java.awt.print.Book;

public class RemoveEmpregadoUseCase {
    private final EmpregadoDAO empregadoDAO;

    public RemoveEmpregadoUseCase(EmpregadoDAO empregadoDAO) {
        this.empregadoDAO = empregadoDAO;
    }
    public boolean remove(Integer id){
        if(id == null || empregadoDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Empregado não encontrado");

        return empregadoDAO.deleteByKey(id);
    }

    public boolean remove(Empregado empregado){
        if(empregado == null || empregadoDAO.findOne(empregado.getId()).isEmpty())
            throw new EntityNotFoundException("Book not found.");
        return empregadoDAO.delete(empregado);
    }
}
