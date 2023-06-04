package Grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BuscaEmProfundidade {
    public static char Cor[];
    public static int Pred[];
    public static int Adj[][];
    public static int n;
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Grafos/Grafos.txt");
        Scanner scanner = new Scanner(file);
        
        n = scanner.nextInt();
        Adj = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                Adj[i][j] = scanner.nextInt();
            }
        }

        System.out.println(n);
        for (int i = 0; i < Adj.length; i++) {
            System.out.print("\n");
            for (int j = 0; j < Adj.length; j++) {
                System.out.print(Adj[i][j] + " ");
            }
        }
        System.out.println();

        BuscaProfundidade(n, 0);
        
        String cores = "Cores: [";
            for (int i = 0; i < n-1; i++){
                cores += Cor[i] + ", ";
            }
        cores += Cor[n - 1] + "]";

        System.out.println(cores);

        scanner.close();
    }

    public static void BuscaProfundidade(int n, int r){
        Cor = new char[n];
        Pred = new int[n];

        for (int w = 0; w < Cor.length; w++) {
            Cor[w] = 'B';
            Pred[w] = Integer.MAX_VALUE;
        }

        for (int u = 0; u < Adj.length; u++) {
            if (Cor[u] == 'B'){
                Visite(u);
            }
        }
    }

    public static void Visite (int r){
        Cor[r] = 'C';
        for (int v = 0; v < Cor.length; v++) {
            if ((Cor[v] == 'B') && (Adj[r][v] == 1)){
                Pred[v] = r;
                Visite(v);
                System.out.println("foi");
            }
        }
        Cor[r] = 'P';
    }
}
