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
    private static final int TotalColuna = 13;
    
    // --- MÉTODOS DE ORDENAÇÃO ---

    // Método principal para iniciar o Quicksort
    public static void quicksort(List<String[]> lista, int menor, int maior) {
        if (menor < maior) {
            int pi = partition(lista, menor, maior);

            quicksort(lista, menor, pi - 1);
            quicksort(lista, pi + 1, maior);
        }
    }

    // Método de Partição para Ordenação DECRESCENTE pela coluna TOTAL
    private static int partition(List<String[]> lista, int menor, int maior) {
        // Obter o valor numérico do pivô (último elemento)
        double pivoValor = parseDoubleSafe(lista.get(maior)[TotalColuna]); // Converte para double de forma segura

        int i = (menor - 1); // Índice do menor elemento

        for (int j = menor; j < maior; j++) {
            double valorAtual = parseDoubleSafe(lista.get(j)[TotalColuna]);

            // --- AJUSTE PARA ORDENAÇÃO DECRESCENTE (MAIOR PARA O MENOR) ---
            // Se o elemento atual for MAIOR ou igual ao pivô
            if (valorAtual >= pivoValor) { // !!! se alterar ali o simbolo de maior ou menor, altera a forma de ordeanação.
                i++;
                // Troca lista[i] e lista[j]
                troca(lista, i, j);
            }
        }

        // Troca o pivô (list[high]) com o elemento em list[i+1]
        troca(lista, i + 1, maior);

        return i + 1;
    }

    // Método auxiliar para trocar dois elementos na lista
    private static void troca(List<String[]> lista, int i, int j) {
        String[] temporario = lista.get(i); // Armazena temporariamente o valor de lista[i]
        lista.set(i, lista.get(j));
        lista.set(j, temporario);
    }
    
    // Método para converter String para Double de forma segura, 
    // tratando colunas vazias ou não-numéricas como 0.0
    private static double parseDoubleSafe(String valor) { // valor é a string que queremos converter
        // Verifica se a string está vazia ou contém apenas espaços (caso do seu último registro)
        if (valor == null || valor.trim().isEmpty()) {
            return 0.0;
        }
        try {
            // Remove espaços e converte.
            return Double.parseDouble(valor.trim());
        } catch (NumberFormatException e) {
            // Para lidar com o cabeçalho ("Total") e linhas de média/máximo/mínimo
            // Retornamos 0.0 para que essas linhas sejam ignoradas ou fiquem no final.
            return 0.0; 
        }
    }


    // --- MÉTODO PRINCIPAL (MAIN) ---
    public static void main(String[] args) {
        // O Path para o seu arquivo CSV
        String caminho = "arquivos\\historico_pais_brasil.csv"; 
        
        // A lista principal armazenará cada linha como um array de Strings.
        List<String[]> data = new ArrayList<>();
        
        try {
            // --- 1. LEITURA DOS DADOS (INTERNA) ---
            // Define o delimitador como vírgula e remove o cabeçalho (a primeira linha)
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            
            // A partir da segunda linha (índice 1)
            for (int i = 1; i < linhas.size(); i++) {
                String linha = linhas.get(i);
                // Ignorar linhas de resumo que não são anos (Ex: Máximo*, Média*)
                if (linha.startsWith("Máximo") || linha.startsWith("Média") || linha.startsWith("Mínimo")) {
                    continue; 
                }
                
                // Trata o CSV: remove espaços e divide pela vírgula
                String[] row = linha.trim().replaceAll("\\s*,\\s*", ",").split(",");
                
                // Adiciona apenas as linhas completas (25 linhas de dados de 1998 a 2025)
                if (row.length == 14) { 
                    data.add(row);
                }
            }
            
            // --- 2. EXECUÇÃO DO QUICKSORT ---
            long tempoInicio = System.nanoTime();
            quicksort(data, 0, data.size() - 1);
            long tempoFim = System.nanoTime();

            // --- 3. EXIBIÇÃO DOS RESULTADOS ---
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