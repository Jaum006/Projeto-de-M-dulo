public class Administrador extends Usuario {

    public Administrador(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
    }

    public void menuADM() {
        int opcao;

        do {
            SistemaUtil.limparTela();
            System.out.println("=====================================");
            System.out.println("        MENU DO ADMINISTRADOR");
            System.out.println("=====================================");
            System.out.println("1 - Listar usuários");
            System.out.println("2 - Buscar usuário por CPF");
            System.out.println("3 - Remover usuário");
            System.out.println("4 - Listar consultas");
            System.out.println("5 - Cancelar consulta por ID");
            System.out.println("0 - Voltar / Logout");
            System.out.println("=====================================");
            System.out.print("Escolha uma opção: ");

            opcao = Main.scanner.nextInt();
            Main.scanner.nextLine();

            switch (opcao) {
                case 1 -> listarUsuarios();
                case 2 -> buscarUsuarioCpf();
                case 3 -> removerUsuario();
                case 4 -> listarConsultas();
                case 5 -> cancelarConsultaPorId();
                case 0 -> {
                    System.out.println("Saindo do menu do administrador...");
                    SistemaUtil.pausar();
                }
                default -> {
                    System.out.println("Opção inválida!");
                    SistemaUtil.pausar();
                }
            }

        } while (opcao != 0);
    }

    private void listarUsuarios() {
        SistemaUtil.limparTela();
        System.out.println("=== LISTA DE USUÁRIOS ===");
        if (Main.usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (Usuario u : Main.usuarios) {
                String tipo = (u instanceof Paciente) ? "Paciente" :
                              (u instanceof Medico) ? "Médico" :
                              (u instanceof Administrador) ? "Administrador" :
                              "Desconhecido";
                System.out.printf("CPF: %s | Nome: %s | Tipo: %s%n",
                        u.getCpf(), u.getNome(), tipo);
            }
        }
        SistemaUtil.pausar();
    }

    private void buscarUsuarioCpf() {
        SistemaUtil.limparTela();
        System.out.println("=== BUSCAR USUÁRIO POR CPF ===");
        System.out.print("Informe o CPF: ");
        String cpf = Main.scanner.nextLine();

        Usuario u = SistemaUtil.buscarUsuarioPorCpf(cpf);
        if (u == null) {
            System.out.println("Usuário não encontrado.");
        } else {
            String tipo = (u instanceof Paciente) ? "Paciente" :
                          (u instanceof Medico) ? "Médico" :
                          (u instanceof Administrador) ? "Administrador" :
                          "Desconhecido";
            System.out.println("Usuário encontrado:");
            System.out.println("Nome: " + u.getNome());
            System.out.println("CPF: " + u.getCpf());
            System.out.println("Tipo: " + tipo);
        }
        SistemaUtil.pausar();
    }

    private void removerUsuario() {
        SistemaUtil.limparTela();
        System.out.println("=== REMOVER USUÁRIO ===");
        System.out.print("Informe o CPF do usuário a remover: ");
        String cpf = Main.scanner.nextLine();

        Usuario u = SistemaUtil.buscarUsuarioPorCpf(cpf);
        if (u == null) {
            System.out.println("Usuário não encontrado.");
            SistemaUtil.pausar();
            return;
        }

        // Remove consultas ligadas a este usuário
        Main.consultas.removeIf(c ->
                c.getCpfPaciente().equals(cpf) || c.getCpfMedico().equals(cpf));

        Main.usuarios.remove(u);
        Main.salvarUsuarios();
        Main.salvarConsultas();

        System.out.println("Usuário e suas consultas associadas foram removidos.");
        SistemaUtil.pausar();
    }

    private void listarConsultas() {
        SistemaUtil.limparTela();
        System.out.println("=== TODAS AS CONSULTAS ===");

        if (Main.consultas.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
        } else {
            for (Consulta c : Main.consultas) {
                System.out.println("--------------------");
                System.out.println(c);
            }
        }
        SistemaUtil.pausar();
    }

    private void cancelarConsultaPorId() {
        SistemaUtil.limparTela();
        System.out.println("=== CANCELAR CONSULTA POR ID ===");
        System.out.print("Informe o ID da consulta: ");
        int id = Main.scanner.nextInt();
        Main.scanner.nextLine();

        Consulta alvo = null;
        for (Consulta c : Main.consultas) {
            if (c.getId() == id) {
                alvo = c;
                break;
            }
        }

        if (alvo == null) {
            System.out.println("Consulta não encontrada.");
        } else {
            alvo.setStatus("CANCELADA");
            Main.salvarConsultas();
            System.out.println("Consulta cancelada com sucesso.");
        }
        SistemaUtil.pausar();
    }
}
    