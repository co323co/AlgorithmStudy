package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_G4_2239_스도쿠 {

	static int N = 9;
	static int[][] sudoku = new int[N][N];
	static boolean finish;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<N; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<N; j++) sudoku[i][j] = line[j]-'0';
		}
		fillSudoku(0);
	}

	static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(sudoku[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void fillSudoku(int pos) {
		if(pos == N*N) {
			print();
			finish = true;
			return;
		}
		if(finish) return;
		int r = pos/N;
		int c = pos%N;
		if(sudoku[r][c]!=0) {
			fillSudoku(pos+1);
			return;
		}
		for(int k=1; k<=N; k++) {
			if(!checkRow(r, k) || !checkCol(c, k) || !checkBox(r, c, k)) continue;
			sudoku[r][c] = k;
			fillSudoku(pos+1);
			sudoku[r][c] = 0;
		}
	}
	
	static boolean checkRow(int r, int num) {
		for(int j=0; j<N; j++) {
			if(sudoku[r][j]==num) return false;
		}
		return true;
	}

	static boolean checkCol(int c, int num) {
		for(int i=0; i<N; i++) {
			if(sudoku[i][c]==num) return false;
		}
		return true;
	}

	static boolean checkBox(int r, int c, int num) {
		int sr, sc;
		//체크할 박스의 시작 r 구하기
		if(r<3) sr = 0;
		else if(r<6) sr=3;
		else sr=6;
		//체크할 박스의 시작 c 구하기
		if(c<3) sc = 0;
		else if(c<6) sc=3;
		else sc=6;
		
		for(int i=sr; i<sr+3; i++) {
			for(int j=sc; j<sc+3; j++) {
				if(sudoku[i][j]==num) return false;
			}
		}
		return true;
	}
}
