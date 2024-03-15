package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDisCountPolicy implements  DiscountPolicy{

    //VIP 등급인 경우 금액에 따라 10% 할인해준다.
    private int percent=10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){
            return price * percent/100;
        }else{
            return 0;
        }
    }
}
