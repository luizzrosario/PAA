package Bastão;

public class Bastao {
    // Função para calcular a soma máxima em dinheiro possível
    public static int calcularSomaMaxima(int[] precos, int tamanho) {
        // Caso base: quando o tamanho do bastão é zero, a soma é zero
        if (tamanho == 0) {
            return 0;
        }
        
        int somaMaxima = Integer.MIN_VALUE;
        
        // Percorre todos os possíveis cortes do bastão
        for (int i = 1; i <= tamanho; i++) {
            // Calcula a soma máxima ao considerar o corte de tamanho i e recursivamente calcular a soma máxima para o restante do bastão
            int soma = precos[i - 1] + calcularSomaMaxima(precos, tamanho - i);
            
            // Atualiza a soma máxima se necessário
            if (soma > somaMaxima) {
                somaMaxima = soma;
            }
        }
        
        return somaMaxima;
    }
    
    public static void main(String[] args) {
        int[] precos = {3, 4, 8, 10, 10, 11, 23, 23, 24, 25};
        int tamanhoBastao = 10;
        
        int somaMaxima = calcularSomaMaxima(precos, tamanhoBastao);
        System.out.println("Soma máxima em dinheiro possível: " + somaMaxima);
    }
}

