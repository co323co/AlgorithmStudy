package week04;

import java.util.Scanner;

//백준 실버5 2941 크로아티아 알파벳
public class BJ_S5_2941_크로아티아알파벳 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		String[] cros = {"c=","c-", "dz=", "d-", "lj", "nj", "s=", "z="};
		
		//크로아티아 알파벳을 숫자로 치환
		for(int i=0; i< cros.length; i++) {
			str = str.replace(cros[i], i+"");
		}
		
		System.out.println(str.length());
		sc.close();
	}
}
