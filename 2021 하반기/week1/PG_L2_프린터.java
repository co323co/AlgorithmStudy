package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PG_L2_프린터 {
    static class Point{
    	int p, l;

		public Point(int p, int l) {
			this.p = p;
			this.l = l;
		}
    	
    }
	public static int solution(int[] priorities, int location) {
    	LinkedList<Point> list = new LinkedList<>();
    	for(int i=0; i<priorities.length; i++) list.add(new Point(priorities[i], i));
    	int answer = 0;
    	while(!list.isEmpty()) {
    		Point curr = list.pollFirst();
    		boolean flag = false;
    		for(int i=0; i<list.size(); i++) {
    			if(list.get(i).p > curr.p) {
    				flag = true;
    			}
    		}
    		if(flag) {
    			list.add(curr);
    		} else {
    			answer++;
    			if(curr.l == location) return answer;
    		}
    	}
        return answer;
    }
	public static void main(String[] args) {
 		System.out.println(solution(new int[] {2,1,3,2},2));
 		System.out.println(solution(new int[] {1,1,9,1,1,1},0));
	}

}
