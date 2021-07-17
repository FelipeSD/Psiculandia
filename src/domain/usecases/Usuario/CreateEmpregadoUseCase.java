package domain.usecases.Usuario;

import domain.entities.Usuario.Administrador;
import domain.entities.Usuario.Empregado;
import domain.entities.Usuario.UsuarioValidator;

public class CreateEmpregadoUseCase {
    private final EmpregadoDAO empregadoDAO;

    public CreateEmpregadoUseCase(EmpregadoDAO empregadoDAO) {
        this.empregadoDAO = empregadoDAO;
    }

    public boolean insert(String tipo, String username, String password){
        Empregado operador;

        if(tipo.equals("empregado")){
            operador = new Empregado(username, password);
        }else{
            operador = new Administrador(username, password);
        }

        UsuarioValidator validator = new UsuarioValidator();

        if(!validator.validar(operador)) return false;

        empregadoDAO.create(operador);
        return true;
    }
}
