import java.util.Arrays;
import java.util.Comparator;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    int V, E;
    Edge[] edges;

    Graph(int V, int E) {
        this.V = V;
        this.E = E;
        edges = new Edge[E];
    }
}

class KruskalAlgorithm {
    // Disjoint Set data structure
    int[] parent;

    KruskalAlgorithm(int vertices) {
        parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }
    }

    int find(int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent[vertex]);
        }
        return parent[vertex];
    }

    void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        parent[xRoot] = yRoot;
    }

    void kruskalMST(Graph graph) {
        Edge[] result = new Edge[graph.V - 1];
        Arrays.sort(graph.edges, Comparator.comparingInt(o -> o.weight));
        int i = 0;
        int e = 0;

        while (e < graph.V - 1) {
            Edge nextEdge = graph.edges[i++];
            int x = find(nextEdge.src);
            int y = find(nextEdge.dest);

            if (x != y) {
                result[e++] = nextEdge;
                union(x, y);
            }
        }

        // Print the minimum spanning tree
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (i = 0; i < e; i++) {
            System.out.println(result[i].src + " - " + result[i].dest + " : " + result[i].weight);
        }
    }

    public static void main(String[] args) {
        int V = 4; // Number of vertices
        int E = 5; // Number of edges
        Graph graph = new Graph(V, E);

        graph.edges[0] = new Edge(0, 1, 10);
        graph.edges[1] = new Edge(0, 2, 6);
        graph.edges[2] = new Edge(0, 3, 5);
        graph.edges[3] = new Edge(1, 3, 15);
        graph.edges[4] = new Edge(2, 3, 4);

        KruskalAlgorithm kruskal = new KruskalAlgorithm(V);
        kruskal.kruskalMST(graph);
    }
}
