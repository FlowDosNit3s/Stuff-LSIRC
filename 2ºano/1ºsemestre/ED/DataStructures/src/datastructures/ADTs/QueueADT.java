package datastructures.ADTs;

import datastructures.exceptions.EmptyCollectionException;

public interface QueueADT<T> {

    public void enqueue(T element);

    public T dequeue() throws EmptyCollectionException;

    public T first() throws EmptyCollectionException;

    public boolean isEmpty();

    public int size();

}
