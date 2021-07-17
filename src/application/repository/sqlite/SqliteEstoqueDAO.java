package application.repository.sqlite;

import domain.entities.Estoque.Estoque;
import domain.usecases.Estoque.EstoqueDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SqliteEstoqueDAO implements EstoqueDAO {

    @Override
    public Integer create(Estoque estoque){
        String sql = "INSERT INTO Estoque(dataAtualizacao, insumo) " +
                "VALUES (?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, estoque.getDataAtualizacao());
//            stmt.setInt(2, estoque.listarInsumos()); // Aqui ele precisa receber apenas o ID desejado, mas não sei como fazer
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
    public Optional<Estoque> findOne(Integer key) {
        String sql = "SELECT * FROM Estoque WHERE id = ?";
        Estoque estoque = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                estoque = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(estoque);
    }

    @Override
    public List<Estoque> findAll() {
        return null;
    }

    private Estoque resultSetToEntity(ResultSet rs) throws SQLException {
        return new Estoque(
                rs.getInt("id"),
                rs.getString("dataAtualizacao"),
                rs.getInt("insumo")
        );
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Estoque WHERE id = ?";
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
    public boolean delete(Estoque estoque) {
        if (estoque == null)
            throw new IllegalArgumentException("Estoque must not be null.");
        return deleteByKey(estoque.getId());
    }

    @Override
    public boolean update(Estoque estoque) {
        String sql = "UPDATE Estoque SET dataAtualizacao = ?, insumo = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, estoque.getDataAtualizacao());
//            stmt.setInt(2, estoque.listarInsumos()); // Aqui ele precisa receber apenas o ID desejado, mas não sei como fazer
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
