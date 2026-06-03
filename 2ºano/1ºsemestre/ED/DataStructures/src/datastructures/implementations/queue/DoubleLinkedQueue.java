package datastructures.implementations.queue;

import datastructures.ADTs.QueueADT;
import datastructures.exceptions.EmptyCollectionException;
import datastructures.implementations.nodes.DoubleLinearNode;

public class DoubleLinkedQueue<T> implements QueueADT<T> {

    private int size;

    private DoubleLinearNode<T> front;
    private DoubleLinearNode<T> rear;

    public DoubleLinkedQueue() {
        this.size = 0;
        this.front = null;
        this.rear = null;
    }

    public DoubleLinkedQueue(T element) {
        DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element);

        this.front = newNode;
        this.rear = newNode;
        this.size = 1;
    }

    @Override
    public void enqueue(T element) {
        DoubleLinearNode<T> newElement = new DoubleLinearNode<>(element);

        if (isEmpty()) {
            this.front = newElement;
            this.rear = newElement;
        } else {
            this.rear.setNext(newElement);
            newElement.setPrevious(this.rear);
            this.rear = newElement;
        }

        this.size++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("The Queue Is Empty");
        }

        DoubleLinearNode<T> removedNode = this.front;

        this.front = this.front.getNext();
        size--;

        if (isEmpty()) {
            this.rear = null;
        } else {
            this.front.setPrevious(null);
        }

        return removedNode.getElement();
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("The Queue Is Empty");
        }

        return this.front.getElement();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String s = "";

        DoubleLinearNode<T> current = this.front;

        while (current != null) {
            s += current.getElement().toString() + '\n';

            if (current.getPrevious() != null) {
                s += "previous: " + current.getPrevious().getElement().toString() + '\n';
            } else {
                s += "previous: null \n";
            }

            if (current.getNext() != null) {
                s += "next: " + current.getNext().getElement().toString() + '\n';
            } else {
                s += "next: null \n";
            }
            s += "---\n";

            current = current.getNext();
        }

        return s;
    }
}