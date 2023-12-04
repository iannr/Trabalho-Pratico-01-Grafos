import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class NaiveBridgeIdentification {
    private Graph graph;
    int time = 0;
    static final int NIL = -1;

    // Lista para armazenar as pontes
    private List<int[]> bridges;

    public NaiveBridgeIdentification(Graph graph) {
        this.graph = graph;
        this.bridges = new ArrayList<>();
    }

    public List<int[]> findBridges() {
        // Mark all the vertices as not visited
		boolean visited[] = new boolean[graph.V];
		int disc[] = new int[graph.V];
		int low[] = new int[graph.V];
		int parent[] = new int[graph.V];


		// Initialize parent and visited, and ap(articulation point)
		// arrays
		for (int i = 0; i < graph.V; i++)
		{
			parent[i] = NIL;
			visited[i] = false;
		}

		// Call the recursive helper function to find Bridges
		// in DFS tree rooted with vertex 'i'
		for (int i = 0; i < graph.V; i++)
			if (visited[i] == false)
				bridgeUtil(i, visited, disc, low, parent);

        return bridges;
    }

    // A recursive function that finds and prints bridges
	// using DFS traversal
	// u --> The vertex to be visited next
	// visited[] --> keeps track of visited vertices
	// disc[] --> Stores discovery times of visited vertices
	// parent[] --> Stores parent vertices in DFS tree
	void bridgeUtil(int u, boolean visited[], int disc[], int low[], int parent[]) {
		// Mark the current node as visited
		visited[u] = true;

		// Initialize discovery time and low value
		disc[u] = low[u] = ++time;

		// Go through all vertices adjacent to this
		Iterator<Integer> i = graph.adj.get(u).iterator();
		while (i.hasNext())
		{
			int v = i.next(); // v is current adjacent of u

			// If v is not visited yet, then make it a child
			// of u in DFS tree and recur for it.
			// If v is not visited yet, then recur for it
			if (!visited[v]) {
				parent[v] = u;
				bridgeUtil(v, visited, disc, low, parent);

				// Check if the subtree rooted with v has a
				// connection to one of the ancestors of u
				low[u] = Math.min(low[u], low[v]);

				// If the lowest vertex reachable from subtree
				// under v is below u in DFS tree, then u-v is
				// a bridge
				if (low[v] > disc[u]) 
                    bridges.add(new int[]{u, v});
                
			} else if (v != parent[u]) // Update low value of u for parent function calls.
				low[u] = Math.min(low[u], disc[v]);
		}
	}
}
