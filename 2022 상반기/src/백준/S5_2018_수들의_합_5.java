package 백준;

import java.util.Scanner;

// 투포인터
public class S5_2018_수들의_합_5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int answer = 1;
        int start = 1, end = 1, sum = 1;
        while(end!=N){
            if(sum==N) {
                answer++;
                sum+=++end;
            }
            if(sum<N){
                sum+=++end;
            }
            if(sum>N){
                sum-=start++;
            }
        }
        System.out.println(answer);
    }
}
