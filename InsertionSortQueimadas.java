import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InsertionSortQueimadas {

    private static final int TotalColuna = 13;

    public static void InsertionSort(List<String[]> lista) {
        int tamanhoLista = lista.size();

        for (int i = 1; i < tamanhoLista; i++) {
            String[] elementoAtual = lista.get(i);
            double valorAtual = parseDoubleSafe(elementoAtual[TotalColuna]);
            int j = i - 1;

            while (j >= 0) {
                double valorComparado = parseDoubleSafe(lista.get(j)[TotalColuna]);

                if (valorComparado < valorAtual) {
                    lista.set(j + 1, lista.get(j));
                    j--;
                } 
                else {
                    break;
                }
            }
            lista.set(j + 1, elementoAtual);
        }
    }

    private static double parseDoubleSafe(String valor) {
        if (valor ==  null || valor.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // NOVO MÉTODO PARA INTEGRAÇÃO COM A MAIN
    public static List<String[]> executarOrdenacao() throws IOException {
        String caminho = "arquivos\\historico_pais_brasil.csv";
        List<String[]> data = new ArrayList<>();
        
        List<String> linhas = Files.readAllLines(Paths.get(caminho));
        for (int i = 1; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            if (linha.startsWith("Máximo") || linha.startsWith("Média") || linha.startsWith("Mínimo")) {
                continue; 
            }   
            String[] fila = linha.trim().replaceAll("\\s*,\\s*", ",").split(",");
            if (fila.length == 14) {
                data.add(fila);
            }
        }
        InsertionSort(data);
        return data;
    }

    public static void main(String[] args) {
        String caminho = "arquivos\\historico_pais_brasil.csv";
        List<String[]> data = new ArrayList<>();

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));

            for (int i = 1; i < linhas.size(); i++) {
                String linha = linhas.get(i);
                if (linha.startsWith("Máximo") || linha.startsWith("Média") || linha.startsWith("Mínimo")) {
                    continue; 
                }   
                String[] fila = linha.trim().replaceAll("\\s*,\\s*", ",").split(",");
                if (fila.length == 14) {
                    data.add(fila);
                }
            }

            long tempoInicio = System.nanoTime();
            InsertionSort(data);
            long tempoFim = System.nanoTime();

            System.out.println("--- Anos Ordenados pro TOTAL de Queimadas (MAIOR para MENOR) Insertion Sort --- \n Ano | Total de Queimadas \n------------------------- ");

            data.forEach(row -> {
                System.out.printf("%s | %s%n", row[0].trim(), row[TotalColuna].trim());
            });
            
            System.out.printf("\nOrdem executada em %.4f ms.%n", (tempoFim - tempoInicio) / 1_000_000.0);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo. Verifique se o arquivo '" + caminho + "' existe no diretório do projeto.");
            e.printStackTrace();
        }
    }
}