package desafio1;

public class JogoDaVelha {
    private char[][] tabuleiro;
    private int tamanho;

    public JogoDaVelha(int tamanho) {
        this.tamanho = tamanho;
        tabuleiro = new char[tamanho][tamanho];
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    public boolean realizarJogada(int linha, int coluna, char simbolo) {
        if (linha < 0 || linha >= tamanho || coluna < 0 || coluna >= tamanho) {
            return false;
        }
        if (tabuleiro[linha][coluna] != ' ') {
            return false;
        }
        tabuleiro[linha][coluna] = simbolo;
        return true;
    }

    public boolean verificarVencedor() {
        // verifica linhas
        for (int i = 0; i < tamanho; i++) {
            char primeiro = tabuleiro[i][0];
            if (primeiro != ' ') {
                boolean venceu = true;
                for (int j = 1; j < tamanho; j++) {
                    if (tabuleiro[i][j] != primeiro) {
                        venceu = false;
                        break;
                    }
                }
                if (venceu) return true;
            }
        }

        // verifica colunas
        for (int j = 0; j < tamanho; j++) {
            char primeiro = tabuleiro[0][j];
            if (primeiro != ' ') {
                boolean venceu = true;
                for (int i = 1; i < tamanho; i++) {
                    if (tabuleiro[i][j] != primeiro) {
                        venceu = false;
                        break;
                    }
                }
                if (venceu) return true;
            }
        }

        //verifica diagonal principal
        char diagPrincipal = tabuleiro[0][0];
        if (diagPrincipal != ' ') {
            boolean venceu = true;
            for (int i = 1; i < tamanho; i++) {
                if (tabuleiro[i][i] != diagPrincipal) {
                    venceu = false;
                    break;
                }
            }
            if (venceu) return true;
        }

        // verifica diagonal secundária
        char diagSecundaria = tabuleiro[0][tamanho - 1];
        if (diagSecundaria != ' ') {
            boolean venceu = true;
            for (int i = 1; i < tamanho; i++) {
                if (tabuleiro[i][tamanho - i - 1] != diagSecundaria) {
                    venceu = false;
                    break;
                }
            }
            if (venceu) return true;
        }

        return false;
    }

    public boolean tabuleiroCompleto() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                sb.append(tabuleiro[i][j]);
                if (j < tamanho - 1) {
                    sb.append(" | ");
                }
            }
            sb.append("\n");
            if (i < tamanho - 1) {
                for (int k = 0; k < tamanho * 4 - 3; k++) {
                    sb.append("-");
                }
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
