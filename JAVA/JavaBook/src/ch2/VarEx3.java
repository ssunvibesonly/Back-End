package ch2;

public class VarEx3 {

	public static void main(String[] args) {
		final int score=100;
		
//		score=200;		
//		score는 상수이므로 값 변경 불가능
		boolean power=true;
		byte b=127; //-128~127
		
		int oct=010; //8진수, 10진수로 8 (0*8^2)+(1*8^1)+(0*8^0)
		int hex=0x10; //16진수, 10진수로 16
		
		long l=10_000_000_000L;
		
		float f=3.14f;
		double d1=3.14;
		double d2=3.14f;
//		System.out.println(oct);
//		System.out.println(hex);
		
		System.out.println(10.);
		System.out.println(.10);
		System.out.println(10f);
		System.out.println(1e3);
		
		char ch='A';// char형에는 문자 1개가 꼭 필요하다. 한개도 없을 수도 없고, 한개를 초과할 수도 없다.
		int i='A'; //65 (ASCII코드)
		
		String str=""; //빈 문자열(empty String)
		String str2="ABCD";
		String str3="123";
		String str4=str2+str3;
		
		System.out.println(str4);
		System.out.println(""+7+7);
		System.out.println(7+7+"");
	}

}
