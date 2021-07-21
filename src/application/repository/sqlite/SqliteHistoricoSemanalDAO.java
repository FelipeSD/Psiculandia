package application.repository.sqlite;

import domain.entities.Historico.Historico;
import domain.entities.Tanque.HistoricoSemanalCrescimento;
import domain.entities.Tanque.Tanque;
import domain.utils.DateHelp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteHistoricoSemanalDAO {
    public Integer create(HistoricoSemanalCrescimento historico) {
        String sql = "INSERT INTO HistoricoSemanal(pesoMedio, dataLancada, tanque" +
                "VALUES (?, ?, ?)";

        String strDate = DateHelp.dateToString(historico.getDataLancada());

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setDouble(1, historico.getPesoMedio());
            stmt.setString(2, strDate);
            stmt.setInt(3, historico.getTanque().getId());
            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            int generatedKey = resultSet.getInt(1);
            return generatedKey;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
