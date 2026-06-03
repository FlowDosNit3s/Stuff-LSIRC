package datastructures.ADTs;

/**
 * The OrderedListADT interface defines the methods for an ordered list.
 *
 * This interface extends ListADT and includes the ability to add elements in
 * the proper position within the list based on their natural order or a defined
 * order.
 *
 * @param <T> the type of elements the list will contain
 *
 * @author carlos
 */
public interface OrderedListADT<T> extends ListADT<T> {

    /**
     * Adds the specified element to this list at the proper location
     *
     * @param element the element to be added to this list
     */
    public void add(T element);
}
