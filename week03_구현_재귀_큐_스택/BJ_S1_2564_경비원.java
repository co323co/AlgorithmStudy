package week03;

import java.util.Scanner;

//백준 S1 2564 경비원
public class BJ_S1_2564_경비원 {

	static int N,M;
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();

		int[] dist = new int[sc.nextInt()];
		int start; //동근이 //동근이가 0,0기준으로 얼마나 떨어져있는지!
		
		for(int i=0; i<dist.length;i++) dist[i]=calDist(sc.nextInt(), sc.nextInt());
		start = calDist(sc.nextInt(),sc.nextInt());
		
		int ans=0;
		
		for(int i=0; i<dist.length; i++) {
			
			//시계방향으로 갔을 경우 동근->상점 걸리는 거리
			int right = Math.abs(dist[i]-start);
			//반시계방향일 경우 (전체길이 - 시계방향길이 == 반시계방향 길이)
			int left = (M*2+N*2) - right;
			ans+=Math.min(right, left);
		}
		System.out.println(ans);
		sc.close();
	}

	//0,0부터 해당 위치까지의 거리(시계방향으로)
	static int calDist(int d, int p) {
		
		//상하좌우
		if(d==1) return p;
		else if(d==2) return N+M+(N-p);
		else if(d==3) return N+M+N+(M-p);
		else if(d==4) return N+p;
		return -1;
		
	}
}

