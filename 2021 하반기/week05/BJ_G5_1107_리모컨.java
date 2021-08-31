package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_1107_리모컨 {

	static int goal;
	static int num;
	static int[] btn; //고장난 버튼들
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		goal = Integer.parseInt(br.readLine());
		num = Integer.parseInt(br.readLine());
		///고장난 버튼이 0개일 때 예외처리
		if(num==0) {
			if(goal==100) System.out.println("0");
			else System.out.println(Math.min(Math.abs(goal - 100), Integer.toString(goal).length()));
			return;
		}
		st = new StringTokenizer(br.readLine());
		btn = new int[num];
		for(int i=0; i<num; i++) {
			btn[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(getMin());

	}
	//해당 채널을 숫자버튼으로 이동할 수 있는지 체크
	static boolean canMove(int goal) {
		String to = Integer.toString(goal);
		for(int i=0; i<to.length(); i++) {
			for(int j=0; j<num; j++) {
				if(btn[j] ==  (int)(to.charAt(i)-'0')) return false;
			}
		}
		return true;
	}
	
	static int getMin() {
		int ans;
		
		// +,- 버튼만으로 이동할 경우
		ans = Math.abs(goal - 100);
		//숫자 버튼으로 한번에 이동할 경우
		if(canMove(goal)) ans = Math.min(ans, Integer.toString(goal).length());
		//숫자 버튼으로 가까운 채널로 이동 후 +,-으로 도착하는 경우
		//더 큰 채널로 가서 -로 내려가는 경우
		for(int curr=goal; Integer.toString(curr).length() + (curr-goal)<ans; curr++) {
			if(canMove(curr)) {
				ans = Math.min(ans, Integer.toString(curr).length() + (curr-goal));
				break;
			}
		}
	
		//더 작은 채널로 가서 +로 올라가는 경우
		for(int curr=goal; curr >= 0 ; curr--) {
			if(canMove(curr)) {
				ans = Math.min(ans, Integer.toString(curr).length() + (goal-curr));
				break;
			}
		}
		return ans;
	}
}
