package domain.usecases.Fornecedor;

import domain.entities.Fornecedor.Fornecedor;
import domain.utils.EntityNotFoundException;

public class RemoveFornecedorUseCase {
    private final FornecedorDAO fornecedorDAO;

    public RemoveFornecedorUseCase(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
    }

    public boolean remove(Integer id){
        if(id == null || fornecedorDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Fornecedor não encontrado.");

        return fornecedorDAO.deleteByKey(id);
    }

    public boolean remove(Fornecedor fornecedor){
        if(fornecedor == null || fornecedorDAO.findOne(fornecedor.getId()).isEmpty())
            throw new EntityNotFoundException("Fornecedor não encontrado.");

        return fornecedorDAO.delete(fornecedor);
    }
}
