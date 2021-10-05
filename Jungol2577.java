package solved;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 회전 초밥
 * 
 * 입력의 범위가 굉장히 크기 때문애 조합으로 풀면 시간초과가 난다.
 * 
 * 2 ≤ N ≤ 3,000,000, 2 ≤ D ≤ 3,000, 2 ≤ K ≤ 3,000 (K ≤ N), 1 ≤ C ≤ D
 * 
 * 때문에 Sliding Window 기법을 이용하여,
 * 
 * 하나의 초밥 접시를 빼고, 하나의 초밥 접시를 더해 결과값을 계속 갱신해서 풀이
 * 
 * 맨 앞 접시를 빼야하기 때문에 Queue를 사용하여 빼고 넣고를 반복
 * 
 * static 배열, Queue등 멤버 변수로 선언해서 사용하니, 마지막 Tese Case에서 시간초과가 걸렸는지 90점이 나왔다
 * 
 * 때문에 전부 지역 변수로 변경하여 풀이하니 100점으로 통과가 완료 되었다.
 * 
 * Memory:74MB / Time:871ms
 */

public class Jungol2577 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()); // 벨트 위 접시 수
		int D = Integer.parseInt(st.nextToken()); // 초밥 가지수
		int K = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int C = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		int[] sushiList = new int[K]; // 초기에 먹은 초밥의 종류들 저장(원형)
		int[] isEat = new int[D + 1]; // 먹은 초밥을 저장할 Counting 배열
		Queue<Integer> queue = new LinkedList<>(); // 현재 먹은 초밥의 종류 큐에 담아놓기

		isEat[C]++; // 쿠폰 종류의 초밥
		int res = 1; // 먹은 초밥 종류수

		// 초기 입력
		for (int i = 0; i < K; i++) {
			int sushi = Integer.parseInt(in.readLine()); // 스시 종류
			
			sushiList[i] = sushi; // 원형이기 때문에 마지막에 사용
			
			queue.offer(sushi); // 큐에 담아놓기

			if (isEat[sushi] == 0) res++; // 먹지 않았던 종류이면 가지수 더하기

			isEat[sushi]++; // 먹었던 종류이건, 먹지 않은 종류이건 개수를 더해줘야 함
		}
		
		int max = res; // max:최대값(결과), 초기는 그냥 저장

		// K까지는 입력받고 처리 완료, 다음번 부터 처리 => N - K
		for (int i = 0, length = N - K; i < length; i++) {
			res = newSushi(queue, isEat, Integer.parseInt(in.readLine()), res);
			max = Math.max(max, res); // 최대값이면 갱신
		}
		
		// 원형이기 때문에 초기에 입력받은 것으로 추가 처리
		for (int i = 0; i < K - 1; i++) {
			res = newSushi(queue, isEat, sushiList[i], res);
			max = Math.max(max, res); // 최대값이면 갱신
		}
		
		System.out.println(max);

		in.close();
	}
	
	/** 제일 앞에 먹었던 초밥을 빼고 새로운 초밥을 더해서 초밥 종류수를 갱신해서 되돌려주는 메서드
	 * @param queue : 현재 먹은 초밥의 종류를 저장해놓는 큐
	 * @param isEat : 먹은 초밥을 저장할 Counting 배열
	 * @param newSushi : 새로운 초밥 접시
	 * @param res : 현재 먹은 초밥의 종류수
	 * @return 갱신된 res값
	 */
	private static int newSushi(Queue<Integer> queue, int[] isEat, int newSushi, int res) {
		int sushi = queue.poll(); // 가장 앞접시 빼기
		
		if (--isEat[sushi] == 0) res--; // 먹었던 개수를 빼고 0이면 먹은 종류 가지수도 빼기
		
		queue.offer(newSushi); // 큐에 담아놓기
		
		if(isEat[newSushi] == 0) res++; // 먹지 않았던 종류이면 가지수 더하기
		
		isEat[newSushi]++; // 먹었던 종류이건, 먹지 않은 종류이건 개수를 더해줘야 함
		
		return res; // 갱신된 값 리턴
	}

}
