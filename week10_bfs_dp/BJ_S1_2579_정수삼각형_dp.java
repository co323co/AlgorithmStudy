package week10;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 실버1 1932 정수 삼각형
/*
 * dp 연습문제. 기초
 */
public class BJ_S1_2579_정수삼각형_dp {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        //입력
        int N = Integer.parseInt(br.readLine());
        int[][] memo = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                memo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //solve
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i+1; j++) {
            	int max = memo[i-1][j];
            	if(j-1>=0) max = Math.max(memo[i-1][j-1], max);
            	memo[i][j] += max;
            }
        }
        
        int max = 0;
        for(int i=0; i<N; i++) {
        	max = Math.max(max, memo[N-1][i]);
        }
        System.out.println(max);
    }
}