package Travessia;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

class Travessia {
    public static int M[][]; // Matriz a ser preenchida

    public static void main(String[] args) {
        try {
            File file = new File("Travessia/deniveis.txt");
            Scanner scanner = new Scanner(file);

            // Pegando n
            int n = scanner.nextInt();

            // Definindo o tamanho da matriz M
            M = new int[n][n];

            // Pegando valores de M
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    M[i][j] = scanner.nextInt();
                }
            }

            // Printando a matriz M por garantia
            System.out.println(n);
            for (int i = 0; i < M.length; i++) {
                System.out.print("\n");
                for (int j = 0; j < M.length; j++) {
                    System.out.print(M[i][j] + " ");
                }
            }
            System.out.println();

            // Printando o calcTravessia
            System.out.println("\nO valor do menor caminho é: " + calcTravessia(n - 1, n - 1));

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int calcTravessia(int l, int c) {

        // Caso já esteja na posição [0][0]
        if (l == 0 && c == 0) {
            return 0;
        }

        // Caso esteja na linha 0 (borda)
        if (l == 0) {
            return (calcTravessia(0, c - 1) + Math.abs(M[0][c - 1] - M[0][c]));
        }

        // Caso esteja na coluna 0 (borda)
        if (c == 0) {
            return (calcTravessia(l - 1, 0) + Math.abs(M[l - 1][0] - M[l][0]));
        }

        // Preenche na matriz o valor mínimo para o caminho [l][c];
        // Retorna o caminho minimo
        return Math.min(calcTravessia(l - 1, c) + Math.abs(M[l][c] - M[l - 1][c]),
                calcTravessia(l, c - 1) + Math.abs(M[l][c] - M[l][c - 1]));
    }
}