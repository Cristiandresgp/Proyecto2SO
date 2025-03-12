/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author cristiandresgp
 */
public class NodoArbol {
    private String nombre;
    private boolean esDirectorio;
    private NodoArbol padre;
    private ListaDoble<NodoArbol> hijos;

    /**
     * Constructor de la clase NodoArbol
     * @param nombre Nombre del archivo o directorio
     * @param esDirectorio Indica si es un directorio (true) o un archivo (false)
     */
    public NodoArbol(String nombre, boolean esDirectorio) {
        this.nombre = nombre;
        this.esDirectorio = esDirectorio;
        this.hijos = new ListaDoble<>();
        this.padre = null;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDirectorio() {
        return esDirectorio;
    }

    public NodoArbol getPadre() {
        return padre;
    }

    public void setPadre(NodoArbol padre) {
        this.padre = padre;
    }

    public ListaDoble<NodoArbol> getHijos() {
        return hijos;
    }

    /**
     * Agrega un hijo al nodo actual (puede ser archivo o directorio)
     * @param hijo NodoArbol a agregar como hijo
     */
    public void agregarHijo(NodoArbol hijo) {
        hijo.setPadre(this);
        hijos.insertFinal(hijo);
    }

    /**
     * Elimina un hijo por nombre
     * @param nombre Nombre del archivo o directorio a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean eliminarHijo(String nombre) {
        NodoDoble<NodoArbol> actual = hijos.getHead();
        while (actual != null) {
            if (actual.getElement().getNombre().equals(nombre)) {
                hijos.deleteInIndex(hijos.searchIndex(actual.getElement()));
                return true;
            }
            actual = actual.getNext();
        }
        return false;
    }
    
    @Override
    public String toString() {
        return this.nombre;
    }
}
