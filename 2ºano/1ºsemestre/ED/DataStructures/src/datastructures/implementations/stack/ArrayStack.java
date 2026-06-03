package datastructures.implementations.stack;

import datastructures.ADTs.StackADT;
import datastructures.exceptions.EmptyCollectionException;

/**
 * A generic array-based implementation of a stack (LIFO - Last In First Out)
 * data structure. This stack dynamically resizes when necessary to accommodate
 * more elements.
 *
 * @param <T> The type of elements stored in the stack.
 */
public class ArrayStack<T> implements StackADT<T> {

    /**
     * constant to represent the default capacity of the array
     */
    private static final int DEFAULT_CAPACITY = 50;

    /**
     * Constant representing the factor by which the stack's capacity expands
     * when full.
     */
    private static final int EXPANSION_FACTOR = 2;

    /**
     * int that represents both the number of elements and the next available
     * position in the array
     */
    protected int count;

    /**
     * array of generic elements to represent the stack
     */
    protected T[] stack;

    /**
     * Creates an empty stack using the default capacity.
     */
    public ArrayStack() {
        this.count = 0;
        this.stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty stack using the specified capacity.
     *
     * @param initialCapacity represents the specified capacity
     */
    public ArrayStack(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }

        this.count = 0;
        this.stack = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Adds the specified element to the top of this stack, expanding the
     * capacity of the stack array if necessary.
     *
     * @param element generic element to be pushed onto stack
     */
    @Override
    public void push(T element) {
        if (this.size() == this.stack.length) {
            expandCapacity();
        }

        this.stack[this.count++] = element;
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

        this.count--;
        T element = this.stack[this.count];
        this.stack[this.count] = null;

        return element;
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

        return this.stack[this.count - 1];
    }

    /**
     * Checks if the stack is empty.
     *
     * @return boolean true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return int the number of elements in the stack
     */
    @Override
    public int size() {
        return this.count;
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
        int position = this.count - 1;

        while (position >= 0) {
            s += this.stack[position--].toString() + '\n';
        }

        return s;
    }

    /**
     * Expands the capacity of the stack by creating a new array with larger
     * capacity and copying the elements from the current array to the new one.
     */
    private void expandCapacity() {
        T[] tempArray = (T[]) (new Object[this.count * EXPANSION_FACTOR]);
        System.arraycopy(this.stack, 0, tempArray, 0, this.count);

        this.stack = tempArray;
    }

}
