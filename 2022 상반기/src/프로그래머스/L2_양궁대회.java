package 프로그래머스;

import java.util.Arrays;

public class L2_양궁대회 {

    public static void main(String[] args) {
        int n = 10;
        int[] info = {0,0,0,0,0,0,0,0,3,4,3};
        solution(n, info);
    }

    static public int[] apeach;
    static public Integer N;
    static public int max = 0;
    static public int[] answer;
    public static int[] solution(int n, int[] info) {
        apeach = info;
        N = n;
        dfs(0, new int[11]);
        if(answer==null) return new int[]{-1}; // 어피치를 이긴 경우가 없음
        return answer;
    }

    public static void dfs(int cnt, int[] lion){
        if(cnt==N) {
            int lionSum = 0, apeachSum = 0;
            // 점수 산출
            for(int i=0; i<11; i++) {
                if(apeach[i]==0 && lion[i]==0) continue; // 둘 다 안쐈으면 점수 X
                if(apeach[i] < lion[i]) lionSum+=10-i; // 라이언이 더 많이 맞힌 경우
                else apeachSum+=10-i; // 어피치가 더 많이 맞힌 경우
            }
            // 라이언 승리 기록이 기존 점수보다 큰 경우
            // 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우 채택
            if(apeachSum < lionSum && max<=lionSum-apeachSum){
                max = lionSum-apeachSum;
                answer = lion.clone();
            }
            return;
        }
        // 0~10점 중 과녘에 맞춤
        // lion[i] > apeach[i]가 되는 순간 n차에서 얻을 수 있는 가장 큰 점수를 얻은 것
        for(int i = 0; i<11 && lion[i] <= apeach[i]; i++){
            System.out.println(i);
            lion[i]++;
            dfs(cnt+1, lion);
            lion[i]--;
        }
    }
}
