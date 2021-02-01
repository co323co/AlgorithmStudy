package hw_20210201;

import java.util.Scanner;

//���� �ǹ�4 1244. ����ġ �Ѱ� ����
public class BJ_1244_switch {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//1�� ����ġ���� ����~!
		int SN = sc.nextInt() + 1;
		boolean[] swh = new boolean[SN];
		for(int i=1; i<SN; i++) {
			int tmp = sc.nextInt();
			swh[i]=(tmp==0)?false:true;
		}
		
		int s = sc.nextInt();	//�л� ��
		
		for(int i=0; i<s; i++) {
		
			int g = sc.nextInt();	//1�̸� ���� 2�� ����
			int num = sc.nextInt();
			
			//�� �κ� �Ǽ� ����...!!
			if(g==1) for(int n=num; n<SN; n+=num) swh[n]=!swh[n];
			
			else if(g==2) {
				int step=1;
				swh[num]=!swh[num];
				
				while(true) {		
					if(num-step<1 || num+step>=SN) break;
					if(swh[num-step]==swh[num+step]) {
						swh[num-step] = !swh[num-step];
						swh[num+step] = !swh[num+step];
						step++;
					}				
					else break;										
				}								
			}			
			
		}
		
		for(int i=1; i<SN; i++) {			
			System.out.print((swh[i]==false)?"0 ":"1 ");
			if(i>=20 && i%20==0)System.out.println();
		}
		sc.close();
	}

}
