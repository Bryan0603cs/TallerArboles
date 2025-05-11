package uniquindio.edu.co.TallerArboles2;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniquindio.edu.co.TallerArboles2.model.ArbolBinario;

@SpringBootApplication
public class TallerArboles2Application {

    public static void main(String[] args) {

        ArbolBinario arbol = new ArbolBinario();

        System.out.println("¿Está vacío? " + arbol.estaVacio());

        int[] datos = {50, 30, 70, 20, 40, 60, 80};
        for (int dato : datos) {
            arbol.agregarDato(dato);
        }

        System.out.println("¿Está vacío después de insertar? " + arbol.estaVacio());

        System.out.print("Recorrido Inorden: ");
        arbol.inOrden();

        System.out.print("Recorrido Preorden: ");
        arbol.preOrden();

        System.out.print("Recorrido Postorden: ");
        arbol.postOrden();

        System.out.println("¿Existe el dato 40? " + arbol.existeDato(40));
        System.out.println("¿Existe el dato 99? " + arbol.existeDato(99));

        System.out.println("Peso del árbol (número de nodos): " + arbol.obtenerPeso());
        System.out.println("Altura del árbol: " + arbol.obtenerAltura());
        System.out.println("Nivel del nodo 60: " + arbol.obtenerNivel(60));
        System.out.println("Cantidad de hojas: " + arbol.contarHojas());
        System.out.println("Nodo menor: " + arbol.obtenerNodoMenor());
        System.out.println("Nodo mayor: " + arbol.obtenerNodoMayor());



        System.out.println("Eliminando nodo 20:");
        arbol.eliminarDato(20);
        arbol.inOrden();

        System.out.println("Eliminando nodo 30:");
        arbol.eliminarDato(30);
        arbol.inOrden();

        System.out.println("Eliminando nodo 50 (raiz):");
        arbol.eliminarDato(50);
        arbol.inOrden();

        System.out.println("Contar hojas después de eliminar: " + arbol.contarHojas());
        System.out.println("Nodo menor: " + arbol.obtenerNodoMenor());
        System.out.println("Nodo mayor: " + arbol.obtenerNodoMayor());

        System.out.println("Arbol eliminado:");
        arbol.borrarArbol();

        System.out.println("¿Árbol vacío después de borrar? " + arbol.estaVacio());
    }
}
