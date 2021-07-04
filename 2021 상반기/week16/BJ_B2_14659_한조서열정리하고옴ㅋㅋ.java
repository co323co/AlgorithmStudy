package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_14659_한조서열정리하고옴ㅋㅋ {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] sol = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				if(arr[i] < arr[j]) break;
				if(arr[i] == arr[j]) continue;
				else sol[i]++;
			}
			max = Math.max(max, sol[i]);
		}
		System.out.println(max);
	}
}
