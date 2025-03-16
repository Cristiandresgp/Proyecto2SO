/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package EDD;

import OBJECTS.Archivo;
import OBJECTS.SistemaArchivos;

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
    public void agregarNodo(String padre, String nombre, boolean esDirectorio) {
    NodoArbol nodoPadre = buscarNodo(padre);

    if (nodoPadre == null || !nodoPadre.isDirectorio()) {
        System.out.println("Error: No se encontró el directorio padre o no es un directorio.");
        return;
    }

    NodoArbol nuevoNodo = new NodoArbol(nombre, esDirectorio);
    nodoPadre.agregarHijo(nuevoNodo);
}



    /**
     * Elimina un nodo por ruta
     * @param ruta Ruta completa del nodo a eliminar
     * @return true si se eliminó correctamente, false si no se encontró
     */
  public boolean eliminarNodo(String ruta, SistemaArchivos sistema) {
    if (ruta.equals("/")) return false;

    String[] partes = ruta.split("/");
    String nombreEliminar = partes[partes.length - 1];

    int index = ruta.lastIndexOf("/");
    String rutaPadre = (index == -1) ? "/" : ruta.substring(0, index);

    NodoArbol padre = buscarNodo(rutaPadre);
    if (padre == null) return false;

    NodoArbol nodoAEliminar = null;
    NodoDoble<NodoArbol> actual = padre.getHijos().getHead();
    while (actual != null) {
        if (actual.getElement().getNombre().equals(nombreEliminar)) {
            nodoAEliminar = actual.getElement();
            break;
        }
        actual = actual.getNext();
    }

    if (nodoAEliminar == null) return false;

    // 🔥 Si es un directorio, eliminar todos los archivos dentro y liberar bloques
    if (nodoAEliminar.isDirectorio()) {
        eliminarContenidoRecursivo(nodoAEliminar, sistema);
    } else {
        // 🔥 Si es un archivo, liberar sus bloques
        ListaDoble<Archivo> listaArchivos = sistema.getTablaAsignacion().search(nombreEliminar);
        if (listaArchivos != null && listaArchivos.getHead() != null) {
            Archivo archivo = listaArchivos.getHead().getElement();
            sistema.liberarBloques(archivo.getPrimerBloque());
            sistema.getTablaAsignacion().delete(nombreEliminar, archivo);
        }
    }

    return padre.eliminarHijo(nombreEliminar);
}
  
  private void eliminarContenidoRecursivo(NodoArbol nodo, SistemaArchivos sistema) {
    NodoDoble<NodoArbol> actual = nodo.getHijos().getHead();
    
    while (actual != null) {
        NodoArbol hijo = actual.getElement();
        
        if (hijo.isDirectorio()) {
            // 🔁 Llamada recursiva para eliminar subdirectorios
            eliminarContenidoRecursivo(hijo, sistema);
        } else {
            // 🔥 Si es un archivo, liberar sus bloques
            ListaDoble<Archivo> listaArchivos = sistema.getTablaAsignacion().search(hijo.getNombre());
            if (listaArchivos != null && listaArchivos.getHead() != null) {
                Archivo archivo = listaArchivos.getHead().getElement();
                sistema.liberarBloques(archivo.getPrimerBloque());
                sistema.getTablaAsignacion().delete(hijo.getNombre(), archivo);
            }
        }
        
        actual = actual.getNext();
    }

    // 🔥 Finalmente, eliminar todos los hijos del nodo
    nodo.getHijos().setHead(null);
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

