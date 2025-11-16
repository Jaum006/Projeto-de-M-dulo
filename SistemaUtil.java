import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaUtil {

    // ======= ARQUIVOS =======

    // Lê todas as linhas de um arquivo texto
    public static List<String> lerLinhas(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (FileNotFoundException e) {
            // se o arquivo não existir ainda
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + nomeArquivo);
            e.printStackTrace();
        }
        return linhas;
    }

    // Sobrescreve o arquivo com as linhas fornecidas
    public static void escreverLinhas(String nomeArquivo, List<String> linhas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (String l : linhas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo: " + nomeArquivo);
            e.printStackTrace();
        }
    }

    // Acrescenta uma linha ao final do arquivo
    public static void acrescentarLinha(String nomeArquivo, String linha) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao acrescentar em arquivo: " + nomeArquivo);
            e.printStackTrace();
        }
    }

    // ======= INTERFACE / TERMINAL =======

    public static void limparTela() {
        try {
            // ANSI escape (funciona na maioria dos terminais modernos)
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            // fallback: imprime várias linhas
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    public static void pausar() {
        System.out.println();
        System.out.print("Pressione ENTER para continuar...");
        Main.scanner.nextLine();
    }

    // ======= BUSCAS / IDs =======

    public static Usuario buscarUsuarioPorCpf(String cpf) {
        for (Usuario u : Main.usuarios) {
            if (u.getCpf().equals(cpf)) {
                return u;
            }
        }
        return null;
    }

    public static int gerarProximoIdConsulta() {
        int maior = 0;
        for (Consulta c : Main.consultas) {
            if (c.getId() > maior) maior = c.getId();
        }
        return maior + 1;
    }
}
