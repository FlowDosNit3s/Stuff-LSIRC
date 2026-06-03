package datastructures.ADTs;

import datastructures.exceptions.ElementNotFoundException;
import datastructures.exceptions.EmptyCollectionException;

/**
 * BinarySearchTreeADT defines the interface to a binary search tree.
 */
public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {

    /**
     * Adds the specified element to the proper location in this tree.
     *
     * @param element the element to be added to this tree
     */
    public void addElement(T element);

    /**
     * Removes and returns the specified element from this tree.
     *
     * @param targetElement the element to be removed from this tree
     * @return the element removed from this tree
     * @throws ElementNotFoundException if an element not found
     * @throws EmptyCollectionException if the tree is empty
     */
    public T removeElement(T targetElement) throws ElementNotFoundException, EmptyCollectionException;

    /**
     * Removes all occurences of the specified element from this tree.
     *
     * @param targetElement the element that the list will have all instances of
     * it remove
     * @throws EmptyCollectionException if the tree is empty
     */
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException;

    /**
     * Removes and returns the smallest element from this tree.
     *
     * @return the smallest element from this tree.
     * @throws EmptyCollectionException if the tree is empty
     */
    public T removeMin() throws EmptyCollectionException;

    /**
     * Removes and returns the largest element from this tree.
     *
     * @return the largest element from this tree
     * @throws EmptyCollectionException if the tree is empty
     */
    public T removeMax() throws EmptyCollectionException;

    /**
     * Returns a reference to the smallest element in this tree.
     *
     * @return a reference to the smallest element in this tree
     * @throws EmptyCollectionException if the tree is empty
     */
    public T findMin() throws EmptyCollectionException;

    /**
     * Returns a reference to the largest element in this tree.
     *
     * @return a reference to the largest element in this tree
     * @throws EmptyCollectionException if the tree is empty
     */
    public T findMax() throws EmptyCollectionException;
}
