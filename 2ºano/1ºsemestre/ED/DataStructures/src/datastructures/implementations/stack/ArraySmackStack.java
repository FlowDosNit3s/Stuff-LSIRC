/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructures.implementations.stack;

import datastructures.ADTs.SmackStackADT;
import datastructures.exceptions.EmptyCollectionException;

/**
 * The ArraySmackStack class represents a stack data structure that allows for
 * both traditional stack operations (like pop()) and a custom operation to
 * remove the bottom element using the smack() method.
 *
 * @param <T> the type of elements stored in the stack
 */
public class ArraySmackStack<T> extends ArrayStack<T> implements SmackStackADT<T> {

    /**
     * Removes and returns the bottom element of the stack.
     *
     * @return the bottom element of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    @Override
    public T smack() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("The Stack Is Empty");
        }

        T elementToRemove = this.stack[0];
        for (int i = 0; i < this.count - 1; i++) {
            this.stack[i] = this.stack[i + 1];
        }
        this.stack[--this.count] = null;

        return elementToRemove;
    }

}
