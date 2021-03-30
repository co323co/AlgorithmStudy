package week02;

import java.util.Scanner;

//SWEA D3 1234. [S/W 문제해결 기본] 10일차 - 비밀번호 
public class SWEA_D3_1234_Basic_Day10_비밀번호 {

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
