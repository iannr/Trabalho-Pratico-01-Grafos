import java.util.*;

public class Graph {
    public int V; // Número de vértices
    public List<List<Integer>> adj; // Lista de adjacência

    Graph(int v) {
        V = v;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    Graph generateRandomGraph(int numVertices) {
        Random random = new Random();
        Graph graph = new Graph(numVertices);

        // Adicionar arestas aleatórias
        for (int u = 0; u < numVertices; u++) {
            for (int v = u + 1; v < numVertices; v++) {
                // Adicionar uma aresta com probabilidade de 0.5
                if (random.nextDouble() < 0.5) {
                    graph.addEdge(u, v);
                }
            }
        }

        return graph;
    }

    List<List<Integer>> getAdj() {
        return adj;
    }

    void addEdge(Integer u, Integer v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    void removeEdge(Integer u, Integer v) {
        adj.get(u).remove(v);
        adj.get(v).remove(u);
    }

    void DFSUtil(int v, boolean visited[]) {
        // Marca o nó atual como visitado
        visited[v] = true;

        // Recorre para todos os vértices adjacentes a este vértice
        Iterator<Integer> i = adj.get(v).listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // Método para verificar se todos os vértices de grau diferente de zero são
    // conectado
    boolean isConnected() {
        // Marca todos os vértices como não visitados
        boolean visited[] = new boolean[V];
        int i;
        for (i = 0; i < V; i++)
            visited[i] = false;

        // Encontre um vértice com grau diferente de zero
        for (i = 0; i < V; i++)
            if (adj.get(i).size() != 0)
                break;

        // Se não houver arestas no gráfico, retorne verdadeiro
        if (i == V)
            return true;

        // Inicia a travessia DFS a partir de um vértice com grau diferente de zero
        DFSUtil(i, visited);

        // Verifica se todos os vértices de graus diferentes de zero foram visitados
        for (i = 0; i < V; i++)
            if (visited[i] == false && adj.get(i).size() > 0)
                return false;

        return true;
    }

    /*
     * A função retorna um dos seguintes valores
     * 0 -> Se o gráfico não for Euleriano
     * 1 -> Se o gráfico tiver um caminho Euleriano (Semi-Euleriano)
     * 2 -> Se o gráfico possui um Circuito Euler (Euleriano)
     */
    int categorizeEulerian() {
        // Verifica se todos os vértices de grau diferente de zero estão conectados
        if (isConnected() == false)
            return 0;

        // Conta vértices com grau ímpar
        int odd = 0;
        for (int i = 0; i < V; i++) {
            if (adj.get(i).size() % 2 != 0)
                odd++;
        }

        // Se a contagem for maior que 2, então o gráfico não é euleriano
        if (odd > 2)
            return 0;

        // Se a contagem ímpar for 2, então semi-euleriano.
        // Se a contagem ímpar for 0, então euleriano
        // Observe que a contagem ímpar nunca pode ser 1 para gráfico não direcionado
        return (odd == 2) ? 1 : 2;
    }

    // Função para executar casos de teste
    void test() {
        int res = categorizeEulerian();
        if (res == 0)
            System.out.println("O grafo é não euleriano");
        else if (res == 1)
            System.out.println("O grafo é semi-euleriano");
        else
            System.out.println("O grafo é euleriano");
    }

}
