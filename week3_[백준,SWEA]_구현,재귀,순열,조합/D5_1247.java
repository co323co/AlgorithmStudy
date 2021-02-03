package com.ssafy.algo;

import java.util.Scanner;

//SWEA D5 1247. [S/W 문제해결 응용] 3일차 - 최적 경로
//순열
public class D5_1247 {

	static Point home;
	static Point com;
	static Point[] ps;
	static int res;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t=1;t<=T;t++) {
			
			int N = sc.nextInt();
			
			home = new Point(sc.nextInt(),sc.nextInt());
			com = new Point(sc.nextInt(),sc.nextInt());
			
			ps = new Point[N];
			for(int i=0; i<N; i++)ps[i]=new Point(sc.nextInt(),sc.nextInt());		

			res=-1;
			recursive(0, new Point[N], new boolean[N]);	
			
			System.out.println("#"+t+" "+res);
			
		}
		sc.close();

	}
	public static void recursive(int k, Point[] sel, boolean[] v) {
		
		if(k==sel.length) {

			int r=Math.abs(com.x-sel[0].x)+Math.abs(com.y-sel[0].y);
			
			for(int i=0; i+1<sel.length; i++) {
				r+=Math.abs(sel[i].x-sel[i+1].x)+Math.abs(sel[i].y-sel[i+1].y);
			}
			
			r+=Math.abs(sel[sel.length-1].x-home.x)+Math.abs(sel[sel.length-1].y-home.y);
			
//			System.out.println(r);
			if(res==-1) res=r;
			else res = Math.min(res, r);
			return;
		}
		
		for(int i=0; i<ps.length; i++) {
			if(v[i]) continue;
			
			v[i]=true;
			sel[k]=ps[i];
			recursive(k+1, sel,v);
			v[i]=false;
		}
		
	}
}

class Point{
	
	int x, y;
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
}
