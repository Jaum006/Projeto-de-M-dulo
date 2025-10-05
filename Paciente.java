import java.util.ArrayList;

// Classe Paciente representa um usuário do tipo paciente, que pode ter consultas associadas
public class Paciente extends Usuario {
    // Lista de consultas realizadas ou agendadas pelo paciente
    private ArrayList<Consulta> consultas = new ArrayList<>();

    // Construtor que inicializa o paciente com nome, cpf, senha e perfil
    public Paciente(String nome, String cpf, String senha, String perfil) {
        // Chama o construtor da classe Usuario
        super(nome, cpf, senha, perfil);
    }
}