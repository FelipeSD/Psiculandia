package application.main;

import application.repository.inmemory.*;
import application.view.WindowLoader;
import domain.entities.Cliente.Cliente;
import domain.entities.Estoque.Estoque;
import domain.entities.Fornecedor.Fornecedor;
import domain.entities.Insumo.Insumo;
import domain.entities.Peixe.Peixe;
import domain.entities.Tanque.HistoricoSemanalCrescimento;
import domain.entities.Tanque.Tanque;
import domain.entities.Usuario.Administrador;
import domain.entities.Usuario.Empregado;
import domain.entities.Venda.Venda;
import domain.usecases.Cliente.*;
import domain.usecases.Estoque.*;
import domain.usecases.Fornecedor.*;
import domain.usecases.Insumo.*;
import domain.usecases.Peixe.*;
import domain.usecases.Tanque.*;
import domain.usecases.Usuario.*;
import domain.usecases.Venda.*;
import domain.utils.InvalidPasswordException;

public class Main {
    private static CreateEmpregadoUseCase createEmpregadoUseCase;
    private static UpdateEmpregadoUseCase updateEmpregadoUseCase;
    private static RemoveEmpregadoUseCase removeEmpregadoUseCase;
    private static FindEmpregadoUseCase findEmpregadoUseCase;
    private static LoginEmpregadoUseCase loginEmpregadoUseCase;

    private static CreateTanqueUseCase createTanqueUseCase;
    private static UpdateTanqueUseCase updateTanqueUseCase;
    private static RemoveTanqueUseCase removeTanqueUseCase;
    private static FindTanqueUseCase findTanqueUseCase;
    private static RegistrarCrescimentoEspecieUseCase registrarCrescimentoEspecieUseCase;
    private static RegistrarAdministracaoDiariaRacaoUseCase registrarAdministracaoDiariaRacaoUseCase;
    private static PrevisaoVendaPeixeUseCase previsaoVendaPeixeUseCase;

    private static CreatePeixeUseCase createPeixeUseCase;
    private static UpdatePeixeUseCase updatePeixeUseCase;
    private static RemovePeixeUseCase removePeixeUseCase;
    private static FindPeixeUseCase findPeixeUseCase;

    private static CreateClienteUseCase createClienteUseCase;
    private static UpdateClienteUseCase updateClienteUseCase;
    private static RemoveClienteUseCase removeClienteUseCase;
    private static FindClienteUseCase findClienteUseCase;

    private static CreateFornecedorUseCase createFornecedorUseCase;
    private static UpdateFornecedorUseCase updateFornecedorUseCase;
    private static RemoveFornecedorUseCase removeFornecedorUseCase;
    private static FindFornecedorUseCase findFornecedorUseCase;

    private static CreateEstoqueUseCase createEstoqueUseCase;
    private static UpdateEstoqueUseCase updateEstoqueUseCase;
    private static RemoveEstoqueUseCase removeEstoqueUseCase;
    private static FindEstoqueUseCase findEstoqueUseCase;

    private static CreateInsumoUseCase createInsumoUseCase;
    private static UpdateInsumoUseCase updateInsumoUseCase;
    private static RemoveInsumoUseCase removeInsumoUseCase;
    private static FindInsumoUseCase findInsumoUseCase;
    private static PrevisaoRepoeEstoqueUseCase previsaoRepoeEstoqueUseCase;

    private static CreateVendaUseCase createVendaUseCase;
    private static UpdateVendaUseCase updateVendaUseCase;
    private static RemoveVendaUseCase removeVendaUseCase;
    private static FindVendaUseCase findVendaUseCase;

    private static Empregado USUARIO_Sistema;

    public static void main(String[] args) {
        configureInjection();
        WindowLoader.main(args);

    }

