package week07;

//백준 실버2 16113 시그널
import java.util.Scanner;

public class BJ_S2_16113_시그널 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String str = sc.next();
		
		//5행으로 쪼개기
		String arr[] = new String[5];
		int step = N/5;
		for(int i=0; i<5; i++) {
			arr[i] = str.substring(i*step, (i+1)*step);
		}
		
		//위에서 아래(↓)로 -> 읽기
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N/5; i++) {
			for(int j=0; j<5; j++) {
				sb.append(arr[j].charAt(i));
			}
		}
		
		str = sb.toString();
		String[] nums = {
				"######...######", // 0
	            "#####", // 1
	            "#.####.#.####.#", // 2
	            "#.#.##.#.######", // 3
	            "###....#..#####", // 4
	            "###.##.#.##.###", // 5
	            "######.#.##.###", // 6
	            "#....#....#####", // 7
	            "######.#.######", // 8
	            "###.##.#.######" // 9
		};
		
		//공백 제거
		str = str.replace(".....", "");

		//(1을 제외한) 숫자가 있으면 바꿔주기
		for(int i=0; i<nums.length; i++) {
			if(i!=1) str = str.replace(nums[i], i+"");
		}
		//1이 있으면 바꿔주기
		str = str.replace(nums[1], 1+"");
		
		System.out.println(str);
		sc.close();
	}
}
