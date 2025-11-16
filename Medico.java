public class Medico extends Usuario {

    private String especialidade;

    public Medico(String cpf, String nome, String senha, String especialidade) {
        super(cpf, nome, senha);
        this.especialidade = especialidade == null ? "" : especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void menuMedico() {
        int opcao;

        do {
            SistemaUtil.limparTela();
            System.out.println("=====================================");
            System.out.println("          MENU DO MÉDICO");
            System.out.println("=====================================");
            System.out.println("1 - Ver consultas agendadas");
            System.out.println("2 - Registrar atendimento / anamnese");
            System.out.println("3 - Histórico de consultas atendidas");
            System.out.println("0 - Voltar / Logout");
            System.out.println("=====================================");
            System.out.print("Escolha uma opção: ");

            opcao = Main.scanner.nextInt();
            Main.scanner.nextLine();

            switch (opcao) {
                case 1 -> verConsultasAgendadas();
                case 2 -> registrarAtendimento();
                case 3 -> historicoConsultas();
                case 0 -> {
                    System.out.println("Saindo do menu do médico...");
                    SistemaUtil.pausar();
                }
                default -> {
                    System.out.println("Opção inválida!");
                    SistemaUtil.pausar();
                }
            }
        } while (opcao != 0);
    }

    private void verConsultasAgendadas() {
        SistemaUtil.limparTela();
        System.out.println("=== CONSULTAS AGENDADAS ===");

        boolean encontrou = false;
        for (Consulta c : Main.consultas) {
            if (c.getCpfMedico().equals(getCpf()) && c.getStatus().equals("AGENDADA")) {
                System.out.println();
                System.out.println(c);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma consulta agendada.");
        }

        SistemaUtil.pausar();
    }

    private void registrarAtendimento() {
        SistemaUtil.limparTela();
        System.out.println("=== REGISTRAR ATENDIMENTO / ANAMNESE ===");

        System.out.print("Informe o ID da consulta atendida: ");
        int id = Main.scanner.nextInt();
        Main.scanner.nextLine();

        Consulta alvo = null;
        for (Consulta c : Main.consultas) {
            if (c.getId() == id && c.getCpfMedico().equals(getCpf())) {
                alvo = c;
                break;
            }
        }

        if (alvo == null) {
            System.out.println("Consulta não encontrada para este médico.");
            SistemaUtil.pausar();
            return;
        }

        System.out.println("Consulta encontrada:");
        System.out.println(alvo);

        System.out.println("\nDigite a anamnese / observações do atendimento:");
        String obs = Main.scanner.nextLine();

        alvo.setObservacoes(obs);
        alvo.setStatus("CONCLUIDA");
        Main.salvarConsultas();

        System.out.println("Atendimento registrado com sucesso!");
        SistemaUtil.pausar();
    }

    private void historicoConsultas() {
        SistemaUtil.limparTela();
        System.out.println("=== HISTÓRICO DE CONSULTAS ATENDIDAS ===");

        boolean encontrou = false;
        for (Consulta c : Main.consultas) {
            if (c.getCpfMedico().equals(getCpf()) && c.getStatus().equals("CONCLUIDA")) {
                System.out.println();
                System.out.println(c);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma consulta atendida ainda.");
        }

        SistemaUtil.pausar();
    }
}
    