package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount=1000; //1000원 할인
    @Override
    public int discount(Member member, int price) {
        //enum 타입은 동치 비교할 때 ==을 사용한다.
        if(member.getGrade()== Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }

    }
}
