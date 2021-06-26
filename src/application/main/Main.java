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
import domain.usecases.Cliente.*;
import domain.usecases.Estoque.*;
import domain.usecases.Fornecedor.*;
import domain.usecases.HistoricoSemanalCrescimento.RegistrarCrescimentoEspecieUseCase;
import domain.usecases.Insumo.*;
import domain.usecases.Peixe.*;
import domain.usecases.Tanque.*;
import domain.usecases.Usuario.*;
import domain.utils.InvalidPasswordException;

//https://github1s.com/lucas-ifsp/symploteca/blob/HEAD/src/main/java/br/edu/ifsp/application/main/Main.java
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

    public static void main(String[] args) {
        configureInjection();

        // CRIANDO EMPREGADOS
        Empregado empregado1 = new Empregado("felpsd", "çlkjh");
        Administrador adm1 = new Administrador("adm", "123");
        createEmpregadoUseCase.insert(empregado1);
        createEmpregadoUseCase.insert(adm1);
        findEmpregadoUseCase.findAll().forEach(empregado -> System.out.println("usuario = " + empregado));

        // AUTENTICANDO EMPREGADOS (LOGAR E DESLOGAR)
        try {
            loginEmpregadoUseCase.logar(empregado1.getUsername(), empregado1.getSenha());
            System.out.println("Usuário está logado?:" + empregado1.estaLogado());

            loginEmpregadoUseCase.deslogar(empregado1);
            System.out.println("Usuário está logado?:" + empregado1.estaLogado());
        }catch (InvalidPasswordException e){
            System.out.println("e = " + e);
        }

        // CRIANDO TANQUES
        Tanque tanque1 = new Tanque("tilápia");
        Tanque tanque2 = new Tanque("atum");
        createTanqueUseCase.insert(tanque1);
        createTanqueUseCase.insert(tanque2);
        findTanqueUseCase.findAll().forEach(tanque -> System.out.println("tanque = " + tanque));

        // CRIANDO PEIXES
        Peixe tilapia = new Peixe("tilápia");
        Peixe atum = new Peixe("atum");
        createPeixeUseCase.insert(tilapia);
        createPeixeUseCase.insert(atum);
        findPeixeUseCase.findAll().forEach(peixe -> System.out.println("peixe = " + peixe));

        // CRIANDO CLIENTES
        Cliente supermercado_mar = new Cliente(
                "09.044.577/0001-90",
                "Supermercado Mar",
                "16992320932",
                "supermar@mercados.com"
        );
        createClienteUseCase.insert(supermercado_mar);
        findClienteUseCase.findAll().forEach(cliente -> System.out.println("cliente = " + cliente));

        // CRIANDO FORNECEDORES
        Fornecedor fornecedor1 = new Fornecedor("FORNE");
        createFornecedorUseCase.insert(fornecedor1);
        findFornecedorUseCase.findAll().forEach(fornecedor -> System.out.println("fornecedor = " + fornecedor));

        // CRIANDO ESTOQUE
        Estoque estoque = new Estoque();
        createEstoqueUseCase.insert(estoque);
        findEstoqueUseCase.findAll().forEach(estoqueItem -> System.out.println("estoque = " + estoqueItem));


        // CRIANDO INSUMO
        Insumo racao = new Insumo();
        createInsumoUseCase.insert(racao);
        findInsumoUseCase.findAll().forEach(insumo -> System.out.println("insumo = " + insumo));
        
        // REGISTRANDO CRESCIMENTO SEMANAL DE ESPÉCIE
        registrarCrescimentoEspecieUseCase.registrar(tanque1, 290);
        registrarCrescimentoEspecieUseCase.registrar(tanque2, 100);
        findTanqueUseCase.findAll().forEach(tanque -> {
            System.out.println("Historico do tanque: " + tanque.getEspecieCriada());
            for(HistoricoSemanalCrescimento historico : tanque.getHistoricoSemanal()){
                System.out.println("historico.getDataLancada = " + historico.getDataLancada());
                System.out.println("historico.getPesoMedio() = " + historico.getPesoMedio());
            }
        });

    }

    private static void configureInjection() {
        EmpregadoDAO empregadoDAO = new InMemoryEmpregadoDAO();
        createEmpregadoUseCase = new CreateEmpregadoUseCase(empregadoDAO);
        updateEmpregadoUseCase = new UpdateEmpregadoUseCase(empregadoDAO);
        removeEmpregadoUseCase = new RemoveEmpregadoUseCase(empregadoDAO);
        findEmpregadoUseCase = new FindEmpregadoUseCase(empregadoDAO);
        loginEmpregadoUseCase = new LoginEmpregadoUseCase(empregadoDAO);

        TanqueDAO tanqueDAO = new InMemoryTanqueDAO();
        createTanqueUseCase = new CreateTanqueUseCase(tanqueDAO);
        updateTanqueUseCase = new UpdateTanqueUseCase(tanqueDAO);
        removeTanqueUseCase = new RemoveTanqueUseCase(tanqueDAO);
        findTanqueUseCase = new FindTanqueUseCase(tanqueDAO);
        //relacionados
        registrarCrescimentoEspecieUseCase = new RegistrarCrescimentoEspecieUseCase(tanqueDAO);

        PeixeDAO peixeDAO = new InMemoryPeixeDAO();
        createPeixeUseCase = new CreatePeixeUseCase(peixeDAO);
        updatePeixeUseCase = new UpdatePeixeUseCase(peixeDAO);
        removePeixeUseCase = new RemovePeixeUseCase(peixeDAO);
        findPeixeUseCase = new FindPeixeUseCase(peixeDAO);

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
        createInsumoUseCase = new CreateInsumoUseCase(insumoDAO);
        updateInsumoUseCase = new UpdateInsumoUseCase(insumoDAO);
        removeInsumoUseCase = new RemoveInsumoUseCase(insumoDAO);
        findInsumoUseCase = new FindInsumoUseCase(insumoDAO);
    }
}
