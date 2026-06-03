package datastructures.implementations.lists;

import datastructures.ADTs.UnorderedListADT;
import datastructures.implementations.nodes.DoubleLinearNode;
import java.util.NoSuchElementException;

/**
 * The DoubleLinkedUnorderedList class is a generic implementation of an
 * unordered doubly linked list. It extends the LinkedList class and implements
 * the UnorderedListADT interface. This class allows adding elements to the
 * front, rear, or after a specified element in the list.
 *
 * @param <T> The type of elements stored in the list
 */
public class DoubleLinkedUnorderedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {

    /**
     * Adds a new element to the front of the list.
     *
     * @param element The element to be added to the front of the list
     * @throws IllegalArgumentException if the element is null
     */
    @Override
    public void addToFront(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        DoubleLinearNode<T> newElement = new DoubleLinearNode<>(element);

        if (isEmpty()) {
            this.front = this.rear = newElement;
        } else {
            newElement.setNext(this.front);
            this.front.setPrevious(newElement);
            this.front = newElement;
        }

        this.count++;
        this.modCount++;
    }

    /**
     * Adds a new element to the rear of the list.
     *
     * @param element The element to be added to the rear of the list
     * @throws IllegalArgumentException if the element is null
     */
    @Override
    public void addToRear(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        DoubleLinearNode<T> newElement = new DoubleLinearNode<>(element);

        if (isEmpty()) {
            this.front = this.rear = newElement;
        } else {
            newElement.setPrevious(this.rear);
            this.rear.setNext(newElement);
            this.rear = newElement;
        }

        this.count++;
        this.modCount++;
    }

    /**
     * Adds a new element after a specified element in the list.
     *
     * @param after The element after which the new element will be added
     * @param element The new element to be added
     * @throws IllegalArgumentException if either the 'after' or 'element'
     * parameter is null
     * @throws NoSuchElementException if the specified 'after' element is not
     * found in the list
     */
    @Override
    public void addAfter(T after, T element) {
        if (after == null || element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        DoubleLinearNode<T> node = findNode(after);

        if (node == null) {
            throw new NoSuchElementException("Element to add after not found");
        }

        if (node == this.rear) {
            addToRear(element);
        } else {
            DoubleLinearNode<T> newElement = new DoubleLinearNode<>(element);

            newElement.setPrevious(node);
            newElement.setNext(node.getNext());
            node.getNext().setPrevious(newElement);
            node.setNext(newElement);

            this.count++;
            this.modCount++;
        }
    }

    /**
     * Finds the node containing the specified element.
     *
     * @param element The element to search for
     * @return The node containing the specified element, or null if not found
     */
    private DoubleLinearNode<T> findNode(T element) {
        DoubleLinearNode<T> current = this.front;

        while (current != null) {
            if (current.getElement().equals(element)) {
                return current;
            }
            current = current.getNext();
        }

        return null;
    }
}
