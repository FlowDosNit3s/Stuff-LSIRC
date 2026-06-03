/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructures.implementations.queue;

import datastructures.ADTs.QueueADT;
import datastructures.exceptions.EmptyCollectionException;

/**
 *
 * @author carlo
 */
public class CircularArrayQueue<T> implements QueueADT<T> {

    private static final int DEFAULT_CAPACITY = 5;

    private static final int EXPANSION_FACTOR = 2;

    private int front, rear, count;

    private T[] queue;

    public CircularArrayQueue() {
        this.front = 0;
        this.rear = 0;
        this.count = 0;

        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    public CircularArrayQueue(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }

        this.front = 0;
        this.rear = 0;
        this.count = 0;
        this.queue = (T[]) (new Object[initialCapacity]);
    }

//this.rear = (this.rear + 1) % this.queue.length;
    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to the rear of this queue
     */
    @Override
    public void enqueue(T element) {
        if (this.count == this.queue.length) {
            this.expandCapacity();
        }

        this.queue[this.rear] = element;
        this.rear = (this.rear + 1) % this.queue.length;
        count++;
    }

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front of this queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The Stack Is Empty");

        }

        T removedElement = this.queue[this.front];

        this.queue[this.front] = null;
        this.front = (this.front + 1) % this.queue.length;
        count--;

        return removedElement;

    }

    /**
     * Returns without removing the element at the front of this queue.
     *
     * @return the first element in this queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        return this.queue[this.front];
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the integer representation of the size of this queue
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the string representation of this queue 37.
     */
    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < queue.length; i++) {
            if (queue[i] == null) {
                System.out.println("null");
            } else {
                System.out.println(queue[i].toString());
            }
        }

        return s;
    }

    /**
     * Expands the capacity of the stack by creating a new array with larger
     * capacity and copying the elements from the current array to the new one.
     */
    private void expandCapacity() {
        T[] tempArray = (T[]) (new Object[this.count * EXPANSION_FACTOR]);

        for (int i = 0; i < this.count; i++) {
            tempArray[i] = this.queue[(this.front + i) % this.queue.length];
        }

        this.front = 0;
        this.rear = this.count;
        this.queue = tempArray;
    }

}
