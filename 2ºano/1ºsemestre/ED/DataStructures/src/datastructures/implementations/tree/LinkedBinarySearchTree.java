package datastructures.implementations.tree;

import datastructures.ADTs.BinarySearchTreeADT;
import datastructures.exceptions.ElementNotFoundException;
import datastructures.exceptions.EmptyCollectionException;
import datastructures.implementations.nodes.BinaryTreeNode;
import datastructures.implementations.tree.LinkedBinaryTree;

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    /**
     * Creates an empty binary search tree.
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Creates a binary search with the specified element as its root.
     *
     * @param element the element that will be the root of the new binary search
     * tree
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Adds the specified object to the binary search tree in the appropriate
     * position according to its key value. Note that equal elements are added
     * to the right.
     *
     * @param element the element to be added to the binary search tree
     */
    @Override
    public void addElement(T element) {

        if (!(element instanceof Comparable)) {
            throw new IllegalArgumentException("The Element Must Be Comparable");
        }

        BinaryTreeNode<T> temp = new BinaryTreeNode<>(element);
        Comparable<T> comparableElement = (Comparable<T>) element;

        if (isEmpty()) {
            root = temp;
        } else {
            BinaryTreeNode<T> current = root;
            boolean added = false;

            while (!added) {
                if (comparableElement.compareTo(current.getElement()) < 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(temp);
                        added = true;
                    } else {
                        current = current.getLeft();
                    }
                } else {
                    if (current.getRight() == null) {
                        current.setRight(temp);
                        added = true;
                    } else {
                        current = current.getRight();
                    }
                }
            }
        }
        count++;
    }

    /**
     * Removes the first element that matches the specified target element from
     * the binary search tree and returns a reference to it. Throws a
     * ElementNotFoundException if the specified target element is not found in
     * the binary search tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @throws ElementNotFoundException if an element not found exception occurs
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException, EmptyCollectionException {

        if (!(targetElement instanceof Comparable)) {
            throw new IllegalArgumentException("The target element must be comparable");
        }
        Comparable<T> comparableTarget = (Comparable<T>) targetElement;

        T result = null;
        if (!isEmpty()) {
            if (comparableTarget.equals(root.getElement())) {
                result = root.getElement();
                root = replacement(root);
                count--;
            } else {
                BinaryTreeNode<T> current, parent = root;
                boolean found = false;

                if (comparableTarget.compareTo(root.getElement()) < 0) {
                    current = root.getLeft();
                } else {
                    current = root.getRight();
                }

                while (current != null && !found) {
                    if (comparableTarget.equals(current.getElement())) {
                        found = true;
                        count--;
                        result = current.getElement();

                        if (current == parent.getLeft()) {
                            parent.setLeft(replacement(current));
                        } else {
                            parent.setRight(replacement(current));
                        }
                    } else {
                        parent = current;

                        if (comparableTarget.compareTo(current.getElement()) < 0) {
                            current = current.getLeft();
                        } else {
                            current = current.getRight();
                        }
                    }
                } //while

                if (!found) {
                    throw new ElementNotFoundException("binary search tree");

                }
            } // end outer if
            return result;
        } else {
            throw new EmptyCollectionException("The Tree Is Empty!!");
        }
    }

    /**
     * Returns a reference to a node that will replace the one specified for
     * removal. In the case where the removed node has two children, the inorder
     * successor is used as its replacement.
     *
     * @param node the node to be removeed
     * @return a reference to the replacing node
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result;
        if ((node.getLeft() == null) && (node.getRight() == null)) {
            result = null;
        } else if ((node.getLeft() != null) && (node.getRight() == null)) {
            result = node.getLeft();
        } else if ((node.getLeft() == null) && (node.getRight() != null)) {
            result = node.getRight();
        } else {
            BinaryTreeNode<T> current = node.getRight();
            BinaryTreeNode<T> parent = node;
            while (current.getLeft() != null) {
                parent = current;
                current = current.getLeft();
            }
            if (node.getRight() == current) {
                current.setLeft(node.getLeft());
            } else {
                parent.setLeft(current.getRight());
                current.setRight(node.getRight());
                current.setLeft(node.getLeft());
            }
            result = current;
        }
        return result;
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
            throw new EmptyCollectionException("The tree is empty.");
        }
        BinaryTreeNode<T> current = root;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getElement();
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
            throw new EmptyCollectionException("The tree is empty.");
        }

        BinaryTreeNode<T> current = root;

        while (current.getRight() != null) {
            current = current.getRight();
        }

        return current.getElement();
    }
}
