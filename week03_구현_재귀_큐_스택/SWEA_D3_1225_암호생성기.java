package com.ssafy.algo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//SWEA D3 1225. [S/W 문제해결 기본] 7일차 - 암호생성기
public class SWEA_D3_1225_암호생성기 {
	
	public static void main(String[] args) {
		
		int T = 10;
		
		Scanner sc = new Scanner(System.in);
		
		for(int tc = 1; tc<=T; tc++) {
			sc.nextInt();
			Queue<Integer> q = new LinkedList<>();
			for(int i=0; i<8; i++) q.offer(sc.nextInt());
			
			int n=0;
			while(true) {
				n++;
				int tmp = q.poll();
				if(tmp-n<=0) {
					q.offer(0);
					break;
				}
				else {
					tmp-=n;
					q.offer(tmp);
				}
				if(n==5) n=0; //한 싸이클 끝남. 초기화
			}
			
			System.out.print("#"+tc+" ");
			while(!q.isEmpty()) System.out.print(q.poll()+" ");
			System.out.println();
		}		
		sc.close();
	}
}
