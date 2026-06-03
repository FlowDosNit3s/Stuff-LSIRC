package datastructures.implementations.lists;

import datastructures.ADTs.OrderedListADT;

/**
 * A generic class that extends ArrayList_it and implements OrderedListADT. 
 * This class represents an ordered array-based list where elements are inserted 
 * in ascending order based on their natural ordering
 *
 * @param <T> the generic type of elements stored in this ordered list
 */
public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    /**
     * Adds the specified element to this ordered list while maintaining the natural
     * order of the elements. The element must implement the Comparable interface.
     * If the list is full, its capacity is expanded before adding the new element.
     * 
     * @param element the element to be added to the list
     * @throws IllegalArgumentException if the element is null or does not implement Comparable
     */
    @Override
    public void add(T element) {

        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (!(element instanceof Comparable)) {
            throw new IllegalArgumentException("Element must be comparable");
        }
        
        if (this.count == this.list.length) {
            this.expandCapacity();
        }

        Comparable<T> comparableElement = (Comparable<T>) element;

        int position = 0;

        while (position < this.count && comparableElement.compareTo(this.list[position]) > 0) {
            position++;
        }

        for (int i = this.count; i > position; i--) {
            this.list[i] = this.list[i - 1];
        }

        this.list[position] = element;
        this.count++;
    }

}
