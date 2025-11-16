import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LerArquivos {

    public static List<String> lerLinhas(String caminho) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }

    public static void gravarLinhas(String caminho, List<String> linhas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (String l : linhas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void adicionarLinha(String caminho, String linha) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true))) {
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
