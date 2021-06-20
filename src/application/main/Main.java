package application.main;

import application.repository.InMemoryEmpregadoDAO;
import application.repository.InMemoryTanqueDAO;
import domain.entities.Tanque.Tanque;
import domain.entities.Usuario.Administrador;
import domain.entities.Usuario.Empregado;
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


    public static void main(String[] args) {
        configureInjection();
    
        // CRIANDO EMPREGADOS
        Empregado empregado1 = new Empregado("felpsd", "çlkjh");
        Administrador adm1 = new Administrador("adm", "123");
        createEmpregadoUseCase.insert(empregado1);
        createEmpregadoUseCase.insert(adm1);

        // LISTANDO EMPREGADOS
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
        createTanqueUseCase.insert(tanque1);
        
        // LISTANDO TANQUES
        findTanqueUseCase.findAll().forEach(tanque -> System.out.println("tanque = " + tanque));
        
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

    }
}
