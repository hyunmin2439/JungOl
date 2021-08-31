package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol1863 {
	
	static int N, M, cnt;
	static int[][] parent; // [n][0] : 부모 / [n][1] : 높이(랭크)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = N; // 모든 학생들의 종교가 다르다고 가정하고 시작
		make();
		
		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			// 합치는데 성공하면 종교 숫자 감소
			if( union(i, j) ) cnt--;
		}
		
		System.out.println(cnt);
		br.close();
	}
	
	private static void make() {
		parent = new int[N + 1][2];
		
		for (int i = 1; i <= N; i++) {
			parent[i][0] = i;
			parent[i][1] = 1; // 랭크 처음 다 1
		}
	}
	
	private static int[] find(int[] x) {
		if(x[0] == parent[x[0]][0]) return x;
		return parent[x[0]] = find(parent[x[0]]);
	}
	
	private static boolean union(int i, int j) {
		int iRt[] = find(parent[i]);
		int jRt[] = find(parent[j]);
		
		// 부모가 같으면 같은 종교
		if(iRt[0] == jRt[0]) return false;
		
		// 높이가 같으면 왼쪽 높이 증가 후 오른쪽 부모에 왼쪽 담기
		if(iRt[1] == jRt[1]) {
			iRt[1]++;
			parent[jRt[0]] = iRt; // parent[j의부모] = parent[i] // 부모, 랭크값 포함
		}
		// 높이가 큰 쪽으로 합쳐주기
		else if(iRt[1] > jRt[1]) 
			parent[jRt[0]] = iRt;
		else parent[iRt[0]] = jRt;
		return true;
	}
}