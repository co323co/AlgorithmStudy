package week02;

import java.util.Scanner;

//SWEA D3 7272. 안경이 없어!
public class SWEA_D3_7272_안경이없어 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			String str1 = sc.next();
			String str2 = sc.next();
			
			boolean isSame = true;
			
			if(str1.length()!=str2.length()) isSame=false;
			
			else {
				for(int i=0; i<str1.length(); i++) {
					if(!equals(str1.charAt(i), str2.charAt(i))){
						isSame=false;
						break;
					}
				}
			}		
			if(isSame) System.out.println("#" + tc + " SAME");
			else System.out.println("#" + tc + " DIFF");		
		}
		sc.close();
	}

	public static boolean equals(char c1, char c2) {
		String[] hole = {"CEFGHIJKLMNSTUVWXYZ","ADOPQR", "B"};
		
		for(String h : hole) 
			if(h.contains(c1+"") && h.contains(c2+"")) return true;
		return false;				
	}
}
