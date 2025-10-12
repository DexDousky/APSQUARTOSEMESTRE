import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BubbleSortQueimadas {
    
    private static final int TotalColuna= 13;  // Índice da coluna 'Total'

    public static void bubbleSort(List<String[]> lista) {
        int tamanhoLista = lista.size(); 

        for (int i = 0; i < tamanhoLista - 1; i++) {
            for (int j = 0; j < tamanhoLista - i - 1; j++) {
                double valor1 = parseDoubleSafe(lista.get(j)[TotalColuna]);
                double valor2 = parseDoubleSafe(lista.get(j + 1)[TotalColuna]);

                if (valor1 < valor2) {
                    troca(lista, j, j + 1);
                }
            }
        }
    }

    // Método auxiliar para trocar dois elementos na lista
    private static void troca(List<String[]> lista, int i, int j) {
        String[] temporario = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temporario);
    }

    // Método para converter String para Double de forma segura 
    private static double parseDoubleSafe(String valor) {
        if (valor == null || valor.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(valor.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
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
            bubbleSort(data);
            long tempoFim = System.nanoTime();

            System.out.println("--- Anos Ordenados pro TOTAL de Queimadas (MAIOR para MENOR) Bubble Sort ---");
            System.out.println("Ano | Total de Queimadas");
            System.out.println("-------------------------");

            data.forEach(row -> 
            System.out.printf("%s | %s%n", row[0].trim(), row[TotalColuna].trim()));

            System.out.printf("\nOrdem executada em %.4f ms.%n", (tempoFim - tempoInicio) / 1_000_000.0); 
            //O que é esse %.4f ms.%? ele pega os milisegundos e transforma em segundos?

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo. Verifique se o arquivo '" + caminho + "' existe no diretório do projeto.");
            e.printStackTrace();
        }
    }
}