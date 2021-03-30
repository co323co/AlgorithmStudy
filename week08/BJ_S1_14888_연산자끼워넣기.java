package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 실버1 14888 연산자 끼워넣기
public class BJ_S1_14888_연산자끼워넣기 {

	static int[] arr;
	static int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		recursive(arr[0], 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		System.out.println(max);
		System.out.println(min);
	}
	
	static void recursive(int res, int idx, int op1, int op2, int op3, int op4){
		if(op1==0 && op2==0 && op3==0 && op4==0) {
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}
		
		if(op1>0) {
			recursive(res+arr[idx], idx+1, op1-1, op2, op3, op4);
		}
		if(op2>0) {
			recursive(res-arr[idx], idx+1, op1, op2-1, op3, op4);
		}
		if(op3>0) {
			recursive(res*arr[idx], idx+1, op1, op2, op3-1, op4);
		}
		if(op4>0) {
			recursive(res/arr[idx], idx+1, op1, op2, op3, op4-1);
		}
	}
}
