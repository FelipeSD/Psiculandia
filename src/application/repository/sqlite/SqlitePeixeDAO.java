package application.repository.sqlite;

import domain.entities.Peixe.Peixe;
import domain.usecases.Peixe.PeixeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SqlitePeixeDAO implements PeixeDAO {
    @Override
    public Integer create(Peixe peixe) {
        String sql = "INSERT INTO Peixe(nome, pesoIdealVenda, racaoConsumida, qtdRacaoDiaria, " +
                "valorMercado) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, peixe.getNome());
            stmt.setDouble(2, peixe.getPesoIdealVenda());
            stmt.setString(3, peixe.getRacaoConsumida());
            stmt.setDouble(4, peixe.getQtdRacaoDiaria());
            stmt.setDouble(5, peixe.getValorMercado());
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
    public Optional<Peixe> findOne(Integer key) {
        String sql = "SELECT * FROM Peixe WHERE id = ?";
        Peixe peixe = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                peixe = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(peixe);
    }

    @Override
    public List<Peixe> findAll() {
        return null;
    }

    private Peixe resultSetToEntity(ResultSet rs) throws SQLException {
        return new Peixe(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDouble("pesoIdealVenda"),
                rs.getString("racaoConsumida"),
                rs.getDouble("qtdRacaoDiaria"),
                rs.getDouble("valorMercado")
        );
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Peixe WHERE id = ?";
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
    public boolean delete(Peixe peixe) {
        if (peixe == null)
            throw new IllegalArgumentException("Peixe must not be null.");
        return deleteByKey(peixe.getId());
    }

    @Override
    public boolean update(Peixe peixe) {
        String sql = "UPDATE Peixe SET nome = ?, pesoIdealVenda = ?, racaoConsumida = ?, " +
                "qtdRacaoDiaria = ?, valorMercado = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, peixe.getNome());
            stmt.setDouble(2, peixe.getPesoIdealVenda());
            stmt.setString(3, peixe.getRacaoConsumida());
            stmt.setDouble(4, peixe.getQtdRacaoDiaria());
            stmt.setDouble(5, peixe.getValorMercado());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Peixe> findByEspecie(String especie) {
        return Optional.empty();
    }
}
