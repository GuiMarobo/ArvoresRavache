import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
            System.out.print(no.valor + ", ");
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

    public int contarNosIterativo(){
        if (raiz == null) return 0;

        int i = 0;
        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            No noAtual = pilha.pop();
            i++;

            if (noAtual.esquerda != null) pilha.add(noAtual.esquerda);
            if (noAtual.direita != null) pilha.add(noAtual.direita);
        }
        return i;
    }

    public void preOrdemIterativo() {
        if (raiz == null) return;

        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            System.out.print(atual.valor + ", ");

            if (atual.direita != null) {
                pilha.push(atual.direita);
            }
            if (atual.esquerda != null) {
                pilha.push(atual.esquerda);
            }
        }
    }


    public void emOrdemIterativo() {
        Stack<No> pilha = new Stack<>();
        No atual = raiz;

        while (atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual);
                atual = atual.esquerda;
            }

            atual = pilha.pop();
            System.out.print(atual.valor + ", ");

            atual = atual.direita;
        }
    }



    public void posOrdemIterativo() {
        if (raiz == null) return;

        Stack<No> pilha1 = new Stack<>();
        Stack<No> pilha2 = new Stack<>();

        pilha1.push(raiz);

        while (!pilha1.isEmpty()) {
            No atual = pilha1.pop();
            pilha2.push(atual);

            if (atual.esquerda != null) {
                pilha1.push(atual.esquerda);
            }
            if (atual.direita != null) {
                pilha1.push(atual.direita);
            }
        }

        while (!pilha2.isEmpty()) {
            No atual = pilha2.pop();
            System.out.print(atual.valor + ", ");
        }
    }


}
