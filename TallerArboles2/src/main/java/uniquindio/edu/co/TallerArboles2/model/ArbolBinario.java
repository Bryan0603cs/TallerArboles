package uniquindio.edu.co.TallerArboles2.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

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
    }

    private void inOrdenRecursivo(Node nodo) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.left);
            System.out.print(nodo.data + " ");
            inOrdenRecursivo(nodo.right);
        }
    }

    public void inOrden(Consumer<Integer> consumidor) {
        inOrdenRecursivo(raiz, consumidor);
    }

    private void inOrdenRecursivo(Node nodo, Consumer<Integer> consumidor) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.left, consumidor);
            consumidor.accept(nodo.data);
            inOrdenRecursivo(nodo.right, consumidor);
        }
    }

    public void preOrden(Consumer<Integer> consumidor) {
        preOrdenRecursivo(raiz, consumidor);
    }

    private void preOrdenRecursivo(Node nodo, Consumer<Integer> consumidor) {
        if (nodo != null) {
            consumidor.accept(nodo.data);
            preOrdenRecursivo(nodo.left, consumidor);
            preOrdenRecursivo(nodo.right, consumidor);
        }
    }

    public void postOrden(Consumer<Integer> consumidor) {
        postOrdenRecursivo(raiz, consumidor);
    }

    private void postOrdenRecursivo(Node nodo, Consumer<Integer> consumidor) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.left, consumidor);
            postOrdenRecursivo(nodo.right, consumidor);
            consumidor.accept(nodo.data);
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

    /**
     * Altura = nivel máximo + 1
     * Ejemplo: si el nodo más profundo está en nivel 3, altura = 4
     */
    public int obtenerAltura() {
        return obtenerNivelMaximo(raiz, 0) + 1;
    }

    private int obtenerNivelMaximo(Node nodo, int nivelActual) {
        if (nodo == null) return nivelActual - 1; // al retroceder un paso en null
        int izq = obtenerNivelMaximo(nodo.left, nivelActual + 1);
        int der = obtenerNivelMaximo(nodo.right, nivelActual + 1);
        return Math.max(izq, der);
    }

    /**
     * Nivel del nodo (raíz = nivel 0)
     */
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

    public void imprimirAmplitud(Consumer<Integer> consumidor) {
        if (raiz == null) return;
        Queue<Node> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            Node actual = cola.poll();
            consumidor.accept(actual.data);
            if (actual.left != null) cola.add(actual.left);
            if (actual.right != null) cola.add(actual.right);
        }
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
            // Nodo encontrado
            if (nodo.left == null) return nodo.right;
            else if (nodo.right == null) return nodo.left;

            // Caso con dos hijos: reemplazar con el menor del subárbol derecho
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

    public Node getRaiz() {
        return raiz;
    }
}
