package domain.usecases.Usuario;

import domain.entities.Usuario.Empregado;
import domain.entities.Usuario.UsuarioValidator;
import domain.utils.EntityNotFoundException;
import domain.utils.InvalidPasswordException;

import java.util.Optional;

public class LoginEmpregadoUseCase {
    private final EmpregadoDAO empregadoDAO;

    public LoginEmpregadoUseCase(EmpregadoDAO empregadoDAO) {
        this.empregadoDAO = empregadoDAO;
    }

    public Empregado logar(String username, String password) throws InvalidPasswordException {
        Empregado empregadoValidar = new Empregado(username, password);
        UsuarioValidator validator = new UsuarioValidator();

        if(!validator.validar(empregadoValidar)) return null;

        Optional<Empregado> empregadoEncontrado = empregadoDAO.findByUsername(empregadoValidar.getUsername());

        if(empregadoEncontrado.isEmpty())
            throw new EntityNotFoundException("Empregado não encontrado");

        if(!empregadoEncontrado.get().getSenha().equals(empregadoValidar.getSenha()))
            throw new InvalidPasswordException("Senha inválida");

        return empregadoEncontrado.get();
    }


    public Empregado deslogar() {
        return null;
    }
}
