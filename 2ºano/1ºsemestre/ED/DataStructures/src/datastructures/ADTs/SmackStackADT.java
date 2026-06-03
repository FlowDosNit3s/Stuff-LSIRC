/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datastructures.ADTs;

import datastructures.exceptions.EmptyCollectionException;

/**
 *
 * @author carlo
 */
public interface SmackStackADT<T> extends StackADT<T>{
    
    public T smack() throws EmptyCollectionException;
}
