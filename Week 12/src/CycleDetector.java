import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CycleDetector {
    int[] edgeTo;
    boolean[] marked;
    boolean hasCycle;
    ArrayList<Integer> cycle;

    public CycleDetector(Graph g) {
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        cycle = new ArrayList<Integer>();

        for (int v = 0; v < g.V(); v++)
            if (!marked[v] && !hasCycle())
                dfs(g, v, v);
    }

    public void dfs(Graph g, int v, int pred) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (hasCycle())
                return;

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w, v);
            } else if (w != pred) { // cycle detected
                hasCycle = true;
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.add(x);
                }
                cycle.add(w);
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Graph g = new Graph(args[0]);
        g.removeEdge(3, 4);
        g.removeEdge(0, 6);
        g.removeEdge(9, 11);

        CycleDetector c = new CycleDetector(g);
        System.out.println("Has Cycle: " + c.hasCycle());
        if (c.hasCycle()) {
            for (int i = c.cycle.size() - 1; i >= 0; i--)
                System.out.print(c.cycle.get(i) + " -> ");
            System.out.print(c.cycle.get(c.cycle.size()-1));
        }
    }
}
