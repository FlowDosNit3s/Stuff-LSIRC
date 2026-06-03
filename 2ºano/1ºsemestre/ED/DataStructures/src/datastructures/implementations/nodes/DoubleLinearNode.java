package datastructures.implementations.nodes;

/**
 *
 * @author Carlos Barbosa
 */
public class DoubleLinearNode<T> {

    /**
     * reference to next node in list
     */
    private DoubleLinearNode<T> next;

    /**
     * reference to previous node in list
     */
    private DoubleLinearNode<T> previous;

    /**
     * element stored at this node
     */
    private T element;

    /**
     * Creates an empty node.
     */
    public DoubleLinearNode() {
        this.next = null;
        this.previous = null;
        this.element = null;
    }

    /**
     * Creates a node storing the specified element.
     *
     * @param element element to be stored
     */
    public DoubleLinearNode(T element) {
        this.element = element;
        this.next = null;
        this.previous = null;
    }

    /**
     * Returns the node that follows this one.
     *
     * @return DoubleLinearNode<T> reference to next node
     */
    public DoubleLinearNode<T> getNext() {
        return this.next;
    }

    /**
     * Sets the node that follows this one.
     *
     * @param node node to follow this one
     */
    public void setNext(DoubleLinearNode<T> node) {
        this.next = node;
    }

    /**
     * Returns the node that precedes it.
     *
     * @return DoubleLinearNode<T> reference to previous node
     */
    public DoubleLinearNode<T> getPrevious() {
        return this.previous;
    }

    /**
     * Sets the node that precedes it.
     *
     * @param node node to precedes this one
     */
    public void setPrevious(DoubleLinearNode<T> node) {
        this.previous = node;
    }

    /**
     * Returns the element stored in this node.
     *
     * @return T element stored at this node
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Sets the element stored in this node.
     *
     * @param element element to be stored at this node
     */
    public void setElement(T element) {
        this.element = element;
    }

}
