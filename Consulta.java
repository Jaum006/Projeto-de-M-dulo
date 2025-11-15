// Classe que representa uma consulta médica realizada ou agendada
public class Consulta {
    // Data e hora da consulta
    private String dataHora;
    // Nome do médico responsável pela consulta
    private String nomeMedico;
    // Notas ou observações sobre a consulta
    private String notas;

    // Construtor que inicializa todos os atributos da consulta
    public Consulta(String dataHora, String nomeMedico, String notas) {
        this.dataHora = dataHora;
        this.nomeMedico = nomeMedico;
        this.notas = notas;
    }

    // Retorna o nome do médico da consulta
    public String getNomeMedico() {
        return nomeMedico;
    }

    // Retorna as notas da consulta
    public String getNotas() {
        return notas;
    }

    // Retorna uma representação textual da consulta, útil para exibição
    @Override
    public String toString() {
        return "Consulta em " + dataHora +
                " com Dr(a). " + nomeMedico +
                ". Notas: " + notas;
    }
}
