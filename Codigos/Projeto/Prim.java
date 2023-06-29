package Projeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Prim {
	// Quantia de vértices do grafo
	public static int n;

	// Prim utilizando o caminho do arquivo
	public void prim(int n) throws FileNotFoundException {
		// Inicia o timer de execução
		long startTime = System.nanoTime();

		// Ponteiro para o arquivo
		File file = new File("Projeto/Grafos/Grafo" + n + ".txt");
		Scanner scanner = new Scanner(file);

		// Lê a quantia de vértices e atribue a n
		Prim.n = scanner.nextInt();

		// Atribue o tamanho da matriz
		int[][] Adj = new int[n][n];

		// Lê a matriz de adjacência do grafo
		for (int i = 0; i < n; i++) { // O(n²)
			for (int j = 0; j < n; j++) {
				Adj[i][j] = scanner.nextInt();
			}
		}

		// Chama a função prim usando a matriz de adjacência
		prim(Adj);

		// Fecha scanner
		scanner.close();

		// Para o timer de execução
		long stopTime = System.nanoTime();
		// Calcula a tempo de execução
		long duration = stopTime - startTime;
		// Converte de Nanosegundos para milisegundos (poderia só dividir por 10^6)
		duration = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS);
		// Printa o tempo de execução
		System.out.println("Tempo de execução: " + duration + " milisegundos");
	}

	// Função do Algoritmo de Prim
	void prim(int Adj[][]) {
		// Vetor para ter como informação o pai de cada vértice
		int pai[] = new int[n];

		// Vetor para sabe o peso de cada aresta e comparar
		int peso[] = new int[n];

		// Apenas para ter uma confirmação de verdadeiro caso
		// o número esteja já na AGM
		Boolean arvoreAGM[] = new Boolean[n];

		// Inicia todos os pesos em infinito
		// E poe como falso os que não visitou ainda
		for (int i = 0; i < n; i++) { // O(2n)
			peso[i] = Integer.MAX_VALUE;
			arvoreAGM[i] = false;
		}

		// Sempre incluir o vétice escolhido já na AGM
		// e torna o peso 0 para ele já ser o menor
		peso[0] = 0;

		// O primeiro sempre vai ser a raiz. Ou seja, não tem pai (-1 está fora)
		pai[0] = -1;

		for (int i = 0; i < n - 1; i++) { // O(n)

			// Pega o menor peso de aresta até algum vértice de fora da AGM
			int u = pesoMin(peso, arvoreAGM);

			// Atualiza sobre o vértice estar na AGM
			arvoreAGM[u] = true;

			// Atualiza os valores considerando apenas os de fora da arvore
			for (int v = 0; v < n; v++) // O(n)

				// Verifica se u e v são adjacentes
				// Se v não foi visitado
				// e se o peso do analisado for menor a registrada
				// no vetor de peso
				// então atualiza os valores
				if (Adj[u][v] != 0 && arvoreAGM[v] == false && Adj[u][v] < peso[v]) { // O(n²)
					pai[v] = u;
					peso[v] = Adj[u][v];
				}
		}

		// Printa a AGM
		printAGM(pai, Adj);
	}

	// Função para printar a AGM
	void printAGM(int pai[], int Adj[][]) {
		System.out.println("Aresta \tPeso");
		for (int i = 1; i < n; i++)
			System.out.println(pai[i] + " - " + i + "\t"
					+ Adj[i][pai[i]]);
	}

	// Função para encontrar o peso minimo a partir da tabela de pesos e dos
	// vertices que não foram incluidos
	int pesoMin(int peso[], Boolean arvoreAGM[]) {
		// Inicializa o valor minimo com infinito para a primeira execução
		int min = Integer.MAX_VALUE;
		// Iniciando o valor do index do minimo
		int min_index = -1;

		for (int v = 0; v < n; v++)
			// Se o vértice não está na arvoreAGM
			// E o peso deles for menor que o min atual, substitue o anterior
			if (arvoreAGM[v] == false && peso[v] < min) {
				min = peso[v];
				min_index = v;
			}

		// Retorna o index do menor
		return min_index;
	}
}