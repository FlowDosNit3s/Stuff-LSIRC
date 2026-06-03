package datastructures.implementations.stack;

import datastructures.ADTs.StackADT;
import datastructures.exceptions.EmptyCollectionException;
import datastructures.implementations.nodes.LinearNode;

/**
 *
 * @author Carlos Barbosa
 */
public class LinkedStack<T> implements StackADT<T> {

    /**
     * int that represents both the number of elements and the next available
     * position in the array
     */
    private int size;

    /**
     * array of generic elements to represent the stack
     */
    private LinearNode<T> head;

    /**
     * Creates an empty stack using the default capacity.
     */
    public LinkedStack() {
        this.size = 0;
        this.head = null;
    }

    /**
     * Creates an stack with one element.
     *
     * @param element the element in the stack
     */
    public LinkedStack(T element) {
        this.head = new LinearNode<>(element);
        this.size = 1;
    }

    /**
     * Adds the specified element to the top of this stack
     *
     * @param element generic element to be pushed onto stack
     */
    @Override
    public void push(T element) {
        LinearNode<T> newElement = new LinearNode<>(element);

        newElement.setNext(this.head);
        this.head = newElement;
        this.size++;
    }

    /**
     * Removes the element at the top of this stack and returns a reference to
     * it. Throws an EmptyCollectionException if the stack is empty.
     *
     * @return T element removed from top of stack
     * @throws EmptyCollectionException if a pop is attempted on empty stack
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("The Stack Is Empty");
        }

        LinearNode<T> oldHead = this.head;
        oldHead.setNext(null);

        this.head = this.head.getNext();
        this.size--;

        return oldHead.getElement();
    }

    /**
     * Returns a reference to the element at the top of this stack. The element
     * is not removed from the stack. Throws an EmptyCollectionException if the
     * stack is empty.
     *
     * @return T element on top of stack
     * @throws EmptyCollectionException if a peek is attempted on empty stack
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("The Stack Is Empty");
        }

        return this.head.getElement();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return boolean true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return int the number of elements in the stack
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns a string representation of the stack, listing elements from top
     * to bottom.
     *
     * @return a string representation of the stack
     */
    @Override
    public String toString() {
        String s = "";

        LinearNode<T> current = this.head;

        while (current != null) {
            s += current.getElement().toString() + '\n';

            current = current.getNext();
        }

        return s;
    }

}
