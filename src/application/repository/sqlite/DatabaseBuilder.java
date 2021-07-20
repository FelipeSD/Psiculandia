package application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public void buildDatabaseIfMissing() {
        if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createClienteTable());
            statement.addBatch(createFornecedorTable());
            statement.addBatch(createInsumoTable());
            statement.addBatch(createEstoqueTable());
            statement.addBatch(createPeixeTable());
            statement.addBatch(createHistoricoSemanalTable());
            statement.addBatch(createTanqueTable());
            statement.addBatch(createEmpregadoTable());
            statement.addBatch(createVendaTable());
            statement.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createClienteTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Cliente (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("cnpj TEXT NOT NULL UNIQUE, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("telefone TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL UNIQUE \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createFornecedorTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Fornecedor (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("cnpj TEXT NOT NULL UNIQUE, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("endereco TEXT, \n");
//        builder.append("produtos TEXT, \n");
        builder.append("tempoEntrega INTEGER NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createInsumoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Insumo (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL UNIQUE, \n");
        builder.append("tipo TEXT NOT NULL, \n");
        builder.append("qtde REAL NOT NULL, \n");
        builder.append("valor REAL NOT NULL, \n");
        builder.append("dataAquisicao TEXT, \n");
        builder.append("fornecedor INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(fornecedor) REFERENCES Fornecedor(id) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createEstoqueTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Estoque (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("dataAtualizacao TEXT NOT NULL, \n");
        builder.append("insumo INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(insumo) REFERENCES Insumo(id) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createPeixeTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Peixe (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("pesoIdealVenda REAL NOT NULL, \n");
        builder.append("racaoConsumida INTEGER NOT NULL, \n");
        builder.append("qtdRacaoDiaria REAL NOT NULL, \n");
        builder.append("valorMercado REAL NOT NULL, \n");
        builder.append("FOREIGN KEY(racaoConsumida) REFERENCES Insumo(id) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createTanqueTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Tanque (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("especieCriada INTEGER NOT NULL, \n");
        builder.append("qtdAlevinos INTEGER NOT NULL, \n");
        builder.append("precoManutencao REAL NOT NULL, \n");
        builder.append("dataInicio TEXT NOT NULL, \n");
        builder.append("dataFim TEXT NOT NULL, \n");
        builder.append("checkAlimentado INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(especieCriada) REFERENCES Peixe(id) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createEmpregadoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Empregado (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("username TEXT NOT NULL UNIQUE, \n");
        builder.append("senha INTEGER NOT NULL, \n");
        builder.append("tipo TEXT NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createHistoricoSemanalTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE HistoricoSemanal (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("pesoMedio REAL NOT NULL, \n");
        builder.append("dataLancada TEXT NOT NULL, \n");
        builder.append("tanque INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(tanque) REFERENCES Tanque(id) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createVendaTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Venda (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("data TEXT NOT NULL, \n");
        builder.append("peixeVendido INTEGER NOT NULL, \n");
        builder.append("qtde REAL NOT NULL, \n");
        builder.append("valor REAL NOT NULL, \n");
        builder.append("cliente INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(cliente) REFERENCES Cliente(id), \n");
        builder.append("FOREIGN KEY(peixeVendido) REFERENCES Cliente(id) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }
}
