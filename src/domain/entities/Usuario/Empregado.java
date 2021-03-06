package domain.entities.Usuario;

public class Empregado {
    private int id;
    private String username;
    private String senha;

    public Empregado(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Empregado{" +
                "username='" + username + '\'' +
                '}';
    }
}