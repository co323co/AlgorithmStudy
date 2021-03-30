package week06_IM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//정답코드
public class S2_2304_창고다각형 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] lines = new int[N][2]; //인덱스 0 : 왼쪽 면의 위치, 인덱스 1 : 높이
		StringTokenizer st;
		for(int i=0; i<N; i++){
			st=new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
		}

		//위치 순으로 정렬
		Arrays.sort(lines, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
		
		//출력 확인
//		for(int i=0; i<N; i++) System.out.print("["+lines[i][0]+","+lines[i][1]+"],");
//		System.out.println();
		
		//가장 높은 막대 구하기
		int highestIdx=0;
		for(int i=0; i<lines.length; i++) if(lines[highestIdx][1]<lines[i][1]) highestIdx=i;
		
		int ans=0;

		//가장 높은 막대를 기준으로 왼쪽 구하기
		int curIdx = 0;
		int nextIdx = curIdx+1;
		while(nextIdx<=highestIdx) {
			
			if(lines[curIdx][1] <= lines[nextIdx][1]) {
				
				int h = lines[curIdx][1];
				int w = lines[nextIdx][0]-lines[curIdx][0];
//				System.out.println( "["+lines[nextIdx][0]+","+  lines[nextIdx][1]  + "] 같거나 큰애만남 "+ h+" x "+w + " = " + h*w);
				ans+=h*w;
				curIdx = nextIdx;
				
				nextIdx++;
				
			} else nextIdx++;
		}
		

		//가장 높은 막대의 길이 더해주기
		ans+=lines[highestIdx][1];
		
		
		//가장 높은 막대를 기준으로 오른쪽 구하기
		curIdx = lines.length-1;
		nextIdx = curIdx-1;
		while(nextIdx>=highestIdx) {
			
			if(lines[curIdx][1] <= lines[nextIdx][1]) {
				
				int h = lines[curIdx][1];
				int w = lines[curIdx][0]-lines[nextIdx][0];
//				System.out.println( "["+lines[nextIdx][0]+","+  lines[nextIdx][1] + "] 같거나 작은애만남 "+ h+" x "+w + " = " + h*w);
				ans+=h*w;
				curIdx = nextIdx;
				
				nextIdx--;
				
			} else nextIdx--;
		}
		
		System.out.println(ans);	

	}
}