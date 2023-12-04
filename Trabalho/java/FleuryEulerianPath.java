import java.util.ArrayList;
import java.util.List;

public class FleuryEulerianPath {
    private Graph graph;
    public long finalTimeTarjan;
    public long finalTimeNaive;

    FleuryEulerianPath(Graph graph) {
        this.graph = graph;
        this.finalTimeTarjan = 0;
        this.finalTimeNaive = 0;
    }

    void printEulerTour(EulerTourTypeEnum type) {
        if (graph.categorizeEulerian() == 0) {
            System.out.println("Não há um caminho euleriano neste grafo");
            return;
        }

        // Encontrar um vértice com grau ímpar
        Integer u = 0;
        for (int i = 0; i < graph.V; i++) {
            if (graph.adj.get(i).size() % 2 == 1) {
                u = i;
                break;
            }
        }

        printEulerUtil(u, type);
        System.out.println();
    }

    // Imprime o tour de Euler começando no vértice u
    private void printEulerUtil(Integer u, EulerTourTypeEnum type) {
        // Recorre para todos os vértices adjacentes a este vértice
        for (int i = 0; i < graph.adj.get(u).size(); i++) {
            Integer v = graph.adj.get(u).get(i);
            // Se a aresta u-v for uma próxima aresta válida

            if (isValidNextEdge(u, v, type)) {
                System.out.print(u + "-" + v + " ");

                // Esta aresta é usada então remova-a agora
                graph.removeEdge(u, v);
                printEulerUtil(v, type);
            }

        }
    }

    boolean isValidNextEdge(Integer u, Integer v, EulerTourTypeEnum type) {
        if (graph.adj.get(u).size() == 1) {
            return true;
        }

        List<int[]> bridgesList = new ArrayList<>();

        if (type == EulerTourTypeEnum.TARJAN) {
            TarjanBridgeIdentification tarjanBridgeIdentification = new TarjanBridgeIdentification(graph);

            long startTimeTarjan = System.currentTimeMillis();
            bridgesList = tarjanBridgeIdentification.findBridges();
            long endTimeTarjan = System.currentTimeMillis();

            this.finalTimeTarjan = endTimeTarjan - startTimeTarjan;
        } else {
            NaiveBridgeIdentification naiveBridgeIdentification = new NaiveBridgeIdentification(graph);

            long startTimeNaive = System.currentTimeMillis();
            bridgesList = naiveBridgeIdentification.findBridges();
            long endTimeNaive = System.currentTimeMillis();

            this.finalTimeNaive = endTimeNaive - startTimeNaive;
        }

        for (int[] bridge : bridgesList) {
            int aux[] = { u, v };

            return (bridge == aux) ? false : true;
        }

        return false;
    }
}
