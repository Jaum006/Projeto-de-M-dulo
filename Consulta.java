public class Consulta {

    private int id;
    private String cpfPaciente;
    private String cpfMedico;
    private String data;      // formato livre, ex: 15/11/2025
    private String horario;   // ex: 14:30
    private String status;    // AGENDADA, CANCELADA, CONCLUIDA
    private String observacoes; // anamnese / observações do médico

    public Consulta(int id, String cpfPaciente, String cpfMedico,
                    String data, String horario, String status, String observacoes) {
        this.id = id;
        this.cpfPaciente = cpfPaciente;
        this.cpfMedico = cpfMedico;
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.observacoes = observacoes == null ? "" : observacoes;
    }

    public int getId() {
        return id;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public String getCpfMedico() {
        return cpfMedico;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getStatus() {
        return status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    // Para salvar no arquivo consultas.txt
    public String toLinhaArquivo() {
        String obsSemPontoVirgula = observacoes.replace(";", ",");
        return id + ";" + cpfPaciente + ";" + cpfMedico + ";" +
                data + ";" + horario + ";" + status + ";" + obsSemPontoVirgula;
    }

    // Para carregar do arquivo
    public static Consulta fromLinhaArquivo(String linha) {
        String[] p = linha.split(";", 7);
        if (p.length < 6) return null;
        int id = Integer.parseInt(p[0]);
        String cpfPaciente = p[1];
        String cpfMedico = p[2];
        String data = p[3];
        String horario = p[4];
        String status = p[5];
        String observacoes = p.length >= 7 ? p[6] : "";
        return new Consulta(id, cpfPaciente, cpfMedico, data, horario, status, observacoes);
    }

    @Override
    public String toString() {
        return "Consulta ID: " + id +
               "\nData: " + data + " às " + horario +
               "\nPaciente (CPF): " + cpfPaciente +
               "\nMédico (CPF): " + cpfMedico +
               "\nStatus: " + status +
               (observacoes.isBlank() ? "" : "\nObservações/Anamnese: " + observacoes);
    }
}
