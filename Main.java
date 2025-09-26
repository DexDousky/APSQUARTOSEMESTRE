import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

public static void main (String[] args) {
    JFrame Janela = new JFrame ("Aps 4° Semestre");
    Janela.setSize(500,400);
    Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Janela.add(new JLabel("Comentário da janela"));
    Janela.setVisible(true);
}}