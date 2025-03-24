package desafio1;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Nome do Jogador 1: ");
        String nome1 = teclado.nextLine();
        Jogador jogador1 = new Jogador(nome1);

        System.out.print("Nome do Jogador 2: ");
        String nome2 = teclado.nextLine();
        Jogador jogador2 = new Jogador(nome2);

        boolean continuar = true;

        while (continuar) {
            System.out.print("Digite o tamanho do tabuleiro: ");
            int tamanho = teclado.nextInt();
            teclado.nextLine();

            JogoDaVelha jogo = new JogoDaVelha(tamanho);
            char simboloAtual = 'X';
            Jogador jogadorAtual = jogador1;
            boolean fimDeJogo = false;

            while (!fimDeJogo) {
                System.out.println(jogo);
                System.out.println(jogadorAtual.getNome() + " (" + simboloAtual + "), jogue!");
                System.out.print("Linha: ");
                int linha = teclado.nextInt();
                System.out.print("Coluna: ");
                int coluna = teclado.nextInt();

                boolean jogadaValida = jogo.realizarJogada(linha, coluna, simboloAtual);
                if (!jogadaValida) {
                    System.out.println("Jogada inválida, tente novamente.");
                    continue;
                }

                if (jogo.verificarVencedor()) {
                    System.out.println(jogo);
                    System.out.println(jogadorAtual.getNome() + " venceu!");
                    jogadorAtual.adicionarPonto();
                    fimDeJogo = true;
                } else if (jogo.tabuleiroCompleto()) {
                    System.out.println(jogo);
                    System.out.println("Empate!");
                    fimDeJogo = true;
                } else {
                    // alternar jogador
                    if (simboloAtual == 'X') {
                        simboloAtual = 'O';
                        jogadorAtual = jogador2;
                    } else {
                        simboloAtual = 'X';
                        jogadorAtual = jogador1;
                    }
                }
            }

            teclado.nextLine(); 
            System.out.print("Desejam jogar novamente? (s/n): ");
            String resposta = teclado.nextLine();
            if (!resposta.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }

        System.out.println("\nPontuação final:");
        System.out.println(jogador1);
        System.out.println(jogador2);

        Jogador vencedorFinal = null;
        if (jogador1.getPontos() > jogador2.getPontos()) {
            vencedorFinal = jogador1;
        } else if (jogador2.getPontos() > jogador1.getPontos()) {
            vencedorFinal = jogador2;
        }

        try (FileWriter arquivo = new FileWriter("resultado.txt")) {
            if (vencedorFinal != null) {
                arquivo.write("Vencedor Geral: " + vencedorFinal.getNome() + "\n");
                arquivo.write("Pontos: " + vencedorFinal.getPontos() + "\n");
                System.out.println("Resultado salvo em resultado.txt.");
            } else {
                arquivo.write("Empate geral entre " + jogador1.getNome() + " e " + jogador2.getNome() + "\n");
                System.out.println("Empate geral. Resultado salvo em resultado.txt.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }

        teclado.close();
    }
}


