package programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class PG_L2_더맵게 {

	public static int solution(int[] scoville, int K) {

		int answer = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < scoville.length; i++) {
			pq.add(scoville[i]);
		}
		while(!pq.isEmpty()) {
			if(pq.peek()>=K) return answer;
			
			if(pq.size()>=2) {
				pq.add(pq.poll() + pq.poll()*2);
				answer++;
			}
			else {
				return -1;
			}
		}
		return answer;

	}

	public static void main(String[] args) {
		System.out.println(solution(new int[] {1,2,3,9,10,12}, 7));
			
		
	}

}
