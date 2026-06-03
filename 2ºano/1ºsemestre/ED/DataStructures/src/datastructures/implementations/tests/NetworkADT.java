/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datastructures.implementations.tests;

/**
 * NetworkADT defines the interface to a network.
 */
public interface NetworkADT<T> extends GraphADT<T> {

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     *
     * @param vertex2 the second vertex
     * @param weight the weight
     */
    void addEdge(T vertex1, T vertex2, double weight);

    /**
     * Returns the weight of the shortest path in this network.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the shortest path in this network
     */
    double shortestPathWeight(T vertex1, T vertex2);
}
