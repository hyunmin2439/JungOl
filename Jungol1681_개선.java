package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 모든 지점을 한번씩 방문하고 돌아와야 함
// Floyd-Warshall Algorithm은 같은 지점 여러번 
// 방문할 가능성도 있기 때문에 사용 불가능

// 틀린 답에서 Floyd-Warshall Algorithm 부분만 제거
// 추가로 마지막 지점에서 회사로 돌아가는 길이 없을 수도 있기 때문에 체크

public class Jungol1681_개선 {

	static final int INF = 1_000_000;
	
	static int N, min, conFlag;
	static int[][] adjMatrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		min = Integer.MAX_VALUE;
		N = Integer.parseInt(in.readLine());
		adjMatrix = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
				
				if(i != j && adjMatrix[i][j] == 0) adjMatrix[i][j] = INF;
			}
		}
		
		conFlag = (1 << N) - 1; // 기저조건 플래그 값 계산
		dfs(0, 0, 1); // 회사에서 시작 / 시작비용 0 / 회사 위치 방문처리
		
		System.out.println(min);
		in.close();
	}

	private static void dfs(int i, int cost, int flag) {
		
		// 기저 조건
		if(flag == conFlag) {
			
			// 회사로 돌아가는 길이 없으면
			if(adjMatrix[i][0] == INF) return;
			
			// 회사로 돌아가는 비용 더해서 작은 값 대치
			min = Math.min(min, cost + adjMatrix[i][0]);
			return;
		}
		
		for (int j = 0; j < N; j++) {
			// 자기 자신을 향한 경로, 방문할 수 없는 곳, 방문한 곳이면 건너뛰기
			if( i == j || adjMatrix[i][j] == INF || (flag & 1 << j) != 0 ) continue;
			
			// 가지 치기
			if(cost + adjMatrix[i][j] >= min) continue;
			
			// j위치 방문, 비용 더하기, 방문 처리
			dfs(j, cost + adjMatrix[i][j], flag | 1 << j);
		}
	}
}