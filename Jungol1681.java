package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// accepted(80)으로 정답이 다 맞지 않음
// Floyd-Warshall Algorithm을 사용하는 방법이 아니라고 보여진다.
// 출력형식에 모든 장소를 한 번씩 들러라고 되어 있으니 이것도 조건인 것으로 추정
// 그렇다면 Floyd-Warshall Algorithm이 아닌 일반 dfs로 탐색하고 적절히 가지치기를 하면 될 것 같다.
public class Jungol1681 {

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
		
		floyd(); // Floyd-Warshall Algorithm 적용
		initFlag(); // 기저조건 플래그 값 계산
		dfs(0, 0, 1); // 회사에서 시작 / 시작비용 0 / 회사 위치 방문처리
		
		System.out.println(min);
		in.close();
	}

	private static void floyd() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if(k == i) continue;
				for (int j = 0; j < N; j++) {
					if(k == j || i == j) continue;
					
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}
	}
	
	private static void initFlag() {
		for (int i = 0; i < N; i++) {
			conFlag |= 1 << i;
		}
	}

	private static void dfs(int idx, int cost, int flag) {
		
		// 기저 조건
		if(flag == conFlag) {
			// 회사로 돌아가는 비용 더해서 작은 값 대치
			min = Math.min(min, cost + adjMatrix[idx][0]);
			return;
		}
		
		// 가지 치기
		if(cost >= min) return;
		
		for (int i = 0; i < N; i++) {
			// 자기 자신을 향한 경로, 방문할 수 없는 곳, 방문한 곳이면 건너뛰기
			if( idx == i || adjMatrix[idx][i] == INF || (flag & 1 << i) != 0 ) continue;
			
			// i위치 방문, 비용 더하기, 방문 처리
			dfs(i, cost + adjMatrix[idx][i], flag | 1 << i);
		}
	}

}
