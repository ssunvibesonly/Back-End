package basic;

public class Casting1 {

	//형변환
	//float형이든, double형이든 형변환 시 반올림 되는게 아니라 소수점 자리는 "버림"
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float f=1.7f; //float형은 뒤에 f붙여줘야 한다.
		double d=1.9;
		
		int a_f=(int)f;
		int a_d=(int)d;
		
		System.out.println(a_f);
		System.out.println(a_d);
		

	}

}
