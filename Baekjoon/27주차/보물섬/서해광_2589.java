// 2589. 보물섬
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R,C,ans;
	static boolean[][]visited;
	static char[][]map;
	static int[]dr={-1,1,0,0},dc={0,0,-1,1};
	static class Node{
		int r;
		int c;
		int w;
		public Node(int r,int c,int w) {
			this.r=r;
			this.c=c;
			this.w=w;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		map=new char[R][C];
		visited=new boolean[R][C];
		for(int i=0;i<R;i++) {
			String str=br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j]=str.charAt(j);
			}
		}
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]=='L') {
					bfs(i,j);
				}
			}
		}
		System.out.println(ans);
	}
	public static void bfs(int r,int c) {
		int tmp=0;
		Queue<Node>q=new LinkedList<Node>();
		visited=new boolean[R][C];
		q.add(new Node(r, c, 0));
		visited[r][c]=true;
		while(!q.isEmpty()) {
			Node cur=q.remove();
			if(cur.w>tmp) {
				tmp=cur.w;
			}
			for(int d=0;d<4;d++) {
				int nr=cur.r+dr[d];
				int nc=cur.c+dc[d];
				if(nr<0||nr>=R||nc<0||nc>=C||map[nr][nc]=='W'||visited[nr][nc])
					continue;
				visited[nr][nc]=true;
				q.add(new Node(nr, nc, cur.w+1));
			}
		}
		ans=Math.max(ans, tmp);
	}
}
