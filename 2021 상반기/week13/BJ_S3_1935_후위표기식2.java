package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//백준 실버3 1935 후위 표기식2
public class BJ_S3_1935_후위표기식2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int arr[] = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		String op = "+-*/";
		Stack<Double> s = new Stack<>();
		for(char ch : str.toCharArray()) {
			if(op.contains(ch+"")) { //연산자면
				double op2 = s.pop();
				double op1 = s.pop();
				if(ch=='+') s.push(op1+op2);
				else if(ch=='-') s.push(op1-op2);
				else if(ch=='*') s.push(op1*op2);
				else if(ch=='/') s.push(op1/op2);
			}
			else { //피연산자면
				s.push((double) arr[ch-'A']);
			}
			
		}
		System.out.printf("%.2f",s.pop());
	}

}
