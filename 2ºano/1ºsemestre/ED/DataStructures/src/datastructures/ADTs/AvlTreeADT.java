/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datastructures.ADTs;

/**
 *
 * @author carlo
 */
public interface AvlTreeADT<T> {

    public void insert(T data);

    public void delete(T data);

    public void traverse();

    public T getMax();

    public T getMin();

    public boolean isEmpty();
}
