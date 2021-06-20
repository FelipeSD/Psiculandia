package domain.usecases.Fornecedor;

import domain.entities.Fornecedor.Fornecedor;

import java.util.List;
import java.util.Optional;

public class FindFornecedorUseCase {
    private final FornecedorDAO fornecedorDAO;

    public FindFornecedorUseCase(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
    }
    public Optional<Fornecedor> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null.");
        return fornecedorDAO.findOne(id);
    }

    public List<Fornecedor> findAll() {
        return fornecedorDAO.findAll();
    }
}
