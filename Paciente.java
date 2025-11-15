import java.util.Scanner;

public class Paciente extends Usuario {
    Scanner scanner = new Scanner(System.in);

    public Paciente(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
    }

    @Override
    public void fazerLogin(String cpf, String senha) {
        if (getCpf().equals(cpf) && getSenha().equals(senha)) {
            System.out.println("Paciente " + getNome() + " logado com sucesso!");
            menuPaciente();
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
        System.out.println("=== Perfil do Paciente ===");
        acessarDados();
        System.out.println("====================");
    }

    public void menuPaciente() {
        int opcao;
        do {// Se tiver mais funcionalidades adicionar
            System.out.println("=== Menu Paciente ===");
            System.out.println("1 - Ver histórico de consultas");
            System.out.println("2 - Agendar consulta");
            System.out.println("0 - Logout");
            System.out.println("====================");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> System.out.println("");
                case 2 -> System.out.println("");
                case 0 -> fazerLogout();
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
