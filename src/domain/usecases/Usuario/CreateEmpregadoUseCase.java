package domain.usecases.Usuario;

import domain.entities.Usuario.Empregado;
import domain.entities.Usuario.UsuarioValidator;

public class CreateEmpregadoUseCase {
    private final EmpregadoDAO empregadoDAO;

    public CreateEmpregadoUseCase(EmpregadoDAO empregadoDAO) {
        this.empregadoDAO = empregadoDAO;
    }

    public boolean insert(Empregado empregado){
        UsuarioValidator validator = new UsuarioValidator();
        if(!validator.validar(empregado)) return false;

        empregadoDAO.create(empregado);
        return true;
    }
}
