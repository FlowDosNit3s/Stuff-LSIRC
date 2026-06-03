/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructures.implementations.tree;

import datastructures.ADTs.BinaryTreeADT;
import datastructures.exceptions.ElementNotFoundException;
import datastructures.exceptions.EmptyCollectionException;
import datastructures.implementations.lists.ArrayUnorderedList;
import datastructures.implementations.nodes.BinaryTreeNode;
import datastructures.implementations.queue.LinkedQueue;
import java.util.Iterator;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected BinaryTreeNode<T> root;
    protected int count;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element that will become the root of the new binary
     * tree
     */
    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode<>(element);
    }

    /**
     * Returns a reference to the root element
     *
     * @return a reference to the root
     */
    @Override
    public T getRoot() {
        return root.getElement();
    }

    /**
     * Returns true if this binary tree is empty and false otherwise.
     *
     * @return true if this binary tree is empty
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Returns the number of elements in this binary tree.
     *
     * @return the integer number of elements in this tree
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns true if the binary tree contains an element that matches the
     * specified element and false otherwise.
     *
     * @param targetElement the element being sought in the tree
     * @return true if the tree contains the target element
     */
    @Override
    public boolean contains(T targetElement) {
        try {
            find(targetElement);
        } catch (ElementNotFoundException ex) {
            return false;
        }

        return true;
    }

    /**
     * Returns a reference to the specified target element if it is found in
     * this binary tree. Throws a NoSuchElementException if the specified target
     * element is not found in the binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @return a reference to the specified target
     * @throws ElementNotFoundException if an element not found exception occurs
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null) {
            throw new ElementNotFoundException("binary tree");
        }

        return (current.getElement());
    }

    /**
     * Returns a reference to the specified target element if it is found in
     * this binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @param next the element to begin searching from
     */
    private BinaryTreeNode<T> findAgain(T targetElement,
            BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }

        if (next.getElement().equals(targetElement)) {
            return next;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());

        if (temp == null) {
            temp = findAgain(targetElement, next.getRight());
        }

        return temp;
    }

    /**
     * Performs an inorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with the root.
     *
     * @return an in order iterator over this binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        inorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inorder(node.getRight(), tempList);
        }
    }

    /**
     * Performs an preorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with the root.
     *
     * @return an pre order iterator over this binary tree
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        preorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preorder(node.getLeft(), tempList);
            preorder(node.getRight(), tempList);
        }
    }

    /**
     * Performs an postorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with the root.
     *
     * @return an post order iterator over this binary tree
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        postorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            postorder(node.getLeft(), tempList);
            postorder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    /**
     * Performs a levelorder traversal on the binary tree, using a queue.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {

        LinkedQueue<BinaryTreeNode<T>> nodes = new LinkedQueue<>();
        ArrayUnorderedList<T> results = new ArrayUnorderedList<>();

        nodes.enqueue(root);

        BinaryTreeNode<T> current;
        try {
            while (!nodes.isEmpty()) {
                current = nodes.dequeue();

                if (current != null) {
                    results.addToRear(current.getElement());

                    BinaryTreeNode<T> left = current.getLeft();
                    BinaryTreeNode<T> right = current.getRight();

                    if (left != null) {
                        nodes.enqueue(left);
                    }

                    if (right != null) {
                        nodes.enqueue(right);
                    }

                } else {
                    results.addToRear(null);
                }

            }

        } catch (EmptyCollectionException ex) {
            System.out.println("Empty!!");
        }

        return results.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb, "", true);
        return sb.toString();
    }

    private void buildString(BinaryTreeNode<T> node, StringBuilder sb, String prefix, boolean isLeft) {
        if (node != null) {
            sb.append(prefix)
                    .append(isLeft ? "├── " : "└── ")
                    .append(node.getElement())
                    .append("\n");

            // Prefixo para o próximo nível
            String newPrefix = prefix + (isLeft ? "│   " : "    ");

            buildString(node.getLeft(), sb, newPrefix, true);   // Filhos à esquerda
            buildString(node.getRight(), sb, newPrefix, false); // Filhos à direita
        }
    }

}
