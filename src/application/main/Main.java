package application.main;

import application.repository.inmemory.*;
import application.view.WindowLoader;
import domain.entities.Usuario.Empregado;
import domain.usecases.Cliente.*;
import domain.usecases.Estoque.*;
import domain.usecases.Fornecedor.*;
import domain.usecases.Insumo.*;
import domain.usecases.Peixe.*;
import domain.usecases.Tanque.*;
import domain.usecases.Usuario.*;
import domain.usecases.Venda.*;

public class Main {
    public static CreateEmpregadoUseCase createEmpregadoUseCase;
    public static UpdateEmpregadoUseCase updateEmpregadoUseCase;
    public static RemoveEmpregadoUseCase removeEmpregadoUseCase;
    public static FindEmpregadoUseCase findEmpregadoUseCase;
    public static LoginEmpregadoUseCase loginEmpregadoUseCase;

    public static CreateTanqueUseCase createTanqueUseCase;
    public static UpdateTanqueUseCase updateTanqueUseCase;
    public static RemoveTanqueUseCase removeTanqueUseCase;
    public static FindTanqueUseCase findTanqueUseCase;
    public static RegistrarCrescimentoEspecieUseCase registrarCrescimentoEspecieUseCase;
    public static RegistrarAdministracaoDiariaRacaoUseCase registrarAdministracaoDiariaRacaoUseCase;
    public static PrevisaoVendaPeixeUseCase previsaoVendaPeixeUseCase;

    public static CreatePeixeUseCase createPeixeUseCase;
    public static UpdatePeixeUseCase updatePeixeUseCase;
    public static RemovePeixeUseCase removePeixeUseCase;
    public static FindPeixeUseCase findPeixeUseCase;

    public static CreateClienteUseCase createClienteUseCase;
    public static UpdateClienteUseCase updateClienteUseCase;
    public static RemoveClienteUseCase removeClienteUseCase;
    public static FindClienteUseCase findClienteUseCase;

    public static CreateFornecedorUseCase createFornecedorUseCase;
    public static UpdateFornecedorUseCase updateFornecedorUseCase;
    public static RemoveFornecedorUseCase removeFornecedorUseCase;
    public static FindFornecedorUseCase findFornecedorUseCase;

    public static CreateEstoqueUseCase createEstoqueUseCase;
    public static UpdateEstoqueUseCase updateEstoqueUseCase;
    public static RemoveEstoqueUseCase removeEstoqueUseCase;
    public static FindEstoqueUseCase findEstoqueUseCase;

    public static CreateInsumoUseCase createInsumoUseCase;
    public static UpdateInsumoUseCase updateInsumoUseCase;
    public static RemoveInsumoUseCase removeInsumoUseCase;
    public static FindInsumoUseCase findInsumoUseCase;
    public static PrevisaoRepoeEstoqueUseCase previsaoRepoeEstoqueUseCase;

    public static CreateVendaUseCase createVendaUseCase;
    public static UpdateVendaUseCase updateVendaUseCase;
    public static RemoveVendaUseCase removeVendaUseCase;
    public static FindVendaUseCase findVendaUseCase;

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
