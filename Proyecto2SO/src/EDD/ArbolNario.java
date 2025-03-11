/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package EDD;

/**
 *
 * @author cristiandresgp
 */
public class ArbolNario {
    private NodoArbol raiz;

    /**
     * Constructor que crea el sistema de archivos con una raíz "/"
     */
    public ArbolNario() {
        this.raiz = new NodoArbol("/", true);
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    /**
     * Busca un nodo en el árbol a partir de un camino
     * @param ruta Ruta en formato "/dir1/dir2/archivo"
     * @return Nodo encontrado o null si no existe
     */
    public NodoArbol buscarNodo(String ruta) {
        if (ruta.equals("/")) return raiz;

        String[] partes = ruta.split("/");
        NodoArbol actual = raiz;

        for (String parte : partes) {
            if (parte.isEmpty()) continue;

            boolean encontrado = false;
            NodoDoble<NodoArbol> nodoActual = actual.getHijos().getHead();

            while (nodoActual != null) {
                if (nodoActual.getElement().getNombre().equals(parte)) {
                    actual = nodoActual.getElement();
                    encontrado = true;
                    break;
                }
                nodoActual = nodoActual.getNext();
            }
            if (!encontrado) return null;
        }
        return actual;
    }

    /**
     * Agrega un archivo o directorio en la ruta indicada
     * @param rutaPadre Ruta del directorio donde se creará el nuevo nodo
     * @param nombre Nombre del archivo o directorio
     * @param esDirectorio Indica si es un directorio (true) o archivo (false)
     * @return true si se creó con éxito, false si hubo error
     */
    public boolean agregarNodo(String rutaPadre, String nombre, boolean esDirectorio) {
        NodoArbol padre = buscarNodo(rutaPadre);
        if (padre == null || !padre.isDirectorio()) return false;

        NodoArbol nuevo = new NodoArbol(nombre, esDirectorio);
        padre.agregarHijo(nuevo);
        return true;
    }

    /**
     * Elimina un nodo por ruta
     * @param ruta Ruta completa del nodo a eliminar
     * @return true si se eliminó correctamente, false si no se encontró
     */
    public boolean eliminarNodo(String ruta) {
        if (ruta.equals("/")) return false;

        String[] partes = ruta.split("/");
        String nombreEliminar = partes[partes.length - 1];
        String rutaPadre = ruta.substring(0, ruta.lastIndexOf("/"));

        NodoArbol padre = buscarNodo(rutaPadre);
        if (padre == null) return false;

        return padre.eliminarHijo(nombreEliminar);
    }

    /**
     * Imprime la estructura del árbol de manera recursiva
     * @param nodo Nodo actual
     * @param nivel Nivel de profundidad (para identación)
     */
    private void imprimirArbol(NodoArbol nodo, int nivel) {
        for (int i = 0; i < nivel; i++) System.out.print("  ");
        System.out.println((nodo.isDirectorio() ? "[D] " : "[A] ") + nodo.getNombre());

        NodoDoble<NodoArbol> hijo = nodo.getHijos().getHead();
        while (hijo != null) {
            imprimirArbol(hijo.getElement(), nivel + 1);
            hijo = hijo.getNext();
        }
    }

    /**
     * Método público para imprimir la estructura del sistema de archivos
     */
    public void imprimirSistemaArchivos() {
        imprimirArbol(raiz, 0);
    }
}

