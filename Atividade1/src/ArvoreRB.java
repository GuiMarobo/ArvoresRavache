public class ArvoreRB {
    private NoRB raiz;
    private final NoRB NIL;

    public ArvoreRB() {
        NIL = new NoRB(-1);
        NIL.color = Color.PRETO;
        NIL.esquerda = NIL.direita = NIL.pai = null;
        raiz = NIL;
    }

    public void insert(int chave) {
        NoRB norb = new NoRB(chave);
        norb.esquerda = norb.direita = norb.pai = NIL;
        norb.color = Color.VERMELHO;

        NoRB y = NIL;
        NoRB x = raiz;

        while (x != NIL) {
            y = x;
            if (norb.chave < x.chave) x = x.esquerda;
            else x = x.direita;
        }

        norb.pai = y;
        if (y == NIL) raiz = norb;
        else if (norb.chave < y.chave) y.esquerda = norb;
        else y.direita = norb;

        insertFix(norb);
    }

    private void insertFix(NoRB k) {
        while (k.pai.color == Color.VERMELHO) {
            if (k.pai == k.pai.pai.esquerda) {
                NoRB tio = k.pai.pai.direita;
                if (tio.color == Color.VERMELHO) {
                    k.pai.color = Color.PRETO;
                    tio.color = Color.PRETO;
                    k.pai.pai.color = Color.VERMELHO;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.direita) {
                        k = k.pai;
                        rotacaoEsquerda(k);
                    }
                    k.pai.color = Color.PRETO;
                    k.pai.pai.color = Color.VERMELHO;
                    rotacaoDireita(k.pai.pai);
                }
            } else {
                NoRB tio = k.pai.pai.esquerda;
                if (tio.color == Color.VERMELHO) {
                    k.pai.color = Color.PRETO;
                    tio.color = Color.PRETO;
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
            if (y.pai == z) {
                x.pai = y;
            } else {
                transplante(y, y.direita);
                y.direita = z.direita;
                y.direita.pai = y;
            }
            transplante(z, y);
            y.esquerda = z.esquerda;
            y.esquerda.pai = y;
            y.color = z.color;
        }

        if (yOriginalColor == Color.PRETO) {
            deleteFix(x);
        }
    }

    private void deleteFix(NoRB x) {
        while (x != raiz && x.color == Color.PRETO) {
            if (x == x.pai.esquerda) {
                NoRB w = x.pai.direita;
                if (w.color == Color.VERMELHO) {
                    w.color = Color.PRETO;
                    x.pai.color = Color.VERMELHO;
                    rotacaoEsquerda(x.pai);
                    w = x.pai.direita;
                }
                if (w.esquerda.color == Color.PRETO && w.direita.color == Color.PRETO) {
                    w.color = Color.VERMELHO;
                    x = x.pai;
                } else {
                    if (w.direita.color == Color.PRETO) {
                        w.esquerda.color = Color.PRETO;
                        w.color = Color.VERMELHO;
                        rotacaoDireita(w);
                        w = x.pai.direita;
                    }
                    w.color = x.pai.color;
                    x.pai.color = Color.PRETO;
                    w.direita.color = Color.PRETO;
                    rotacaoEsquerda(x.pai);
                    x = raiz;
                }
            } else {
                NoRB w = x.pai.esquerda;
                if (w.color == Color.VERMELHO) {
                    w.color = Color.PRETO;
                    x.pai.color = Color.VERMELHO;
                    rotacaoDireita(x.pai);
                    w = x.pai.esquerda;
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

    private void transplante(NoRB u, NoRB v) {
        if (u.pai == NIL) raiz = v;
        else if (u == u.pai.esquerda) u.pai.esquerda = v;
        else u.pai.direita = v;
        v.pai = u.pai;
    }

    private NoRB minimo(NoRB no) {
        while (no.esquerda != NIL) no = no.esquerda;
        return no;
    }

    private NoRB procurarArvore(NoRB no, int chave) {
        if (no == NIL || chave == no.chave) return no;
        if (chave < no.chave) return procurarArvore(no.esquerda, chave);
        return procurarArvore(no.direita, chave);
    }

    public void emOrdem() {
        emOrdem(raiz);
        System.out.println();
    }

    private void emOrdem(NoRB no) {
        if (no != NIL) {
            emOrdem(no.esquerda);
            System.out.print(no.chave + "(" + no.color + ") ");
            emOrdem(no.direita);
        }
    }
}
