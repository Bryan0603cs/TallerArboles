package uniquindio.edu.co.TallerArboles2.model;

public class ArbolBinario {

    private Node raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public void agregarDato(int dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private Node insertarRecursivo(Node nodo, int dato) {
        if (nodo == null) return new Node(dato);
        if (dato < nodo.data) nodo.left = insertarRecursivo(nodo.left, dato);
        else if (dato > nodo.data) nodo.right = insertarRecursivo(nodo.right, dato);
        return nodo;
    }

    public void inOrden() {
        inOrdenRecursivo(raiz);
        System.out.println();
    }

    private void inOrdenRecursivo(Node nodo) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.left);
            System.out.print(nodo.data + " ");
            inOrdenRecursivo(nodo.right);
        }
    }

    public void preOrden() {
        preOrdenRecursivo(raiz);
        System.out.println();
    }

    private void preOrdenRecursivo(Node nodo) {
        if (nodo != null) {
            System.out.print(nodo.data + " ");
            preOrdenRecursivo(nodo.left);
            preOrdenRecursivo(nodo.right);
        }
    }

    public void postOrden() {
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(Node nodo) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.left);
            postOrdenRecursivo(nodo.right);
            System.out.print(nodo.data + " ");
        }
    }

    public boolean existeDato(int dato) {
        return buscar(raiz, dato);
    }

    private boolean buscar(Node nodo, int dato) {
        if (nodo == null) return false;
        if (nodo.data == dato) return true;
        return dato < nodo.data ? buscar(nodo.left, dato) : buscar(nodo.right, dato);
    }

    public int obtenerPeso() {
        return contarNodos(raiz);
    }

    private int contarNodos(Node nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.left) + contarNodos(nodo.right);
    }

    public int obtenerAltura() {
        return altura(raiz);
    }

    private int altura(Node nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(altura(nodo.left), altura(nodo.right));
    }

    public int obtenerNivel(int dato) {
        return nivel(raiz, dato, 0);
    }

    private int nivel(Node nodo, int dato, int nivel) {
        if (nodo == null) return -1;
        if (nodo.data == dato) return nivel;
        if (dato < nodo.data) return nivel(nodo.left, dato, nivel + 1);
        return nivel(nodo.right, dato, nivel + 1);
    }

    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Node nodo) {
        if (nodo == null) return 0;
        if (nodo.left == null && nodo.right == null) return 1;
        return contarHojasRecursivo(nodo.left) + contarHojasRecursivo(nodo.right);
    }

    public int obtenerNodoMenor() {
        Node actual = raiz;
        while (actual.left != null) actual = actual.left;
        return actual.data;
    }

    public int obtenerNodoMayor() {
        Node actual = raiz;
        while (actual.right != null) actual = actual.right;
        return actual.data;
    }

    public void imprimirAmplitud() {
        if (raiz == null) return;
        java.util.Queue<Node> cola = new java.util.LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            Node actual = cola.poll();
            System.out.print(actual.data + " ");
            if (actual.left != null) cola.add(actual.left);
            if (actual.right != null) cola.add(actual.right);
        }
        System.out.println();
    }

    public void eliminarDato(int dato) {
        raiz = eliminarRecursivo(raiz, dato);
    }

    private Node eliminarRecursivo(Node nodo, int dato) {
        if (nodo == null) return null;

        if (dato < nodo.data) {
            nodo.left = eliminarRecursivo(nodo.left, dato);
        } else if (dato > nodo.data) {
            nodo.right = eliminarRecursivo(nodo.right, dato);
        } else {
            if (nodo.left == null) return nodo.right;
            else if (nodo.right == null) return nodo.left;

            nodo.data = obtenerMinimo(nodo.right);
            nodo.right = eliminarRecursivo(nodo.right, nodo.data);
        }
        return nodo;
    }

    private int obtenerMinimo(Node nodo) {
        int minimo = nodo.data;
        while (nodo.left != null) {
            minimo = nodo.left.data;
            nodo = nodo.left;
        }
        return minimo;
    }

    public void borrarArbol() {
        raiz = null;
    }
}
