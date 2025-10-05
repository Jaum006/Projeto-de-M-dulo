// Classe base para todos os tipos de usuários do sistema (ex: paciente, médico, administrador)

public class Usuario {
    // Nome do usuário.
    private String nome;
    // CPF do usuário, utilizado como identificador único
    private String cpf;
    // Senha do usuário, utilizada para autenticação
    private String senha;
    // Perfil do usuário (ex: "paciente", "medico", "admin")
    private String perfil;

     // Construtor que inicializa todos os atributos do usuário
    public Usuario(String nome, String cpf, String senha, String perfil) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.perfil = perfil;
    }

    // Retorna o CPF do usuário. Usado para identificação
    public String getCPF() {
        return cpf;
    }

    // Retorna a senha do usuário. Usado para autenticação
    public String getSenha() {
        return senha;
    }

    // Retorna o perfil do usuário. Usado para verificar permissões
    public String getPerfil() {
        return perfil;
    }
}
