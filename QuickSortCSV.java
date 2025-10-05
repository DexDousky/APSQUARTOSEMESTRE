import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuickSortCSV {

    // Método principal para iniciar o Quicksort
    public static void quicksort(List<String[]> list, int low, int high) {
        if (low < high) {
            // pi é o índice de partição, list[pi] agora está no lugar certo
            int pi = partition(list, low, high); // Partição do array e retorna o índice do pivô 

            // Chamadas recursivas para as sub-listas
            quicksort(list, low, pi - 1);
            quicksort(list, pi + 1, high);
        }
    }

    // Método de Partição do Quicksort (usaremos a primeira coluna para comparação)
    private static int partition(List<String[]> list, int low, int high) {
        // Escolhe o último elemento como pivô
        String pivot = list.get(high)[0]; // [0] -> Primeira coluna para ordenação
        int i = (low - 1); // Índice do menor elemento

        for (int j = low; j < high; j++) {
            // Se o elemento atual for "menor" ou igual ao pivô
            // Usamos compareTo() para comparação de Strings
            if (list.get(j)[0].compareTo(pivot) <= 0) { // !!! quando altera o simbolo de maior ou menor, altera a forma de ordenação.
                i++;

                // Troca list[i] e list[j]
                swap(list, i, j);
            }
        }

        // Troca o pivô (list[high]) com o elemento em list[i+1]
        swap(list, i + 1, high);

        return i + 1;
    }

    // Método auxiliar para trocar dois elementos na lista
    private static void swap(List<String[]> list, int i, int j) {
        String[] temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // Método principal (main)
    public static void main(String[] args) {
        // --- 1. DEFINA O PATH DO SEU ARQUIVO ---
        String filePath = "teste\\historico_pais_brasil.csv"; // Altere para o path real no seu computador!
        
        List<String[]> data = new ArrayList<>();
        
        try {
            // --- 2. LEITURA DOS DADOS (INTERNA) ---
            // Leitura de todas as linhas e separação das colunas por vírgula (,)
            data = Files.readAllLines(Paths.get(filePath))
                        .stream()
                        .map(line -> line.split(",")) // Divide cada linha por ','
                        .collect(Collectors.toList());

            System.out.println("--- Dados antes da ordenação ---");
            data.forEach(row -> System.out.println(String.join(" | ", row)));

            // --- 3. EXECUÇÃO DO QUICKSORT ---
            long startTime = System.nanoTime();
            quicksort(data, 0, data.size() - 1);
            long endTime = System.nanoTime();

            System.out.println("\n--- Dados DEPOIS da ordenação (Quicksort) ---");
            data.forEach(row -> System.out.println(String.join(" | ", row)));

            System.out.printf("\nOrdenação concluída em %.4f ms.%n", (endTime - startTime) / 1_000_000.0);

            // --- 4. SALVAR OS DADOS ORDENADOS (OPCIONAL) ---
            // List<String> linhasOrdenadas = data.stream()
            //         .map(row -> String.join(",", row))
            //         .collect(Collectors.toList());
            // Files.write(Paths.get("dados_ordenados.csv"), linhasOrdenadas);

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo. Verifique o path: " + filePath);
            e.printStackTrace();
        }
    }
}