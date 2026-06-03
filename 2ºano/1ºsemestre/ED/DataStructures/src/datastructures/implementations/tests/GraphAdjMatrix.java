package datastructures.implementations.tests;

import datastructures.exceptions.EmptyCollectionException;
import datastructures.implementations.lists.ArrayUnorderedList;
import datastructures.implementations.queue.LinkedQueue;
import datastructures.implementations.stack.LinkedStack;
import java.util.Iterator;

public class GraphAdjMatrix<T> implements GraphADT<T> {

    private static int DEFAULT_CAPACITY = 10;
    private static int EXPANSION_FATORIAL = 5;

    private T[] vertices;
    private int[][] adjMatrix;
    private int numVertices;

    public GraphAdjMatrix() {
        this.vertices = (T[]) new Object[DEFAULT_CAPACITY];
        this.adjMatrix = new int[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.numVertices = 0;
    }

    public GraphAdjMatrix(int initialCapacity) {
        this.vertices = (T[]) new Object[initialCapacity];
        this.adjMatrix = new int[initialCapacity][initialCapacity];
        this.numVertices = 0;
    }

    @Override
    public void addVertex(T vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("The Element Cant Be Null");
        }

        if (this.numVertices == this.vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = 0;
            adjMatrix[i][numVertices] = 0;
        }

        numVertices++;;
    }

    private void expandCapacity() {
        T[] newVertices = (T[]) new Object[numVertices * EXPANSION_FATORIAL];
        System.arraycopy(vertices, 0, newVertices, 0, numVertices);
        this.vertices = newVertices;

        int[][] newAdjMatrix = new int[numVertices * EXPANSION_FATORIAL][numVertices * EXPANSION_FATORIAL];
        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(adjMatrix[i], 0, newAdjMatrix[i], 0, numVertices);
        }
        this.adjMatrix = newAdjMatrix;
    }

    @Override
    public void removeVertex(T vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("The Element Cant Be Null");
        }
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                removeVertex(i);
            }
        }
    }

    protected void removeVertex(int vertexIndex) {
        for (int i = vertexIndex; i < numVertices - 1; i++) {
            vertices[i] = vertices[i + 1];
        }
        vertices[numVertices - 1] = null;

        for (int i = vertexIndex; i < numVertices; i++) {
            for (int j = 0; j < numVertices + 1; j++) {
                adjMatrix[i][j] = adjMatrix[i + 1][j];
            }
        }
        for (int j = vertexIndex; j < numVertices; j++) {
            for (int i = 0; i < numVertices; i++) {
                adjMatrix[i][j] = adjMatrix[i][j + 1];
            }
        }

        for (int i = 0; i < numVertices + 1; i++) {
            adjMatrix[numVertices][i] = 0;
            adjMatrix[i][numVertices] = 0;
        }
    }

    private int getIndex(T vertex) {
        int index = -1;
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    private void addEdge(int indexVertex1, int indexVertex2) {
        adjMatrix[indexVertex1][indexVertex2] = 1;
        adjMatrix[indexVertex2][indexVertex1] = 1;
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    private void removeEdge(int indexVertex1, int indexVertex2) {
        adjMatrix[indexVertex1][indexVertex2] = 0;
        adjMatrix[indexVertex2][indexVertex1] = 0;
    }

    private boolean indexIsValid(int index) {
        return index < numVertices && index >= 0;
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * Returns an iterator that performs a breadth first search traversal
     * starting at the given index.
     *
     * @param startIndex the index to begin the search from
     * @return an iterator that performs a breadth first traversal
     */
    private Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        traversalQueue.enqueue(new Integer(startIndex));
        visited[startIndex] = true;

        try {
            while (!traversalQueue.isEmpty()) {
                x = traversalQueue.dequeue();
                resultList.addToRear(vertices[x.intValue()]);
                /**
                 * Find all vertices adjacent to x that have not been visited
                 * and queue them up
                 */
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[x.intValue()][i] == 1 && !visited[i]) {
                        traversalQueue.enqueue(new Integer(i));
                        visited[i] = true;
                    }
                }
            }
        } catch (EmptyCollectionException ex) {
            System.out.println(ex.getMessage());
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * Returns an iterator that performs a depth first search traversal starting
     * at the given index.
     *
     * @param startIndex the index to begin the search traversal from
     * @return an iterator that performs a depth first traversal
     */
    private Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        traversalStack.push(new Integer(startIndex));
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        try {
            while (!traversalStack.isEmpty()) {
                x = traversalStack.peek();
                found = false;
                /**
                 * Find a vertex adjacent to x that has not been visited and
                 * push it on the stack
                 */
                for (int i = 0; (i < numVertices) && !found; i++) {
                    if (adjMatrix[x.intValue()][i] == 1 && !visited[i]) {
                        traversalStack.push(new Integer(i));
                        resultList.addToRear(vertices[i]);
                        visited[i] = true;
                        found = true;
                    }
                }
                if (!found && !traversalStack.isEmpty()) {
                    traversalStack.pop();
                }
            }
        } catch (EmptyCollectionException ex) {
            System.out.println(ex.getMessage());
        }

        return resultList.iterator();
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            throw new IllegalArgumentException("Invalid Vertices");
        }

        if (startIndex == targetIndex) {
            ArrayUnorderedList<T> result = new ArrayUnorderedList<>();
            result.addToRear(startVertex);
            return result.iterator();
        }

        LinkedQueue<Integer> queue = new LinkedQueue<>();
        boolean[] visited = new boolean[numVertices];
        int[] path = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            path[i] = -1;
        }

        queue.enqueue(startIndex);
        visited[startIndex] = true;

        boolean found = false;

        try {
            while (!queue.isEmpty() && !found) {
                int current = queue.dequeue();

                // Explore neighbors
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[current][i] == 1 && !visited[i]) {
                        queue.enqueue(i);
                        visited[i] = true;
                        path[i] = current;

                        // if the target is found
                        if (i == targetIndex) {
                            found = true;
                            break;
                        }
                    }
                }
            }
        } catch (EmptyCollectionException e) {
            System.out.println(e.getMessage());
        }

        // If no path exists
        if (!found) {
            return new ArrayUnorderedList<T>().iterator(); // Empty iterator
        }

        // Reconstruct the path
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        int current = targetIndex;
        while (current != -1) {
            resultList.addToFront(vertices[current]);
            current = path[current];
        }

        return resultList.iterator();
    }

    @Override
    public boolean isEmpty() {
        return this.numVertices == 0;
    }

    @Override
    public boolean isConnected() {
        if (isEmpty()) {
            return false;
        }

        Iterator it = iteratorBFS(0);
        int counter = 0;

        while (it.hasNext()) {
            it.next();
            counter++;
        }

        return counter == numVertices;
    }

    @Override
    public int size() {
        return this.numVertices;
    }

    @Override
    public String toString() {
        if (numVertices == 0) {
            return "Graph is empty";
        }

        String result = "";

        result += "Adjacency Matrix\n";
        result += "----------------\n";
        result += "index\t";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i;
            if (i < 10) {
                result += " ";
            }
        }
        result += "\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";

            for (int j = 0; j < numVertices; j++) {
                result += "" + adjMatrix[i][j] + " ";

            }
            result += "\n";
        }

        result += "\n\nVertex Values";
        result += "\n-------------\n";
        result += "index\tvalue\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }
        result += "\n";
        return result;
    }
}
