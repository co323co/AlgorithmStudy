package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//백준 골드5 1107 리모컨
public class G5_1107_리모컨 {
	
//고장난 버튼
	static Set<Character> btn = new HashSet<>();
	static int goal;
	static int ans;
	static Integer[] arr;
	public static void main(String[] args) throws IOException {
	
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		goal= Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		if(N>0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) btn.add(st.nextToken().charAt(0));
		}
		
		//풀이
		bf();
		
		System.out.println(ans);
	}
	
	//누를 수 있는 버튼만 있는지 체크하는 함수
	static boolean isPossible(int num) {
		String snum = Integer.toString(num);
		for(int i=0; i<snum.length(); i++) {
			if(btn.contains(snum.charAt(i))) return false; 
		}
		return true;
	}

	//구현
	static void bf() {
		
		//1. + or -버튼만 눌러서 목표 채널에 도달
		ans = Math.abs(Math.abs(goal-100));
		
		//2. 숫자 버튼을 통해 목표 채널에 도달
		if(isPossible(goal)) ans = Math.min(ans, Integer.toString(goal).length());
		
		//3. 숫자버튼으로 가까운 채널로 이동 후, +, -버튼을 통해 목표 채널에 도달
		//아래로 검사
		for(int curr=goal; curr>=0; curr--) {
			if(isPossible(curr)) {
				ans = Math.min(ans,(goal-curr)+Integer.toString(curr).length());
				break;
			}
		}
		//위로 검사(최솟값보다 작을 수 있는 범위까지만)
		for(int curr=goal; (curr-goal)+Integer.toString(curr).length() < ans; curr++) {
			if(isPossible(curr)) {
				ans = Math.min(ans,(curr-goal)+Integer.toString(curr).length());
				break;
			}
		}
	}
} 