import java.util.ArrayList;
import java.util.LinkedList;

public class Ford_Fulkerson  {
    static final int INF = Integer.MAX_VALUE;
    static final int V = 6;
    static int capacity[][] =new int[][] {
            {0, 12, 0, 13, 0, 0},
            {0, 0, 10, 0, 0, 0},
            {0, 0, 0, 13, 3, 15},
            {0, 0, 7, 0, 15, 0},
            {0, 0, 0, 0, 0, 17},
            {0, 0, 0, 0, 0, 0}
    };
    static int parent[] = new int[V];
    static boolean visited[] = new boolean[V];

    public static void main (String[] args)    {
        System.out.println("\n유량 네트워크의 전체 사용량 : " + fordFulkerson(0, V - 1));
    }

    public static int fordFulkerson(int source, int sink) {
        int totalFlow = 0;
        ArrayList<Integer> path = new ArrayList<>();

        while(true) {
            parent[sink] = -1;
            for(int i = 0; i < V; i++) visited[i] = false;
            LinkedList<Integer> q = new LinkedList<>();
            q.add(source);
            visited[source] = true;

            while (q.size() != 0) {
                int here = q.peek();
                q.pop();
                for (int there = 0; there < V; ++there) {
                    if (visited[there] == false && capacity[here][there] > 0) {
                        q.add(there);
                        parent[there] = here;
                        visited[there] = true;
                    }
                }
            }

            if (parent[sink] == -1) break;

            int amount = INF;
            for (int p = sink; p != source; p = parent[p])
                amount = Math.min(capacity[parent[p]][p], amount);

            for (int p = sink; p != source; p = parent[p]) {
                path.add(parent[p]);
                capacity[parent[p]][p] -= amount;
                capacity[p][parent[p]] += amount;
            }

            System.out.print("## 경로 : ");
            for(int i = path.size() - 1; i >= 0; i--)
                System.out.print(path.get(i) + " -> ");
            System.out.println(V - 1);
            path = new ArrayList<>();
            System.out.println("     최대용량 : " + amount);
            totalFlow += amount;
        }
        return totalFlow;
    }
}

