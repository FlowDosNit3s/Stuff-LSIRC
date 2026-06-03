package datastructures.implementations.lists;

import datastructures.ADTs.OrderedListADT;
import datastructures.implementations.nodes.DoubleLinearNode;

public class DoubleLinkedOrderedList<T> extends DoubleLinkedList<T> implements OrderedListADT<T> {

    @Override
    public void add(T element) {

        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (!(element instanceof Comparable)) {
            throw new IllegalArgumentException("Element must be comparable");
        }

        DoubleLinearNode<T> current = front;

        Comparable<T> comparableElement = (Comparable<T>) element;

        while (current != null && comparableElement.compareTo(current.getElement()) > 0) {
            current = current.getNext();
        }

        DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element);

        if (current == null) {
            if (isEmpty()) {
                front = newNode;
                rear = newNode;
            } else {
                rear.setNext(newNode);
                newNode.setPrevious(rear);
                rear = newNode;
            }
        } else if (current == front) {
            newNode.setNext(front);
            front.setPrevious(newNode);
            front = newNode;
        } else {
            newNode.setPrevious(current.getPrevious());
            newNode.setNext(current);
            current.getPrevious().setNext(newNode);
            current.setPrevious(newNode);
        }
        
        count++;
        modCount++;

    }

}
