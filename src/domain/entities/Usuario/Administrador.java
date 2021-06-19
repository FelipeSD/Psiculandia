package domain.entities.Usuario;

public class Administrador extends Empregado {

    public Administrador(String username, String senha) {
        super(username, senha);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                    "username='" + getUsername() + '\'' +
                '}';
    }
}
