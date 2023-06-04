/*
Fila, vetor
Branca: vértice não visitado
Cinza : vértice visitado
Preta : vértice foi visitado e seus vizinhos também

 * 1 para u <- 1 até n faça
 * 2 cor[u] <- branco
 * 3 cor[r] <- cinza
 * 4 F <- Cria-Fila(r)
 * 5 enquanto F não está vazia faça
 * 6 u <- Sai-da-Fila(F)
 * 7 para cada v em Adj[u] faça
 * 8 se cor[v] = branco então
 * 9 cor[v] <- cinza
 * 10 Entra-na-Fila(v, F)
 * 11 cor[u] <- preto
 * 12 devolva cor[1..n]
 */

package Grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BuscaEmLargura {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Grafos/Grafos.txt");
        Scanner scanner = new Scanner(file);
        
        int n = scanner.nextInt();
        int[][] Adj = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                Adj[i][j] = scanner.nextInt();
            }
        }

        BuscaLargura(n, Adj, 0);

        scanner.close();
    }

    public static void BuscaLargura(int n, int Adj[][], int r){
        char Cor[] = new char[n];

        for (int i = 0; i < n; i++){
            Cor[i] = 'B';
        }

        Queue<Integer> F = new LinkedList<Integer>();
        F.add(r);

        while(!F.isEmpty()){
            int u = F.poll();

            for(int v = 0; v < n; v++){
                if((Cor[v] == 'B') && (Adj[u][v] == 1)){
                    Cor[v] = 'C';
                    F.add(v);
                }
            }
            Cor[u] = 'P';
        }
        
        String cores = "Cores: [";
            for (int i = 0; i < n-1; i++){
                cores += Cor[i] + ", ";
            }
        cores += Cor[n - 1] + "]";

        System.out.println(cores);
    }
}