public class No{
    String valor;
    No esquerda, direita;

    public No(String valor, No esquerda, No direita) {
        this.valor = valor;
        esquerda = null;
        direita = null;
    }

    public int contarNos(No no){
        if (no == null) return 0;
        return 1 + contarNos(no.esquerda) + contarNos(no.direita);
    }

    public void preOrdem(No no) {
        if (no != null) {
            System.out.print(no.valor + ", ");
            preOrdem(no.esquerda);
            preOrdem(no.direita);

        }
    }

}
