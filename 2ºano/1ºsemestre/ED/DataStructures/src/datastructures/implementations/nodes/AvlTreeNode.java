/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructures.implementations.nodes;

public class AvlTreeNode<T> {

    private T element;
    private int height;
    private AvlTreeNode<T> right, left;

    public AvlTreeNode() {
        this.element = null;
        this.right = this.left = null;
        this.height = 1;
    }

    public AvlTreeNode(T element) {
        this.element = element;
        this.right = this.left = null;
        this.height = 1;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AvlTreeNode<T> getRight() {
        return right;
    }

    public void setRight(AvlTreeNode right) {
        this.right = right;
    }

    public AvlTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(AvlTreeNode left) {
        this.left = left;
    }
}
