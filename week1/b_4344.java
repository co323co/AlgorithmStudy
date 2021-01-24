package week1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class b_4344 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("week1/b_4344.txt"));
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=0; t<T; t++) {
			
			int n = sc.nextInt();
			int[] arr = new int[n];
			int cnt = 0;
			
			int sum = 0;
			float avg;
			
			for (int i=0; i<n; i++) {
				arr[i]=sc.nextInt();
				sum += arr[i];
			}
			
			avg = sum/(float)n;
			
			for (int i=0; i<n; i++) if(arr[i]>avg) cnt++;
			System.out.printf("%.3f%%\n", cnt/(float)n*100);
			
		}

		sc.close();
	}

}
