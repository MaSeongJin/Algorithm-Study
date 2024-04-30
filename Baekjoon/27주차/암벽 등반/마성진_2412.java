import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (this.y != o.y) {
                return Integer.compare(this.y, o.y);
            } else {
                return Integer.compare(this.x, o.x);
            }
        }
    }

    private static Node[] graph;
    private static int[] visited;
    private static int N, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        graph = new Node[N + 1];
        visited = new int[N + 1];

        graph[0] = new Node(0, 0);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[i] = new Node(x, y);
        }

        Arrays.sort(graph, 1, N + 1);

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.add(0);
        visited[0] = 0;

        while (!que.isEmpty()) {
            int nowIndex = que.poll();
            if (graph[nowIndex].y == T) {
                return visited[nowIndex];
            }
            int nowX = graph[nowIndex].x;
            int nowY = graph[nowIndex].y;

            for (int i = nowIndex + 1; i <= N; i++) {
                if (visited[i] != 0) {
                    continue;
                }
                int nextX = graph[i].x;
                int nextY = graph[i].y;
                if (nextY - nowY > 2) {
                    break;
                }
                if (Math.abs(nextX - nowX) > 2) {
                    continue;
                }
                visited[i] = visited[nowIndex] + 1;
                que.add(i);
            }
            for (int i = nowIndex - 1; i > 0; i--) {
                if (visited[i] != 0) {
                    continue;
                }
                int nextX = graph[i].x;
                int nextY = graph[i].y;
                if (nextY - nowY < -2) {
                    break;
                }
                if (Math.abs(nextX - nowX) > 2) {
                    continue;
                }
                visited[i] = visited[nowIndex] + 1;
                que.add(i);
            }
        }
        return -1;
    }
}
