public class No{
    String valor;
    No esquerda, direita;
    int altura;

    public No(String valor, No esquerda, No direita) {
        this.valor = valor;
        esquerda = null;
        direita = null;
        this.altura = 1;
    }


}
