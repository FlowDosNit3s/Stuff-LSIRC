package datastructures.implementations.tree;

import datastructures.ADTs.BinaryTreeADT;
import datastructures.exceptions.ElementNotFoundException;
import datastructures.exceptions.EmptyCollectionException;
import datastructures.implementations.lists.ArrayUnorderedList;
import datastructures.implementations.queue.LinkedQueue;
import java.util.Iterator;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {

    protected T[] tree;
    protected int count;
    private static final int INITIAL_CAPACITY = 10;

    public ArrayBinaryTree() {
        this.tree = (T[]) new Object[INITIAL_CAPACITY];
        this.count = 0;

    }

    public ArrayBinaryTree(T element) {
        this.tree = (T[]) new Object[INITIAL_CAPACITY];
        this.tree[0] = element;
        this.count = 1;

    }

    @Override
    public T getRoot() {
        if (count > 0) {
            return this.tree[0];
        } else {
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean contains(T targetElement) {
        for (T element : tree) {
            if (element != null) {
                if (element.equals(targetElement)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        for (int i = 0; i < this.count; i++) {
            if (tree[i].equals(targetElement)) {
                return tree[i];
            }
        }
        throw new ElementNotFoundException("The Element Does Not Exists");
    }

    /**
     * Performs an inorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        inorder(0, templist);
        return templist.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void inorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder(node * 2 + 1, templist); //left
                templist.addToRear(tree[node]);
                inorder((node + 1) * 2, templist); //right
            }
        }
    }

    /**
     * Performs an preorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        preorder(0, templist);
        return templist.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void preorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                templist.addToRear(tree[node]);
                preorder(node * 2 + 1, templist); //left
                preorder((node + 1) * 2, templist); //right
            }
        }
    }

    /**
     * Performs an postorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        postorder(0, templist);
        return templist.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void postorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                postorder(node * 2 + 1, templist); //left
                postorder((node + 1) * 2, templist); // right
                templist.addToRear(tree[node]);
            }
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {

        LinkedQueue<Integer> nodes = new LinkedQueue<>();
        ArrayUnorderedList<T> results = new ArrayUnorderedList<>();

        nodes.enqueue(0);
        int current;
        try {
            while (!nodes.isEmpty()) {
                current = nodes.dequeue();

                if (tree[current] != null) {
                    results.addToRear(tree[current]);

                    int left = current * 2 + 1;
                    int right = (current + 1) * 2;

                    if (left < tree.length && tree[left] != null) {
                        nodes.enqueue(left);
                    }
                    if (right < tree.length && tree[right] != null) {
                        nodes.enqueue(right);
                    }

                } else {
                    results.addToRear(null);
                }
            }
        } catch (EmptyCollectionException ex) {
            System.out.println("ERROR");
        }

        return results.iterator();
    }

}
