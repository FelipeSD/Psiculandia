package application.repository.sqlite;

import domain.entities.Cliente.Cliente;
import domain.usecases.Cliente.ClienteDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteClienteDAO implements ClienteDAO {
    @Override
    public Integer create(Cliente cliente) {
        String sql = "INSERT INTO Cliente(cnpj, nome, telefone, email) " +
                "VALUES (?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, cliente.getCnpj());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
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
    public Optional<Cliente> findOne(Integer key) {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        Cliente cliente = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                cliente = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        String sql = "SELECT * FROM Cliente";
        List<Cliente> clientes = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Cliente cliente = resultSetToEntity(rs);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    private Cliente resultSetToEntity(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt("id"),
                rs.getString("cnpj"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("email")
        );
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Cliente WHERE id = ?";
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
    public boolean delete(Cliente cliente) {
        if (cliente == null)
            throw new IllegalArgumentException("Cliente and cliente ID must not be null.");
        return deleteByKey(cliente.getId());
    }

    @Override
    public boolean update(Cliente cliente) {
        String sql = "UPDATE Cliente SET cnpj = ?, nome = ?, telefone = ?, email = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, cliente.getCnpj());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, String.valueOf(cliente.getId()));
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
