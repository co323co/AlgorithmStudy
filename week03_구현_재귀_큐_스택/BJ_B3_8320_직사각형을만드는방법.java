package week03;

import java.util.Scanner;

//백준 브론즈3 8320 직사각형을 만드는 방법
public class BJ_B3_8320_직사각형을만드는방법 {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int cnt=0;
		for(int i=1; i<=N; i++)
			for(int j=i; j<=N; j++)
				if(i*j<=N) cnt++;
		
		System.out.println(cnt);
		sc.close();
		
	}
}
