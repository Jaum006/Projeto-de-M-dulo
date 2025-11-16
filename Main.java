import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Consulta> consultas = new ArrayList<>();

    public static void main(String[] args) {
        carregarUsuarios();
        carregarConsultas();
        menuLogin();
        salvarUsuarios();
        salvarConsultas();
        System.out.println("Sistema encerrado. Até logo!");
    }

    // ======= ARQUIVOS: USUÁRIOS =======

    public static void carregarUsuarios() {
        List<String> linhas = SistemaUtil.lerLinhas("usuarios.txt");
        usuarios.clear();

        for (String ln : linhas) {
            String[] p = ln.split(";", -1);
            if (p.length < 4) continue;
            String tipo = p[0];
            String cpf = p[1];
            String nome = p[2];
            String senha = p[3];

            switch (tipo) {
                case "PACIENTE" -> {
                    // Esperado: tipo;cpf;nome;senha;data;tel;contNome;contTel;alergias;medic;doencas;obs
                    String dataNasc = p.length > 4 ? p[4] : "";
                    String tel = p.length > 5 ? p[5] : "";
                    String contNome = p.length > 6 ? p[6] : "";
                    String contTel = p.length > 7 ? p[7] : "";
                    String alergias = p.length > 8 ? p[8] : "";
                    String medic = p.length > 9 ? p[9] : "";
                    String doencas = p.length > 10 ? p[10] : "";
                    String obs = p.length > 11 ? p[11] : "";
                    usuarios.add(new Paciente(cpf, nome, senha,
                            dataNasc, tel, contNome, contTel, alergias, medic, doencas, obs));
                }
                case "MEDICO" -> {
                    String esp = p.length > 4 ? p[4] : "";
                    usuarios.add(new Medico(cpf, nome, senha, esp));
                }
                case "ADM" -> {
                    usuarios.add(new Administrador(cpf, nome, senha));
                }
                default -> {
                    // ignora ou registra log
                }
            }
        }
    }

    public static void salvarUsuarios() {
        List<String> linhas = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u instanceof Paciente p) {
                linhas.add(
                        "PACIENTE;" +
                        p.getCpf() + ";" +
                        p.getNome() + ";" +
                        p.getSenha() + ";" +
                        p.getDataNascimento() + ";" +
                        p.getTelefone() + ";" +
                        p.getContatoEmergenciaNome() + ";" +
                        p.getContatoEmergenciaTelefone() + ";" +
                        p.getAlergias() + ";" +
                        p.getMedicamentos() + ";" +
                        p.getDoencasCronicas() + ";" +
                        p.getObservacoesGerais()
                );
            } else if (u instanceof Medico m) {
                linhas.add(
                        "MEDICO;" +
                        m.getCpf() + ";" +
                        m.getNome() + ";" +
                        m.getSenha() + ";" +
                        m.getEspecialidade()
                );
            } else if (u instanceof Administrador a) {
                linhas.add(
                        "ADM;" +
                        a.getCpf() + ";" +
                        a.getNome() + ";" +
                        a.getSenha()
                );
            }
        }

        SistemaUtil.escreverLinhas("usuarios.txt", linhas);
    }

    // ======= ARQUIVOS: CONSULTAS =======

    public static void carregarConsultas() {
        List<String> linhas = SistemaUtil.lerLinhas("consultas.txt");
        consultas.clear();

        for (String ln : linhas) {
            Consulta c = Consulta.fromLinhaArquivo(ln);
            if (c != null) consultas.add(c);
        }
    }

    public static void salvarConsultas() {
        List<String> linhas = new ArrayList<>();
        for (Consulta c : consultas) {
            linhas.add(c.toLinhaArquivo());
        }
        SistemaUtil.escreverLinhas("consultas.txt", linhas);
    }

    // ======= MENUS PRINCIPAIS =======

    public static void menuLogin() {
        int opcao = -1;

        do {
            SistemaUtil.limparTela();
            System.out.println("=====================================");
            System.out.println("   PEP - SISTEMA PARA TERCEIRA IDADE");
            System.out.println("=====================================");
            System.out.println("1 - Registrar novo usuário");
            System.out.println("2 - Fazer login");
            System.out.println("0 - Sair do sistema");
            System.out.println("=====================================");
            System.out.print("Escolha uma opção: ");

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Por favor, digite apenas números.");
                SistemaUtil.pausar();
                continue;
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> registroPEP();
                case 2 -> loginPEP();
                case 0 -> System.out.println("Encerrando Sistema...");
                default -> {
                    System.out.println("Opção inválida!");
                    SistemaUtil.pausar();
                }
            }

        } while (opcao != 0);
    }

    public static void registroPEP() {
        SistemaUtil.limparTela();
        System.out.println("=== REGISTRO DE NOVO USUÁRIO ===");

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        if (SistemaUtil.buscarUsuarioPorCpf(cpf) != null) {
            System.out.println("CPF já cadastrado! Registro cancelado.");
            SistemaUtil.pausar();
            return;
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
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> {
                Paciente p = new Paciente(cpf, nome, senha);
                usuarios.add(p);
                salvarUsuarios();
                System.out.println("Paciente cadastrado com sucesso!");
                SistemaUtil.pausar();
            }
            case 2 -> {
                System.out.print("Especialidade do médico: ");
                String esp = scanner.nextLine();
                Medico m = new Medico(cpf, nome, senha, esp);
                usuarios.add(m);
                salvarUsuarios();
                System.out.println("Médico cadastrado com sucesso!");
                SistemaUtil.pausar();
            }
            case 3 -> {
                Administrador a = new Administrador(cpf, nome, senha);
                usuarios.add(a);
                salvarUsuarios();
                System.out.println("Administrador cadastrado com sucesso!");
                SistemaUtil.pausar();
            }
            case 0 -> {
                System.out.println("Registro cancelado.");
                SistemaUtil.pausar();
            }
            default -> {
                System.out.println("Opção inválida!");
                SistemaUtil.pausar();
            }
        }
    }

    public static void loginPEP() {
        SistemaUtil.limparTela();
        System.out.println("=== LOGIN ===");

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
            SistemaUtil.pausar();
            return;
        }

        System.out.println("Bem-vindo(a), " + usuarioEncontrado.getNome() + "!");
        SistemaUtil.pausar();

        if (usuarioEncontrado instanceof Paciente p) {
            p.menuPaciente();
        } else if (usuarioEncontrado instanceof Medico m) {
            m.menuMedico();
        } else if (usuarioEncontrado instanceof Administrador a) {
            a.menuADM();
        } else {
            System.out.println("Tipo de usuário desconhecido!");
            SistemaUtil.pausar();
        }
    }
}
