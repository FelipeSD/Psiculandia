package application.repository.sqlite;

import domain.entities.Fornecedor.Fornecedor;
import domain.entities.Peixe.Peixe;
import domain.entities.Tanque.Tanque;
import domain.entities.Venda.Venda;
import domain.usecases.Tanque.TanqueDAO;
import domain.utils.DateHelp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static application.main.Main.findFornecedorUseCase;
import static application.main.Main.findPeixeUseCase;

public class SqliteTanqueDAO implements TanqueDAO {

    @Override
    public Integer create(Tanque tanque) {
        String sql = "INSERT INTO Tanque(especieCriada, qtdAlevinos, precoManutencao, dataInicio, " +
                "dataFim, pesoMedioInicial, checkAlimentado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            String dateInicio =  DateHelp.dateToString(tanque.getDataInicio());
            String dateFim =  DateHelp.dateToString(tanque.getDataFim());

            stmt.setInt(1, tanque.getEspecieCriada().getId());
            stmt.setInt(2, tanque.getQtdAlevinos());
            stmt.setDouble(3, tanque.getPrecoManutencao());
            stmt.setString(4, dateInicio);
            stmt.setString(5, dateFim);
            stmt.setDouble(6, tanque.getPesoMedioInicial());
            stmt.setInt(7, tanque.isCheckAlimentado() ? 1 : 0); // tem que ver se está correto esse cast
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
        List<Tanque> tanques = new ArrayList<>();
        String sql = "SELECT * FROM Tanque";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Tanque tanque = resultSetToEntity(resultSet);
                tanques.add(tanque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tanques;
    }

    private Tanque resultSetToEntity(ResultSet rs) throws SQLException {
        Date dateInicio = null;
        Date dateFim = null;

        try {
            dateInicio = DateHelp.stringToDate(rs.getString("dataInicio"));
            dateFim = DateHelp.stringToDate(rs.getString("dataFim"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Optional<Peixe> peixe = findPeixeUseCase.findOne(rs.getInt("especieCriada"));

        return new Tanque(
                rs.getInt("id"),
                peixe.get(),
                rs.getInt("qtdAlevinos"),
                rs.getDouble("precoManutencao"),
                dateInicio,
                dateFim,
                rs.getDouble("pesoMedioInicial"),
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
                "dataInicio = ?, dataFim = ?, pesoMedioInicial = ?, checkAlimentado = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            String dateInicio =  DateHelp.dateToString(tanque.getDataInicio());
            String dateFim =  DateHelp.dateToString(tanque.getDataFim());

            stmt.setInt(1, tanque.getEspecieCriada().getId());
            stmt.setInt(2, tanque.getQtdAlevinos());
            stmt.setDouble(3, tanque.getPrecoManutencao());
            stmt.setString(4, dateInicio);
            stmt.setString(5, dateFim);
            stmt.setDouble(6, tanque.getPesoMedioInicial());
            stmt.setInt(7, tanque.isCheckAlimentado() ? 1 : 0); // tem que ver se está correto esse cast
            stmt.setInt(8, tanque.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // para alterar o preco de manutenção do tanque com base na ração investida e os custos adicionais com saude
    public boolean updatePreco(Tanque tanque) {
        String sql = "UPDATE Tanque SET precoManutencao = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setDouble(1, tanque.getPrecoManutencao());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
