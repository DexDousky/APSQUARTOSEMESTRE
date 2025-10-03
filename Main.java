import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

<<<<<<< Updated upstream
public static void main (String[] args) {
    JFrame Janela = new JFrame ("Aps 4° Semestre");
    
    // - CONFIGURAÇÕES DE JANELA -
    
    Janela.setSize(1260,740);
    Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Janela.setResizable(false);

    Janela.setVisible(true);

    // - BOTÕES E VISUAL -
    

    JLabel TituloJanela = new JLabel("insira aq nome do nome da tela e deixar no topo");
    TituloJanela.setBounds(500,0,500,500);
    Janela.add(TituloJanela);

    JLabel TelaDesc = new JLabel("descrição do projeto da tela princiapl");
    TelaDesc.setBounds(500,200,100,100);
    Janela.add(TelaDesc);

    JButton botao_queimadas = new JButton("Queimadas");
    botao_queimadas.setBounds(0,0,100,100);
    JButton botao = new JButton("Num sei");
    botao.setBounds(100,0,100,100);
=======
    public static void main(String[] args) {
        JFrame janela = criarJanela("APS 4° Semestre", 1270, 800);
        adicionarComponentes(janela);
        janela.setVisible(true);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null); // Centraliza a janela na tela
>>>>>>> Stashed changes
        
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
