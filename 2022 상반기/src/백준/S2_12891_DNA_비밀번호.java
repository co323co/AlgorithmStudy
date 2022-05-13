package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 슬라이딩 윈도우 알고리즘
public class S2_12891_DNA_비밀번호 {

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // 문자열의 길이
        int P = Integer.parseInt(st.nextToken()); // 부분문자열의 길이
        char[] str = br.readLine().toCharArray(); // 임의의 DNA 문자열
        int[] check = new int[4];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<4; i++) {
            check[i] = Integer.parseInt(st.nextToken());
        }


        // solve
        int ans = 0;
        Map<Character, Integer> indexMap = new HashMap() {{put('A',0); put('C',1); put('G',2); put ('T',3);}};
        int[] count = new int[4];
        int checkCount = 0;

        for(int i=0; i<P; i++){
            int idx = indexMap.get(str[i]);
            //Add
            ++count[idx];
        }

        // 0인 경우도 있으니 맨 처음엔 다 세어주기
        for(int i=0; i<4; i++){
            if(count[i]>=check[i]) checkCount++;
        }

        if(checkCount == 4) ans++;

        for(int i=P; i<S; i++){
            //Sub
            int idx = indexMap.get(str[i-P]);
            if(count[idx]-- ==  check[idx]) checkCount--;
            //Add
            idx = indexMap.get(str[i]);
            if(++count[idx] == check[idx]) checkCount++;
            if(checkCount == 4 ) ans++;
        }
        System.out.println(ans);
    }
}
