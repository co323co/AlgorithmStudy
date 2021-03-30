package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//백준 실버2 8911 거북이
public class S2_8911_거북이 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			String inst = br.readLine();

			int minR = 0, maxR = 0, minC = 0, maxC = 0;

			//정면부터 오른쪽으로 90도씩 회전 (앞으로 전진하는 기준)
			int[] dr = {-1, 0, 1, 0}; 
			int[] dc = {0, 1, 0,-1}; 

			int[] curr = new int[] {0,0};
			int dIdx=0; //거북는 앞을 보고 시작함
			for(int i=0; i<inst.length(); i++) {
				
				if(inst.charAt(i)=='F') {
					int[] next = { curr[0]+dr[dIdx], curr[1]+dc[dIdx] }; //현재 방향으로 이동
					curr = next;
					
					minR = Math.min(minR, curr[0]);
					maxR = Math.max(maxR, curr[0]);
					minC = Math.min(minC, curr[1]);
					maxC = Math.max(maxC, curr[1]);
				}
				
				else if(inst.charAt(i)=='B') {
					int[] next = { curr[0]-dr[dIdx], curr[1]-dc[dIdx] }; //현재 방향 전진의 반대 방향으로 이동
					curr = next;
					
					minR = Math.min(minR, curr[0]);
					maxR = Math.max(maxR, curr[0]);
					minC = Math.min(minC, curr[1]);
					maxC = Math.max(maxC, curr[1]);
				}
				
				else if(inst.charAt(i)=='L') {
					dIdx = (dIdx-1+4)%4; //방향을 왼쪽으로 90도 회전
				}
				
				else if(inst.charAt(i)=='R') {
					dIdx = (dIdx+1)%4; //방향을 오른쪽으로 90도 회전
				}
			}
			sb.append((maxR-minR)*(maxC-minC)+"\n");
		}
		System.out.println(sb);
	}
}
