package week2;

//프로그래머스 레벨2 2017 카카오코드 본선 단체사진 찍기

/*
 * 순서상관있고 중복X, 순열문제
 */
public class PG_L2_단체사진찍기 {

	static char[] arr = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
	static int ans = 0;
    public static int solution(int n, String[] data) {
    	ans = 0;
        permutation(data, new char[8], 0, new boolean[8]);
        return ans;
    }
	
	public static void main(String[] args) {
		int n = 2;
		String[] data = {"N~F=0", "R~T>2"};
//		String[] data = {"M~C<2", "C~M>1"};
		int answer = solution(n, data);
		System.out.println(answer);
	}

	public static void permutation(String[] data, char[] sel, int k, boolean[] v) {
		if(k==sel.length) {
			if(isOk(sel,data)) ans++; 
//			System.out.println(Arrays.toString(sel));
			return;
		}
		for(int i=0; i<8; i++) {
			if(v[i]) continue;
			sel[k] = arr[i];
			v[i] = true;
			permutation(data, sel, k+1, v);
			v[i] = false;
		}
	}
	
	public static boolean isOk(char[] sel, String[] data) {
		
		for(int i=0; i<data.length; i++) {
			
			char from = data[i].charAt(0);
			char to = data[i].charAt(2);
			char condition = data[i].charAt(3);
			int num = data[i].charAt(4)-'0';
//			System.out.println(from+","+to+","+condition+","+num);
			//캐릭터끼리 몇 칸 떨어져있는지 세기
			int cnt=0;
			boolean start = false;
			for(int j=0; j<8; j++) {
				if(sel[j]==from || sel[j]==to) {
					if(start) break;
					else start = true;
				}
				else if(start) cnt++;
			}
			if(condition=='=' && cnt!=num) return false;
			else if(condition=='<' && cnt>=num) return false;
			else if(condition=='>' && cnt<=num) return false;
		}
		return true;
	}
}
