/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author cristiandresgp
 */
public class ListaDoble<T> implements ILista<T> {
    private int size; 
    private NodoDoble<T> head, tail;

    public ListaDoble() {
        this.size = 0;
        this.head = this.tail = null;
    }
    
    public int getSize() {
        return size;
    }

    public NodoDoble<T> getHead() {
        return head;
    }

    public void setHead(NodoDoble<T> head) {
        this.head = head;
    }

    public NodoDoble<T> getTail() {
        return tail;
    }

    public void setTail(NodoDoble<T> tail) {
        this.tail = tail;
    }

    @Override
    public void insertBegin(T element) {
        NodoDoble<T> newNodo = new NodoDoble<>(element);
        if (isEmpty()) {
            setHead(newNodo); 
            setTail(newNodo); 
        } else {
            newNodo.setNext(getHead());
            getHead().setPrevious(newNodo);
            setHead(newNodo); 
        }
        size++;
    }

    @Override
    public void insertFinal(T element) {
        NodoDoble<T> newNodo = new NodoDoble<>(element);
        if (isEmpty()) {
            setHead(newNodo); 
            setTail(newNodo); 
        } else {
            newNodo.setPrevious(getTail());
            getTail().setNext(newNodo);
            setTail(newNodo);
        }
        size++;
    }

    @Override
    public void insertInIndex(T element, int index) {
        NodoDoble<T> newNodo = new NodoDoble<>(element);
        if (isEmpty()) {
            setHead(newNodo); 
            setTail(newNodo); 
        } else if (index == size) {
            insertFinal(element); 
        } else if (index == 0) {
            insertBegin(element); 
        } else if (index > size || index < 0) {
            System.out.println("Index Error");
        } else {
            if (index > size / 2) {
                NodoDoble<T> pointer = getTail(); 
                for (int end = size - index; end > 1; end--) {
                    pointer = pointer.getPrevious(); 
                }
                NodoDoble<T> previous = pointer.getPrevious();
                newNodo.setNext(pointer);
                newNodo.setPrevious(previous);
                previous.setNext(newNodo);
                pointer.setPrevious(newNodo); 
            } else {
                NodoDoble<T> pointer = getHead();
                for (int i = 1; i < index; i++) {
                    pointer = pointer.getNext(); 
                }
                NodoDoble<T> nodoNext = pointer.getNext(); 
                newNodo.setNext(nodoNext);
                newNodo.setPrevious(pointer);
                pointer.setNext(newNodo);
                nodoNext.setPrevious(newNodo); 
            }
            size++; 
        }
    }

    @Override
    public T deleteFinal() {
        if (isEmpty()) {
            System.out.println("Delete Error, empty list.");
            return null;
        } else {
            NodoDoble<T> newTail = getTail().getPrevious();
            if (newTail == null) {
                setHead(null);
                setTail(null); 
                size--;
            } else {
                NodoDoble<T> eliminated = getTail(); 
                newTail.setNext(null);
                setTail(newTail);
                size--; 
                return eliminated.getElement(); 
            }
        }
        return null;
    }

    @Override
    public T deleteBegin() {
        if (isEmpty()) {
            System.out.println("Delete Error, empty list.");
            return null;
        } else {
            NodoDoble<T> newHead = getHead().getNext();
            NodoDoble<T> eliminated = getHead(); 
            if (newHead != null) {
                newHead.setPrevious(null);
            }
            setHead(newHead); 
            size--;
            return eliminated.getElement(); 
        }
    }

    @Override
    public T deleteInIndex(int index) {
        if (isEmpty()) {
            System.out.println("Delete Error, list empty.");
            return null;
        } else if (index >= size || index < 0) {
            System.out.println("Index Error");
            return null;
        } else if (index == 0) {
            return deleteBegin(); 
        } else if (index == size - 1) {
            return deleteFinal(); 
        } else {
            NodoDoble<T> pointer;
            if (index > size / 2) {
                pointer = getTail(); 
                for (int end = size - index; end > 1; end--) {
                    pointer = pointer.getPrevious();   
                }
            } else {
                pointer = getHead();
                for (int i = 1; i < index; i++) {
                    pointer = pointer.getNext(); 
                }
            }
            NodoDoble<T> deleted = pointer.getNext();
            pointer.setNext(deleted.getNext());
            deleted.getNext().setPrevious(pointer);
            size--;
            return deleted.getElement();
        }
    }

    @Override
    public boolean isEmpty() {
        return (getHead() == null && getTail() == null); 
    }

    @Override
    public void print() {
        NodoDoble<T> pointer = getHead();
        int aux = 0;
        while (pointer != null) {
            System.out.println(aux + "._ [" + pointer.getElement() + "]");
            aux++;
            pointer = pointer.getNext();
        }
        if (getHead() == null) {
            System.out.println("Empty List");
        }
    }

    public String printString() {
        NodoDoble<T> pointer = getHead();
        StringBuilder list = new StringBuilder();
        while (pointer != null) {
            list.append(pointer.getElement());
            if (pointer.getNext() != null) {
                list.append(" -> ");
            }
            pointer = pointer.getNext();
        }
        return list.toString();
    }

    public boolean isIn(T element) {
        NodoDoble<T> pointer = getHead(); 
        while (pointer != null) {
            if (pointer.getElement().equals(element)) {
                return true;
            }
            pointer = pointer.getNext();
        }
        return false; 
    }

    public void emptyButHead() {
        while (getHead() != null && getHead().getNext() != null) {
            deleteFinal(); 
        }
    }

    public int searchIndex(T element) {
        if (isEmpty()) return -1; 
        int aux = 0; 
        NodoDoble<T> pointer = getHead(); 
        while (pointer != null) {
            if (pointer.getElement().equals(element)) return aux;
            aux++; 
            pointer = pointer.getNext();
        }
        return -1; 
    }
}

