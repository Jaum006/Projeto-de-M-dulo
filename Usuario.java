public abstract class Usuario implements AcessoSistema {
    private String cpf;
    private String nome;
    private String senha;

    public Usuario(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void acessarDados() {
        System.out.println("CPF: " + cpf);
        System.out.println("Nome: " + nome);
    }

    public abstract void acessarPerfil();
}
