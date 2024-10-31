//메모리: 20496	 시간: 224
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    int N, M;
    int[] parent;
    List<Edge> edges = new ArrayList<>();
    String[] schoolType;

    class Edge implements Comparable<Edge> {
        int u, v, d;
        Edge(int u, int v, int d) {
            this.u = u;
            this.v = v;
            this.d = d;
        }
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.d, other.d);
        }
    }

    int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) parent[rootY] = rootX;
    }

    void Input() throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        schoolType = input.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(input.readLine());
            int u = Integer.parseInt(tokens.nextToken());
            int v = Integer.parseInt(tokens.nextToken());
            int d = Integer.parseInt(tokens.nextToken());

            // 남초 대학교와 여초 대학교만 연결하는 간선만 추가
            if ((schoolType[u - 1].equals("M") && schoolType[v - 1].equals("W")) ||
                (schoolType[u - 1].equals("W") && schoolType[v - 1].equals("M"))) {
                edges.add(new Edge(u, v, d));
            }
        }
    }

    void Solve() throws IOException {
        Input();

        Collections.sort(edges);
        int totalCost = 0;
        int count = 0;

        for (Edge edge : edges) {
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                totalCost += edge.d;
                count++;
                
                if (count == N - 1) break;
            }
        }

        if (count != N - 1) {
            System.out.println(-1);
        } else {
            System.out.println(totalCost);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().Solve();
    }
}
