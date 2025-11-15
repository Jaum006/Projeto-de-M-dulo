import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        menuLogin();
    }

    public static void menuLogin() {
        int opcao = 0;
        do {
            System.out.println("=== PEP - Sistema de Cadastro ===");
            System.out.println("1 - Registrar-se");
            System.out.println("2 - Login");
            System.out.println("0 - Sair");
            System.out.println("==============================");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> registroPEP();
                case 2 -> loginPEP();
                case 0 -> System.out.println("Encerrando Sistema...");
            }
        } while (opcao != 0);
    }

    public static void registroPEP() {
        boolean chaveTIPO = false;
        int opcao = 0;
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) {
                System.out.println("CPF já cadastrado! Registro cancelado.\n");
                return;
            }
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
            System.out.println("======= Tipo de usuário =======");
            System.out.println("1 - Paciente");
            System.out.println("2 - Médico");
            System.out.println("3 - Administrador");
            System.out.println("0 - Cancelar registro");
            System.out.println("==============================");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> usuarios.add(new Paciente(cpf, nome, senha));
                case 2 -> usuarios.add(new Medico(cpf, nome, senha));
                case 3 -> usuarios.add(new Administrador(cpf, nome));
                case 0 -> System.out.println("Registro cancelado.");
                default -> System.out.println("Opção inválida!");
            }
    }

    public static void loginPEP() {
        System.out.print("CPF: ");
        String cpfLogin = scanner.nextLine();
        System.out.print("Senha: ");
        String senhaLogin = scanner.nextLine();

        Usuario usuarioEncontrado = null;
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpfLogin) && u.getSenha().equals(senhaLogin)) {
                usuarioEncontrado = u;
                break;
            }
        }
        if (usuarioEncontrado == null) {
            System.out.println("Usuário não encontrado ou senha incorreta!");
            return;
        }

        System.out.println("Bem-vindo, " + usuarioEncontrado.getNome() + "!");

        if (usuarioEncontrado instanceof Paciente) {
            ((Paciente) usuarioEncontrado).menuPaciente();
        } else if (usuarioEncontrado instanceof Medico) {
            ((Medico) usuarioEncontrado).menuMedico();
        } else if (usuarioEncontrado instanceof Administrador) {
            ((Administrador) usuarioEncontrado).menuADM();
        } else {
            System.out.println("Tipo desconhecido!");
        }
    }
}