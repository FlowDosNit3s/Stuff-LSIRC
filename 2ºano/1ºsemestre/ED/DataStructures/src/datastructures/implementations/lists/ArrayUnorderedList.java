package datastructures.implementations.lists;

import datastructures.ADTs.UnorderedListADT;
import java.util.NoSuchElementException;

/**
 * A generic class that extends ArrayList and implements UnorderedListADT. This
 * class represents an unoerder array-based list where elements are inserted on
 * the front, rear, or after a specific element in the list.
 *
 * @param <T> the generic type of elements stored in this list
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Adds the specified element to the front of the list. If the array is
     * full, the capacity is expanded.
     *
     * @param element the element to be added to the front of the list
     * @throws IllegalArgumentException if the element is null
     */
    @Override
    public void addToFront(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        if (this.count == this.list.length) {
            this.expandCapacity();
        }

        for (int i = count; i > 0; i--) {
            this.list[i] = this.list[i - 1];
        }

        this.list[0] = element;
        this.count++;
    }

    /**
     * Adds the specified element to the rear of the list. If the array is full,
     * the capacity is expanded.
     *
     * @param element the element to be added to the rear of the list
     * @throws IllegalArgumentException if the element is null
     */
    @Override
    public void addToRear(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        if (this.count == this.list.length) {
            this.expandCapacity();
        }

        this.list[this.count++] = element;
    }

    /**
     * Adds the specified element after a given element in the list. If the
     * array is full, the capacity is expanded.
     *
     * @param after the element after which the new element will be added
     * @param element the element to be added after the specified "after"
     * element
     * @throws IllegalArgumentException if either the 'after' or 'element'
     * parameter is null
     * @throws NoSuchElementException if the specified 'after' element is not
     * found in the lists
     */
    @Override
    public void addAfter(T after, T element) {
        if (after == null || element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        if (this.count == this.list.length) {
            this.expandCapacity();
        }

        int position = -1;
        for (int i = 0; i < this.count; i++) {
            if (this.list[i].equals(after)) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            throw new NoSuchElementException("Element to add after not found");
        }

        for (int i = this.count; i > position + 1; i--) {
            this.list[i] = this.list[i - 1];
        }

        this.list[position + 1] = element;
        this.count++;
    }
}
