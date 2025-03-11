/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package EDD;

/**
 *
 * @author cristiandresgp
 */
public interface ILista<T> {
    public void insertBegin(T element);
    public void insertFinal(T element);
    public void insertInIndex(T element, int index);
    public T deleteBegin();
    public T deleteFinal();
    public T deleteInIndex(int index);
    public boolean isEmpty();
    public void print();
}

