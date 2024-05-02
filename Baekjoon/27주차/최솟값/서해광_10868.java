// 10868. 최솟값
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static long[]arr,seg;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr=new long[N+1];
		seg=new long[4*N];
		for(int i=1;i<=N;i++) {
			arr[i]=Long.parseLong(br.readLine());
		}
		initSeg(1, N, 1);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			sb.append(getMin(a, b, 1, N, 1)).append("\n");
		}
		System.out.println(sb);
	}
	public static long initSeg(int s,int e,int node) {
		if(s==e) {
			return seg[node]=arr[s];
		}
		int mid=(s+e)/2;
		return seg[node]=Math.min(initSeg(s, mid, node*2), initSeg(mid+1, e, node*2+1));
	}
	public static long getMin(int a,int b,int s,int e,int node) {
		if(e<a||b<s) return Long.MAX_VALUE;
        if(a<=s&&e<=b) return seg[node];
		int mid=(s+e)/2;
		return Math.min(getMin(a, b, s, mid, node*2), getMin(a, b, mid+1, e, node*2+1));
	}
}
