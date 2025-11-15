import java.util.Scanner;

public class Administrador extends Usuario {
    Scanner scanner = new Scanner(System.in);

    public Administrador(String cpf, String nome) {
        super(cpf, nome, "1234");
    }

    @Override
    public void fazerLogin(String cpf, String senha) {
        if (getCpf().equals(cpf) && getSenha().equals(senha)) {
            System.out.println("Administrador " + getNome() + " logado com sucesso!");
            menuADM();
        } else {
            System.out.println("Falha no login! CPF ou senha incorretos.");
        }
    }

    @Override
    public void fazerLogout() {
        System.out.println("Administrador " + getNome() + " deslogado.");
    }

    @Override
    public void acessarPerfil() {
        System.out.println("=== Perfil do Administrador ===");
        acessarDados();
        System.out.println("===============================");
    }

    public void menuADM() {
        int opcao;
        do {// Se tiver mais funcionalidades adicionar
            System.out.println("=== Menu Administrador ===");
            System.out.println("1 - Gerenciar usuários");
            System.out.println("2 - Visualizar relatórios");
            System.out.println("3 - Atualizar permissões");
            System.out.println("0 - Logout");
            System.out.print("=============================");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> System.out.println("");
                case 2 -> System.out.println("");
                case 3 -> System.out.println("");
                case 0 -> fazerLogout();
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
