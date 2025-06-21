
public class Main {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        ArvoreRB arvorerb = new ArvoreRB();

        int[] chaves = {10, 20, 30, 15, 5, 25};
        for (int chave : chaves) {
            arvorerb.insert(chave);
        }
        System.out.println("-------- Árvore RB --------");
        System.out.println("Árvore após inserções (in-order): ");
        arvorerb.emOrdem();
        arvorerb.delete(15);
        arvorerb.delete(10);

         System.out.println("Árvore após remoções (in-order):");
         arvorerb.emOrdem();

        System.out.println("");
        System.out.println("------------------------------------------");
        System.out.println("-------- Árvore AVL --------");

        System.out.println("Inserindo 10:");
        arvore.inserirValor(30);
        arvore.imprimirArvore();
        System.out.println("Inserindo 30:");
        arvore.inserirValor(10);
        arvore.imprimirArvore();
        System.out.println("Inserindo 20 (causará rotação RL):");
        arvore.inserirValor(20);
        arvore.imprimirArvore();


    }
}
