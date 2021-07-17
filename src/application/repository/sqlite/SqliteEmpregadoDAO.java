package application.repository.sqlite;

import domain.entities.Usuario.Administrador;
import domain.entities.Usuario.Empregado;
import domain.usecases.Usuario.EmpregadoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SqliteEmpregadoDAO implements EmpregadoDAO {
    @Override
    public Optional<Empregado> findByUsername(String username) {
        String sql = "SELECT * FROM Empregado WHERE username = ?";
        Empregado empregado = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                empregado = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(empregado);
    }

    private Empregado resultSetToEntity(ResultSet rs) throws SQLException {
        if(rs.getString("tipo").equals("empregado")){
            return new Empregado(
                    rs.getString("username"),
                    rs.getString("senha")
            );
        }else{
            return new Administrador(
                    rs.getString("username"),
                    rs.getString("senha")
            );
        }
    }

    @Override
    public Integer create(Empregado empregado) {
        String sql = "INSERT INTO Empregado(username, senha, tipo) " +
                "VALUES (?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, empregado.getUsername());
            stmt.setString(2, empregado.getSenha());
            stmt.setString(3, empregado instanceof Administrador ? "administrador" : "empregado");
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
    public Optional<Empregado> findOne(Integer key) {
        String sql = "SELECT * FROM Empregado WHERE id = ?";
        Empregado empregado = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                empregado = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(empregado);
    }

    @Override
    public List<Empregado> findAll() {
        return null;
    }

    @Override
    public boolean update(Empregado empregado) {
        String sql = "UPDATE Empregado SET username = ?, senha = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, empregado.getUsername());
            stmt.setString(2, empregado.getSenha());
            stmt.setInt(3, empregado.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Empregado type) {
        return false;
    }
}
