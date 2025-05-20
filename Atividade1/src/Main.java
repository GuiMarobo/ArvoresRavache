public class Main {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        arvore.raiz = new No("A", null, null);
        arvore.raiz.esquerda = new No("B", null, null);
        arvore.raiz.direita = new No("C", null, null);

        arvore.raiz.esquerda.esquerda = new No("D", null, null);
        arvore.raiz.esquerda.direita = new No("E", null, null);
        arvore.raiz.direita.esquerda = new No("F", null, null);

        arvore.setRaiz(arvore.raiz);

        System.out.println("Contar nós: " + arvore.raiz.contarNos(arvore.raiz));
        System.out.print("Pré-Ordem: ");
        arvore.raiz.preOrdem(arvore.raiz);

    }
}