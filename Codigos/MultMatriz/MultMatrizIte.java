package MultMatriz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MultMatrizIte {

    public static void main(String[] args) {
        try {
            File file = new File("Codigos/MultMatriz/Dimensoes.txt");
            Scanner scanner = new Scanner(file);

            // Lê a quantia de matrizes
            int n = scanner.nextInt();

            // Cria um array d para armazenar as dimensões das matrizes.
            int[] d = new int[n + 1];

            // Chama a função readIndexes para ler as dimensões e atribuir os valores
            // apropriados ao array d.
            readIndexes(scanner, d, n);

            // Printa o multMatriz
            System.out.println(multMatriz(d, n));

            scanner.close();
        } catch (FileNotFoundException e) { // Catch para caso não seja encontrado o arquivo
            e.printStackTrace();
        }
    }

    public static int multMatriz(int[] d, int n) {
        // Cria uma matriz pra armazenar os valores intermediarios
        int[][] m = new int[n + 1][n + 1];

        // Cria uma matriz para armazenar a ordem dos parênteses
        int[][] paren = new int[n + 1][n + 1];

        // Inicia as matrizes da diagonal principal com 0 pois multiplicação de uma
        // unica matriz tem custo 0
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        // Loop externo para percorrer o número de submatrizes a serem multiplicadas em
        // cada iteração
        for (int I = 1; I < n; I++) {
            // Loop interno para percorrer as posições iniciais das submatrizes
            for (int i = 1; i <= n - I; i++) {
                // Posição final da submatriz
                int j = i + I;

                // Define um valor inicialmente infinito para o custo da submatriz
                m[i][j] = Integer.MAX_VALUE;

                // Calcula o custo da multiplicação das submatrizes na posição k
                for (int k = i; k <= j - 1; k++) {
                    // Cálculo do custo para a multiplicação das submatrizes nas posições i, k e
                    // k+1, j,
                    // considerando as dimensões das matrizes e os custos já calculados
                    // anteriormente
                    int q = m[i][k] + m[k + 1][j] + d[i - 1] * d[k] * d[j];

                    // Verifica se o custo calculado é menor do que o custo atualmente armazenado
                    if (q < m[i][j]) {
                        m[i][j] = q; // Atualiza o custo mínimo para a posição i, j
                        paren[i][j] = k; // Armazena a posição k como o ponto de divisão
                    }
                }
            }
        }
        System.out.println(printParen(paren, 1, n));
        // Retorna o custo mínimo da multiplicação de todas as matrizes
        return m[1][n];
    }

    // Cria a string que mostrará como é a ordem dos parentesis na menor
    // multiplicação
    public static String printParen(int[][] paren, int i, int j) {
        if (i == j) {
            return "M" + i;
        } else {
            StringBuilder sb = new StringBuilder(); // usar o string builder pra construir a string de parentesis
            sb.append("(");
            sb.append(printParen(paren, i, paren[i][j]));
            sb.append(printParen(paren, paren[i][j] + 1, j));
            sb.append(")");
            return sb.toString();
        }
    }

    // Basicamente, o código lê as dimensões das matrizes do arquivo e armazena
    // essas dimensões no array d.
    public static void readIndexes(Scanner scanner, int[] d, int n) {
        // Cria um novo array chamado d2 com o mesmo tamanho que o número de matrizes n
        int[] d2 = new int[n];

        // Loop para ler as dimensões das matrizes do arquivo
        for (int i = 0; i < n; i++) {
            d[i] = scanner.nextInt();
            d2[i] = scanner.nextInt();
        }

        // Atribui o valor da última dimensão lida (em d2[n - 1]) ao último elemento do
        // array d.
        // Para completar as dimensões das matrizes quando não há segunda dimensão
        // especificada no arquivo.
        d[n] = d2[n - 1];
    }
}