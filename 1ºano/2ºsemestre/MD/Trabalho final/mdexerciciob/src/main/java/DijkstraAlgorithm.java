
import java.util.ArrayList;
import java.util.List;


public class DijkstraAlgorithm {

    public int V;
    public int[][] graph;

    public DijkstraAlgorithm(int vertices) {
        V = vertices;
        graph = new int[V][V];
    }

    private int minimumDistance(int[] distances, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!visited[v] && distances[v] <= minDistance) {
                minDistance = distances[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private void printPath(int[] distances, int[] parent, int source, int destination) {
        List<Integer> path = new ArrayList<>();
        int crawl = destination;
        path.add(crawl);

        while (parent[crawl] != -1) {
            path.add(parent[crawl]);
            crawl = parent[crawl];
        }

        System.out.print("Caminho mínimo entre " + (source + 1) + " e " + (destination + 1) + ": ");
        for (int i = path.size() - 1; i > 0; i--) {
            System.out.print((path.get(i) + 1) + " -> ");
        }
        System.out.print((path.get(0) + 1));

        System.out.println("\nCusto total: " + distances[destination]);
    }

    public void dijkstra(int source, int destination) {
        int[] distances = new int[V];
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
            parent[i] = -1;
        }
        distances[source] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = minimumDistance(distances, visited);
            visited[u] = true;
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && distances[u] != Integer.MAX_VALUE && distances[u] + graph[u][v] < distances[v]) {
                    distances[v] = distances[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }
        printPath(distances, parent, source, destination);
    }// Programa principal para testar o algoritmo
}
