import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    public static void main(String[] args) {
        JFrame janela = criarJanela("APS 4° Semestre", 1270, 800);
        adicionarComponentes(janela);
        janela.setVisible(true);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null); // Centraliza a janela na tela
        
    }

    private static JFrame criarJanela(String titulo, int largura, int altura) {
        JFrame janela = new JFrame(titulo);
        janela.setSize(largura, altura);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);
        return janela;
    }

    private static void adicionarComponentes(JFrame janela) {
        JLabel tituloJanela = new JLabel("TÍTULO");
        tituloJanela.setBounds(570, 50, 150, 30); // posição ajustada
        janela.add(tituloJanela);

        String textoBotao = "CLIQUE AQUI PARA MAIS";
        int larguraBotao = 200;
        int alturaBotao = 200;
        int yBotao = 250;
        int[] XPBotoes = {250, 500, 750};

        for (int x : XPBotoes) {
            JButton botao = new JButton(textoBotao);
            botao.setBounds(x, yBotao, larguraBotao, alturaBotao);
            janela.add(botao);
        }

        JLabel descricaoJanela = new JLabel("DESCRIÇÃO");
        descricaoJanela.setBounds(570, 500, 150, 30);
        janela.add(descricaoJanela);
    }
}
