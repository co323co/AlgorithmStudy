package week01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//백준 브론즈1 2167 2차원 배열의 합
public class BJ_B1_2167_2차원배열의합 {

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("week1/b_2167.txt"));

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] arr = new int[N][M];
		
		for(int i=0; i<N; i++) for(int j=0; j<M; j++) arr[i][j]=sc.nextInt();
		
		int k = sc.nextInt();

		for(int l=0; l<k; l++) {

			int sum = 0;
			
			int i = sc.nextInt()-1;
			int j = sc.nextInt()-1;
			int x = sc.nextInt()-1;
			int y = sc.nextInt()-1;
			
			for(int m=0; m<x-i+1; m++) for(int n=0; n<y-j+1; n++) sum+=arr[i+m][j+n];
			System.out.println(sum);			
		}		
		sc.close();
	}

}
