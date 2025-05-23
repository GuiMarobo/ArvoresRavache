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

        System.out.println("Contar nós: " + arvore.contarNos(arvore.raiz));
        System.out.print("Pré-Ordem: ");
        arvore.preOrdem(arvore.raiz);

        System.out.println("---------------------");

        System.out.print("\nEm Ordem: ");
        arvore.emOrdem(arvore.raiz);

        System.out.println("---------------------");

        System.out.println("\nPós-Ordem: ");
        arvore.posOrdem(arvore.raiz);

        System.out.println("---------------------");

        System.out.println("\nBusca por Nível: ");
        arvore.buscarNivel();

        




    }
}