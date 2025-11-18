/* 
    Bibliotecas utilizadas:
        - javax.swing para a criação da interface gráfica
        - java.awt para manipulação de componentes gráficos
        - java.nio.file para leitura de arquivos
        - java.util.List para manipulação de listas
        - java.util.ArrayList para criação de listas dinâmicas
        - java.awt.event para tratamento de eventos (cliques nos botões)
        - java.io.IOException para tratamento de exceções de entrada/saída
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.*;

public class Janela extends Object {

    private static JTextArea areaResultados;
     
    //Configuração da janela principal e adição dos componentes (botões, área de texto, etc.)
    
    public static void main(String[] args) {
        JFrame janela = criarJanela("APS 4° Semestre - Sistema de Análise de Dados usando Sorts!", 1270, 800);
        adicionarComponentes(janela);
        janela.setVisible(true);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);
    }

    private static JFrame criarJanela(String titulo, int largura, int altura) {
        JFrame janela = new JFrame(titulo);
        janela.setSize(largura, altura);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);
        janela.getContentPane().setBackground(new Color(240, 240, 240));
        return janela;
    }

    private static void adicionarComponentes(JFrame janela) { 
        
        JLabel tituloJanela = new JLabel("SISTEMA DE ANÁLISE DE DADOS");
        tituloJanela.setBounds(230, 50, 800, 40);
        tituloJanela.setFont(new Font("Arial", Font.BOLD, 30));
        tituloJanela.setHorizontalAlignment(SwingConstants.CENTER);
        janela.add(tituloJanela);

        try {
            Font fontPersonalizada = Font.createFont(Font.TRUETYPE_FONT, new File("arquivos/tardling.ttf"));
            Font derivedFont = fontPersonalizada.deriveFont(Font.BOLD, 30); 
            tituloJanela.setFont(derivedFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Se der erro, use a fonte padrão
            tituloJanela.setFont(new Font("Arial", Font.BOLD, 30));
        }
        
        JLabel subtitulo = new JLabel("Escolha uma das opções abaixo:");
        subtitulo.setBounds(450, 90, 400, 25);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        janela.add(subtitulo);

        String[] textosBotoes = {
            "DADOS BRUTOS",
            "BUBBLE SORT", 
            "INSERTION SORT", 
            "QUICK SORT"
        };
        
        String[] descricoesBotoes = {
            "Exibe os dados originais do arquivo CSV",
            "Ordena tudo utilizando Bubble Sort",
            "Ordena tudo utilizando Insertion Sort",
            "Ordena tudo utilizando Quick Sort"
        };

        int larguraBotao = 180;
        int alturaBotao = 80;
        int yBotao = 200;
         
        int inicioArea = 100;
        int larguraArea = 1070;
        int larguraTotalBotoes = 4 * larguraBotao;
        int espacoDisponivel = larguraArea - larguraTotalBotoes;
        int espacamento = espacoDisponivel / 5; // 5 espaços (antes do 1º, entre eles e depois do último)
        
        int[] xBotoes = {
            inicioArea + espacamento,
            inicioArea + espacamento + (larguraBotao + espacamento),
            inicioArea + espacamento + 2 * (larguraBotao + espacamento),
            inicioArea + espacamento + 3 * (larguraBotao + espacamento)
        };

        for (int i = 0; i < xBotoes.length; i++) {
            JButton botao = new JButton("<html><center>" + textosBotoes[i] + "</center></html>");
            botao.setBounds(xBotoes[i], yBotao, larguraBotao, alturaBotao);
            botao.setFont(new Font("Arial", Font.BOLD, 12));
            botao.setBackground(new Color(70, 130, 180));
            botao.setForeground(Color.WHITE);
            botao.setFocusPainted(false);
            
            botao.setToolTipText(descricoesBotoes[i]);
            
            final int opcao = i;
            botao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    executarAcaoBotao(opcao);
                }
            });
            
            janela.add(botao);
        }

        JLabel labelResultados = new JLabel("RESULTADOS:");
        labelResultados.setBounds(100, 350, 150, 25);
        labelResultados.setFont(new Font("Arial", Font.BOLD, 14));
        janela.add(labelResultados);

        areaResultados = new JTextArea();
        areaResultados.setFont(new Font("Consolas", Font.PLAIN, 15)); // Mudei de ComicSans para Consolas (mais profissional)
        areaResultados.setEditable(false);
        areaResultados.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        JScrollPane scrollPane = new JScrollPane(areaResultados);
        scrollPane.setBounds(100, 380, 1070, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        janela.add(scrollPane);

        JLabel rodape = new JLabel("Desenvolvido por Isabela K. , João V., Matheus B. e Raphael S.");
        rodape.setBounds(450, 720, 400, 20);
        rodape.setFont(new Font("Arial", Font.ITALIC, 12));
        rodape.setHorizontalAlignment(SwingConstants.CENTER);
        janela.add(rodape);
    }

    private static void executarAcaoBotao(int opcao) {
        areaResultados.setText("");
        
        switch (opcao) {
            case 0:
                exibirDadosBrutos();
                break;
            case 1:
                executarBubbleSort();
                break;
            case 2:
                executarInsertionSort();
                break;
            case 3:
                executarQuickSort();
                break;
            default:
                areaResultados.setText("Opção não implementada");
        }
    }

    private static void exibirDadosBrutos() {
        areaResultados.append("=== DADOS BRUTOS DO ARQUIVO CSV ===\n\n");
        try {
            String caminho = "arquivos\\ordenada.csv"; // alteramos aqui para mudar o caminho das listas
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            
            for (String linha : linhas) {
                areaResultados.append(linha + "\n");
            }
            
            areaResultados.append("\n✓ Total de " + linhas.size() + " linhas carregadas");
            
        } catch (Exception e) {
            areaResultados.append("❌ Erro ao carregar arquivo: " + e.getMessage());
        }
    }

    private static void executarBubbleSort() {
        areaResultados.append("=== EXECUTANDO BUBBLE SORT ===\n");
        try {
            long tempoInicio = System.nanoTime();
            List<String[]> resultado = BubbleSortQueimadas.executarOrdenacao();
            long tempoFim = System.nanoTime();
            exibirResultados(resultado, "BUBBLE SORT", tempoInicio, tempoFim);
        } catch (Exception e) {
            areaResultados.append("Erro: " + e.getMessage());
        }
    }

    private static void executarInsertionSort() {
        areaResultados.append("=== EXECUTANDO INSERTION SORT ===\n");
        try {
            long tempoInicio = System.nanoTime();
            List<String[]> resultado = InsertionSortQueimadas.executarOrdenacao();
            long tempoFim = System.nanoTime();
            exibirResultados(resultado, "INSERTION SORT", tempoInicio, tempoFim);
        } catch (Exception e) {
            areaResultados.append("Erro: " + e.getMessage());
        }
    }

    private static void executarQuickSort() {
        areaResultados.append("=== EXECUTANDO QUICK SORT ===\n");
        try {
            long tempoInicio = System.nanoTime();
            List<String[]> resultado = QuickSortQueimadas.executarOrdenacao();
            long tempoFim = System.nanoTime();
            exibirResultados(resultado, "QUICK SORT", tempoInicio, tempoFim);
        } catch (Exception e) {
            areaResultados.append("Erro: " + e.getMessage());
        }
    }

    private static void exibirResultados(List<String[]> resultado, String algoritmo, long inicio, long fim) {
        areaResultados.append("Carregando dados do arquivo CSV...\n\n");
        areaResultados.append("--- Anos Ordenados por TOTAL de Queimadas (MAIOR para MENOR) ---\n");
        areaResultados.append("Ano | Total de Queimadas\n");
        areaResultados.append("-------------------------\n");
        
        for (String[] row : resultado) {
            areaResultados.append(String.format("%s | %s\n", row[0].trim(), row[13].trim()));
        }
        
        double tempoExecucao = (fim - inicio) / 1_000_000.0;
        areaResultados.append(String.format("\n✓ %s concluído em %.4f ms", algoritmo, tempoExecucao));
    }
}