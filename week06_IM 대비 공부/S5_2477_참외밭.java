package week06_IM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_2477_참외밭 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[6][2]; // 6변,[][0]은 방향 [][1]은 길이
		StringTokenizer st;
		for(int i=0; i<6; i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		
		//큰 직사각형에서 작은 직사각형을 뺀다
		int bxIdx=-1, byIdx=-1; //큰사각형의 가로, 세로
		int sxIdx=-1, syIdx=-1; //작은사각형의 가로, 세로
		for(int i=0; i<6; i++) {
			//가로방향 변일때, 최댓값인지 체크
			if(arr[i][0]==1 || arr[i][0]==2) {
				if(bxIdx==-1 || arr[i][1]>arr[bxIdx][1]) bxIdx=i; 
			}
			//세로방향 변일때, 최댓값인지 체크
			else {	
				if(byIdx==-1 || arr[i][1]>arr[byIdx][1]) byIdx=i;	
			}
		}
		//큰 직사작형의 가로, 세로와 인접하지 않으면 작은 사각형의 가로, 세로 
		for(int i=0; i<6; i++) {
			//큰 직사각형 가로,세로의 오른쪽 왼쪽에 없어야함
			if(i == (bxIdx-1+6)%6) continue;
			else if(i == (bxIdx+1+6)%6) continue;
			else if(i == (byIdx-1+6)%6) continue;
			else if(i == (byIdx+1+6)%6) continue;
			
			//아니면 작은 사각형임 (방향이 가로면 작은사각형의 가로, 세로면 작은사각형의 세로)
			if(arr[i][0]==1 || arr[i][0]==2) sxIdx = i;
			else syIdx = i;
		}
//		System.out.println(arr[bxIdx][1]+","+arr[byIdx][1]+","+arr[sxIdx][1]+","+arr[syIdx][1]);
		System.out.println(K*(arr[bxIdx][1]*arr[byIdx][1] - arr[sxIdx][1]*arr[syIdx][1]));
	}
}
