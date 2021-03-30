package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 실버5 16506 CPU
public class S5_16506_CPU {

	static enum Inst{
		ADD,SUB,MOV,AND,OR,NOT,MULT,LSFTL,LSFTR,ASFTR,RL,RR
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			st= new StringTokenizer(br.readLine());
			System.out.println(assembler(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
		}
	}
	
	//3 or 4자리수 이진코드로 바꿔줌
	static String toBinaryCode(String num, int len) {
		StringBuilder tmp= new StringBuilder(Integer.toBinaryString(Integer.parseInt(num))); //2진코드로 바꿔줌
		if(len==3) tmp.insert(0, "000", 0, 3-tmp.length()); //자릿수 맞춰주기
		else if(len==4) tmp.insert(0, "0000", 0, 4-tmp.length()); //자릿수 맞춰주기
		return tmp.toString();
	}
	
	static String assembler(String op, String arg1, String arg2, String arg3) {
		
		StringBuilder sb = new StringBuilder();
		//(bit 0~3)
		StringBuilder tmp = new StringBuilder(op);
		if(op.charAt(op.length()-1)=='C') tmp.setLength(tmp.length()-1);
		String code = Integer.toString(Inst.valueOf(tmp.toString()).ordinal());
		sb.append(toBinaryCode(code, 4));
		//(bit 4)
		if(op.charAt(op.length()-1)!='C') sb.append("0"); //레지스터 rB를 사용함
		else sb.append("1"); //rB대신 상수 #C를 사용함
		//(bit 5)
		sb.append("0"); //사용하지 않는 bit이며, 항상 0이다. 
		//(bit 6~8)
		sb.append(toBinaryCode(arg1,3));
		//bit(9~11)
		sb.append(toBinaryCode(arg2,3));
		
		//bit(12~15)
		if(sb.charAt(4)=='0') {
			sb.append(toBinaryCode(arg3,3)); //rB사용
			sb.append("0"); //bit(15)
		} 
		else sb.append(toBinaryCode(arg3, 4));  //#C사용
			
		return sb.toString();
	}
}
