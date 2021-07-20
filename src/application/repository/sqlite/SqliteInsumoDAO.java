package application.repository.sqlite;

import application.repository.sqlite.ConnectionFactory;
import domain.entities.Fornecedor.Fornecedor;
import domain.entities.Insumo.Insumo;
import domain.usecases.Insumo.InsumoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static application.main.Main.findFornecedorUseCase;

public class SqliteInsumoDAO implements InsumoDAO {
    @Override
    public Integer create(Insumo insumo) {
        String sql = "INSERT INTO Insumo(nome, tipo, qtde, valor, dataAquisicao, fornecedor) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, insumo.getNome());
            stmt.setString(2, insumo.getTipo());
            stmt.setDouble(3, insumo.getQtde());
            stmt.setDouble(4, insumo.getValor());
            stmt.setString(5, insumo.getDataAquisicao());
            stmt.setInt(6, insumo.getFornecedor().getId());
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
    public Optional<Insumo> findOne(Integer key) {
        String sql = "SELECT * FROM Insumo WHERE id = ?";
        Insumo insumo = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                insumo = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(insumo);
    }

    @Override
    public List<Insumo> findAll() {
        List<Insumo> insumos = new ArrayList<>();
        String sql = "SELECT * FROM Insumo";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Insumo insumo = resultSetToEntity(resultSet);
                insumos.add(insumo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insumos;
    }

    public Insumo resultSetToEntity(ResultSet rs) throws SQLException {
        Optional<Fornecedor> fornecedor = findFornecedorUseCase.findOne(rs.getInt("fornecedor"));

        return new Insumo(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("tipo"),
                rs.getDouble("qtde"),
                rs.getDouble("valor"),
                rs.getString("dataAquisicao"),
                fornecedor.get()
        );
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Insumo WHERE id = ?";
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
    public boolean delete(Insumo insumo) {
        if (insumo == null)
            throw new IllegalArgumentException("Insumo must not be null.");
        return deleteByKey(insumo.getId());
    }

    @Override
    public boolean update(Insumo insumo) {
        String sql = "UPDATE Insumo SET nome = ?, tipo = ?, qtde = ?, valor = ?, " +
                "dataAquisicao = ?, fornecedor = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, insumo.getNome());
            stmt.setString(2, insumo.getTipo());
            stmt.setDouble(3, insumo.getQtde());
            stmt.setDouble(4, insumo.getValor());
            stmt.setString(5, insumo.getDataAquisicao());
            stmt.setInt(6, insumo.getFornecedor().getId());
            stmt.setInt(7, insumo.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
