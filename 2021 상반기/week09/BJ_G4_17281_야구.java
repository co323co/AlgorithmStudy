package week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 
 * 9 : 9, 두 팀. 번갈아가며 공격, 수비
 * 1이닝 : 공격 vs 수비
 * 		1이닝에서 3아웃이 발생하면 이닝 종료. 공,수 바꿈 (다음이닝때 쓸 공수)
 * 		경기중에 타순 변경 불가능
 * 		모든 애들 전부 다 쳤는데 3아웃 발생안했으면 이닝 안끝나고 다음타자가 다시 타석에섬.
 * 		공격 팀 애들, 1루, 2루, 3루 거쳐서 홈에 도착하면 +1점. (루 위에있음 주자)
 * 총 N이닝 게임
 * 
 * 4번타자는 무조건 1번선수다. 
 * 선수들이 각 이닝별 낼 결과가 미리 정해져있을 때, 어떤 타순으로 쳐야 가장 고득점일까?
 * 8명의 순열
 * 
 * 재귀를 돌린다. 재귀는 (몇회차이닝인지n, 타자 ,int[] base, 점수, 아웃수)
 * 	다음 이닝 갈때마다 주자는 초기화
 * 
 */
public class BJ_G4_17281_야구 {
	//0~9번 선수별 0~N번 이닝별 스코어
	static int[][] rs_innings;
	static int ans;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		rs_innings = new int[N][9]; //1:안타, 2:2루타, 3:3루타, 4:홈런, 0:아웃
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) rs_innings[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[] sel = new int[9];
		//4번 타자는 1번 선수로 고정되어있음
		sel[3] = 0;
		permutaion(0, sel, new boolean[9]);
		System.out.println(ans);
	}
	
	//s_hitter : 이닝 시작시 타자 번호
	static void innings(int n, int s_hitter, int[] base, int out, int score, int[] order) {
		
		//이닝 끝까지 다 가봄
		if(n == rs_innings.length) {
			ans = Math.max(ans, score);
			return;
		}
		
		int hitter = s_hitter;
		while(true) {
				
			//현재 공격할 선수가 (order[타자번호]) 현재 이닝에서 낼 결과 
			int res = rs_innings[n][order[hitter]];
			if(res==0) {
				out++;
				if(out==3) {
					//다음 이닝 넘어가니 주자 전부 들어감
					for(int i=0; i<9; i++) base[i]=0;
					innings(n+1, (hitter+1)%9, base, 0, score, order);
					return;
				}
			}
			
			//1~3이면 그만큼 전진
			else if(res>0 && res<4) {
				for(int i=0; i<base.length; i++) {
					//타자, 혹은 1~3루에 있는 사람이면 res만큼 전진
					if(i==hitter||base[i]>0)  base[i]+=res;
					//홈에 들어왔으면 1점 득점, 타자는 루에서 빠져나옴
					if(base[i]>3) {
						score++;
						base[i]=0;
					}
				}
			}
			//홈런
			else if(res==4) {
				for(int j=0; j<base.length; j++) {
					if(j==hitter||base[j]>0) {
						score++;
						base[j]=0;
					}
				}
			}
			//다음 타자
			hitter = (hitter+1)%9;
		}
	}
	
	//0~8번 타자에 어떤 선수가 설 지를 순열로 뽑는다. //ex sel[3] = 0 이면 0번 선수가 4(==3+1)번째 타자임 
	static void permutaion(int idx, int[] sel, boolean[] v){
		
		if(idx == sel.length) {
			innings(0, 0, new int[9], 0, 0, sel);
			return;
		}
		
		//4번 타자는 고를 필요 없음
		if(idx == 3) {
			permutaion(idx+1, sel, v);
			return;
		}
		
		for(int i=1; i<sel.length; i++) {
			if(v[i]) continue;
			
			v[i] = true;
			sel[idx] = i;
			permutaion(idx+1, sel, v);
			v[i] = false;
		}
	}
}
