package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 간단한 순열, 조합 되새기는 문제 */

public class Jungol1169 {

	static int N, M;
	
	static int[] arr;
	static boolean[] used;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		used = new boolean[7];
		
		switch(M) {
		case 1: run1(0); break;
		case 2: run2(0, 1); break;
		case 3: run3(0); break;
		}
		
		in.close();
	}
	
	private static void print() {
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");			
		}
		System.out.println();
	}

	private static void run1(int idx) {
		if(idx == N) {
			print();
			return;
		}
		
		for (int i = 1; i <= 6; i++) {
			arr[idx] = i;
			run1(idx + 1);
		}
	}
	
	private static void run2(int idx, int start) {
		
		if(idx == N) {
			print();
			return;
		}
		
		for (int i = start; i <= 6; i++) {
			arr[idx] = i;
			run2(idx + 1, i);
		}
	}
	
	private static void run3(int idx) {
		
		if(idx == N) {
			print();
			return;
		}
		
		for (int i = 1; i <= 6; i++) {
			if( used[i] ) continue;
			arr[idx] = i;
			used[i] = true;
			run3(idx + 1);
			used[i] = false;
		}
	}
}
