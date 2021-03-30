package week02;

import java.io.FileInputStream;
import java.util.Scanner;

//SWEA D3 2805. 농작물 수확하기
public class SWEA_D3_2805_농작물수확하기 {

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("week2/D3_2805.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc = 1; tc<=T; tc++) {
			
			int N = sc.nextInt();
			
			int[][] board = new int[N][N];
			String tmp[] = new String[N];
			for(int i=0; i<N; i++) tmp[i]=sc.next();
			
			for(int i=0; i<N; i++) for(int j=0; j<N; j++) board[i][j]=(int)tmp[i].charAt(j)-(int)'0';
			
			int sum=0;
			int step=0;
			for(int r=0; r<N; r++) {
				for(int c=N/2-step; c<=N/2+step; c++) sum+=board[r][c];
				if(r<N/2) step++;
				else step--;
			}
			
			System.out.println("#"+tc+" "+sum);
			
		}
		sc.close();

	}

}
