

import java.util.Scanner;
import java.util.Stack;

public class D4_5432_sol {

	//20210204
	//SWEA D4 5432. 쇠막대기 자르기
	
	/////////////꼭 스택에 무조건 입력을 전부 넣어야한다는 생각을 버리자.
	//필요한 것만 넣어야 할 수도 있어. 이번 예제에 스택에 (여는괄호만 넣는 것처럼!
	
	/*
	 * 
	 * ()는 무조건 레이저
	 * 쌍이아닌 (가 나올 때마다 쇠막대기가 위로 계속 놓인다고 생각
	 * 쌍이아닌 )는 쇠막대기 끝
	 * 
	 * 레이저가 아닌 (가 나올 때마다 현재 위치의 쇠막대기 수 +1 && 쇠막대기 하나가 조각이니 조각 + 1
	 * 레이저가 아닌 )가 나오면 현재 위치의 쇠막대기 수 -1 
	 * 
	 * () 레이저 쏠 때마다 현재 쇠막대기 수만큼 조각이 추가됨 ( 스택에 있는 '('의 갯수가 현재 위치의 쇠막대기의 수 )
	 */
	
	
	/*
	 * 
	 * 
	 * 스택 : (를 만나면 넣어둠
	 * 즉 pop하면 나보다 바로 앞에 있는 (를 알 수 있음
	 * 
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			
			int ans = 0;
			
			char[] line = sc.next().toCharArray();

			//지나가는 문자를 저장해야겠네, 접근은 마지막에 넣었던 것들만 접근 해야겠네( 레이저인지 막대인지 닫는 것으로 판단  )
			//한번 접근된 문자는 다시 접근하지 말아야하네
			//Stack
			
			//사실 이문제 스택을 쓰긴 했지만 (여는 괄호의 갯수만 세도 되는 거임! 
			//스택에선 아이디어성만 가져온거지
			
//			Stack<Character> stack = new Stack<>();
			int cnt=0;
			for(int i=0; i<line.length; i++) {
				//여는(,		레이저 시직인지, 막대의 시작인지
				if(line[i]=='(') {
//					stack.push('(');
					cnt++;
				}
				//닫는) , 	레이저의 시작인지 막대의 끝인지
				//닫을 때 알 수 있으니 여는 것보다 닫는게 중요함, 닫는 걸 보고 판단
				else {
					//바로 앞에게 (였으면 레이저고 아니면 막대기
					
					//레이저
					if(line[i-1]=='(') {
						cnt--;
						ans+=cnt;
//						stack.pop();
//						ans+=stack.size();
					}
					//막대의 끝
					else {
						cnt--;
//						stack.pop();
						//조각이 하나씩 늘어남
						ans++;					
					}					
				}
			}
			
			
			
			System.out.println("#"+t+" "+ans);
			
		}
		
		sc.close();
		
	}
	
}
