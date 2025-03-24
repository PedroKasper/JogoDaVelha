package desafio1;

public class Jogador {
    private String nome;
    private int pontos;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontos = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void adicionarPonto() {
        pontos++;
    }

    @Override
    public String toString() {
        return nome + " - Pontos: " + pontos;
    }


    // exibir o estado do jogador
    public String toString1() {
        return "Jogador: " + nome + " | Pontos: " + pontos;
    }
}

