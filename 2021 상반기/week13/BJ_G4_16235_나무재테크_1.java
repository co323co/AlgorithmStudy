package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 
 * 800ms
 *  ** 	LinkedList의 get은 O(n)이다. 
 *  	시간 엄청 잡아먹어서 시간초과나니 반드시 forEach를 사용해야한다!!!!!!!!!
 *  
 *  	삭제도 LinkedList의 .remove(t)는 t를 찾아가야해서 O(n)이 걸린다.
 *  	iterator에서 it.remove를 통해 래퍼런스로 바로 삭제하자!(O(1))
 * 
 */
public class BJ_G4_16235_나무재테크_1 {

	//map : 남아있는 양분, food : 겨울에 추가할 양분 데이터 저장
	static int N, M, K, map[][], food[][];
	//LinkedList가 ArrayList보다 삽입삭제 빠름
	static LinkedList<Tree> trees;

	static class Tree implements Comparable<Tree>{
		int r, c, age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {	//나이가 어린 순으로
			return this.age-o.age;
		}
		
		@Override
		public String toString() {
			return "(" + r + "," + c + ", age=" + age + ")";
		}
	}

	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		food = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = 5;
				food[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		trees = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			trees.add(new Tree(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken())));
		}

		// solve
		//사실 문제에는 안적혀있지만 입력이 어린나무 순으로 들어와서 필요 없음
		Collections.sort(trees);
		for (int i = 0; i < K; i++) {
			//봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다 (죽은 나무들 반환)
			summer(spring()); //여름에는 봄에 죽은 나무가 양분으로 변하게 된다. (죽은 나무들 패러미터로 전달받음)
			autumn(); //가을에는 나무가 번식한다. 
			winter(); //겨울에는 땅에 양분을 추가한다. 
		}
		System.out.println(trees.size());
	}

	private static void winter() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j]+=food[i][j];
			}
		}
	}

	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1};
	private static void autumn() {

		ArrayList<Tree> newTreeList = new ArrayList<>();
		for (Tree t : trees) {
			if(t.age%5!=0) continue; 
			for(int d=0; d<8; d++) {
				int nr = t.r+dr[d];
				int nc = t.c+dc[d];
				if(nr>-1 && nr<N && nc>-1 && nc<N) {
					newTreeList.add(new Tree(nr, nc, 1));
				}
			}
		}
		/*
		 * 앞에다 붙임으로서 정렬 필요 없게 됨
		 * 맨 앞에 일괄추가. O(1)
		 * for문으로 addFirst하면 size만큼 걸림
		 */
		trees.addAll(0,newTreeList);
	}

	private static void summer(LinkedList<Tree> deadTrees) {
		while(!deadTrees.isEmpty()) {
			Tree t = deadTrees.poll();
			map[t.r][t.c] += t.age/2;
		}
	}

	//O(tree사이즈)
	private static LinkedList<Tree> spring() {

		LinkedList<Tree> deadTree = new LinkedList<>();
		for (Iterator<Tree> iterator = trees.iterator(); iterator.hasNext();) {
			Tree t = (Tree) iterator.next();
			if (map[t.r][t.c] >= t.age) {
				map[t.r][t.c] -= t.age++;
			} else {
				deadTree.add(t);
				/*
				 * for문에서 trees.remove(t)로 지우면 t를 찾아가야해서 O(n)이 걸림
				 * iterator에서 바로 지우면 O(1)로 지울 수 있음
				 */
				iterator.remove();
			}
			
		}
		return deadTree;
	}

	static void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}