package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//백준 골드4 1759 암호 만들기
public class BJ_G4_1759_암호만들기 {

	static String moeum = "aeiou";
	static char[] alpha;
	static int L, C;
	static ArrayList<String> result = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		alpha = new char[C];
		for (int i = 0; i < C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		combination(0, 0, new char[L], 0, 0);
		Collections.sort(result);
		StringBuilder sb = new StringBuilder();
		for(String str : result) sb.append(str+"\n");
		System.out.println(sb);
	}


	static void combination(int jaeumCnt, int moeumCnt, char sel[], int start, int k) {
		if (k == L) {
			if(moeumCnt<1) return;
			if(jaeumCnt<2) return;
			char[] tmp = sel.clone(); //sel을 정렬해버리면 다음 재귀에 영향을 줌
			Arrays.sort(tmp);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < sel.length; i++)
				sb.append(tmp[i]);
			result.add(sb.toString());
			return;
		}
		for (int i = start; i < C; i++) {
			sel[k] = alpha[i];
			if (moeum.contains(alpha[i] + ""))
				combination(jaeumCnt, moeumCnt+1, sel, i+1, k+1);
			else
				combination(jaeumCnt+1, moeumCnt, sel, i+1, k+1);
		}
	}
}
