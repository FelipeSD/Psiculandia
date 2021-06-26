package domain.usecases.Peixe;

import domain.entities.Peixe.Peixe;
import domain.entities.Usuario.Empregado;
import domain.utils.DAO;

import java.util.Optional;

public interface PeixeDAO extends DAO<Peixe, Integer> {
    Optional<Peixe> findByEspecie(String especie);
}
