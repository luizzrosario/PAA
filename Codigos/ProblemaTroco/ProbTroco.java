package ProblemaTroco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProbTroco {

    public static void main(String[] args) {
        /*
         * int[] moedas = { 1, 3, 4, 5};
         * int troco = 7;
         */
        try {
            int troco = 7; // Aqui vem o valor do troco

            // Lendo arquivo
            File file = new File("Codigos/ProblemaTroco/Moedas.txt");
            Scanner scanner = new Scanner(file);
            // primeiro inteiro vai ser já a quantia de moedas M
            int M = scanner.nextInt();
            // Criando e preenchendo o vetor de tamanho M com os valores das moedas
            int[] moedas = new int[M];
            for (int i = 0; i < M; i++) {
                moedas[i] = scanner.nextInt();
            }

            // Printando saídas
            System.out.println("Quantia de moedas: " + M);
            System.out.println("Valores das moedas:");
            for (int i = 0; i < moedas.length; i++) {
                System.out.println(moedas[i]);
            }
            System.out.println("Troco: " + troco);
            System.out.println("Min de moedas para o troco: " + calcMin(moedas, troco));

            scanner.close();
        } catch (FileNotFoundException e) { // Catch para caso o arquivo não for encontrado
            e.printStackTrace();
        }

    }

    public static int calcMin(int[] moedas, int troco) {
        int[] valores = new int[troco + 1];

        // foreach que percorre todas as moedas
        for (int moedaAtual : moedas) {
            // percorre todas as moedas a partir da moeda atual para ter todas as combinações possíveis
            for (int valorAtual = moedaAtual; valorAtual < valores.length; valorAtual++) {
                // valor restante do troco
                int diferenca = valorAtual - moedaAtual;

                // serve para que se já não houver mais solução, evitar mais calculos
                if (diferenca > 0 && valores[diferenca] == 0) {
                    continue;
                }
                // verifica se ainda não tem solução para este valor
                if (valores[valorAtual] == 0) {
                    // atribue o minimo de moedas que é 1 + o valor para a diferença
                    valores[valorAtual] = 1 + valores[diferenca];
                } else {
                    // Verifica se a nova solução encontrada é melhor (menor) do que a solução anterior. A função Math.min é usada para atualizar o valor mínimo no vetor valores.
                    valores[valorAtual] = Math.min(valores[valorAtual], 1 + valores[diferenca]);
                }
            }
        }

        // Retorna o valor minimo de moedas. E se for igual a 0, retorna -1
        return valores[troco] == 0 ? -1 : valores[troco];
    }
}
