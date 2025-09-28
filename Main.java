import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Main {

public static void main (String[] args) {
    JFrame Janela = new JFrame ("Aps 4° Semestre");
    
    // - CONFIGURAÇÕES DE JANELA -
    
    Janela.setSize(1270,800);
    Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Janela.add(new JLabel("Faz o L"));
    Janela.setVisible(true);

    // - BOTÕES -
    
    JButton botao_queimadas = new JButton("Queimadas");
    botao_queimadas.setBounds(0,0,100,100);
    JButton botao = new JButton("Num sei");
    botao.setBounds(100,0,100,100);
        
    Janela.add(botao_queimadas);
    Janela.add(botao);
    }
}