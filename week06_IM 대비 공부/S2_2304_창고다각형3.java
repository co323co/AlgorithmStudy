package week06_IM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class S2_2304_창고다각형3 {

	static int[][] lines;
	public static void main(String[] args) throws NumberFormatException, IOException {

		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		lines = new int[N][2]; //인덱스 0 : 왼쪽 면의 위치, 인덱스 1 : 높이
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
		
		for(int i=0; i<N; i++) {
			System.out.print("["+lines[i][0]+","+lines[i][1]+"],");
		}
		System.out.println();
//		System.out.println(lines[1][1]);
		
		int currIdx=0, nextIdx=1;
		
		int ans = 0;
		while(true){

//			//끝까지 다 봤다
//			if(currIdx==N) {
//				System.out.println("검사다함");
//				break;
//			}
			
			if(nextIdx==N) {
				ans+=lines[nextIdx-1][1];
//					if(lines[currIdx][1] <= lines[nextIdx][1])
				break;
			}
			//현재 막대보다 다음 막대가 같거나 더 높다면
			if(lines[currIdx][1] <= lines[nextIdx][1]) {
				int h = lines[currIdx][1];
				int w = lines[nextIdx][0]-lines[currIdx][0];
//				System.out.println("/ " + lines[nextIdx][0]+","+lines[currIdx][0]);
				System.out.println(h+","+w);
				int size = h*w;
				System.out.println( lines[nextIdx][0]+","+  lines[nextIdx][1] + " 같거나 큰애만남 "+size);
				ans+=size;
//				System.out.println(h+","+w+","+size);
				currIdx=nextIdx;
				nextIdx++;
			}
			//다음 막대가 현재 막대보다  작다
			else {
				System.out.println("작은애만남");
				
				int h = lines[nextIdx][1];
				int w = lines[nextIdx][0]-lines[currIdx][0];
				
				int size = h*w;
				ans+=size;
				nextIdx++;
				
//				//맨 끝 막대 처리
//				if(nextIdx == N) {
//					//끝까지 봤는데도 나랑 같거나 큰애가 없다. (내 뒤로 전부 나보다 작다)
//					ans += lines[currIdx][1]; //일단 내 길이만큼의 직사각형 나옴
//					System.out.println("나자신 더함 "+ lines[currIdx][1]);
//					int[] max= new int[2]; //내 뒤에 애들 중 가장 큰 높이
//					for(int i=currIdx+1; i<N; i++ ) {
//						if(max[1]<lines[i][1]) {
//							max[1] = lines[i][1];
//							max[0] = lines[i][0];
//						}
//					}
//					int w = max[0]-lines[currIdx][0];
//					int h = max[1];
//					int size= w*h;
//					System.out.println("내가끝임"+size);
//					System.out.println(max[0]+","+max[1]+"가 그 다음으로 큼");
//					ans += size;
//					break;
//				}
				
				//뒤에 나보다 큰 애가 있다면
			}
			
		}
		System.out.println(ans);
		
	}
	
	//내 뒤에 나보다 높은애가 있는지 검사
	static boolean isExistHigh(int idx) {
		for(int i=idx+1; i<lines.length; i++) {
			if(lines[idx][1] < lines[i][1]) return true;
		}
		return false;
	}
}
