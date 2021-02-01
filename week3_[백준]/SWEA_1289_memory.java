package hw_20210201;

import java.util.Arrays;
import java.util.Scanner;

//SWEA D3 1289. 원재의 메모리 복구하기
public class SWEA_1289_memory {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int res = 0;
			
			String str = sc.next();
			char[] ch = new char[str.length()];
			Arrays.fill(ch, '0');

			for(int i=0; i<ch.length; i++) {
				if(str.charAt(i)!=ch[i]) {
					res++;
					for(int j=i; j<ch.length; j++) ch[j] = str.charAt(i);
				}			
			}					
			System.out.println("#"+test_case+" "+res);		
		}
		sc.close();
	}
}