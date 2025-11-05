import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BubbleSortQueimadas {
    
    private static final int TotalColuna = 13;

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

    private static void troca(List<String[]> lista, int i, int j) {
        String[] temporario = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temporario);
    }

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
    
    // NOVO MÉTODO PARA INTEGRAÇÃO COM A MAIN
    public static List<String[]> executarOrdenacao() throws IOException {
        String caminho = "arquivos\\historico_pais_brasil.csv";
        List<String[]> dados = new ArrayList<>();
        
        List<String> linhas = Files.readAllLines(Paths.get(caminho));
        for (int i = 1; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            if (linha.startsWith("Máximo") || linha.startsWith("Média") || linha.startsWith("Mínimo")) {
                continue; 
            }   
            String[] fila = linha.trim().replaceAll("\\s*,\\s*", ",").split(",");
            if (fila.length == 14) {
                dados.add(fila);
            }
        }
        bubbleSort(dados);
        return dados;
    }
}