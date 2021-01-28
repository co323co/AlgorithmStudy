package week2;

import java.io.FileInputStream;
import java.util.Scanner;

//SWEA D3 1208. [S/W 문제해결 기본] 1일차 - Flatten
public class D3_1208 {

	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("week2/D3_1208.txt"));
		
		Scanner sc = new Scanner(System.in);
		int T=10;
	
		final int N = 100;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int dump = sc.nextInt();
			//보드를 오른쪽으로 90도 회전했다고 생각한다.
            int[] board = new int[N];
            for(int i=0; i<N; i++) board[i] = sc.nextInt();
            
            for(int d=0; d<dump; d++){
             	board[FindMaxIndex(board)]--;
        		board[FindMinIndex(board)]++;
            }
            
            System.out.println("#"+test_case + " " + (board[FindMaxIndex(board)] - board[FindMinIndex(board)]));
		}
		
		sc.close();
	}
	
	
	static int FindMaxIndex(int[] arr) {
		int idx = arr[0];
		for(int i=0; i<arr.length; i++) if(arr[idx]<arr[i]) idx=i;
		return idx;
	}
	
	static int FindMinIndex(int[] arr) {
		int idx = arr[0];
		for(int i=0; i<arr.length; i++) if(arr[idx]>arr[i]) idx=i;
		return idx;
	}
}
