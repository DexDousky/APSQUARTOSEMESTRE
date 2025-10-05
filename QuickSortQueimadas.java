import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class QuickSortQueimadas { // ATENCAO ATECAO!! NÃO APAGAR!!!!!!!
    // ISSO É UM TESTE DE ORNDENAR A LISTA POR MAIOR(ou menor) NUMERO DE QUEIMADAS POR ANO!!

    // --- VARIÁVEIS CHAVE ---
    // O índice da coluna 'Total' (considerando 'Ano' como 0) é 13.
    // O seu arquivo de entrada tem 14 colunas no total (0 a 13).
    private static final int TOTAL_COLUMN_INDEX = 13; 
    
    // --- MÉTODOS DE ORDENAÇÃO ---

    // Método principal para iniciar o Quicksort
    public static void quicksort(List<String[]> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);

            quicksort(list, low, pi - 1);
            quicksort(list, pi + 1, high);
        }
    }

    // Método de Partição para Ordenação DECRESCENTE pela coluna TOTAL
    private static int partition(List<String[]> list, int low, int high) {
        // Obter o valor numérico do pivô (último elemento)
        double pivotValue = parseDoubleSafe(list.get(high)[TOTAL_COLUMN_INDEX]); // Converte para double de forma segura
        
        int i = (low - 1); // Índice do menor elemento

        for (int j = low; j < high; j++) {
            double currentValue = parseDoubleSafe(list.get(j)[TOTAL_COLUMN_INDEX]); 
            
            // --- AJUSTE PARA ORDENAÇÃO DECRESCENTE (MAIOR PARA O MENOR) ---
            // Se o elemento atual for MAIOR ou igual ao pivô
            if (currentValue >= pivotValue) { // !!! se alterar ali o simbolo de maior ou menor, altera a forma de ordeanação.
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
    
    // Método para converter String para Double de forma segura, 
    // tratando colunas vazias ou não-numéricas como 0.0
    private static double parseDoubleSafe(String s) {
        // Verifica se a string está vazia ou contém apenas espaços (caso do seu último registro)
        if (s == null || s.trim().isEmpty()) {
            return 0.0;
        }
        try {
            // Remove espaços e converte.
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            // Para lidar com o cabeçalho ("Total") e linhas de média/máximo/mínimo
            // Retornamos 0.0 para que essas linhas sejam ignoradas ou fiquem no final.
            return 0.0; 
        }
    }


    // --- MÉTODO PRINCIPAL (MAIN) ---
    public static void main(String[] args) {
        // O Path para o seu arquivo CSV
        String filePath = "teste\\historico_pais_brasil.csv"; 
        
        // A lista principal armazenará cada linha como um array de Strings.
        List<String[]> data = new ArrayList<>();
        
        try {
            // --- 1. LEITURA DOS DADOS (INTERNA) ---
            // Define o delimitador como vírgula e remove o cabeçalho (a primeira linha)
            List<String> allLines = Files.readAllLines(Paths.get(filePath));
            
            // A partir da segunda linha (índice 1)
            for (int i = 1; i < allLines.size(); i++) {
                String line = allLines.get(i);
                // Ignorar linhas de resumo que não são anos (Ex: Máximo*, Média*)
                if (line.startsWith("Máximo") || line.startsWith("Média") || line.startsWith("Mínimo")) {
                    continue; 
                }
                
                // Trata o CSV: remove espaços e divide pela vírgula
                String[] row = line.trim().replaceAll("\\s*,\\s*", ",").split(",");
                
                // Adiciona apenas as linhas completas (25 linhas de dados de 1998 a 2025)
                if (row.length == 14) { 
                    data.add(row);
                }
            }
            
            // --- 2. EXECUÇÃO DO QUICKSORT ---
            long startTime = System.nanoTime();
            quicksort(data, 0, data.size() - 1);
            long endTime = System.nanoTime();

            // --- 3. EXIBIÇÃO DOS RESULTADOS ---
            System.out.println("--- Anos Ordenados por TOTAL de Queimadas (MAIOR para MENOR) ---");
            System.out.println("Ano | Total de Queimadas");
            System.out.println("-------------------------");
            
            data.forEach(row -> 
                System.out.printf("%s | %s%n", row[0].trim(), row[TOTAL_COLUMN_INDEX].trim())
            );

            System.out.printf("\nOrdenação concluída em %.4f ms.%n", (endTime - startTime) / 1_000_000.0);

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo. Verifique se o arquivo '" + filePath + "' existe no diretório do projeto.");
            e.printStackTrace();
        }
    }
}