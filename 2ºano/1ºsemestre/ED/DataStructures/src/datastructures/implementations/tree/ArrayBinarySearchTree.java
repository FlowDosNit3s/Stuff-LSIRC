package datastructures.implementations.tree;

import datastructures.ADTs.BinarySearchTreeADT;
import datastructures.exceptions.ElementNotFoundException;
import datastructures.exceptions.EmptyCollectionException;
import datastructures.implementations.lists.ArrayUnorderedList;
import java.util.Iterator;

public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {

    private int height;
    private int maxIndex;

    public ArrayBinarySearchTree() {
        super();
        height = maxIndex = 0;
    }

    public ArrayBinarySearchTree(T element) {
        super(element);
        height = 1;
        maxIndex = 0;
    }

    /**
     * Adds the specified element to the proper location in this tree.
     *
     * @param element the element to be added to this tree
     */
    @Override
    public void addElement(T element) {

        if (!(element instanceof Comparable)) {
            throw new IllegalArgumentException("The Element Must Be Comparable");
        }

        Comparable<T> comparableElement = (Comparable<T>) element;

        if (count >= tree.length) {
            expandCapacity();
        }

        if (isEmpty()) {
            tree[0] = element;
            maxIndex = 0;
        } else {
            int current = 0;
            boolean added = false;

            while (!added) {
                if (comparableElement.compareTo(tree[current]) < 0) {
                    int leftChild = current * 2 + 1;
                    if (leftChild >= tree.length) {
                        expandCapacity();
                    }

                    if (tree[leftChild] == null) {
                        tree[leftChild] = element;
                        maxIndex = Math.max(maxIndex, leftChild);
                        added = true;
                    } else {
                        current = leftChild;
                    }
                } else {
                    int rightChild = (current + 1) * 2;
                    if (rightChild >= tree.length) {
                        expandCapacity();
                    }

                    if (tree[rightChild] == null) {
                        tree[rightChild] = element;
                        maxIndex = Math.max(maxIndex, rightChild);
                        added = true;
                    } else {
                        current = rightChild;
                    }
                }
            }
        }

        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        count++;
    }

    /**
     * Expands the capacity of the array if needed by creating a larger array
     * and copying elements into it.
     */
    private void expandCapacity() {
        T[] temp = (T[]) (new Object[tree.length * 2]);
        System.arraycopy(tree, 0, temp, 0, tree.length);
        tree = temp;
    }

    /**
     * Removes and returns the specified element from this tree.
     *
     * @param targetElement the element to be removed from this tree
     * @return the element removed from this tree
     * @throws ElementNotFoundException if an element not found
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException, EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("binary search tree");
        }

        Comparable<T> tempElement = (Comparable<T>) targetElement;

        int targetIndex = findIndex(tempElement, 0);

        if (targetIndex == -1) {
            throw new ElementNotFoundException("The Element Does Not Exist");
        }

        T result = tree[targetIndex];
        replace(targetIndex);
        count--;

        int temp = maxIndex;
        maxIndex = -1;
        for (int i = 0; i <= temp; i++) {
            if (tree[i] != null) {
                maxIndex = i;
            }
        }

        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;

        return result;
    }

    /**
     * Removes the node specified for removal and shifts the tree array
     * accordingly.
     *
     * @param targetIndex the node to be removed
     */
    protected void replace(int targetIndex) {
        int currentIndex, oldIndex, newIndex;
        ArrayUnorderedList<Integer> oldlist = new ArrayUnorderedList<>();
        ArrayUnorderedList<Integer> newlist = new ArrayUnorderedList<>();
        ArrayUnorderedList<Integer> templist = new ArrayUnorderedList<>();
        Iterator<Integer> oldIt, newIt;

        /**
         * if target node has no children
         */
        if ((targetIndex * 2 + 1 >= tree.length) || (targetIndex * 2 + 2 >= tree.length)) {
            tree[targetIndex] = null;
        } /**
         * if target node has no children
         */
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] == null)) {
            tree[targetIndex] = null;
        } /**
         * if target node only has a left child
         */
        else if ((tree[targetIndex * 2 + 1] != null) && (tree[targetIndex * 2 + 2] == null)) {
            /**
             * fill newlist with indices of nodes that will replace the
             * corresponding indices in oldlist
             */
            currentIndex = targetIndex * 2 + 1;
            templist.addToRear(currentIndex);

            while (!templist.isEmpty()) {
                try {
                    currentIndex = (templist.removeFirst());
                } catch (EmptyCollectionException ex) {
                    System.out.println("Unexpected Error");
                }
                newlist.addToRear(currentIndex);
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(currentIndex * 2 + 1);
                    templist.addToRear(currentIndex * 2 + 2);
                }
            }

            /**
             * fill oldlist
             */
            currentIndex = targetIndex;
            templist.addToRear(currentIndex);
            while (!templist.isEmpty()) {
                try {
                    currentIndex = (templist.removeFirst());
                } catch (EmptyCollectionException ex) {
                    System.out.println("Unexpected Error");
                }
                oldlist.addToRear(currentIndex);
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(currentIndex * 2 + 1);
                    templist.addToRear(currentIndex * 2 + 2);
                }
            }

            /**
             * do replacement
             */
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();
                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } /**
         * if target node only has a right child
         */
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] != null)) {
            /**
             * fill newlist with indices of nodes that will replace the
             * corresponding indices in oldlist
             */
            currentIndex = targetIndex * 2 + 2;
            templist.addToRear(currentIndex);
            while (!templist.isEmpty()) {
                try {
                    currentIndex = (templist.removeFirst());
                } catch (EmptyCollectionException ex) {
                    System.out.println("Unexpected Error");
                }
                newlist.addToRear(currentIndex);
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(currentIndex * 2 + 1);
                    templist.addToRear(currentIndex * 2 + 2);
                }
            }

            /**
             * fill oldlist
             */
            currentIndex = targetIndex;
            templist.addToRear(currentIndex);
            while (!templist.isEmpty()) {
                try {
                    currentIndex = (templist.removeFirst());
                } catch (EmptyCollectionException ex) {
                    System.out.println("Unexpected Error");
                }
                oldlist.addToRear(currentIndex);
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(currentIndex * 2 + 1);
                    templist.addToRear(currentIndex * 2 + 2);
                }
            }

            /**
             * do replacement
             */
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();

                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } /**
         * if target node has two children
         */
        else {
            currentIndex = targetIndex * 2 + 2;

            while (tree[currentIndex * 2 + 1] != null) {
                currentIndex = currentIndex * 2 + 1;
            }

            tree[targetIndex] = tree[currentIndex];

            /**
             * the index of the root of the subtree to be replaced
             */
            int currentRoot = currentIndex;

            /**
             * if currentIndex has a right child
             */
            if (tree[currentRoot * 2 + 2] != null) {
                /**
                 * fill newlist with indices of nodes that will replace the
                 * corresponding indices in oldlist
                 */
                currentIndex = currentRoot * 2 + 2;
                templist.addToRear(currentIndex);
                while (!templist.isEmpty()) {
                    try {
                        currentIndex = (templist.removeFirst());
                    } catch (EmptyCollectionException ex) {
                        System.out.println("Unexpected Error");
                    }
                    newlist.addToRear(currentIndex);
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(currentIndex * 2 + 1);
                        templist.addToRear(currentIndex * 2 + 2);
                    }
                }

                /**
                 * fill oldlist
                 */
                currentIndex = currentRoot;
                templist.addToRear(currentIndex);
                while (!templist.isEmpty()) {
                    try {
                        currentIndex = (templist.removeFirst());
                    } catch (EmptyCollectionException ex) {
                        System.out.println("Unexpected Error");
                    }
                    oldlist.addToRear(currentIndex);
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(currentIndex * 2 + 1);
                        templist.addToRear(currentIndex * 2 + 2);
                    }
                }

                /**
                 * do replacement
                 */
                oldIt = oldlist.iterator();
                newIt = newlist.iterator();
                while (newIt.hasNext()) {
                    oldIndex = oldIt.next();
                    newIndex = newIt.next();

                    tree[oldIndex] = tree[newIndex];
                    tree[newIndex] = null;
                }
            } else {
                tree[currentRoot] = null;
            }
        }
    }

    /**
     * Removes all occurences of the specified element from this tree.
     *
     * @param targetElement the element that the list will have all instances of
     * it remove
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException {
        while (contains(targetElement)) {
            try {
                removeElement(targetElement);
            } catch (ElementNotFoundException e) {
                System.out.println("Not Found");
            }
        }
    }

    /**
     * Removes and returns the smallest element from this tree.
     *
     * @return the smallest element from this tree.
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        try {
            return removeElement(findMin());
        } catch (ElementNotFoundException ex) {
            System.out.println("Unexpected Error");
        }
        return null;
    }

    /**
     * Removes and returns the largest element from this tree.
     *
     * @return the largest element from this tree.
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeMax() throws EmptyCollectionException {
        try {
            return removeElement(findMax());
        } catch (ElementNotFoundException ex) {
            System.out.println("Unexpected Error");
        }
        return null;
    }

    /**
     * Returns a reference to the smallest element in this tree.
     *
     * @return a reference to the smallest element in this tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty Tree");
        }

        int currentIndex = 0;
        while (tree[currentIndex * 2 + 1] != null) {
            currentIndex = currentIndex * 2 + 1;
        }

        return tree[currentIndex];
    }

    /**
     * Returns a reference to the largest element in this tree.
     *
     * @return a reference to the largest element in this tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T findMax() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty Tree");
        }

        int currentIndex = 0;
        while (tree[(currentIndex + 1) * 2] != null) {
            currentIndex = (currentIndex + 1) * 2;
        }

        return tree[currentIndex];
    }

    /**
     * Finds the index of the specified element in the tree array.
     *
     * @param tempElement the element to be found in the tree
     * @param min the starting index to begin the search
     * @return the index of the element if found, or -1 if the element is not
     * found
     */
    private int findIndex(Comparable<T> tempElement, int min) {
        for (int i = min; i < maxIndex; i++) {
            if (tree[i] != null) {
                if (tempElement.compareTo(tree[i]) == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

}
