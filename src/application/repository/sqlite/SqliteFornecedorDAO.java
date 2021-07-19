package application.repository.sqlite;

import domain.entities.Fornecedor.Fornecedor;
import domain.usecases.Fornecedor.FornecedorDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteFornecedorDAO implements FornecedorDAO {

    @Override
    public Integer create(Fornecedor fornecedor) {
        String sql = "INSERT INTO Fornecedor(cnpj, nome, endereco, tempoEntrega) " +
                "VALUES (?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getNome());
            stmt.setString(3, fornecedor.getEndereco());
//            stmt.setString(4, fornecedor.getProdutos());
            stmt.setInt(4, fornecedor.getTempoEntrega());
            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            int generatedKey = resultSet.getInt(1);
            return generatedKey;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Fornecedor> findOne(Integer key) {
        String sql = "SELECT * FROM Fornecedor WHERE id = ?";
        Fornecedor fornecedor = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                fornecedor = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(fornecedor);
    }

    @Override
    public List<Fornecedor> findAll() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM Fornecedor";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Fornecedor fornecedor = resultSetToEntity(resultSet);
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fornecedores;
    }

    private Fornecedor resultSetToEntity(ResultSet rs) throws SQLException {
        return new Fornecedor(
                rs.getInt("id"),
                rs.getString("cnpj"),
                rs.getString("nome"),
                rs.getString("endereco"),
//                rs.getString("produtos"),
                rs.getInt("tempoEntrega")
        );
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Fornecedor WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Fornecedor fornecedor) {
        if (fornecedor == null)
            throw new IllegalArgumentException("Fornecedor and fornecedor ID must not be null.");
        return deleteByKey(fornecedor.getId());
    }

    @Override
    public boolean update(Fornecedor fornecedor) {
        String sql = "UPDATE Fornecedor SET cnpj = ?, nome = ?, endereco = ?, " +
                "tempoEntrega = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getNome());
            stmt.setString(3, fornecedor.getEndereco());
//            stmt.setString(4, fornecedor.getProdutos());
            stmt.setInt(4, fornecedor.getTempoEntrega());
            stmt.setInt(5, fornecedor.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
