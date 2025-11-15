import java.util.Scanner;

public class Medico extends Usuario {
    private Scanner scanner = new Scanner(System.in);

    public Medico(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
    }

    @Override
    public void fazerLogin(String cpf, String senha) {
        if (getCpf().equals(cpf) && getSenha().equals(senha)) {
            System.out.println("Médico " + getNome() + " logado com sucesso!");
            menuMedico();
        } else {
            System.out.println("Falha no login! CPF ou senha incorretos.");
        }
    }

    @Override
    public void fazerLogout() {
        System.out.println("Paciente " + getNome() + " deslogado.");
    }

    @Override
    public void acessarPerfil() {
        System.out.println("=== Perfil do Médico ===");
        acessarDados();
        System.out.println("====================");
    }

    public void menuMedico() {
        int opcao;
        do {// Se tiver mais funcionalidades adicionar
            System.out.println("=== Menu Médico ===");
            System.out.println("1 - Ver lista de pacientes");
            System.out.println("2 - Registrar consulta");
            System.out.println("3 - Emitir receita");
            System.out.println("4 - Atualizar informações do paciente");
            System.out.println("0 - Logout");
            System.out.println("====================");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> System.out.println("");
                case 2 -> System.out.println("");
                case 0 -> fazerLogout();
                default -> System.out.println("");
            }
        } while (opcao != 0);
    }
}
