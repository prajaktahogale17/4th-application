import java.util.*;
public class NetworkDesign {
    static class Edge {
        int src, dest, weight;
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    static class Graph {
        int vertices;
        int[][] adjMatrix;
        Graph(int vertices) {
            this.vertices = vertices;
            adjMatrix = new int[vertices][vertices];
        }
        void addEdge(int src, int dest, int weight) {
            adjMatrix[src][dest] = weight;
            adjMatrix[dest][src] = weight;
        }
        void primMST() {
            int[] key = new int[vertices];
            int[] parent = new int[vertices];
            boolean[] inMST = new boolean[vertices];
            Arrays.fill(key, Integer.MAX_VALUE);
            key[0] = 0;
            parent[0] = -1;
            for (int count = 0; count < vertices - 1; count++) {
                int u = minKey(key, inMST);
                inMST[u] = true;
                for (int v = 0; v < vertices; v++) {
                    if (adjMatrix[u][v] != 0 && !inMST[v] && adjMatrix[u][v] < key[v]) {
                        parent[v] = u;
                        key[v] = adjMatrix[u][v];
                    }
                }
            }
            printMST(parent);
        }
        int minKey(int[] key, boolean[] inMST) {
            int min = Integer.MAX_VALUE, minIndex = -1;
            for (int v = 0; v < vertices; v++) {
                if (!inMST[v] && key[v] < min) {
                    min = key[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }
        void printMST(int[] parent) {
            int totalCost = 0;
            System.out.println("\nNetwork Connections (Minimum Spanning Tree):");
            System.out.println("Edge\tCost");
            for (int i = 1; i < vertices; i++) {
                System.out.println(parent[i] + " - " + i + "\t" + adjMatrix[i][parent[i]]);
                totalCost += adjMatrix[i][parent[i]];
            }
            System.out.println("Total Minimum Network Cost: " + totalCost);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of network nodes: ");
        int n = sc.nextInt();
        Graph graph = new Graph(n);
        System.out.println("Enter the cost adjacency matrix (0 if no connection):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph.adjMatrix[i][j] = sc.nextInt();
            }
        }
        graph.primMST();
    }
}
