package week03;

import java.util.Arrays;
import java.util.Scanner;

//백준 브론즈2 1592 영식이와 친구들
public class BJ_B2_1592_영식이와친구들 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int L = sc.nextInt();
		
		int[] arr = new int[N];
		Arrays.fill(arr, 0);
		
		int curr=0; //현재 받은 애
		int cnt=0; //던진 횟수
		while(true) {		
			//받은 횟수 +1
			arr[curr]++;
			if(arr[curr]==M) break;
			if(arr[curr]%2==0) curr = curr-L>=0?curr-L:N+(curr-L); //짝수번 받았으면 반시계방향으로 던진다.
			else curr=(curr+L)%N; //홀수번 받았으면 시계방향으로 던진다
			cnt++; //던진다
		}
		
		System.out.println(cnt);
		sc.close();
	}
}
