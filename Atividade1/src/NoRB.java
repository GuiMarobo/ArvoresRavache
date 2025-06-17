public class NoRB {
    int chave;
    NoRB esquerda, direita, pai;
    Color color;

    public NoRB(int chave) {
        this.chave = chave;
        this.color = color.VERMELHO;
    }

    public boolean ehVermelho() {
        return this.color == Color.VERMELHO;
    }
}
