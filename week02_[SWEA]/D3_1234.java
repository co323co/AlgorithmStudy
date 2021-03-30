package week2;

import java.util.Scanner;

//SWEA D3 [S/W 문제해결 기본] 10일차 - 비밀번호 
public class D3_1234 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		sc.nextInt();
		
		StringBuffer str = new StringBuffer(sc.next());
		
		for(int t=1; t<=10; t++) {
			while(true) {
				boolean flag = false;
				
				for(int i=0; i<str.length()-1;i++) {
					if(str.charAt(i)==str.charAt(i+1)) {
						
						str.delete(i, i+2);
						flag = true;
					}				
				}
				
				if(flag==false) break;						
			}			
			System.out.println("#"+t+" "+str);			
		}		
		sc.close();
	}
}
