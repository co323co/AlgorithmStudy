package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S4_1940_주몽 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 재료의 수
        int M = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        int ans = 0;
        int start=0, end=N-1;
        while(start<end){
            if(arr[start] + arr[end] == M) {
                ans++;
                start++;
            }
            else if(arr[start] + arr[end] < M) start++;
            else if(arr[start] + arr[end] > M) end--;
        }
        System.out.println(ans);
    }
}
