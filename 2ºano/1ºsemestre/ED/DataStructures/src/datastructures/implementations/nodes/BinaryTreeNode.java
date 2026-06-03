/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructures.implementations.nodes;

/**
 * BinaryTreeNode represents a node in a binary tree with a left and right
 * child.
 */
public class BinaryTreeNode<T> {

    private T element;
    private BinaryTreeNode<T> left, right;

    /**
     * Creates a new tree node with the specified data.
     *
     * @param obj the element that will become a part of the new tree node
     */
    public BinaryTreeNode(T obj) {
        element = obj;
        left = null;
        right = null;

    }

    public void setElement(T element) {
        this.element = element;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public T getElement() {
        return element;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    /**
     * Returns the number of non-null children of this node. This method may be
     * able to be written more efficiently.
     *
     * @return the integer number of non-null children of this node
     */
    public int numChildren() {
        int children = 0;
        if (left != null) {
            children = 1 + left.numChildren();
        }
        if (right != null) {
            children = children + 1 + right.numChildren();
        }
        return children;
    }
}
