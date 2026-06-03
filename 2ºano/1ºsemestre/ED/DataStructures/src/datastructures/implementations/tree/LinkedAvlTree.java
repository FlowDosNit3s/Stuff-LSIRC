/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructures.implementations.tree;

import datastructures.implementations.nodes.AvlTreeNode;
import datastructures.ADTs.AvlTreeADT;

public class LinkedAvlTree<T> implements AvlTreeADT<T> {

    private AvlTreeNode<T> root;

    @Override
    public void insert(T element) {
        root = insert(element, root);
    }

    private AvlTreeNode<T> insert(T element, AvlTreeNode<T> node) {

        if (!(element instanceof Comparable)) {
            throw new IllegalArgumentException("The Element Must Be Comparable");
        }

        Comparable<T> comparableElement = (Comparable<T>) element;

        if (node == null) {
            return new AvlTreeNode<>(element);
        }

        if (comparableElement.compareTo(node.getElement()) < 0) {
            node.setLeft(insert(element, node.getLeft()));
        } else if (comparableElement.compareTo(node.getElement()) > 0) {
            node.setRight(insert(element, node.getRight()));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void delete(T data) {
        root = delete(data, root);
    }

    private AvlTreeNode<T> delete(T element, AvlTreeNode<T> node) {
        if (!(element instanceof Comparable)) {
            throw new IllegalArgumentException("The Element Must Be Comparable");
        }

        Comparable<T> comparableElement = (Comparable<T>) element;

        if (node == null) {
            return null;
        }
        if (comparableElement.compareTo(node.getElement()) < 0) {
            node.setLeft(delete(element, node.getLeft()));
        } else if (comparableElement.compareTo(node.getElement()) > 0) {
            node.setRight(delete(element, node.getRight()));
        } else {
            // One Child or Leaf Node (no children)
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            // Two Children
            node.setElement(getMax(node.getLeft()));
            node.setLeft(delete(node.getElement(), node.getLeft()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void traverse() {
        traverseInOrder(root);
    }

    private void traverseInOrder(AvlTreeNode<T> node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.println(node);
            traverseInOrder(node.getRight());
        }
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(AvlTreeNode<T> node) {
        if (node.getRight() != null) {
            return getMax(node.getRight());
        }
        return node.getElement();
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    private T getMin(AvlTreeNode<T> node) {
        if (node.getLeft() != null) {
            return getMin(node.getLeft());
        }
        return node.getElement();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private AvlTreeNode<T> applyRotation(AvlTreeNode<T> node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private AvlTreeNode<T> rotateRight(AvlTreeNode<T> node) {
        AvlTreeNode<T> leftNode = node.getLeft();
        AvlTreeNode<T> centerNode = leftNode.getRight();

        leftNode.setRight(node);
        node.setLeft(centerNode);

        updateHeight(node);
        updateHeight(leftNode);

        return leftNode;
    }

    private AvlTreeNode<T> rotateLeft(AvlTreeNode<T> node) {
        AvlTreeNode<T> rightNode = node.getRight();
        AvlTreeNode<T> centerNode = rightNode.getLeft();

        rightNode.setLeft(node);
        node.setRight(centerNode);

        updateHeight(node);
        updateHeight(rightNode);

        return rightNode;
    }

    private void updateHeight(AvlTreeNode<T> node) {
        int maxHeight = Math.max(height(node.getLeft()), height(node.getRight()));
        node.setHeight(maxHeight + 1);
    }

    private int balance(AvlTreeNode<T> node) {
        return node != null ? height(node.getLeft()) - height(node.getRight()) : 0;
    }

    private int height(AvlTreeNode<T> node) {
        return node != null ? node.getHeight() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb, "", true);
        return sb.toString();
    }

    private void buildString(AvlTreeNode<T> node, StringBuilder sb, String prefix, boolean isLeft) {
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
