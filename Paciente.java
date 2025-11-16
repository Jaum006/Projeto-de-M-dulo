import java.util.ArrayList;

public class Paciente extends Usuario {

    // Ficha médica / anamnese básica
    private String dataNascimento;
    private String telefone;
    private String contatoEmergenciaNome;
    private String contatoEmergenciaTelefone;
    private String alergias;
    private String medicamentos;
    private String doencasCronicas;
    private String observacoesGerais;

    // Construtor usado no registro simples
    public Paciente(String cpf, String nome, String senha) {
        this(cpf, nome, senha,
                "", "", "", "",
                "", "", "", "");
    }

    // Construtor completo (para carregar do arquivo)
    public Paciente(String cpf, String nome, String senha,
                    String dataNascimento, String telefone,
                    String contatoEmergenciaNome, String contatoEmergenciaTelefone,
                    String alergias, String medicamentos,
                    String doencasCronicas, String observacoesGerais) {
        super(cpf, nome, senha);
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.contatoEmergenciaNome = contatoEmergenciaNome;
        this.contatoEmergenciaTelefone = contatoEmergenciaTelefone;
        this.alergias = alergias;
        this.medicamentos = medicamentos;
        this.doencasCronicas = doencasCronicas;
        this.observacoesGerais = observacoesGerais;
    }

    // Getters para salvar
    public String getDataNascimento() { return dataNascimento; }
    public String getTelefone() { return telefone; }
    public String getContatoEmergenciaNome() { return contatoEmergenciaNome; }
    public String getContatoEmergenciaTelefone() { return contatoEmergenciaTelefone; }
    public String getAlergias() { return alergias; }
    public String getMedicamentos() { return medicamentos; }
    public String getDoencasCronicas() { return doencasCronicas; }
    public String getObservacoesGerais() { return observacoesGerais; }

    public void menuPaciente() {
        int opcao;

        do {
            SistemaUtil.limparTela();
            System.out.println("=====================================");
            System.out.println("         MENU DO PACIENTE");
            System.out.println("=====================================");
            System.out.println("1 - Ver lembretes de consultas");
            System.out.println("2 - Histórico de consultas");
            System.out.println("3 - Marcar nova consulta");
            System.out.println("4 - Cancelar consulta agendada");
            System.out.println("5 - Ver/Atualizar ficha médica");
            System.out.println("0 - Voltar / Logout");
            System.out.println("=====================================");
            System.out.print("Escolha uma opção: ");

            opcao = Main.scanner.nextInt();
            Main.scanner.nextLine();

            switch (opcao) {
                case 1 -> verLembretesConsultas();
                case 2 -> verHistoricoConsultas();
                case 3 -> marcarConsulta();
                case 4 -> cancelarConsulta();
                case 5 -> menuFichaMedica();
                case 0 -> {
                    System.out.println("Saindo do menu do paciente...");
                    SistemaUtil.pausar();
                }
                default -> {
                    System.out.println("Opção inválida!");
                    SistemaUtil.pausar();
                }
            }
        } while (opcao != 0);
    }

    // ======= CONSULTAS PACIENTE =======

