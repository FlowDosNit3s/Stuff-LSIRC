package datastructures.ADTs;

public interface UnorderedListADT<T> extends ListADT<T> {

    public void addToFront(T element);

    public void addToRear(T element);

    public void addAfter(T after, T element);

}
