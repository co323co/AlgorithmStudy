package week1;

import java.util.Scanner;

//백준 실버1 6588 골드바흐의 추측
public class S1_6588 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//소수 구하기, 에라토스테네스의 체
		int arr[] = new int[1000001];
		for(int i=2; i<arr.length; i++) arr[i]=i;

		//2이상의 숫자부터 체크함
		for(int i=2; i*i<arr.length; i++) {	
			//i의 배수들을 거르기
			for(int j=i*i; j<arr.length; j+=i) {
				arr[j]=0;	
			}
		}
		
		
		while(true) {
			
			int num = sc.nextInt();
			if(num==0) return;
			
			for(int i=0; i<arr.length; i++) {
				if(arr[i]==0) continue;
				
				if(arr[num-arr[i]]!=0) {
					System.out.println(num + " = " + i + " + " + (num-arr[i]));
					break;
				}									
			}							
		}
		
	}

}
