import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;


public class OrdenacaoInternaJava {
    String Arquivo = "arquivos\\historico_pais_brasil.csv";
    
    List<String> dados = new ArrayList<>(); 

    public OrdenacaoInternaJava() {
        try {
            // Lê todas as linhas do arquivo diretamente para a List (para arquivos pequenos)
            dados = Files.readAllLines(Paths.get(Arquivo));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao ler o arquivo.");
            // Você pode lançar uma exceção ou lidar de outra forma aqui
        }
    }
}