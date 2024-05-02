// 2412. 암벽 등반
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,T,ans;
	static List<Integer>[]graph;
	static class Node{
		int x;
		int y;
		int w;
		public Node(int x,int y,int w) {
			this.x=x;
			this.y=y;
			this.w=w;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", w=" + w + "]";
		}
	}
	static class Pair{
		int x;
		int y;
		public Pair(int x,int y) {
			this.x=x;
			this.y=y;
		}
		@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Pair pair = (Pair) o;
	        return x == pair.x && y == pair.y;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(x, y);
	    }
	}
	static Map<Pair, Boolean> map;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		graph=new List[200001];
		map=new HashMap<Pair, Boolean>();
		for(int i=0;i<graph.length;i++) {
			graph[i]=new ArrayList<Integer>();
		}
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			graph[y].add(x);
		}
		for(int i=0;i<graph.length;i++) {
			Collections.sort(graph[i]);
		}
		ans=Integer.MAX_VALUE;
		bfs();
		System.out.println(ans==Integer.MAX_VALUE?-1:ans);
	}
	public static void bfs() {
		Queue<Node>q=new LinkedList<Node>();
		q.add(new Node(0, 0, 0));
		map.put(new Pair(0, 0), true);
		while(!q.isEmpty()) {
			Node cur=q.remove();
			if(cur.y==T) {
				ans=Math.min(ans, cur.w);
				continue;
			}
			for(int i=Math.max(0, cur.y-2);i<=Math.min(200000, cur.y+2);i++) {
				for(int j=0;j<graph[i].size();j++) {
					if(cur.x-graph[i].get(j)>2) continue;
					if(graph[i].get(j)-cur.x>2) break;
					Node next = new Node(graph[i].get(j), i, cur.w+1);
					if(!map.containsKey(new Pair(graph[i].get(j), i))) {
						map.put(new Pair(graph[i].get(j), i), true);
						q.add(next);
					}
				}
			}
		}
	}
}
