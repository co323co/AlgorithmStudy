package hw_20210203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 S3 16935 배열 돌리기3
public class BJ_S3_16935_배열돌리기3 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());	
		int N = Integer.parseInt((st.nextToken()));
		int M = Integer.parseInt((st.nextToken()));
		int R = Integer.parseInt((st.nextToken()));
		
		int[][] board = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) board[i][j]=Integer.parseInt((st.nextToken()));
		}
		
		int[] rs = new int[R];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) rs[i]=Integer.parseInt((st.nextToken()));
		
		for(int r=0; r<R; r++) {
			
			int[][] res=null;
			
			//1. 상하 반전
			if(rs[r]==1) {
				res = new int[N][M];
				for(int i=0; i<N; i++) for(int j=0; j<M; j++) res[i][j]=board[N-i-1][j];		
				board = res;
			}
			
			//2. 좌우 반전
			else if(rs[r]==2) {
				res = new int[N][M];
				for(int i=0; i<N; i++) for(int j=0; j<M; j++) res[i][j]=board[i][M-j-1];	
				board = res;
			}
			
			//3. 오른쪽으로 90도 회전
			else if(rs[r]==3) {
				res = new int[M][N];
				for(int i=0; i<N; i++) for(int j=0; j<M; j++) res[j][N-i-1]=board[i][j];
				N=res.length;
				M=res[0].length;
				
				board = res;
			}
			
			//4. 왼쪽으로 90도 회전
			else if(rs[r]==4) {
				res = new int[M][N];
				for(int i=0; i<N; i++) for(int j=0; j<M; j++) res[M-j-1][i]=board[i][j];
				N=res.length;
				M=res[0].length;
				
				board = res;
			}
			
			//5. 1그룹->2그룹, 2그룹->3그룹, 4그룹->1그룹
			else if(rs[r]==5) {
				
				int[][][] b = new int[4][N/2][M/2];
				int[] dr = {0,0,N/2,N/2};
				int[] dc = {0,M/2,M/2,0};
				
				for(int d=0; d<4; d++) 		
					for(int i=0; i<N/2; i++) 		
						for(int j=0; j<M/2; j++) 
							b[d][i][j] = board[i+dr[d]][j+dc[d]];				
				
				
				int[][][]nb = new int[4][][];
				
				//시계방향으로 회전
				for(int i=0; i<4; i++) nb[(i+1)%4]=b[i];
				
				for(int d=0; d<4; d++)
					for(int i=0; i<N/2; i++) 
						for(int j=0; j<M/2; j++) 
							board[i+dr[d]][j+dc[d]] = nb[d][i][j];							
		}	
			
			else if(rs[r]==6) {
				
				int[][][] b = new int[4][N/2][M/2];
				int[] dr = {0,0,N/2,N/2};
				int[] dc = {0,M/2,M/2,0};
				
				for(int d=0; d<4; d++) 		
					for(int i=0; i<N/2; i++) 		
						for(int j=0; j<M/2; j++) 
							b[d][i][j] = board[i+dr[d]][j+dc[d]];				
				
				
				int[][][]nb = new int[4][][];

				//반시계 방향으로
				for(int i=0; i<4; i++) nb[i]=b[(i+1)%4];
				
				for(int d=0; d<4; d++) 
					for(int i=0; i<N/2; i++) 
						for(int j=0; j<M/2; j++) 
							board[i+dr[d]][j+dc[d]] = nb[d][i][j];							
		}		
			
	}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) sb.append(board[i][j]+" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
