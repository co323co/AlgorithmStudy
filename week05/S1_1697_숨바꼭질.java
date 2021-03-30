package week05;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//백준 실버1 1697 숨바꼭질
public class S1_1697_숨바꼭질 {

	static int N,K;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
	
		System.out.println(bfs());
		sc.close();

	}

	static class Point{
		
		int pos,time;

		public Point(int pos, int time) {
			super();
			this.pos = pos;
			this.time = time;
		}
		
	}
	
	static int bfs() {
	
		boolean[] v = new boolean[200001];
		
		Queue<Point> q = new LinkedList<>();
		
		q.add(new Point(N, 0));
		
		while(!q.isEmpty()) {
			
			Point curr = q.poll();
			if(curr.pos==K) return curr.time;
			
			v[curr.pos] = true;
			
			if(curr.pos-1 >= 0 && !v[curr.pos-1]) 	
				q.add(new Point(curr.pos-1, curr.time+1));;
				
			if(curr.pos+1 < v.length && !v[curr.pos+1]) 
				q.add(new Point(curr.pos+1, curr.time+1));
			
			if(curr.pos*2 < v.length && !v[curr.pos*2]) 
				q.add(new Point(curr.pos*2, curr.time+1));
			
		}
		return -1;
	}
}
