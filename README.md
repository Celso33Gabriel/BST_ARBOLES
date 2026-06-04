# Laboratorio: Extensión de Árbol Binario de Búsqueda (BST)

Este proyecto contiene una implementación estructurada y manual de un **Árbol Binario de Búsqueda (BST)** en Java, desarrollada como parte del curso de **Programación 3**. La característica principal de este diseño es que se realizó **100% desde cero**, sin utilizar ninguna clase del paquete `java.util` (como `List`, `Queue` o `Stack`), cumpliendo estrictamente con las restricciones de manejo de memoria y punteros manuales.

## Características del Proyecto
* **Estructura Base:** Nodos enlazados dinámicamente (`Nodo.java`) con punteros independientes a subárboles izquierdo y derecho.
* **Estructuras Auxiliares Propias:** Implementación de una estructura de cola (`ColaNodos`) interna para soportar el recorrido por niveles (BFS) de forma nativa.
* **Operaciones Principales:** Soporte completo para inserción (evitando duplicados), búsquedas eficientes y eliminación de nodos cubriendo sus 3 casos matemáticos.

---

## Solución de los 5 Problemas Solicitados

A continuación se detalla la lógica e implementación de las 5 funciones requeridas en la guía de actividades:

### 1. Contador de Nodos Recursivo (`contarNodos()`)
Calcula la cantidad total de elementos actualmente almacenados en la estructura mediante un enfoque recursivo puro, sin apoyarse en variables contadoras globales o en el campo `tamanio`.
* **Caso Base:** Si el nodo actual es `null`, retorna `0`.
* **Caso Recursivo:** Suma `1` (por el nodo actual) más el resultado acumulado del subárbol izquierdo y derecho.

### 2. Validador de Equilibrio (`esBalanceado()`)
Determina si el árbol cumple con la condición de balanceo (donde la diferencia de alturas entre el subárbol izquierdo y derecho de **cada nodo** no es mayor a 1). Se optimizó el algoritmo utilizando un valor de control (`-2`) que detiene la recursividad inmediatamente al detectar el primer desequilibrio, evitando cálculos innecesarios.

### 3. Verificación de Propiedad BST (`esBSTValido()`)
Valida que la estructura mantenga los criterios estrictos de un árbol binario de búsqueda en cada nivel. El método recorre el árbol evaluando que cada nodo se encuentre dentro de un rango permitido de valores límites (`min` y `max`), inicializados con `Integer.MIN_VALUE` e `Integer.MAX_VALUE`.

### 4. Ancestro Común Más Bajo (`ancestroComunMasBajo()`)
Encuentra el nodo base compartido más cercano (LCA) entre dos valores dados. Aprovecha la propiedad de orden del BST de forma eficiente:
* Si ambos valores son menores que el nodo actual, se desplaza a la izquierda.
* Si ambos son mayores, se desplaza a la derecha.
* Si se separan o uno de ellos es igual al nodo actual, ese nodo es el ancestro buscado.
* *Lanza una excepción `IllegalArgumentException` si alguno de los datos consultados no existe.*

### 5. Inversión de Árbol en Espejo (`invertir()`)
Modifica físicamente la estructura del árbol de forma recursiva, intercambiando las referencias de los punteros `izquierdo` y `derecho` de todos los nodos del sistema. Al finalizar la ejecución, el recorrido `InOrden` se despliega en orden descendente estricto.

---

## Evidencias de Ejecución

A continuación se adjunta la captura de pantalla de la consola de Eclipse, demostrando la compilación exitosa con Maven y el correcto funcionamiento de todas las pruebas unitarias manuales integradas en la clase `Principal.java`:

![Resultados del Laboratorio en Consola](evidencia/image_773a1c.png)

---

## Instrucciones de Ejecución

El proyecto está gestionado con Apache Maven. Puede compilarse y ejecutarse desde la terminal de comandos de su sistema operativo mediante:

1. **Compilar el código fuente:**
   ```bash
   mvn compile
