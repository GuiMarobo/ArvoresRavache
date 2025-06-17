public class ArvoreRB {
    private NoRB raiz;
    private final NoRB NIL;

    public ArvoreRB(){
        NIL = new NoRB(-1);
        NIL.color = Color.PRETO;
        NIL.esquerda = NIL.direita = NIL.pai = null;
        raiz = NIL;
    }


//    public NoRB inserirRB(NoRB raiz, int chave){
//        raiz = inserirRB(raiz, chave);
//        raiz.color = Color.PRETO;
//    }

    public void insert(int chave){
        NoRB norb = new NoRB(chave);
        norb.esquerda = norb.direita = norb.pai = NIL;

        NoRB y = null;
        NoRB x = raiz;

        while (x != NIL){
            y = x;
            if (norb.chave < x.chave) x = x.esquerda;
            else x = x.direita;
        }

        norb.pai = y;
        if (y == null) raiz = norb;
        else if (norb.chave < y.chave) y.esquerda = norb;
        else y.direita = norb;

        norb.esquerda = NIL;
        norb.direita = NIL;
        norb.color = Color.VERMELHO;

        insertFix(norb);
    }

    private void insertFix(NoRB k) {
        while (k.pai != null && k.pai.color == Color.VERMELHO) {
            if (k.pai == k.pai.pai.esquerda) {
                NoRB u = k.pai.pai.direita;
                if (u.color == Color.VERMELHO) {
                    k.pai.color = Color.PRETO;
                    u.color = Color.PRETO;
                    k.pai.pai.color = Color.VERMELHO;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.direita) {
                        k = k.pai;
                        rotacaoEsquerda(k);
                    }
                    k.pai.color = Color.PRETO;
                    k.pai.pai.color = Color.PRETO;
                    rotacaoDireita(k.pai.pai);
                } else {
                    NoRB u = k.pai.pai.esquerda;
                    if (u.color == Color.VERMELHO) {
                        k.pai.color = Color.PRETO;
                        u.color = Color.PRETO;
                        k.pai.pai.color = Color.VERMELHO;
                        k = k.pai.pai;
                    } else {
                        if (k == k.pai.esquerda) {
                            k = k.pai;
                            rotacaoDireita(k);
                        }
                        k.pai.color = Color.PRETO;
                        k.pai.pai.color = Color.VERMELHO;
                        rotacaoEsquerda(k.pai.pai);
                    }
                }
                if (k == raiz) break;
            }
            raiz.color = Color.PRETO;
        }
    }

    private void transplante(NoRB u, NoRB v) {
        if (u.pai == null) raiz = v;
        else if (u == u.pai.esquerda) u.pai.esquerda = v;
        else u.pai.direita = v;
        v.pai = u.pai;
    }

    private NoRB minimo(NoRB norb) {
        while (norb.esquerda != NIL) norb = norb.esquerda;
        return norb;
    }

    public void delete(int chave) {
        NoRB z = procurarArvore(raiz, chave);
        if (z == NIL) return;

        NoRB y = z;
        Color yOriginalColor = y.color;
        NoRB x;

        if (z.esquerda == NIL) {
            x = z.direita;
            transplante(z, z.direita);
        } else if (z.direita == NIL) {
            x = z.esquerda;
            transplante(z, z.esquerda);
        } else {
            y = minimo(z.direita);
            yOriginalColor = y.color;
            x = y.direita;
            if (y.pai == z) x.pai = y;
            else {
                transplante(y, y.direita);
                y.direita = z.direita;
                y.direita.pai = y;
            }
            transplante(z, y);
            y.esquerda = z.esquerda;
            y.esquerda.pai = y;
            y.color = z.color;
        }

        if (yOriginalColor == Color.PRETO) deleteFix(x);
    }

    private void deleteFix(NoRB x) {
        NoRB w;
        while (x != raiz && x.color == Color.PRETO) {
            if (x == x.pai.esquerda) {
                w = x.pai.direita;
                if (w.color == Color.VERMELHO) {
                    w.color = Color.PRETO;
                    x.pai.color = Color.VERMELHO;
                    rotacaoEsquerda(x.pai);
                    w = x.pai.direita;
                }
                if (w.direita.color == Color.PRETO && w.esquerda.color == Color.PRETO) {
                    w.color = Color.VERMELHO;
                    x = x.pai;
                } else {
                    if (w.esquerda.color == Color.PRETO) {
                        w.direita.color = Color.PRETO;
                        w.color = Color.VERMELHO;
                        rotacaoEsquerda(w);
                        w = x.pai.esquerda;
                    }
                    w.color = x.pai.color;
                    x.pai.color = Color.PRETO;
                    w.esquerda.color = Color.PRETO;
                    rotacaoDireita(x.pai);
                    x = raiz;
                }
            }
        }
        x.color = Color.PRETO;
    }

    private void rotacaoEsquerda(NoRB x) {
        NoRB y = x.direita;
        x.direita = y.esquerda;

        if (y.esquerda != NIL) y.esquerda.pai = x;

        y.pai = x.pai;

        if (x.pai == null) raiz = y;
        else if (x == x.pai.esquerda) x.pai.esquerda = y;
        else x.pai.direita = y;
    }

    private void rotacaoDireita(NoRB y) {
        NoRB x = y.esquerda;
        y.esquerda = x.esquerda;

        if (x.direita != NIL) x.direita.pai = y;

        x.pai = y.pai;

        if (y.pai == null) raiz = x;
        else if (y == y.pai.direita) y.pai.direita = x;
        else y.pai.esquerda = x;
    }

    private NoRB procurarArvore(NoRB norb, int chave) {
        if (norb == NIL || chave == norb.chave) return norb;
        if (chave < norb.chave) return procurarArvore(norb.esquerda, chave);
        return procurarArvore(norb.direita, chave);
    }

    public void emOrdem(){
        emOrdem(raiz);
        System.out.println();
    }

//    private boolean ehVermelho(NoRB no) {
//        if (no == null) return false;
//        return no.ehVermelho();
//    }

//    private void inverterCores(NoRB no) {
//        no.color = Color.VERMELHO;
//        if (no.esquerda != null) no.esquerda.color = Color.PRETO;
//        if (no.direita != null) no.direita.color = Color.PRETO;
//    }



}
