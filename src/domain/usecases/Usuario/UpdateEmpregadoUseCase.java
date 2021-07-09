package domain.usecases.Usuario;


import domain.entities.Usuario.Empregado;
import domain.entities.Usuario.UsuarioValidator;
import domain.utils.EntityNotFoundException;

public class UpdateEmpregadoUseCase {
    private final EmpregadoDAO empregadoDAO;
    public UpdateEmpregadoUseCase(EmpregadoDAO empregadoDAO) {
        this.empregadoDAO = empregadoDAO;
    }
    public boolean update(Empregado empregado){
        UsuarioValidator validator = new UsuarioValidator();
        if(!validator.validar(empregado)) return false;

        int id = empregado.getId();
        if(empregadoDAO.findOne(id).isEmpty()){
            throw new EntityNotFoundException("Empregado não encontrado");
        }

        empregadoDAO.update(empregado);
        return true;
    }
}
