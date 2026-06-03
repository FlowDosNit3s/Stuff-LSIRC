package datastructures.implementations.lists;

import datastructures.ADTs.ListADT;
import datastructures.exceptions.ElementNotFoundException;
import datastructures.exceptions.EmptyCollectionException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An abstract class representing a generic array-based list that implements the
 * ListADT interface. Provides basic functionality for adding, removing, and
 * iterating over elements, as well as utility methods for checking list size,
 * emptiness, and containment.
 *
 * @param <T> the generic type of elements stored in this list
 */
public abstract class ArrayList<T> implements ListADT<T> {

    // Default initial capacity of the list
    private static final int INITIAL_CAPACITY = 10;

    // The amount by which the list expands when it runs out of space
    private static final int EXPANSION_FACTOR = 10;

    // Array to store elements in the list
    protected T[] list;
    // Number of elements currently in the list
    protected int count;
    // Number of modifications in the list
    protected int modCount;

    /**
     * Initializes the array list with the default initial capacity and sets the
     * element count to 0.
     */
    public ArrayList() {
        this.list = (T[]) (new Object[INITIAL_CAPACITY]);
        this.count = this.modCount = 0;
    }
    

    /**
     * Removes and returns the first element of the list.
     *
     * @return the first element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The List Is Empty");
        }

        T element = this.list[0];

        for (int i = 0; i < this.count - 1; i++) {
            this.list[i] = this.list[i + 1];
        }

        this.list[--this.count] = null;

        this.modCount++;
        return element;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * @return the last element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The List Is Empty");
        }

        T element = this.list[this.count - 1];
        this.list[--this.count] = null;

        this.modCount++;
        return element;
    }

    /**
     * Removes and returns the specified element from the list.
     *
     * @param element the element to be removed
     * @return the removed element
     * @throws EmptyCollectionException if the list is empty
     * @throws ElementNotFoundException if the element is not found in the list
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The List Is Empty");
        }

        int index = this.find(element);

        if (index == -1) {
            throw new ElementNotFoundException("Element not found in the list");
        }

        T elementToRemove = this.list[index];

        for (int i = index; i < this.count - 1; i++) {
            this.list[i] = this.list[i + 1];
        }

        this.list[--this.count] = null;

        this.modCount++;
        return elementToRemove;
    }

    /**
     * Returns the first element in the list without removing it.
     *
     * @return the first element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The List Is Empty");
        }
        return this.list[0];
    }

    /**
     * Returns the last element in the list without removing it.
     *
     * @return the last element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The List Is Empty");
        }
        return this.list[this.count - 1];
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param target the element to be checked
     * @return true if the list contains the element, false otherwise
     */
    @Override
    public boolean contains(T target) {
        for (int i = 0; i < this.count; i++) {
            if (this.list[i].equals(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Finds the index of the specified element in the list.
     *
     * @param element the element to find
     * @return the index of the element, or -1 if not found
     */
    private int find(T element) {
        int position = -1;

        for (int i = 0; i < this.count; i++) {
            if (this.list[i].equals(element)) {
                position = i;
                break;
            }
        }

        return position;
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an iterator over the elements in the list
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator();
    }

    /**
     * Expands the capacity of the list by the EXPANSION_FACTOR.
     */
    protected void expandCapacity() {
        T[] temp = (T[]) (new Object[this.list.length + EXPANSION_FACTOR]);
        System.arraycopy(this.list, 0, temp, 0, this.count);
        this.list = temp;
    }

    /**
     * Returns a string representation of the list.
     *
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        String s = "******\n List \n******\n";

        for (T element : this.list) {
            if (element != null) {
                s += "➯ " + element.toString() + '\n';
            }
        }

        return s;
    }

    /**
     * A basic iterator class to iterate over the elements in the list.
     */
    public class BasicIterator implements Iterator<T> {

        // Tracks the current index for iteration
        private int current;
        // Tracks the modCount at the time the iterator was created
        private final int expectedModCount;

        /**
         * Initializes the iterator starting at the first element.
         */
        public BasicIterator() {
            this.current = 0;
            this.expectedModCount = modCount;
        }

        /**
         * Checks if there are more elements in the list.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            checkForConcurrentModification();
            return this.current < count;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the list
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public T next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException("No More Elements In The List");
            }

            return list[this.current++];
        }

        /**
         * The remove operation is not supported.
         *
         * @throws UnsupportedOperationException always
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Checks if the modCount has changed, indicating a concurrent
         * modification.
         *
         * @throws ConcurrentModificationException if the collection was
         * modified during iteration
         */
        private void checkForConcurrentModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Concurrent Modification Detected");
            }
        }

    }
}
