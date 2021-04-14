package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 브론즈2 13458 시험감독
public class BJ_B2_13458_시험감독 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		/*
		 * 입력
		 * 각 입력이 전부 최대 100만이라서 이중 포문 불가능임
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); //시험장의 수
		int arr[] = new int[N]; //각 시험장에 있는 응시자의 수
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken()); //총감독관이 한 시험장에서 감시할 수 있는 응시자의 수
		int C = Integer.parseInt(st.nextToken()); //부감독관이 한 시험장에서 감시할 수 있는 응시자의 수
		
		//A[j]에  필요한 감독관 수 = 1 + ceil((A[j]-B)/C)
		
		long sum=0;
		for(int i=0; i<N; i++) {
			
			if(arr[i]-B > 0) {
				sum+= 1 + Math.ceil((arr[i]-B)/(double)C);
				System.out.println(Math.ceil((arr[i]-B)/(double)C));
			} else sum+=1;
		}
		System.out.println(sum);
	}

}
