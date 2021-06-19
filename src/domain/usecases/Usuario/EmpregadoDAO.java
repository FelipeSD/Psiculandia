package domain.usecases.Usuario;

import domain.entities.Usuario.Empregado;
import domain.utils.DAO;

import java.util.Optional;

public interface EmpregadoDAO extends DAO<Empregado, Integer> {
    Optional<Empregado> findByUsername(String username);
}