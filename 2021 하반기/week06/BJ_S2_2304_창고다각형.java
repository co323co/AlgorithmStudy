package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//백준 실버2 2034 창고 다각형
public class BJ_S2_2304_창고다각형 {

	static class Gidong implements Comparable<Gidong>{
		int pos;
		int height;
		public Gidong(int pos, int height) {
			this.pos = pos;
			this.height = height;
		}
		@Override
		public int compareTo(Gidong o) {
			return this.pos-o.pos;
		}
		
	}
	static int N;
	static Gidong[] gidongs;
	static int maxIdx;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		gidongs = new Gidong[N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			gidongs[i] = new Gidong(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(gidongs);
		//가장 긴 막대 구하기
		for(int i=0; i<N; i++) {
			if(gidongs[maxIdx].height < gidongs[i].height) {
				maxIdx = i;
			}
		}
		int area=0;
		int sIdx=0, eIdx=sIdx+1;
		//가장 긴 막대의 왼쪽 부분
		while(eIdx <= maxIdx) {
			if(gidongs[sIdx].height <= gidongs[eIdx].height) {
				int h = gidongs[sIdx].height;
				int w = gidongs[eIdx].pos - gidongs[sIdx].pos;
				area+=h*w;
				sIdx=eIdx;
			}
			eIdx++;
		}
		//가장 긴 막대의 넓이
		area += gidongs[maxIdx].height;
		//가장 긴 막대의 오른쪽 부분 
		//끝애서부터 위의 과정을 다시 반복함
		sIdx = N-1;
		eIdx = sIdx-1;
		while(eIdx >= maxIdx) {
			if(gidongs[sIdx].height <= gidongs[eIdx].height) {
				int h = gidongs[sIdx].height;
				int w = gidongs[sIdx].pos - gidongs[eIdx].pos;
				area+=h*w;
				sIdx=eIdx;
			}
			eIdx--;
		}
		System.out.println(area);
	}
}
