package umg.edu.progra.arboles;

/**
 * Arbol Binario de Busqueda (BST) implementado manualmente,
 * sin utilizar java.util ni librerias externas.
 *
 * Reglas del BST:
 *  - Para cada nodo N, todos los valores del subarbol izquierdo
 *    son MENORES que N.dato.
 *  - Para cada nodo N, todos los valores del subarbol derecho
 *    son MAYORES que N.dato.
 *  - No se permiten duplicados (se ignoran al insertar).
 *
 * @author Walter Cordova
 */
public class ArbolBinarioBusqueda {

    private Nodo raiz;
    private int tamanio;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
        this.tamanio = 0;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int tamanio() {
        return tamanio;
    }

    /**
     * Inserta un valor en el arbol respetando la propiedad del BST.
     * Si el valor ya existe se ignora (no se insertan duplicados).
     */
    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            tamanio++;
            return;
        }
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            tamanio++;
            return new Nodo(valor);
        }
        if (valor < actual.dato) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        return actual;
    }

    /**
     * Busca un valor dentro del arbol. Devuelve el Nodo si existe
     * o null si no se encuentra.
     */
    public Nodo buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo buscarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor == actual.dato) {
            return actual;
        }
        if (valor < actual.dato) {
            return buscarRecursivo(actual.izquierdo, valor);
        }
        return buscarRecursivo(actual.derecho, valor);
    }

    public boolean contiene(int valor) {
        return buscar(valor) != null;
    }

    /**
     * Elimina un valor del arbol. Cubre los 3 casos clasicos:
     *  1. Nodo hoja (sin hijos)
     *  2. Nodo con un solo hijo
     *  3. Nodo con dos hijos (se reemplaza por el sucesor inorden:
     *     el menor del subarbol derecho).
     */
    public boolean eliminar(int valor) {
        int tamanioPrevio = tamanio;
        raiz = eliminarRecursivo(raiz, valor);
        return tamanio < tamanioPrevio;
    }

    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor < actual.dato) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = eliminarRecursivo(actual.derecho, valor);
        } else {
            // Nodo encontrado
            if (actual.izquierdo == null && actual.derecho == null) {
                tamanio--;
                return null;
            }
            if (actual.izquierdo == null) {
                tamanio--;
                return actual.derecho;
            }
            if (actual.derecho == null) {
                tamanio--;
                return actual.izquierdo;
            }
            // Nodo con dos hijos: se reemplaza con el sucesor inorden
            int sucesor = minimo(actual.derecho);
            actual.dato = sucesor;
            actual.derecho = eliminarRecursivo(actual.derecho, sucesor);
        }
        return actual;
    }

    /**
     * Devuelve el valor minimo del arbol (el nodo mas a la izquierda).
     */
    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        return minimo(raiz);
    }

    private int minimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.dato;
    }

    /**
     * Devuelve el valor maximo del arbol (el nodo mas a la derecha).
     */
    public int maximo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        Nodo actual = raiz;
        while (actual.derecho != null) {
            actual = actual.derecho;
        }
        return actual.dato;
    }

    /**
     * Altura del arbol: cantidad de aristas del camino mas largo
     * desde la raiz hasta una hoja. Un arbol vacio tiene altura -1.
     * Un arbol con solo raiz tiene altura 0.
     */
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int izq = alturaRecursiva(nodo.izquierdo);
        int der = alturaRecursiva(nodo.derecho);
        return 1 + (izq > der ? izq : der);
    }

    /**
     * Cuenta cuantos nodos hoja (sin hijos) tiene el arbol.
     */
    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }
        return contarHojasRecursivo(nodo.izquierdo) + contarHojasRecursivo(nodo.derecho);
    }

    // ============================================================
    // RECORRIDOS DEL ARBOL
    // ============================================================

    /**
     * Recorrido InOrden: Izquierdo -> Raiz -> Derecho.
     * En un BST imprime los valores ordenados de menor a mayor.
     */
    public void inOrden() {
        inOrdenRecursivo(raiz);
        System.out.println();
    }

    private void inOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        inOrdenRecursivo(nodo.izquierdo);
        System.out.print(nodo.dato + " ");
        inOrdenRecursivo(nodo.derecho);
    }

    /**
     * Recorrido PreOrden: Raiz -> Izquierdo -> Derecho.
     * Util para clonar el arbol.
     */
    public void preOrden() {
        preOrdenRecursivo(raiz);
        System.out.println();
    }

    private void preOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        System.out.print(nodo.dato + " ");
        preOrdenRecursivo(nodo.izquierdo);
        preOrdenRecursivo(nodo.derecho);
    }

    /**
     * Recorrido PostOrden: Izquierdo -> Derecho -> Raiz.
     * Util para liberar/eliminar el arbol.
     */
    public void postOrden() {
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        postOrdenRecursivo(nodo.izquierdo);
        postOrdenRecursivo(nodo.derecho);
        System.out.print(nodo.dato + " ");
    }

    /**
     * Recorrido por niveles (BFS) implementado con una cola casera
     * (sin usar java.util). Imprime el arbol por anchura.
     */
    public void recorridoPorNiveles() {
        if (raiz == null) {
            System.out.println();
            return;
        }
        ColaNodos cola = new ColaNodos();
        cola.encolar(raiz);
        while (!cola.estaVacia()) {
            Nodo actual = cola.desencolar();
            System.out.print(actual.dato + " ");
            if (actual.izquierdo != null) {
                cola.encolar(actual.izquierdo);
            }
            if (actual.derecho != null) {
                cola.encolar(actual.derecho);
            }
        }
        System.out.println();
    }

    /**
     * Imprime el arbol de forma jerarquica y visual en consola
     * (rotado 90 grados: la raiz queda a la izquierda).
     */
    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("(arbol vacio)");
            return;
        }
        imprimirArbolRecursivo(raiz, 0);
    }

    private void imprimirArbolRecursivo(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        imprimirArbolRecursivo(nodo.derecho, nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("     ");
        }
        System.out.println("-> " + nodo.dato);
        imprimirArbolRecursivo(nodo.izquierdo, nivel + 1);
    }

    // ============================================================
    // PROBLEMA 1 — contarNodos() recursivo
    // Cuenta todos los nodos del arbol sin usar el campo 'tamanio'.
    // Caso base: nodo null = 0 nodos.
    // Caso recursivo: 1 (nodo actual) + nodos izquierda + nodos derecha.
    // ============================================================

    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }

    private int contarNodosRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodosRecursivo(nodo.izquierdo) + contarNodosRecursivo(nodo.derecho);
    }

    // ============================================================
    // PROBLEMA 2 — esBalanceado()
    // Un arbol esta balanceado si en CADA nodo la diferencia de
    // altura entre subarbol izquierdo y derecho es <= 1.
    // Usamos un helper que devuelve -2 como "codigo de error" cuando
    // ya detecta un desequilibrio, para no seguir calculando.
    // ============================================================

    public boolean esBalanceado() {
        return alturaBalanceada(raiz) != -2;
    }

    private int alturaBalanceada(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int izq = alturaBalanceada(nodo.izquierdo);
        if (izq == -2) return -2; // ya hay desequilibrio abajo

        int der = alturaBalanceada(nodo.derecho);
        if (der == -2) return -2; // ya hay desequilibrio abajo

        int diferencia = izq - der;
        if (diferencia < -1 || diferencia > 1) {
            return -2; // este nodo esta desequilibrado
        }
        return 1 + (izq > der ? izq : der);
    }

    // ============================================================
    // PROBLEMA 3 — esBSTValido()
    // Verifica que el arbol cumple la propiedad BST recorriendo
    // con un rango permitido (min, max) en cada llamada recursiva.
    // Usamos Integer.MIN_VALUE e Integer.MAX_VALUE como limites iniciales.
    // ============================================================

    public boolean esBSTValido() {
        return esBSTValido(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean esBSTValido(Nodo nodo, int min, int max) {
        if (nodo == null) {
            return true; // arbol vacio es BST valido
        }
        if (nodo.dato <= min || nodo.dato >= max) {
            return false; // viola el rango permitido
        }
        // Subarbol izquierdo: todos deben ser < nodo.dato
        // Subarbol derecho:   todos deben ser > nodo.dato
        return esBSTValido(nodo.izquierdo, min, nodo.dato)
            && esBSTValido(nodo.derecho, nodo.dato, max);
    }

    // ============================================================
    // PROBLEMA 4 — ancestroComunMasBajo(int a, int b)  [LCA]
    // Aprovecha la propiedad del BST:
    //   - Si a y b son ambos menores que el nodo actual -> ir izquierda
    //   - Si a y b son ambos mayores que el nodo actual -> ir derecha
    //   - En cualquier otro caso, el nodo actual ES el LCA
    // Lanza IllegalArgumentException si alguno de los valores no existe.
    // ============================================================

    public int ancestroComunMasBajo(int a, int b) {
        if (!contiene(a) || !contiene(b)) {
            throw new IllegalArgumentException(
                "Uno o ambos valores no existen en el arbol: " + a + ", " + b);
        }
        return lcaRecursivo(raiz, a, b);
    }

    private int lcaRecursivo(Nodo nodo, int a, int b) {
        if (a < nodo.dato && b < nodo.dato) {
            return lcaRecursivo(nodo.izquierdo, a, b);
        }
        if (a > nodo.dato && b > nodo.dato) {
            return lcaRecursivo(nodo.derecho, a, b);
        }
        // Los valores estan en lados distintos (o uno ES el nodo actual)
        return nodo.dato;
    }

    // ============================================================
    // PROBLEMA 5 — invertir()  [espejo del arbol]
    // Intercambia izquierdo y derecho en TODOS los nodos recursivamente.
    // Caso base: nodo null -> no hace nada.
    // Caso recursivo: invierte primero los subarboles, luego intercambia.
    // ============================================================

    public void invertir() {
        invertirRecursivo(raiz);
    }

    private void invertirRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        // Primero invertir los subarboles hijos
        invertirRecursivo(nodo.izquierdo);
        invertirRecursivo(nodo.derecho);
        // Luego intercambiar los punteros
        Nodo temp = nodo.izquierdo;
        nodo.izquierdo = nodo.derecho;
        nodo.derecho = temp;
    }

    // ============================================================
    // COLA INTERNA (lista enlazada simple) usada para BFS.
    // Se implementa aqui para NO depender de java.util.
    // ============================================================
    
    private static class NodoCola {
        Nodo valor;
        NodoCola siguiente;

        NodoCola(Nodo valor) {
            this.valor = valor;
        }
    }

    private static class ColaNodos {
        private NodoCola frente;
        private NodoCola fondo;

        boolean estaVacia() {
            return frente == null;
        }

        void encolar(Nodo n) {
            NodoCola nuevo = new NodoCola(n);
            if (frente == null) {
                frente = fondo = nuevo;
            } else {
                fondo.siguiente = nuevo;
                fondo = nuevo;
            }
        }

        Nodo desencolar() {
            if (frente == null) {
                throw new IllegalStateException("Cola vacia");
            }
            Nodo valor = frente.valor;
            frente = frente.siguiente;
            if (frente == null) {
                fondo = null;
            }
            return valor;
        }
    }
}
