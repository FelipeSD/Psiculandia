package application.main;

import application.repository.*;
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
import domain.usecases.Tanque.RegistrarCrescimentoEspecieUseCase;
import domain.usecases.Insumo.*;
import domain.usecases.Peixe.*;
import domain.usecases.Tanque.*;
import domain.usecases.Usuario.*;
import domain.usecases.Venda.CreateVendaUseCase;
import domain.usecases.Venda.FindVendaUseCase;
import domain.usecases.Venda.RemoveVendaUseCase;
import domain.usecases.Venda.UpdateVendaUseCase;
import domain.usecases.Venda.VendaDAO;
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

        // CRIANDO EMPREGADOS
        Empregado empregado1 = new Empregado("felpsd", "çlkjh");
        Administrador adm1 = new Administrador("adm", "123");
        createEmpregadoUseCase.insert(empregado1);
        createEmpregadoUseCase.insert(adm1);
        System.out.println("\n========= LISTANDO USUÁRIOS ==========");
        findEmpregadoUseCase.findAll().forEach(empregado -> System.out.println("usuario = " + empregado));

        // AUTENTICANDO EMPREGADOS (LOGAR E DESLOGAR)
        try {
            USUARIO_Sistema = loginEmpregadoUseCase.logar(empregado1.getUsername(), empregado1.getSenha());
            //USUARIO_Sistema = loginEmpregadoUseCase.deslogar();
        }catch (InvalidPasswordException e){
            System.out.println("e = " + e);
            return;
        }

        if(USUARIO_Sistema == null){
            System.out.println("Insira corretamente as credenciais para acessar o sistema.");
            return;
        }

        // CRIANDO PEIXES
        Peixe tilapia = new Peixe("tilápia", 200, "Ração Tilápia", 4, 20);
        Peixe atum = new Peixe("atum", 100, "Ração Atum", 6, 10);
        createPeixeUseCase.insert(tilapia);
        createPeixeUseCase.insert(atum);
        System.out.println("\n========= LISTANDO PEIXES ==========");
        findPeixeUseCase.findAll().forEach(peixe -> System.out.println("peixe = " + peixe));

        // CRIANDO TANQUES
        Tanque tanque1 = new Tanque("tilápia", 20);
        Tanque tanque2 = new Tanque("atum", 10);
        createTanqueUseCase.insert(tanque1);
        createTanqueUseCase.insert(tanque2);
        System.out.println("\n========= LISTANDO TANQUES ==========");
        findTanqueUseCase.findAll().forEach(tanque -> System.out.println("tanque = " + tanque));

        // CRIANDO CLIENTES
        Cliente supermercado_mar = new Cliente(
                "09.044.577/0001-90",
                "Supermercado Mar",
                "16992320932",
                "supermar@mercados.com"
        );
        createClienteUseCase.insert(supermercado_mar);
        System.out.println("\n========= LISTANDO CLIENTES ==========");
        findClienteUseCase.findAll().forEach(cliente -> System.out.println("cliente = " + cliente));

        // CRIANDO FORNECEDORES
        Fornecedor fornecedor1 = new Fornecedor("FORNE");
        createFornecedorUseCase.insert(fornecedor1);
        System.out.println("\n========= LISTANDO FORNECEDORES ==========");
        findFornecedorUseCase.findAll().forEach(fornecedor -> System.out.println("fornecedor = " + fornecedor));

        // CRIANDO ESTOQUE
        Estoque estoque = new Estoque();
        createEstoqueUseCase.insert(estoque);

        // CRIANDO INSUMO
        Insumo racao = new Insumo("Ração Tilápia", 160, 80, fornecedor1);
        createInsumoUseCase.insert(racao, estoque);
        System.out.println("\n========= LISTANDO INSUMOS ESTOQUE ==========");
        findEstoqueUseCase.findAll().forEach(estoqueItem -> {
            for(Insumo insumo : estoqueItem.listarInsumos()){
                System.out.println("insumo = " + insumo);
            }
        });

        // CRIANDO VENDA
        Venda venda1 = new Venda("tilápia", 123, 600);
        createVendaUseCase.insert(venda1);
        System.out.println("\n========= LISTANDO VENDAS ==========");
        findVendaUseCase.findAll().forEach(venda -> System.out.println("venda = " + venda));
        System.out.println("\n");

        // REGISTRANDO CRESCIMENTO SEMANAL DE ESPÉCIE
        registrarCrescimentoEspecieUseCase.registrar(tanque1, 290);
        try {
            Thread.sleep(2000);
            registrarCrescimentoEspecieUseCase.registrar(tanque1, 300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        registrarCrescimentoEspecieUseCase.registrar(tanque2, 100);
        System.out.println("\n========= LISTANDO HISTORICO DE CRESCIMENTO ==========");
        findTanqueUseCase.findAll().forEach(tanque -> {
            System.out.println("\n\t***Historico do tanque: " + tanque.getEspecieCriada() + "***");
            for(HistoricoSemanalCrescimento historico : tanque.getHistoricoSemanal()){
                System.out.println("historico.getDataLancada = " + historico.getDataLancada());
                System.out.println("historico.getPesoMedio() = " + historico.getPesoMedio());
            }
        });

        registrarAdministracaoDiariaRacaoUseCase.administrarRacao(tanque1, estoque);
        // registrarAdministracaoDiariaRacaoUseCase.administrarRacao(tanque1, estoque);
        previsaoVendaPeixeUseCase.preveVenda(tanque1);
        previsaoRepoeEstoqueUseCase.preveReposicao(tanque1, estoque);

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
