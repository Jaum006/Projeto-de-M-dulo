import java.util.Scanner;
import java.util.ArrayList;

public class PepSystem {

    /*public static void main(String[] args) {
        System.out.println("=== TESTE DE INSTÂNCIA DOS OBJETOS ===");

        // Instanciando um usuário genérico
        Usuario usuarioTeste = new Usuario("Maria Silva", "12345678900", "senha123", "administrativo");
        System.out.println("Usuário criado:");
        System.out.println("  Nome: Maria Silva");
        System.out.println("  CPF: " + usuarioTeste.getCPF());
        System.out.println("  Perfil: " + usuarioTeste.getPerfil());
        System.out.println("  Senha: " + usuarioTeste.getSenha());
        System.out.println("  Objeto: " + usuarioTeste);

        // Instanciando um paciente
        Paciente pacienteTeste = new Paciente("João Marcos", "98765432100", "senha456", "paciente");
        System.out.println("\nPaciente criado:");
        System.out.println("  Nome: João Marcos");
        System.out.println("  CPF: " + pacienteTeste.getCPF());
        System.out.println("  Perfil: " + pacienteTeste.getPerfil());
        System.out.println("  Senha: " + pacienteTeste.getSenha());
        System.out.println("  Objeto: " + pacienteTeste);

        // Instanciando uma consulta
        Consulta consultaTeste = new Consulta("05/10/2025 14:00", "Dra. Ana Paula", "Consulta de rotina");
        System.out.println("\nConsulta criada:");
        System.out.println("  Data/Hora: 05/10/2025 14:00");
        System.out.println("  Médico: " + consultaTeste.getNomeMedico());
        System.out.println("  Notas: " + consultaTeste.getNotas());
        System.out.println("  Objeto: " + consultaTeste);
        System.out.println("  toString: " + consultaTeste.toString());
    }*/
    
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static boolean LoginConfirmado = false;
    private static Usuario usuariologado = null;
    public static void main(String[] args) {
        MenuCadastro();
        if (LoginConfirmado) {
        }
    }

    private static void MenuProfile(){
        switch(usuariologado.getPerfil()){
            case "paciente":
        }
    }

    private static void MenuCadastro() {
        int opcao = 0;
        do {
            System.out.println("=== SISTEMA PEP - LOGIN E CADASTRO ===");
            System.out.println("1 - Fazer Login");
            System.out.println("2 - Cadastrar-se");
            System.out.println("0 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> LoginUsuario();
                case 2 -> RegistrarUsuarios();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // Log-in de Usuário
    private static void LoginUsuario() {
        LoginConfirmado = false;
        System.out.println("\n=== LOGIN USUÁRIO ===");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Usuario u : usuarios) {
            if (cpf.equals(u.getCPF()) && senha.equals(u.getSenha())) { // Compara o CPF e a SENHA com a do
                                                                        // Arraylist<Usuario>
                usuariologado = u;
                LoginConfirmado = true;
                break;
            }
        }
        if (usuariologado != null) {
            System.out.println("Log-in Concluido!");
        } else {
            System.out.println("Usuário Inexistente! Cadastre-se primeiro.");
        }
    }

    // Cadastro de Usuário
    private static void RegistrarUsuarios() {
        System.out.println("\n=== CADASTRO DE NOVO USUÁRIO ===");

        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("CPF (somente números): ");
        String cpf = scanner.nextLine();
        for (Usuario u : usuarios) { // Verifica se há um cpf igual ao usado no cadastro
            if (cpf.equals(u.getCPF())) {
                System.out.println("Já existe um usuário com esse CPF!");
                return;
            }
        }
        System.out.print("Crie uma senha: ");
        String senha = scanner.nextLine();

        System.out.println("\nSelecione o perfil do usuário:");
        System.out.println("  1 - Paciente");
        System.out.println("  2 - Médico");
        System.out.println("  3 - Enfermagem");
        System.out.println("  4 - Administrativo");
        System.out.print("Opção escolhida: ");
        int perfilOP = scanner.nextInt();
        scanner.nextLine();
        String perfil = null;
        switch (perfilOP) {
            case 1 -> perfil = "paciente";
            case 2 -> perfil = "medico";
            case 3 -> perfil = "enfermagem";
            case 4 -> perfil = "administrativo";
            default -> perfil = "paciente";
        }
        Usuario novoUsuario = new Usuario(nome, cpf, senha, perfil);
        usuarios.add(novoUsuario);
        System.out.println("Usuário Cadastrado com sucesso!");
    }
}

