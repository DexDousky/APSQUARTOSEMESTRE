import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class QuickSortQueimadas {
    private static final int TotalColuna = 13;
    
    public static void quicksort(List<String[]> lista, int menor, int maior) {
        if (menor < maior) {
            int pi = partition(lista, menor, maior);
            quicksort(lista, menor, pi - 1);
            quicksort(lista, pi + 1, maior);
        }
    }

    private static int partition(List<String[]> lista, int menor, int maior) {
        double pivoValor = parseDoubleSafe(lista.get(maior)[TotalColuna]);
        int i = (menor - 1);

        for (int j = menor; j < maior; j++) {
            double valorAtual = parseDoubleSafe(lista.get(j)[TotalColuna]);

            if (valorAtual >= pivoValor) {
                i++;
                troca(lista, i, j);
            }
        }

        troca(lista, i + 1, maior);
        return i + 1;
    }

    private static void troca(List<String[]> lista, int i, int j) {
        String[] temporario = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temporario);
    }
    
    private static double parseDoubleSafe(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(valor.trim());
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
            String[] row = linha.trim().replaceAll("\\s*,\\s*", ",").split(",");
            if (row.length == 14) { 
                data.add(row);
            }
        }
        quicksort(data, 0, data.size() - 1);
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
                
                String[] row = linha.trim().replaceAll("\\s*,\\s*", ",").split(",");
                
                if (row.length == 14) { 
                    data.add(row);
                }
            }
            
            long tempoInicio = System.nanoTime();
            quicksort(data, 0, data.size() - 1);
            long tempoFim = System.nanoTime();

            System.out.println("--- Anos Ordenados pro TOTAL de Queimadas (MAIOR para MENOR) Quick Sort --- \n Ano | Total de Queimadas \n------------------------- ");
            
            data.forEach(row -> 
                System.out.printf("%s | %s%n", row[0].trim(), row[TotalColuna].trim()));

            System.out.printf("\nOrdem executada em %.4f ms.%n", (tempoFim - tempoInicio) / 1_000_000.0);

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo. Verifique se o arquivo '" + caminho + "' existe no diretório do projeto.");
            e.printStackTrace();
        }
    }
}