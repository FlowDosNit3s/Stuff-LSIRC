package datastructures.implementations.lists;

import datastructures.ADTs.ListADT;
import datastructures.exceptions.EmptyCollectionException;
import datastructures.implementations.nodes.DoubleLinearNode;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a doubly linked list with basic list operations such as
 * adding, removing, checking elements, and iteration. It supports concurrent
 * modification detection through an iterator.
 *
 * @param <T> the type of elements stored in the list
 */
public abstract class DoubleLinkedList<T> implements ListADT<T> {

    /** Reference to the first node in the list. */
    protected DoubleLinearNode<T> front;

    /** Reference to the last node in the list. */
    protected DoubleLinearNode<T> rear;

    /** Number of elements in the list. */
    protected int count;

    /** Modification count to detect concurrent modifications. */
    protected int modCount;

    /**
     * Constructs an empty linked list.
     */
    public DoubleLinkedList() {
        this.front = this.rear = null;
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
        checkIfEmpty();

        T element = this.front.getElement();
        removeNode(this.front);
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
        checkIfEmpty();

        T element = this.rear.getElement();
        removeNode(this.rear);
        return element;
    }

    /**
     * Removes and returns the specified element from the list, if it exists.
     *
     * @param element the element to be removed
     * @return the removed element
     * @throws EmptyCollectionException if the list is empty
     * @throws NoSuchElementException if the element is not found in the list
     */
    @Override
    public T remove(T element) throws EmptyCollectionException {
        checkIfEmpty();

        DoubleLinearNode<T> current = this.front;

        while (current != null) {
            if (current.getElement().equals(element)) {
                removeNode(current);
                return current.getElement();
            }
            current = current.getNext();
        }

        throw new NoSuchElementException("Element not found in the list");
    }

    /**
     * Returns the first element in the list without removing it.
     *
     * @return the first element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        checkIfEmpty();
        return this.front.getElement();
    }

    /**
     * Returns the last element in the list without removing it.
     *
     * @return the last element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public T last() throws EmptyCollectionException {
        checkIfEmpty();
        return this.rear.getElement();
    }

    /**
     * Checks if the list contains the specified target element.
     *
     * @param target the element to search for in the list
     * @return true if the list contains the target element, false otherwise
     */
    @Override
    public boolean contains(T target) {
        DoubleLinearNode<T> current = this.front;

        while (current != null) {
            if (current.getElement().equals(target)) {
                return true;
            }
            current = current.getNext();
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
     * Returns an iterator to traverse the elements of the list.
     *
     * @return an iterator for the list
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Removes the specified node from the list and updates links accordingly.
     *
     * @param node the node to be removed from the list
     */
    private void removeNode(DoubleLinearNode<T> node) {
        if (node == this.front) {
            this.front = node.getNext();
            if (this.front != null) {
                this.front.setPrevious(null);
            } else {
                this.rear = null;
            }
        } else if (node == this.rear) {
            this.rear = node.getPrevious();
            if (this.rear != null) {
                this.rear.setNext(null);
            } else {
                this.front = null;
            }
        } else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        }

        this.count--;
        this.modCount++;
    }

    /**
     * Checks if the list is empty and throws an exception if it is.
     *
     * @throws EmptyCollectionException if the list is empty
     */
    private void checkIfEmpty() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The List Is Empty");
        }
    }

    /**
     * Inner class that implements an iterator to traverse the linked list.
     */
    public class LinkedListIterator implements Iterator<T> {

        /** Current node being iterated. */
        private DoubleLinearNode<T> current;

        /** Expected modification count to detect concurrent modifications. */
        private final int expectedModCount;

        /**
         * Constructs a new iterator for the linked list, starting from the front node.
         */
        public LinkedListIterator() {
            this.current = front;
            this.expectedModCount = modCount;
        }

        /**
         * Returns true if the iteration has more elements.
         *
         * @return true if the iteration has more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            checkForConcurrentModification();
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if there are no more elements to iterate
         */
        @Override
        public T next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException("No More Elements In The List");
            }

            T element = this.current.getElement();
            this.current = current.getNext();

            return element;
        }

        /**
         * Removes the current element from the list.
         * This operation is not supported in this iterator.
         *
         * @throws UnsupportedOperationException always thrown as remove is not supported
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not Supported");
        }

        /**
         * Checks if the modification count has changed, indicating a concurrent modification.
         *
         * @throws ConcurrentModificationException if the collection was modified during iteration
         */
        private void checkForConcurrentModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Concurrent Modification Detected");
            }
        }
    }

}
