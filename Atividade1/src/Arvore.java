import java.util.LinkedList;
import java.util.Queue;

public class Arvore {
    No raiz;

    public void setRaiz(No raiz) {
        this.raiz = raiz;
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

    public void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.print(no.valor + ", ");
            emOrdem(no.direita);
        }
    }

    public void posOrdem(No no){
        if (no != null) {
            posOrdem(no.esquerda);
            posOrdem(no.direita);
            System.out.println();
        }
    }

    public void buscarNivel(){
        if (raiz == null) return;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No noAtual = fila.poll();
            System.out.print(noAtual.valor + ", ");

            if (noAtual.esquerda != null) fila.add(noAtual.esquerda);
            if (noAtual.direita != null) fila.add(noAtual.direita);
        }
    }

}
