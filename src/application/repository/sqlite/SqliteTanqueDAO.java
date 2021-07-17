package application.repository.sqlite;

import domain.entities.Tanque.Tanque;
import domain.usecases.Tanque.TanqueDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SqliteTanqueDAO implements TanqueDAO {

    @Override
    public Integer create(Tanque tanque) {
        String sql = "INSERT INTO Tanque(especieCriada, qtdAlevinos, precoManutencao, dataInicio, " +
                "dataFim, checkAlimentado VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, tanque.getEspecieCriada());
            stmt.setInt(2, tanque.getQtdAlevinos());
            stmt.setDouble(3, tanque.getPrecoManutencao());
            stmt.setString(4, tanque.getDataInicio());
            stmt.setString(5, tanque.getDataFim());
            // int check = tanque.isCheckAlimentado() ? 1 : 0;
            stmt.setInt(6, tanque.isCheckAlimentado() ? 1 : 0); // tem que ver se está correto esse cast
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
    public Optional<Tanque> findOne(Integer key) {
        String sql = "SELECT * FROM Tanque WHERE id = ?";
        Tanque tanque = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                tanque = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(tanque);
    }

    @Override
    public List<Tanque> findAll() {
        return null;
    }

    private Tanque resultSetToEntity(ResultSet rs) throws SQLException {
        return new Tanque(
                rs.getInt("id"),
                rs.getString("especieCriada"),
                rs.getInt("qtdAlevinos"),
                rs.getDouble("precoManutencao"),
                rs.getString("dataInicio"),
                rs.getString("dataFim"),
                rs.getInt("checkAlimentado")
        );
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Tanque WHERE id = ?";
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
    public boolean delete(Tanque tanque) {
        if (tanque == null)
            throw new IllegalArgumentException("Tanque and tanque ID must not be null.");
        return deleteByKey(tanque.getId());
    }

    @Override
    public boolean update(Tanque tanque) {
        String sql = "UPDATE Tanque SET especieCriada = ?, qtdAlevinos = ?, precoManutencao = ?, " +
                "dataInicio = ?, dataFim = ?, check Alimentado = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, tanque.getEspecieCriada());
            stmt.setInt(2, tanque.getQtdAlevinos());
            stmt.setDouble(3, tanque.getPrecoManutencao());
            stmt.setString(4, tanque.getDataInicio());
            stmt.setString(5, tanque.getDataFim());
            stmt.setInt(6, tanque.isCheckAlimentado() ? 1 : 0); // tem que ver se está correto esse cast
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
