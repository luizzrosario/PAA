package Projeto;

import java.io.FileWriter;
import java.io.IOException;

// Gerador de grafos de tamanho n

public class Gerador {
    public static int n;

    public static void main(String[] args) throws IOException {
        geraMatriz(5);
        geraMatriz(10);
        geraMatriz(20);
        geraMatriz(30);
        geraMatriz(40);
        geraMatriz(50);
        geraMatriz(100);
    }

    public static void geraMatriz(int Tamanho) throws IOException {
        Gerador.n = Tamanho;
        // Tamanho da matriz de adjacência
        int[][] adjacencyMatrix = generateAdjacencyMatrix(n);

        // Nome do arquivo de saída
        String fileName = "Projeto/Grafos/Grafo" + n + ".txt";

        // Escreve a matriz de adjacência no arquivo
        writeAdjacencyMatrixToFile(adjacencyMatrix, fileName);
    }

    public static int[][] generateAdjacencyMatrix(int n) {
        int[][] adjacencyMatrix = new int[n][n];
        // Preenche a matriz de adjacência com valores aleatórios 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    // Não há conexão de um vértice para ele mesmo
                    adjacencyMatrix[i][j] = 0; 
                } else {
                    // Gera um valor aleatório de 1 a 100 e atribue aos espelhos
                    adjacencyMatrix[i][j] = (int) (Math.random() * 100) + 1; 
                    adjacencyMatrix[j][i] = adjacencyMatrix[i][j]; 
                }
            }
        }
        return adjacencyMatrix;
    }

    public static void writeAdjacencyMatrixToFile(int[][] adjacencyMatrix, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(n + "\n");
        // Escreve a matriz de adjacência no arquivo
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                writer.write(adjacencyMatrix[i][j] + " ");
            }
            writer.write("\n");
        }
        writer.close();
        System.out.println("Matriz de adjacência gravada no arquivo " + fileName);
    }
}
