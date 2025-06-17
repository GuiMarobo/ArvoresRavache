public class Main {
    public static void main(String[] args) {
        ArvoreRB arvore = new ArvoreRB();

        int[] chaves = {10, 20, 30, 15, 5, 25};
        for (int chave : chaves) {
            arvore.insert(chave);
        }

        System.out.println("Árvore após inserções (in-order):");
        arvore.emOrdem();

        arvore.delete(15);
        arvore.delete(10);

        System.out.println("Árvore após remoções (in-order):");
        arvore.emOrdem();







    }
}