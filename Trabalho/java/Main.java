import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

enum EulerTourTypeEnum {
    TARJAN,
    NAIVE
}

public class Main {
    public static void main(String[] args) {
        // -------------------------------------------------------- CÓDIGO COMENTADO PARA TESTE -----------------------------------------
        // int numVertices = 5;

        // Graph g1 = new Graph(numVertices);
        // g1.addEdge(0, 1);
        // g1.addEdge(1, 2);
        // g1.addEdge(2, 0);
        // g1.addEdge(1, 3);
        // g1.addEdge(3, 4);

        // System.out.println("Caminho Euleriano Por Tarjan: ");
        // FleuryEulerianPath fleuryEulerianPathG1 = new FleuryEulerianPath(g1);
        // fleuryEulerianPathG1.printEulerTour(EulerTourTypeEnum.TARJAN);

        // Graph g2 = new Graph(numVertices);
        // g2.addEdge(0, 1);
        // g2.addEdge(0, 2);
        // g2.addEdge(0, 3);
        // g2.addEdge(1, 2);
        // g2.addEdge(3, 4);

        // System.out.println("\n\nCaminho Euleriano Por Naive: ");
        // FleuryEulerianPath fleuryEulerianPathG2 = new FleuryEulerianPath(g2);
        // fleuryEulerianPathG2.printEulerTour(EulerTourTypeEnum.NAIVE);

        // com 100 vertices
        Graph randomGraph100_1 = generateRandomGraph(100);
        randomGraph100_1.test();
        FleuryEulerianPath fleuryEulerianPath100_1 = new FleuryEulerianPath(randomGraph100_1);
        fleuryEulerianPath100_1.printEulerTour(EulerTourTypeEnum.TARJAN);
        System.out.println(
                "Tempo de execução do método Tarjan p/ o grafo com 100 vértices em milissegundos: " + fleuryEulerianPath100_1.finalTimeTarjan + "\n\n");

        Graph randomGraph100_2 = generateRandomGraph(100);
        randomGraph100_2.test();
        FleuryEulerianPath fleuryEulerianPath100_2 = new FleuryEulerianPath(randomGraph100_2);
        fleuryEulerianPath100_2.printEulerTour(EulerTourTypeEnum.NAIVE);
        System.out.println(
                "Tempo de execução do método Naive p/ o grafo com 100 vértices em milissegundos: " + fleuryEulerianPath100_2.finalTimeNaive + "\n\n");

        // com 1.000 vertices
        Graph randomGraph1000_1 = generateRandomGraph(1000);
        randomGraph1000_1.test();
        FleuryEulerianPath fleuryEulerianPath1000_1 = new FleuryEulerianPath(randomGraph1000_1);
        fleuryEulerianPath1000_1.printEulerTour(EulerTourTypeEnum.TARJAN);
        System.out.println(
                "Tempo de execução do método Tarjan p/ o grafo com 1.000 vértices em milissegundos: " + fleuryEulerianPath1000_1.finalTimeTarjan + "\n\n");

        Graph randomGraph1000_2 = generateRandomGraph(1000);
        randomGraph1000_2.test();
        FleuryEulerianPath fleuryEulerianPath1000_2 = new FleuryEulerianPath(randomGraph1000_2);
        fleuryEulerianPath1000_2.printEulerTour(EulerTourTypeEnum.NAIVE);
        System.out.println(
                "Tempo de execução do método Naive p/ o grafo com 1.000 vértices em milissegundos: " + fleuryEulerianPath1000_2.finalTimeNaive + "\n\n");

        // com 10.000 vertices
        Graph randomGraph10000_1 = generateRandomGraph(10000);
        randomGraph10000_1.test();
        FleuryEulerianPath fleuryEulerianPath10000_1 = new FleuryEulerianPath(randomGraph10000_1);
        fleuryEulerianPath10000_1.printEulerTour(EulerTourTypeEnum.TARJAN);
        System.out.println(
                "Tempo de execução do método Tarjan p/ o grafo com 10.000 vértices em milissegundos: " + fleuryEulerianPath10000_1.finalTimeTarjan + "\n\n");

        Graph randomGraph10000_2 = generateRandomGraph(10000);
        randomGraph10000_2.test();
        FleuryEulerianPath fleuryEulerianPath10000_2 = new FleuryEulerianPath(randomGraph10000_2);
        fleuryEulerianPath10000_2.printEulerTour(EulerTourTypeEnum.NAIVE);
        System.out.println(
                "Tempo de execução do método Naive p/ o grafo com 10.000 vértices em milissegundos: " + fleuryEulerianPath10000_2.finalTimeNaive  + "\n\n");

        // com 100.000 vertices
        Graph randomGraph100000_1 = generateRandomGraph(100000);
        randomGraph100000_1.test();
        FleuryEulerianPath fleuryEulerianPath100000_1 = new FleuryEulerianPath(randomGraph100000_1);
        fleuryEulerianPath100000_1.printEulerTour(EulerTourTypeEnum.TARJAN);
        System.out.println(
                "Tempo de execução do método Tarjan p/ o grafo com 100.000 vértices em milissegundos: " + fleuryEulerianPath100000_1.finalTimeTarjan + "\n\n");

        Graph randomGraph100000_2 = generateRandomGraph(100000);
        randomGraph100000_2.test();
        FleuryEulerianPath fleuryEulerianPath100000_2 = new FleuryEulerianPath(randomGraph100000_2);
        fleuryEulerianPath100000_2.printEulerTour(EulerTourTypeEnum.NAIVE);
        System.out.println(
                "Tempo de execução do método Naive p/ o grafo com 100.000 vértices em milissegundos: " + fleuryEulerianPath100000_2.finalTimeNaive + "\n\n");
    }

    static Graph generateRandomGraph(int numVertices) {
        Random random = new Random();
        Graph graph = new Graph(numVertices);

        // Conjunto para rastrear arestas já adicionadas
        Set<String> addedEdges = new HashSet<>();

        // Lista de arestas da árvore geradora mínima
        List<int[]> mstEdges = new ArrayList<>();

        // Adicionar arestas aleatórias na árvore geradora mínima (usando algoritmo de Prim)
        boolean[] visited = new boolean[numVertices];
        visited[0] = true; // Iniciar a partir do vértice 0
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge[2]));

        for (int v = 1; v < numVertices; v++) {
            pq.offer(new int[] { 0, v, random.nextInt(100) });
        }

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int u = edge[0];
            int v = edge[1];

            if (!visited[v]) {
                visited[v] = true;
                mstEdges.add(new int[] { u, v });
                for (int w = 0; w < numVertices; w++) {
                    if (!visited[w]) {
                        pq.offer(new int[] { v, w, random.nextInt(100) });
                    }
                }
            }
        }

        // Adicionar arestas da árvore geradora mínima ao grafo
        for (int[] edge : mstEdges) {
            int u = edge[0];
            int v = edge[1];
            graph.addEdge(u, v);
            addedEdges.add(u + "-" + v);
            addedEdges.add(v + "-" + u);
        }

        // Adicionar arestas adicionais aleatórias
        for (int u = 0; u < numVertices; u++) {
            for (int v = u + 1; v < numVertices; v++) {
                String edgeKey = u + "-" + v;
                if (!addedEdges.contains(edgeKey) && random.nextDouble() < 0.5) {
                    graph.addEdge(u, v);
                    addedEdges.add(edgeKey);
                    addedEdges.add(v + "-" + u);
                }
            }
        }

        return graph;
    }
}