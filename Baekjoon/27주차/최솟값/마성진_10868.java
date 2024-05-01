import java.io.*;
import java.util.*;

public class Main {
    static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        tree = new int[N * 4];
        makeTree(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            sb.append(minFind(1, N, 1, left, right)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int makeTree(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = Math.min(makeTree(start, mid, node * 2), makeTree(mid + 1, end, node * 2 + 1));
    }

    public static int minFind(int start, int end, int node, int left, int right) {
        if (right < start || end < left) {
            return Integer.MAX_VALUE;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return Math.min(minFind(start, mid, node * 2, left, right), minFind(mid + 1, end, node * 2 + 1, left, right));
    }

}