    private static void configureInjection() {
        EmpregadoDAO empregadoDAO = new InMemoryEmpregadoDAO();
        createEmpregadoUseCase = new CreateEmpregadoUseCase(empregadoDAO);
        updateEmpregadoUseCase = new UpdateEmpregadoUseCase(empregadoDAO);
        removeEmpregadoUseCase = new RemoveEmpregadoUseCase(empregadoDAO);
        findEmpregadoUseCase = new FindEmpregadoUseCase(empregadoDAO);
        loginEmpregadoUseCase = new LoginEmpregadoUseCase(empregadoDAO);

        PeixeDAO peixeDAO = new InMemoryPeixeDAO();
        createPeixeUseCase = new CreatePeixeUseCase(peixeDAO);
        updatePeixeUseCase = new UpdatePeixeUseCase(peixeDAO);
        removePeixeUseCase = new RemovePeixeUseCase(peixeDAO);
        findPeixeUseCase = new FindPeixeUseCase(peixeDAO);

        TanqueDAO tanqueDAO = new InMemoryTanqueDAO();
        createTanqueUseCase = new CreateTanqueUseCase(tanqueDAO);
        updateTanqueUseCase = new UpdateTanqueUseCase(tanqueDAO);
        removeTanqueUseCase = new RemoveTanqueUseCase(tanqueDAO);
        findTanqueUseCase = new FindTanqueUseCase(tanqueDAO);
        //relacionados
        registrarCrescimentoEspecieUseCase = new RegistrarCrescimentoEspecieUseCase(tanqueDAO);
        registrarAdministracaoDiariaRacaoUseCase = new RegistrarAdministracaoDiariaRacaoUseCase(tanqueDAO);
        previsaoVendaPeixeUseCase = new PrevisaoVendaPeixeUseCase(tanqueDAO, peixeDAO);

        ClienteDAO clienteDAO = new InMemoryClienteDAO();
        createClienteUseCase = new CreateClienteUseCase(clienteDAO);
        updateClienteUseCase = new UpdateClienteUseCase(clienteDAO);
        removeClienteUseCase = new RemoveClienteUseCase(clienteDAO);
        findClienteUseCase = new FindClienteUseCase(clienteDAO);

        FornecedorDAO fornecedorDAO = new InMemoryFornecedorDAO();
        createFornecedorUseCase = new CreateFornecedorUseCase(fornecedorDAO);
        updateFornecedorUseCase = new UpdateFornecedorUseCase(fornecedorDAO);
        removeFornecedorUseCase = new RemoveFornecedorUseCase(fornecedorDAO);
        findFornecedorUseCase = new FindFornecedorUseCase(fornecedorDAO);

        EstoqueDAO estoqueDAO = new InMemoryEstoqueDAO();
        createEstoqueUseCase = new CreateEstoqueUseCase(estoqueDAO);
        updateEstoqueUseCase = new UpdateEstoqueUseCase(estoqueDAO);
        removeEstoqueUseCase = new RemoveEstoqueUseCase(estoqueDAO);
        findEstoqueUseCase = new FindEstoqueUseCase(estoqueDAO);

        InsumoDAO insumoDAO = new InMemoryInsumoDAO();
        createInsumoUseCase = new CreateInsumoUseCase(insumoDAO, estoqueDAO);
        updateInsumoUseCase = new UpdateInsumoUseCase(insumoDAO, estoqueDAO);
        removeInsumoUseCase = new RemoveInsumoUseCase(insumoDAO, estoqueDAO);
        findInsumoUseCase = new FindInsumoUseCase(insumoDAO, estoqueDAO);
        previsaoRepoeEstoqueUseCase = new PrevisaoRepoeEstoqueUseCase(peixeDAO, tanqueDAO);

        VendaDAO vendaDAO = new InMemoryVendaDAO();
        createVendaUseCase = new CreateVendaUseCase(vendaDAO);
        updateVendaUseCase = new UpdateVendaUseCase(vendaDAO);
        removeVendaUseCase = new RemoveVendaUseCase(vendaDAO);
        findVendaUseCase = new FindVendaUseCase(vendaDAO);
    }
}
