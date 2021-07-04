package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 
 * 각 땅별로 나무를 관리하는 버전
 * 이렇게하면 addAll사용 못해서 1버전보단 좀 느려짐 (980ms)
 * 
 */
public class BJ_G4_16235_나무재테크_2_셀별로관리버전 {

	static int N, M, K, food[][];
	static Spot[][] map;
	static class Spot{
		int food=5;
		LinkedList<Tree> trees = new LinkedList<>();
	}
	
	static class Tree implements Comparable<Tree>{
		int r, c, age = 1;
		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
		@Override
		public int compareTo(Tree o) {
			return this.age-o.age;
		}
		@Override
		public String toString() {
			return "(" + r + "," + c + ", a:" + age + ")";
		}
	}
	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new Spot[N][N];
		food = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				food[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = new Spot();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			map[R][C].trees.add(new Tree(R,C,age));
		}

		// solve
		
		//sort //사실 문제에는 안적혀있지만 입력이 어린나무 순으로 들어와서 필요 없음
		for(int i=0; i<N; i++) for(int j=0; j<N; j++) Collections.sort(map[i][j].trees);

		for (int i = 0; i < K; i++) {
			//봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다 (죽은 나무들 반환)
			summer(spring()); //여름에는 봄에 죽은 나무가 양분으로 변하게 된다. (죽은 나무들 패러미터로 전달받음)
			autumn(); //가을에는 나무가 번식한다. 
			winter(); //겨울에는 땅에 양분을 추가한다. 
		}

		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sum+=map[i][j].trees.size();
			}
		}
		System.out.println(sum);
	}

	private static void winter() {

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j].food+=food[i][j];
			}
		}
	}

	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1};
	private static void autumn() {

		ArrayList<Tree> newTreeList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for (Tree t : map[i][j].trees) {
					if(t.age%5!=0) continue;
					for(int d=0; d<8; d++) {
						int nr = t.r+dr[d];
						int nc = t.c+dc[d];
						if(nr<0 || nr>N-1 || nc<0 || nc>N-1) continue;
						newTreeList.add(new Tree(nr, nc, 1));
					}
					
				}
			}
		}
		for(Tree t : newTreeList) {	//앞에다 붙임으로서 정렬 필요 없게 됨
			map[t.r][t.c].trees.addFirst(t);
		}
	}

	private static void summer(LinkedList<Tree> deadTrees) {
		for(Tree t : deadTrees) {
			map[t.r][t.c].food += t.age/2;
		}
	}

	private static LinkedList<Tree> spring() {

		LinkedList<Tree> deadTree = new LinkedList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for (Iterator<Tree> it = map[i][j].trees.iterator(); it.hasNext();) {
					Tree t = it.next();
					if (map[t.r][t.c].food >= t.age) {
						map[t.r][t.c].food-=t.age++;
					} else {
						deadTree.add(t);
						it.remove();
					}
				}
			}
		}
		return deadTree;
	}

	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j].food+" ");
				System.out.println(map[i][j].trees);
			}
		}
		System.out.println();
	}

}
