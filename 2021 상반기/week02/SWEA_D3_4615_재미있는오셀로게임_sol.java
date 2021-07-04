package week02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class SWEA_D3_4615_재미있는오셀로게임_sol {
	
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, 1, 0, -1, -1, 1, 1, -1};
	static int[][] v;
	static int[][] a;
	static int n;
	public static void dfs(int x, int y, int d_x, int d_y, int k, int idx) {
		if(a[x][y] == k) {
			for(int i = 0; i < idx; i++) {
				a[v[i][0]][v[i][1]] = k;
			}
			return;
		}
		v[idx][0] = x;
		v[idx][1] = y;
		
		int nx = x + d_x;
		int ny = y + d_y;
		
		if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
			if(a[nx][ny] != 0)
				dfs(nx, ny, d_x, d_y, k, idx+1);
		}
		v[idx][0] = 0;
		v[idx][1] = 0;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("week2/D3_4615.txt"));
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			n = sc.nextInt();
			int m = sc.nextInt();
			a = new int[n][n];
			v = new int[n][2];
			int b = 0;
			int w = 0;
			
			a[n/2][n/2] = 2;
			a[n/2-1][n/2-1] = 2;
			a[n/2-1][n/2] = 1;
			a[n/2][n/2-1] = 1;
			
			for(int i = 0; i < m; i++) {
				int x = sc.nextInt() - 1;
				int y = sc.nextInt() - 1;
				int k = sc.nextInt();
				
				a[x][y] = k;
				
				for(int j = 0; j < 8; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					if(nx >= 0 && ny >= 0 && nx < n && ny < n && a[nx][ny] != 0) {
						dfs(nx, ny, dx[j], dy[j], k, 0);
					}
				}
				
				for(int ii=0; ii<n; ii++) {
					for(int j=0; j<n; j++) {
						
						if(a[j][ii]==1) System.out.print("B ");
						else if(a[j][ii]==2) System.out.print("W ");
						else System.out.print("  ");
						
					}
					System.out.println();
				}
				System.out.println();
			}
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(a[i][j] == 1)
						b++;
					if(a[i][j] == 2)
						w++;
					
				}
			}
			
			
			System.out.println("#" + t + " " + b + " " + w);
		}
	}
}