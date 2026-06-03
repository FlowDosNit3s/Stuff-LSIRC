package datastructures.implementations.queue;

import datastructures.ADTs.QueueADT;
import datastructures.exceptions.EmptyCollectionException;
import datastructures.implementations.nodes.LinearNode;

public class LinkedQueue<T> implements QueueADT<T> {

    private int size;

    private LinearNode<T> front;
    private LinearNode<T> rear;

    public LinkedQueue() {
        this.size = 0;
        this.front = null;
        this.rear = null;
    }

    public LinkedQueue(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);

        this.front = newNode;
        this.rear = newNode;
        this.size = 1;
    }

    @Override
    public void enqueue(T element) {
        LinearNode<T> newElement = new LinearNode<>(element);

        if (isEmpty()) {
            this.front = newElement;
            this.rear = newElement;
        } else {
            this.rear.setNext(newElement);
            this.rear = newElement;
        }

        this.size++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("The Queue Is Empty");
        }

        LinearNode<T> removedNode = this.front;

        this.front = this.front.getNext();
        size--;

        if (isEmpty()) {
            this.rear = null;
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

        LinearNode<T> current = this.front;

        while (current != null) {
            s += current.getElement().toString() + '\n';

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