import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {

        //matrice 5x5
        int[][] graph = {
                {0, 4, 0, 0, 0},
                {4, 0, 8, 0, 0},
                {0, 8, 0, 7, 0},
                {0, 0, 7, 0, 9},
                {0, 0, 0, 9, 0}
        };
        //start è il mio punto di inizio
        int start = 0;
        //dist è ciò con cui visualizzerò la distanza
        int[] dist = dijkstra(graph, start);
        System.out.println("Distances from start node " + start + ": " + Arrays.toString(dist));
    }

    public static int[] dijkstra(int[][] graph, int start) {

        //n è il numero di file nel grafo
        int n = graph.length;
        //array dist che tiene la lunghezza di n
        int[] dist = new int[n];
        //array booleano che tiene la lunghezza di n
        boolean[] visited = new boolean[n];
        //riempio l'array dist con il valore max di interi
        Arrays.fill(dist, Integer.MAX_VALUE);
        //inizializzo start al punto 0
        dist[start] = 0;
        //inizializzo i e pongo n-1 perché parto dal nodo iniziale
        for (int i = 0; i < n - 1; i++) {

            //con il metodo findmindist metto come 'già percorso' il nodo
            int u = findMinDist(dist, visited);
            visited[u] = true;

            //'v' = nodi non visitati e 'u' = prossimo nodo
            for (int v = 0; v < n; v++) {

                //se 'v' non è stato visitato, se c'è un collegamento tra 'u' e 'v', inoltre la distanza tra
                //start e 'u' non è uguale a Integer.MAX_VALUE e il peso di 'u' e 'v' è minore della distanza attuale
                //start e 'v' allora aggiorna la distanza tra start e 'v'
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        return dist;
    }

    //metodo che cerca il nodo con cui non è stato 'visitato' e calcola il cammino più corto dal nodo start
    private static int findMinDist(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}