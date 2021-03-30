package week05;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class test {

	public static void main(String[] args) {
		
		LinkedList<Integer> list = new LinkedList<>();
		
		for(int i=0; i<5; i++) list.add(i+1);

		ListIterator<Integer> it = list.listIterator();


		
		it.next();
		it.next();
		// remove()는 next() 혹은 previous()에 의해 반환된
		// 가장 마지막 요소를 리스트에서 제거함
		it.remove();
//		it.next();
//		System.out.println(it.previous());
//		System.out.println(it.next());
//		it.remove();
//		
//		System.out.println(it.previous());
//		System.out.println(it.next());
//		System.out.println(it.next());
//		
		System.out.println(list);
	}

}
