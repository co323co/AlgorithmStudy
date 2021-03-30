package week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_3985_롤케이크 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int[] roll = new int[L+1];
		int[] person = new int[N+1];
		int[] expected = new int[2]; // 0: 사람 번호, 1 : max 값
		
		for(int i=1; i<=N; i++) {
			
			st=new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			//가장 많은 케이크를 받을 것 같은 사람 예상 갱신
			if(expected[1]<end-start+1) {
				expected[0]=i;
				expected[1]=end-start+1;
			}
			
			for(int j = start; j<=end; j++) {
				if(roll[j]==0) {
					roll[j]=i;
					person[i]++;
				}
			}
		}
		
		int[] real = new int[2]; // 0 : 사람 번호	1 : max값 
		for(int i=1; i<=N; i++) if(real[1]<person[i]) {
			real[0]=i;
			real[1]=person[i];
		}
		System.out.println(expected[0]);
		System.out.println(real[0]);
	}

}
