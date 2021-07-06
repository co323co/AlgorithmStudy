package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//백준 골드5 15686 치킨배달
public class G5_15686_치킨배달 {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "(" + r + "," + c + ")";
		}

	}

	static int N, M, map[][], answer = Integer.MAX_VALUE;
	static ArrayList<Pos> chickenList = new ArrayList<>();
	static ArrayList<Pos> homeList = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N]; // 0은 빈 칸, 1은 집, 2는 치킨집
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) chickenList.add(new Pos(i, j));
				else if(map[i][j]==1) homeList.add(new Pos(i,j));
			}

		}
		combination(0, 0, new Pos[M]);
		System.out.println(answer);
	}

	// 치킨집 중 최대 M개를 고르는 조합
	static void combination(int start, int k, Pos sel[]) {
		if (k == sel.length) {
			// 도시의 치킨거리 구하기
			int dosiDist = 0;
			for(Pos home : homeList) {
				dosiDist += getChickendDist(home, sel);
			}
			answer = Math.min(answer, dosiDist);
			return;
		}
		for (int i = start; i < chickenList.size(); i++) {
			sel[k] = chickenList.get(i);
			combination(i + 1, k + 1, sel);
		}
	}
	
	// 가장 가까운 치킨집과의 거리 구하는 함수
	static int getChickendDist(Pos home, Pos[] chickenList) {
		int ans = Integer.MAX_VALUE;
		for(Pos store : chickenList) {
			// 문제에서 임의의 두 칸 (r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|로 구한다.
			ans = Math.min(Math.abs(home.r - store.r) + Math.abs(home.c - store.c), ans);
		}
		return ans;
	}
}
