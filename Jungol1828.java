package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jungol1828 {

	static int N, cnt, temp; // cnt : 냉장고 개수, temp : 현재 냉장고 온도
	static Chemi[] chemi; // 화학물질이 저장될 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Input
		N = Integer.parseInt(br.readLine());
		chemi = new Chemi[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			chemi[i] = new Chemi(Integer.parseInt(st.nextToken()), 
								 Integer.parseInt(st.nextToken()));
		}
		
		// 정렬
		Arrays.sort(chemi);

		// 첫번째 냉장고 처리
		// 냉장고 온도 첫번째 화학물질 최고 보관 온도로 저장
		cnt++;
		temp = chemi[0].y;
		
		for (int i = 1; i < N; i++) {
			if(temp < chemi[i].x) {
				cnt++; // 냉장고 개수 증가
				temp = chemi[i].y; // 냉장고 온도 변경
			}
		}
		
		System.out.println(cnt);
		br.close();
	}
	
	static class Chemi implements Comparable<Chemi>{
		int x; // 최저 보관 온도
		int y; // 최고 보관 온도
		
		public Chemi(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Chemi other) {
			// 최고 보관 온도 순으로 정렬
			if(this.y != other.y) return this.y - other.y;
			// 최고 보관 온도가 같으면 최저 보관 온도 순으로 정렬
			return this.x - other.x;
		}
		
		// 디버깅용
		@Override
		public String toString() {
			return "Chemi [x=" + x + ", y=" + y + "]";
		}
	}

}
