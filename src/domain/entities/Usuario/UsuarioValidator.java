package domain.entities.Usuario;

public class UsuarioValidator {

    public boolean validar(Empregado empregado){
        return validaSenhaVazia(empregado.getSenha()) && validaUsername(empregado.getUsername());
    }

    private boolean validaSenhaVazia(String senha) {
        return senha.length() != 0;
    }

    public boolean validaUsername(String username){
        return username.length() != 0;
    }
}