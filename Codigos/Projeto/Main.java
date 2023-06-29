package Projeto;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Prim t = new Prim();

        // É possivel dizer qual arquivo executar apenas mudando o valor de n para o
        // valor do grafo escolhido (5, 10, 20, 30, 40, 50, 100)
        t.prim(5);
    }
}
