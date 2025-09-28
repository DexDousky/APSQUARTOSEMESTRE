import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Main {

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
        
    Janela.add(botao_queimadas);
    Janela.add(botao);
    }
}