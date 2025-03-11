/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author cristiandresgp
 */
public class NodoDoble<T> {
    // Atributos de la clase
    private T element;
    private NodoDoble<T> next;
    private NodoDoble<T> previous;
    
    /**
     * Constructor de la clase Nodo
     * @param element el cual sera el elemento correspondiente al nodo
     */
    public NodoDoble(T element) {
        this.element = element;
        this.next = this.previous = null;
    }

    /**
     * Metodo para obtener el objeto correspondiente al elemento del nodo
     * @return element en tipo T
     */
    public T getElement() {
        return element;
    }

    /**
     * Metodo para setear el elemento del nodo, a uno nuevo.
     * @param element nuevo elemento de tipo T
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Metodo para obtener el siguiente nodo al Nodo correspondiente
     * @return NodoDoble<T> al que este nodo apunta a siguiente
     */
    public NodoDoble<T> getNext() {
        return next;
    }

    /**
     * Metodo para setear el apuntador siguiente del nodo a otro nodo especifico
     * @param next NodoDoble<T> al que se desea apuntar
     */
    public void setNext(NodoDoble<T> next) {
        this.next = next;
    }

    /**
     * Metodo para obtener el nodo que es apuntado con el apuntador a previo
     * @return previo NodoDoble<T>
     */
    public NodoDoble<T> getPrevious() {
        return previous;
    }

    /**
     * Metodo para setear el previo nodo a uno especifico.
     * @param previous NodoDoble<T> anterior
     */
    public void setPrevious(NodoDoble<T> previous) {
        this.previous = previous;
    }
}

