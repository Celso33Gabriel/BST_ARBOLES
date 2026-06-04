package umg.edu.progra.arboles;

/**
 * Clase principal que demuestra el uso del Arbol Binario de Busqueda (BST)
 * implementado manualmente, sin usar librerias como java.util.
 *
 * Ejecucion sugerida:
 *   1. mvn compile
 *   2. mvn exec:java -Dexec.mainClass="umg.edu.progra.arboles.Principal"
 *
 * @author Walter Cordova
 */
public class Principal {

    @SuppressWarnings("unused")
	public static void main(String[] args) {

        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        /*
         * Insertamos estos valores para formar el siguiente BST:
         *
         *               50
         *              /  \
         *            30    70
         *           /  \   / \
         *          20  40 60  80
         *         /
         *        10
         */
        int[] valores = { 50, 30, 70, 20, 40, 60, 80, 10 };
        for (int v : valores) {
            arbol.insertar(v);
        }

        System.out.println("===== Arbol Binario de Busqueda =====");
        System.out.println("Tamanio: " + arbol.tamanio());
        System.out.println("Altura:  " + arbol.altura());
        System.out.println("Minimo:  " + arbol.minimo());
        System.out.println("Maximo:  " + arbol.maximo());
        System.out.println("Hojas:   " + arbol.contarHojas());

        System.out.println("\n--- Representacion visual (rotada 90 grados) ---");
        arbol.imprimirArbol();

        System.out.println("\n--- Recorridos ---");
        System.out.print("InOrden    (ascendente): ");
        arbol.inOrden();

        System.out.print("PreOrden   (raiz primero): ");
        arbol.preOrden();

        System.out.print("PostOrden  (raiz al final): ");
        arbol.postOrden();

        System.out.print("Por niveles (BFS):         ");
        arbol.recorridoPorNiveles();

        System.out.println("\n--- Busquedas ---");
        System.out.println("Contiene 40? " + arbol.contiene(40));
        System.out.println("Contiene 99? " + arbol.contiene(99));

        System.out.println("\n--- Eliminacion ---");
        System.out.println("Eliminando 20 (nodo con 1 hijo)...");
        arbol.eliminar(20);
        System.out.print("InOrden tras eliminar 20: ");
        arbol.inOrden();

        System.out.println("Eliminando 30 (nodo con 2 hijos)...");
        arbol.eliminar(30);
        System.out.print("InOrden tras eliminar 30: ");
        arbol.inOrden();

        System.out.println("Eliminando 50 (raiz)...");
        arbol.eliminar(50);
        System.out.print("InOrden tras eliminar la raiz: ");
        arbol.inOrden();

        System.out.println("\n--- Estado final ---");
        arbol.imprimirArbol();
        System.out.println("Tamanio final: " + arbol.tamanio());
        System.out.println("Altura final:  " + arbol.altura());

        // ============================================================
        // Reiniciamos el arbol con los 8 valores originales
        // ============================================================
        arbol = new ArbolBinarioBusqueda();
        for (int v : valores) {
            arbol.insertar(v);
        }

        // ============================================================
        // PROBLEMA 1 — contarNodos() recursivo
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("PROBLEMA 1 — contarNodos() recursivo");
        System.out.println("========================================");
        System.out.println("tamanio() dice:    " + arbol.tamanio());
        System.out.println("contarNodos() dice: " + arbol.contarNodos());
        System.out.println("Coinciden? " + (arbol.tamanio() == arbol.contarNodos()));
        arbol.insertar(5);
        System.out.println("Tras insertar 5:");
        System.out.println("  tamanio():    " + arbol.tamanio());
        System.out.println("  contarNodos(): " + arbol.contarNodos());
        arbol.eliminar(5);
        System.out.println("Tras eliminar 5:");
        System.out.println("  tamanio():    " + arbol.tamanio());
        System.out.println("  contarNodos(): " + arbol.contarNodos());

        // ============================================================
        // PROBLEMA 2 — esBalanceado()
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("PROBLEMA 2 — esBalanceado()");
        System.out.println("========================================");
        System.out.println("Arbol original (balanceado):");
        arbol.imprimirArbol();
        System.out.println("esBalanceado()? " + arbol.esBalanceado());

        ArbolBinarioBusqueda desbalanceado = new ArbolBinarioBusqueda();
        desbalanceado.insertar(1);
        desbalanceado.insertar(2);
        desbalanceado.insertar(3);
        desbalanceado.insertar(4);
        desbalanceado.insertar(5);
        System.out.println("\nArbol desbalanceado (1,2,3,4,5 en orden):");
        desbalanceado.imprimirArbol();
        System.out.println("esBalanceado()? " + desbalanceado.esBalanceado());

        // ============================================================
        // PROBLEMA 3 — esBSTValido()
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("PROBLEMA 3 — esBSTValido()");
        System.out.println("========================================");
        System.out.println("Arbol normal generado por insertar():");
        System.out.println("esBSTValido()? " + arbol.esBSTValido());

        // Construir arbol "roto" manualmente violando la propiedad BST
        //Nodo roto = new Nodo(50, new Nodo(80, new Nodo(20), new Nodo(90)), new Nodo(70, new Nodo(60), new Nodo(80)));
        ArbolBinarioBusqueda arbolRoto2 = new ArbolBinarioBusqueda();
        arbolRoto2.insertar(50);
        arbolRoto2.insertar(30);
        arbolRoto2.insertar(70);
        // Rompemos: cambiamos el dato de la raiz directamente
        arbolRoto2.getRaiz().dato = 35; // ahora 35 tiene hijo derecho 70, que es correcto, pero hijo izquierdo 30 que ya NO es < 35
        arbolRoto2.getRaiz().izquierdo.dato = 999; // 999 a la izquierda de 35: INVALIDO
        System.out.println("\nArbol roto manualmente (999 a la izquierda de 35):");
        arbolRoto2.imprimirArbol();
        System.out.println("esBSTValido()? " + arbolRoto2.esBSTValido());

        // ============================================================
        // PROBLEMA 4 — ancestroComunMasBajo (LCA)
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("PROBLEMA 4 — ancestroComunMasBajo (LCA)");
        System.out.println("========================================");
        System.out.println("Arbol utilizado:");
        arbol.imprimirArbol();
        System.out.println("LCA(10, 40) -> esperado 30, obtenido: " + arbol.ancestroComunMasBajo(10, 40));
        System.out.println("LCA(10, 80) -> esperado 50, obtenido: " + arbol.ancestroComunMasBajo(10, 80));
        System.out.println("LCA(60, 80) -> esperado 70, obtenido: " + arbol.ancestroComunMasBajo(60, 80));
        System.out.println("LCA(30, 70) -> esperado 50, obtenido: " + arbol.ancestroComunMasBajo(30, 70));
        System.out.println("LCA(10, 99) -> esperado excepcion:");
        try {
            arbol.ancestroComunMasBajo(10, 99);
        } catch (IllegalArgumentException e) {
            System.out.println("  IllegalArgumentException: " + e.getMessage());
        }

        // ============================================================
        // PROBLEMA 5 — invertir() [espejo]
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("PROBLEMA 5 — invertir() [espejo]");
        System.out.println("========================================");
        System.out.println("ANTES de invertir:");
        arbol.imprimirArbol();
        System.out.print("InOrden ANTES (ascendente): ");
        arbol.inOrden();

        arbol.invertir();

        System.out.println("DESPUES de invertir:");
        arbol.imprimirArbol();
        System.out.print("InOrden DESPUES (descendente): ");
        arbol.inOrden();

        // Invertir de nuevo para volver al estado original
        arbol.invertir();
        System.out.print("InOrden tras segunda inversion (vuelve al original): ");
        arbol.inOrden();
    }
}
