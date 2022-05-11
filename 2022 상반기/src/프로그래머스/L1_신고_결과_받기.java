package 프로그래머스;

import java.util.*;
public class L1_신고_결과_받기 {
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
        solution(id_list,report,k);
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String ,Integer> idMap = new HashMap<>();
        HashMap<String,HashSet> reportMap = new HashMap();
        for(int i=0; i< id_list.length; i++){
            idMap.put(id_list[i],i);
            reportMap.put(id_list[i],new HashSet<String>());
        }
        for(String re : report){
            StringTokenizer st = new StringTokenizer(re);
            reportMap.get(st.nextToken()).add(st.nextToken());
        }
        //신고 당한 횟수 세기
        HashMap<String, Integer> countMap = new HashMap<>();
        for(HashSet<String> set : reportMap.values()){
            for(String name : set){
                countMap.put(name, countMap.getOrDefault(name,0)+1);
            }
        }
        // 알림 횟수 세기
        for(String reporter : reportMap.keySet()){
            for (String name : countMap.keySet()){
                if(countMap.get(name)<k) continue;;
                if(reportMap.get(reporter).contains(name)) answer[idMap.get(reporter)]++;
            }
        }
        System.out.println(countMap);
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}
