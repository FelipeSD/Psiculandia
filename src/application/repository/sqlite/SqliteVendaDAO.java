package application.repository.sqlite;

import domain.entities.Cliente.Cliente;
import domain.entities.Insumo.Insumo;
import domain.entities.Venda.Venda;
import domain.entities.Peixe.Peixe;
import domain.entities.Venda.Venda;
import domain.usecases.Venda.VendaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static application.main.Main.findClienteUseCase;
import static application.main.Main.findInsumoUseCase;

public class SqliteVendaDAO implements VendaDAO {
    @Override
    public Integer create(Venda venda) {
        String sql = "INSERT INTO Venda(data, peixeVendido, qtde, valor, " +
                "cliente) VALUES (?, ?, ?, ?, ?)";

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(venda.getData());

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, strDate);
            stmt.setString(2, venda.getPeixeVendido());
            stmt.setDouble(3, venda.getQtde());
            stmt.setDouble(4, venda.getValor());
            stmt.setInt(5, venda.getCliente().getId());
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
    public Optional<Venda> findOne(Integer key) {
        String sql = "SELECT * FROM Venda WHERE id = ?";
        Venda venda = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                venda = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(venda);
    }

    @Override
    public List<Venda> findAll() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM Venda";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Venda venda = resultSetToEntity(resultSet);
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }

    @Override
    public boolean update(Venda venda) {
        String sql = "UPDATE Venda SET data = ?, peixeVendido = ?, qtde = ?, valor = ?, " +
                "cliente = ? WHERE id = ?";

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(venda.getData());

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, strDate);
            stmt.setString(2, venda.getPeixeVendido());
            stmt.setDouble(3, venda.getQtde());
            stmt.setDouble(4, venda.getValor());
            stmt.setInt(5, venda.getCliente().getId());
            stmt.setInt(7, venda.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Venda WHERE id = ?";
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
    public boolean delete(Venda venda) {
        return false;
    }

    private Venda resultSetToEntity(ResultSet rs) throws SQLException {
        Optional<Cliente> cliente = findClienteUseCase.findOne(rs.getInt("cliente"));

        return new Venda(
                rs.getInt("id"),
                rs.getString("data"),
                rs.getDouble("pesoIdealVenda"),
                cliente.get(),
                rs.getDouble("qtdRacaoDiaria"),
                rs.getDouble("valorMercado")
        );
    }
}
