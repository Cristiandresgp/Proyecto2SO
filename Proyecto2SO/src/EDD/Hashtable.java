/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import OBJECTS.Archivo;
import java.util.LinkedList;

/**
 *
 * @author cristiandresgp
 */
public class Hashtable {
    //Atributos
    private ListaDoble<Archivo>[] hashtable; 

    //Constructor
    public Hashtable() {
        this.hashtable = new ListaDoble[1259];
        for (int i = 0; i < this.hashtable.length; i++) {
            this.hashtable[i] = new ListaDoble<>(); 
        }
    }

    // Getters y Setters
    public ListaDoble<Archivo>[] getHashtable() {
        return hashtable;
    }

    public void setHashtable(ListaDoble<Archivo>[] hashtable) {
        this.hashtable = hashtable;
    }

    public int getHashSize(){
        return this.hashtable.length;
    }

    // Algoritmo djb2 para la función hash 
    public int hashFunction (String str){
        long hash = 5381;
        int c; 
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i); 
            hash = ((hash << 5) + hash) + c; 
        }
        int index = (int) (hash % getHashSize());  
        return (index < 0) ? -index : index; 
    }

    // Método para insertar un archivo
    public void insert(String str, Archivo archivo) {
        int index = hashFunction(str);
        this.hashtable[index].insertBegin(archivo);
    }

    // Método para eliminar un archivo
    public void delete(String str, Archivo archivo){
        int index = hashFunction(str);
        if (this.hashtable[index].getHead() != null){
            int index2 = this.hashtable[index].searchIndex(archivo);
            this.hashtable[index].deleteInIndex(index2); 
        }
    }

    // Búsqueda de archivos por clave
    public ListaDoble<Archivo> search(String str) {
        int index = hashFunction(str);
        return this.hashtable[index]; 
    }

    // Método para imprimir la tabla hash
    public void print() {
    System.out.println("📌 Contenido de la tabla de asignación:");
    for (int i = 0; i < this.hashtable.length; i++) {
        if (!this.hashtable[i].isEmpty()) {
            System.out.print("[" + i + "] -> ");
            NodoDoble<Archivo> actual = this.hashtable[i].getHead();
            while (actual != null) {
                System.out.print(actual.getElement().getNombre() + " ");
                actual = actual.getNext();
            }
            System.out.println();
        }
    }
}

}

