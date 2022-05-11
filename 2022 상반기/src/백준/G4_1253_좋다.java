package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_1253_좋다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Long.parseLong(st.nextToken());
        Arrays.sort(arr);
        int answer = 0;
        for(int i=0; i<N; i++){
            int start=0, end=N-1;
            while(start<end){
                if(arr[start] + arr[end] == arr[i]){
                    if(start==i) {
                        start++;
                    }
                    else if(end==i) {
                        end--;
                    }
                    else {
                        answer++;
                        break;
                    }
                }
                if(arr[start] + arr[end] < arr[i]){
                    start++;
                }
                if(arr[start] + arr[end] > arr[i]) end--;
            }
        }
        System.out.println(answer);
    }
}
