package week06_IM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//백준 실버5 2628 종이자르기
public class S5_2628_종이자르기 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Integer> cutRow = new ArrayList<>();
		ArrayList<Integer> cutCol = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		//맨 앞과 뒤는 이미 잘렸다고 생각
		cutRow.add(0);
		cutRow.add(C);
		cutCol.add(0);
		cutCol.add(R);
		
		int N  = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("0")) cutRow.add(Integer.parseInt(st.nextToken()));
			else cutCol.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(cutRow);
		Collections.sort(cutCol);
	
		int ans = 0;
		for(int i=1; i<cutRow.size(); i++) {
			
			//지금 컷된 번호-이전에 컷된 번호 하면 현재 파트의 길이가 나옴
			int h = cutRow.get(i)-cutRow.get(i-1);
			
			for(int j=1; j<cutCol.size(); j++) {
				int w = cutCol.get(j)-cutCol.get(j-1);
				int size = h*w;
				ans = Math.max(ans, size);
//				System.out.println(h+","+w+","+size);
			}
		}
//		System.out.println(cutRow);
//		System.out.println(cutCol);
		System.out.println(ans);
	}
}
