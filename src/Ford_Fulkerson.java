import java.util.ArrayList;
import java.util.LinkedList;

public class Ford_Fulkerson {
    static final int INF = Integer.MAX_VALUE;
    static final int Val = 6;
    static int capacity[][] = new int[][]{
            {0, 12, 0, 13, 0, 0},
            {0, 0, 10, 0, 0, 0},
            {0, 0, 0, 13, 3, 15},
            {0, 0, 7, 0, 15, 0},
            {0, 0, 0, 0, 0, 17},
            {0, 0, 0, 0, 0, 0}
    };
    static int parent[] = new int[Val];
    static boolean visited[] = new boolean[Val];

    public static void main(String[] args) {
        System.out.println("\n전체 사용량 : " + ford_Fulkerson(0, Val - 1));
    }

    public static int ford_Fulkerson(int source, int sink) {
        int totalFlow = 0;
        ArrayList<Integer> path = new ArrayList<>();

        while (true) {
            parent[sink] = -1;
            for (int i = 0; i < Val; i++) visited[i] = false;
            LinkedList<Integer> q = new LinkedList<>();
            q.add(source);
            visited[source] = true;

            while (q.size() != 0) {
                int now = q.peek();
                q.pop();

                for (int i = 0; i < Val; ++i) {
                    if (visited[i] == false && capacity[now][i] > 0) {
                        q.add(i);
                        parent[i] = now;
                        visited[i] = true;
                    }
                }
            }

            if (parent[sink] == -1)
                break;

            int value = INF;

            for (int p = sink; p != source; p = parent[p])
                value = Math.min(capacity[parent[p]][p], value);

            for (int p = sink; p != source; p = parent[p]) {
                path.add(parent[p]);
                capacity[parent[p]][p] -= value;
                capacity[p][parent[p]] += value;
            }

            System.out.print(" 경로 : ");
            for (int i = path.size() - 1; i >= 0; i--)
                System.out.print(path.get(i) + " > ");
            System.out.println(Val - 1);
            path = new ArrayList<>();
            System.out.println("     최대용량 : " + value);
            totalFlow += value;
        }
        return totalFlow;
    }
}

