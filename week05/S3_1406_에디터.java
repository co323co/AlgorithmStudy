package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

//백준 S3 1406 에디터
//시간초과 생각 많이해야하는 문제... 0.3초기 때문에 list를 size로 순회해버리면 터진다.
public class S3_1406_에디터 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		LinkedList<Character> list = new LinkedList<Character>();
		
		char[] tmp = br.readLine().toCharArray();
		int length = tmp.length;
		for(int i=0; i<length; i++) list.add(tmp[i]);
		
		ListIterator<Character> cursor = list.listIterator(length);
		
		//ListIterator,  앞뒤에 head, tail 달려있음. 걔네 값은 각 0번째 값, 마지막 요소값이랑 같음 

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			String token = st.nextToken();
			if(token.equals("P")) {
				cursor.add(st.nextToken().charAt(0));
			}
			else if(token.equals("L")) {
				if(cursor.hasPrevious()) cursor.previous();
			}
			else if(token.equals("D")) {
				if(cursor.hasNext()) cursor.next();
			}
			else if(token.equals("B")) {
				// remove()는 가장 마지막으로 사용한 next() or previous()이 반환했던 요소를 리스트에서 제거함
				if(cursor.hasPrevious()) {
					cursor.previous();
					cursor.remove();
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!list.isEmpty()) sb.append(list.poll()); 
		System.out.println(sb);
	}
}
