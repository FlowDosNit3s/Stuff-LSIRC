package datastructures.ADTs;

import datastructures.exceptions.ElementNotFoundException;
import datastructures.exceptions.EmptyCollectionException;
import java.util.Iterator;

/**
 * The ListADT interface defines the general methods for a list.
 * 
 * This interface includes basic operations such as removal, element access,
 * existence check, list size, and obtaining an iterator.
 * 
 * @param <T> the type of elements the list will contain
 * 
 * @author carlos
 */
public interface ListADT<T> extends Iterable<T> {

    /**
     * Removes and returns the first element of the list.
     *
     * @return the first element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    public T removeFirst() throws EmptyCollectionException;

    /**
     * Removes and returns the last element from this list.
     * 
     * @return the last element from this list
     * @throws EmptyCollectionException if the list is empty
     */
    public T removeLast() throws EmptyCollectionException;

    /**
     * Removes and returns the specified element from this list.
     * 
     * @param element the element to be removed from the list
     * @return the removed element
     * @throws EmptyCollectionException if the list is empty
     * @throws ElementNotFoundException if the element is not on the list
     */
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException;

    /**
     * Returns a reference to the first element in this list.
     * 
     * @return a reference to the first element in this list
     * @throws EmptyCollectionException if the list is empty
     */
    public T first() throws EmptyCollectionException;

    /**
     * Returns a reference to the last element in this list.
     * 
     * @return a reference to the last element in this list
     * @throws EmptyCollectionException if the list is empty
     */
    public T last() throws EmptyCollectionException;

    /**
     * Returns true if this list contains the specified target element.
     * 
     * @param target the target that is being sought in the list
     * @return true if the list contains this element
     */
    public boolean contains(T target);

    /**
     * Returns true if this list contains no elements.
     * 
     * @return true if this list contains no elements
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this list.
     * 
     * @return the integer representation of the number of elements in this list
     */
    public int size();

    /**
     * Returns an iterator for the elements in this list.
     * 
     * @return an iterator over the elements in this list
     */
    public Iterator<T> iterator();

    /**
     * Returns a string representation of this list.
     * 
     * @return a string representation of this list
     */
    @Override
    public String toString();
    
}