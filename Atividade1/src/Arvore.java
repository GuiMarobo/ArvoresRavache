import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Arvore {
    No raiz;

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    public int contarNos(No no) {
        if (no == null) return 0;
        return 1 + contarNos(no.esquerda) + contarNos(no.direita);
    }

    public void preOrdem(No no) {
        if (no != null) {
            System.out.print(no.chave + ", ");
            preOrdem(no.esquerda);
            preOrdem(no.direita);

        }
    }

    public void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.print(no.chave + ", ");
            emOrdem(no.direita);
        }
    }

    public void posOrdem(No no) {
        if (no != null) {
            posOrdem(no.esquerda);
            posOrdem(no.direita);
            System.out.print(no.chave + ", ");
        }
    }

    public void buscarNivel() {
        if (raiz == null) return;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No noAtual = fila.poll();
            System.out.print(noAtual.chave + ", ");

            if (noAtual.esquerda != null) fila.add(noAtual.esquerda);
            if (noAtual.direita != null) fila.add(noAtual.direita);
        }
    }

    public int contarNosIterativo() {
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

    public int contarNosComFila() { // código feito na aula, para estudo
        if (raiz == null) return 0;

        int i = 0;
        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No noAtual = fila.poll();
            i++;

            if (noAtual.esquerda != null) fila.add(noAtual.esquerda);
            if (noAtual.direita != null) fila.add(noAtual.direita);
        }
        return i;
    }

    public void preOrdemIterativo() {
        if (raiz == null) return;

        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            System.out.print(atual.chave + ", ");

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
            System.out.print(atual.chave + ", ");

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
            System.out.print(atual.chave + ", ");
        }
    }

    public int contarFolhas(No no) {
        if (no == null) return 0;
        if (no.esquerda == null && no.direita == null)
            return 1;
        else {
            return contarFolhas(no.esquerda) + contarFolhas(no.direita);
        }
    }

    public int contarFolhasIterativo() {
        if (raiz == null) return 0;

        int i = 0;
        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            No noAtual = pilha.pop();
            if (noAtual.esquerda == null && noAtual.direita == null){
                i++;
            }
            else {
                if (noAtual.esquerda != null) pilha.add(noAtual.esquerda);
                if (noAtual.direita != null) pilha.add(noAtual.direita);
            }
        }
        return i;
    }

    private int altura(No no){
        if (no == null) return 0;
        return no.altura;
    }

    private int fatorBalanceamento(No no){
        if (no == null) return 0;
        return altura(no.esquerda) - altura(no.direita);
    }

    // pergunta da prova, essa é a rotação LL.
    No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        atualizarAltura(x);
        atualizarAltura(y);

        return x;
    }

    //pergunta da prova, essa é a rotação RR
    No rotacaoEsquerda(No x){
        No y = x.direita;
        No T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    public void atualizarAltura(No no){
        int alturaEsquerda = altura(no.esquerda);
        int alturaDireita = altura(no.direita);

        no.altura = Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    public No inserir(No no, int chave){
        if (no == null){
            return new No(chave, null, null);
        }

        if (chave < no.chave){
            no.esquerda = inserir(no.esquerda, chave);
        } else if (chave > no.chave) {
            no.direita = inserir(no.direita, chave);
        } else {
            return no;
        }

        atualizarAltura(no);

        // Rotacao LL
        if (fatorBalanceamento(no) > 1 && chave < no.esquerda.chave)
            return rotacaoDireita(no);

        // Rotacao RR
        if (fatorBalanceamento(no) < -1 && chave > no.direita.chave)
            return rotacaoEsquerda(no);

        // Rotacao LR
        if (fatorBalanceamento(no) > 1 && chave > no.esquerda.chave) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        // Rotacao RL
        if (fatorBalanceamento(no) < -1 && chave < no.direita.chave) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }


    public void inserirValor(int chave) {
        raiz = inserir(raiz, chave);
    }

}