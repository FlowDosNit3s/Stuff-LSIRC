/**
 *
 * @author hugol
 */
public class b {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int vertices = 12;
        int[][] graph = {{0, 0,  0, 0,  0,  0, 5,  0,  0,  0,  0,  19},
                         {0, 0,  0, 0,  0,  0, 28, 14, 0,  0,  0,  0 },
                         {0, 0,  0, 0,  0,  0, 0,  0,  11, 27, 0,  0 },
                         {0, 0,  0, 0,  0,  0, 0,  0,  0,  18, 0,  0 },
                         {0, 0,  0, 0,  0,  0, 0,  0,  0,  0,  20, 23},
                         {0, 0,  0, 0,  0,  0, 0,  0,  0,  0,  9,  7},
                         {5, 28, 0, 0,  0,  0, 0,  0,  0,  0,  0,  0},
                         {0, 14, 0, 0,  0,  0, 0,  0,  7,  0,  0,  15},
                         {0, 0, 11, 0,  0,  0, 0,  7,  0,  0,  0,  0},
                         {0, 0, 27, 18, 0,  0, 0,  0,  0,  0,  6,  0},
                         {0, 0, 0,  0,  20, 9, 0,  0,  0,  6,  0,  0},
                         {19,0, 0,  0,  23, 7, 0,  15,  0,  0,  0,  0},};
        int source = 6;
        int destination = 7;
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(vertices);
        dijkstra.graph = graph;
        dijkstra.dijkstra(source - 1, destination - 1);
    }
    
}
