/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package datastructures.implementations.tests;

import datastructures.implementations.tree.LinkedAvlTree;

/**
 *
 * @author carlo
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedAvlTree<Integer> tree = new LinkedAvlTree<>();

        tree.insert(15);
        tree.insert(10);
        tree.insert(12);
        tree.insert(30);
        tree.insert(20);
        tree.insert(35);
        tree.insert(32);
        tree.insert(25);
        tree.insert(50);
        tree.insert(50);
        tree.insert(50);
        tree.insert(50);
        tree.insert(50);
        tree.insert(50);
        tree.insert(50);
        tree.insert(50);
        tree.insert(50);
        tree.insert(50);
        tree.insert(36);
        tree.insert(17);
        tree.insert(5);
        tree.insert(1);
        tree.insert(27);

        tree.delete(10);
        tree.delete(12);
        tree.delete(17);
        tree.delete(32);
        
        System.out.println(tree.toString());

        /*
        System.out.println("---IN---");
        Iterator itI = tree.iteratorInOrder();

        while (itI.hasNext()) {
            System.out.println(itI.next());
        }

        System.out.println("-------");
        System.out.println(tree.toString());
        
        try {
            tree.removeAllOccurrences(50);
        } catch (EmptyCollectionException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("-------");
        System.out.println(tree.toString());
        
         */

//        System.out.println("---POST---");
//        Iterator itP = tree.iteratorPostOrder();
//
//        while (itP.hasNext()) {
//            System.out.println(itP.next());
//        }
//
//        System.out.println("---PRE---");
//        Iterator itPre = tree.iteratorPreOrder();
//
//        while (itPre.hasNext()) {
//            System.out.println(itPre.next());
//        }
    }

}