    private void verLembretesConsultas() {
        SistemaUtil.limparTela();
        System.out.println("=== LEMBRETES DE CONSULTAS (AGENDADAS) ===");

        boolean encontrou = false;
        for (Consulta c : Main.consultas) {
            if (c.getCpfPaciente().equals(getCpf()) && c.getStatus().equals("AGENDADA")) {
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

    private void verHistoricoConsultas() {
        SistemaUtil.limparTela();
        System.out.println("=== HISTÓRICO DE CONSULTAS ===");

        boolean encontrou = false;
        for (Consulta c : Main.consultas) {
            if (c.getCpfPaciente().equals(getCpf())) {
                System.out.println();
                System.out.println(c);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma consulta encontrada.");
        }

        SistemaUtil.pausar();
    }

    private void marcarConsulta() {
        SistemaUtil.limparTela();
        System.out.println("=== MARCAR NOVA CONSULTA ===");

        ArrayList<Medico> medicos = new ArrayList<>();
        for (Usuario u : Main.usuarios) {
            if (u instanceof Medico medico) {
                medicos.add(medico);
            }
        }

        if (medicos.isEmpty()) {
            System.out.println("Não há médicos cadastrados no sistema.");
            SistemaUtil.pausar();
            return;
        }

        System.out.println("Escolha o médico:");
        for (int i = 0; i < medicos.size(); i++) {
            Medico m = medicos.get(i);
            System.out.printf("%d - %s (CPF: %s, Esp.: %s)%n",
                    i + 1, m.getNome(), m.getCpf(), m.getEspecialidade());
        }
        System.out.print("Opção: ");
        int opc = Main.scanner.nextInt();
        Main.scanner.nextLine();

        if (opc < 1 || opc > medicos.size()) {
            System.out.println("Opção inválida.");
            SistemaUtil.pausar();
            return;
        }

        Medico medicoEscolhido = medicos.get(opc - 1);

        System.out.print("Data da consulta (ex: 15/11/2025): ");
        String data = Main.scanner.nextLine();
        System.out.print("Horário (ex: 14:30): ");
        String horario = Main.scanner.nextLine();

        int novoId = SistemaUtil.gerarProximoIdConsulta();
        Consulta consulta = new Consulta(novoId, getCpf(), medicoEscolhido.getCpf(),
                data, horario, "AGENDADA", "");

        Main.consultas.add(consulta);
        Main.salvarConsultas();

        System.out.println("Consulta agendada com sucesso!");
        System.out.println(consulta);
        SistemaUtil.pausar();
    }

    private void cancelarConsulta() {
        SistemaUtil.limparTela();
        System.out.println("=== CANCELAR CONSULTA ===");

        ArrayList<Consulta> minhasAgendadas = new ArrayList<>();
        for (Consulta c : Main.consultas) {
            if (c.getCpfPaciente().equals(getCpf()) && c.getStatus().equals("AGENDADA")) {
                minhasAgendadas.add(c);
            }
        }

        if (minhasAgendadas.isEmpty()) {
            System.out.println("Você não possui consultas agendadas.");
            SistemaUtil.pausar();
            return;
        }

        System.out.println("Consultas agendadas:");
        for (Consulta c : minhasAgendadas) {
            System.out.println("-------------------------");
            System.out.println(c);
        }

        System.out.print("\nDigite o ID da consulta que deseja cancelar: ");
        int id = Main.scanner.nextInt();
        Main.scanner.nextLine();

        Consulta paraCancelar = null;
        for (Consulta c : minhasAgendadas) {
            if (c.getId() == id) {
                paraCancelar = c;
                break;
            }
        }

        if (paraCancelar == null) {
            System.out.println("ID não encontrado entre suas consultas agendadas.");
            SistemaUtil.pausar();
            return;
        }

        paraCancelar.setStatus("CANCELADA");
        Main.salvarConsultas();
        System.out.println("Consulta cancelada com sucesso.");
        SistemaUtil.pausar();
    }

    // ======= FICHA MÉDICA / ANAMNESE =======

    private void menuFichaMedica() {
        int opc;
        do {
            SistemaUtil.limparTela();
            System.out.println("=== FICHA MÉDICA DO PACIENTE ===");
            System.out.println("1 - Ver ficha completa");
            System.out.println("2 - Atualizar/Preencher ficha");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opc = Main.scanner.nextInt();
            Main.scanner.nextLine();

            switch (opc) {
                case 1 -> exibirFichaMedica();
                case 2 -> atualizarFichaMedica();
                case 0 -> {}
                default -> {
                    System.out.println("Opção inválida!");
                    SistemaUtil.pausar();
                }
            }

        } while (opc != 0);
    }

    private void exibirFichaMedica() {
        SistemaUtil.limparTela();
        System.out.println("=== FICHA MÉDICA ===");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Data de nascimento: " + vazioOu(dataNascimento));
        System.out.println("Telefone: " + vazioOu(telefone));
        System.out.println("Contato de emergência: " + vazioOu(contatoEmergenciaNome));
        System.out.println("Telefone do contato: " + vazioOu(contatoEmergenciaTelefone));
        System.out.println("Alergias: " + vazioOu(alergias));
        System.out.println("Medicamentos em uso: " + vazioOu(medicamentos));
        System.out.println("Doenças crônicas: " + vazioOu(doencasCronicas));
        System.out.println("Observações gerais: " + vazioOu(observacoesGerais));
        SistemaUtil.pausar();
    }

    private void atualizarFichaMedica() {
        SistemaUtil.limparTela();
        System.out.println("=== ATUALIZAR FICHA MÉDICA ===");

        System.out.print("Data de nascimento (dd/mm/aaaa): ");
        dataNascimento = Main.scanner.nextLine();
        System.out.print("Telefone: ");
        telefone = Main.scanner.nextLine();
        System.out.print("Nome do contato de emergência: ");
        contatoEmergenciaNome = Main.scanner.nextLine();
        System.out.print("Telefone do contato de emergência: ");
        contatoEmergenciaTelefone = Main.scanner.nextLine();
        System.out.print("Alergias: ");
        alergias = Main.scanner.nextLine();
        System.out.print("Medicamentos em uso: ");
        medicamentos = Main.scanner.nextLine();
        System.out.print("Doenças crônicas: ");
        doencasCronicas = Main.scanner.nextLine();
        System.out.print("Observações gerais: ");
        observacoesGerais = Main.scanner.nextLine();

        Main.salvarUsuarios(); // persiste as alterações da ficha
        System.out.println("Ficha atualizada com sucesso!");
        SistemaUtil.pausar();
    }

    private String vazioOu(String s) {
        return (s == null || s.isBlank()) ? "(não informado)" : s;
    }
}
