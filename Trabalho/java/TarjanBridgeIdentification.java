import java.util.*;

public class TarjanBridgeIdentification {
    private Graph graph;
    private int[] disc; // Número de descoberta para cada vértice
    private int[] low; // Menor número de descoberta alcançável a partir do vértice
    private boolean[] visited;
    private List<int[]> bridges; // Lista para armazenar as pontes identificadas

    public TarjanBridgeIdentification(Graph graph) {
        this.graph = graph;
        int numVertices = graph.getAdj().size();
        disc = new int[numVertices];
        low = new int[numVertices];
        visited = new boolean[numVertices];
        bridges = new ArrayList<>();
    }

    public List<int[]> findBridges() {
        for (int i = 0; i < graph.getAdj().size(); i++) {
            if (!visited[i]) {
                dfs(i, -1);
            }
        }
        return bridges;
    }

    private void dfs(int u, int parent) {
        visited[u] = true;
        disc[u] = low[u];

        for (int v : graph.getAdj().get(u)) {
            if (!visited[v]) {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    bridges.add(new int[] { u, v });
                }
            } else if (v != parent) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}
