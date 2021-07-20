package application.repository.sqlite;

import domain.entities.Fornecedor.Fornecedor;
import domain.entities.Insumo.Insumo;
import domain.entities.Peixe.Peixe;
import domain.usecases.Peixe.PeixeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static application.main.Main.findInsumoUseCase;

public class SqlitePeixeDAO implements PeixeDAO {
    @Override
    public Integer create(Peixe peixe) {
        String sql = "INSERT INTO Peixe(nome, pesoIdealVenda, racaoConsumida, qtdRacaoDiaria, " +
                "valorMercado) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, peixe.getNome());
            stmt.setDouble(2, peixe.getPesoIdealVenda());
            stmt.setInt(3, peixe.getRacaoConsumida().getId());
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
        List<Peixe> peixes = new ArrayList<>();
        String sql = "SELECT * FROM Peixe";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Peixe peixe = resultSetToEntity(resultSet);
                peixes.add(peixe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peixes;
    }

    private Peixe resultSetToEntity(ResultSet rs) throws SQLException {
        Optional<Insumo> insumo = findInsumoUseCase.findOne(rs.getInt("racaoConsumida"));
        
        return new Peixe(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDouble("pesoIdealVenda"),
                insumo.get(),
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
            stmt.setInt(3, peixe.getRacaoConsumida().getId());
            stmt.setDouble(4, peixe.getQtdRacaoDiaria());
            stmt.setDouble(5, peixe.getValorMercado());
            stmt.setDouble(6, peixe.getId());
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
