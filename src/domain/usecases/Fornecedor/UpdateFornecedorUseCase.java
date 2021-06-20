package domain.usecases.Fornecedor;

import domain.entities.Fornecedor.Fornecedor;
import domain.utils.EntityNotFoundException;

public class UpdateFornecedorUseCase {
    private final FornecedorDAO fornecedorDAO;

    public UpdateFornecedorUseCase(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
    }

    public boolean update(Fornecedor fornecedor){
        int id = fornecedor.getId();
        if(fornecedorDAO.findOne(id).isEmpty()){
            throw new EntityNotFoundException("Tanque não encontrado.");
        }

        fornecedorDAO.update(fornecedor);
        return true;
    }
}
