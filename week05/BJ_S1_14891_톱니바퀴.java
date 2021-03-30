package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//백준 실버1 14891 톱니바퀴
public class BJ_S1_14891_톱니바퀴 {

	static LinkedList<Integer> tobni[];
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tobni = new LinkedList[4];
		
		for(int i=0; i<4; i++) {
			char[] tmp = br.readLine().toCharArray();
			tobni[i] = new LinkedList<>();
			for(int j=0; j<8; j++) tobni[i].add(tmp[j]-'0');
		}
		
		int R = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());

			turn(i, d, new boolean[4]);
			
		}
		int res=0;
		for(int i=0; i<tobni.length; i++) res+=tobni[i].get(0)*Math.pow(2, i);
		System.out.println(res);
	}
	
	
	static void turn(int i, int d, boolean[] v) {
		
		if(v[i]) return;
		v[i]=true;
		//왼쪽 톱니와 맞닿는 톱니가 다른 극이면 왼쪽 톱니 회전
		if(i-1>=0 && tobni[i].get(6) != tobni[i-1].get(2)) {
			turn(i-1, d==1?-1:1, v);
		}
		//오른쪽 톱니와 맞닿는 톱니가 다른 극이면 오는쪽 톱니 회전
		if(i+1<4 && tobni[i].get(2) != tobni[i+1].get(6)) {
			turn(i+1, d==1?-1:1, v);
		}
		
		//d가 1이면 시계방향으로 회전
		if(d==1) tobni[i].addFirst(tobni[i].pollLast());
		//d가 -1이면 반시계방향으로 회전
		if(d==-1) tobni[i].addLast(tobni[i].pollFirst());
		
	}

}
