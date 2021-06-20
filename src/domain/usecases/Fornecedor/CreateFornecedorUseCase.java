package domain.usecases.Fornecedor;

import domain.entities.Fornecedor.Fornecedor;

public class CreateFornecedorUseCase {
    private final FornecedorDAO fornecedorDAO;

    public CreateFornecedorUseCase(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
    }

    public boolean insert(Fornecedor fornecedor){
        fornecedorDAO.create(fornecedor);
        return true;
    }
}
