package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Casting2 {

	//BufferedReader를 이용한 캐스팅
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		float f1=Float.parseFloat(br.readLine());
		float f2=Float.parseFloat(br.readLine());
		
		float f_sum=f1+f2;
		System.out.println("f1+f2="+f_sum); //float출력
	}

}
