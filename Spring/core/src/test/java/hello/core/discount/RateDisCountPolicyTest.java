package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDisCountPolicyTest {
    RateDisCountPolicy disCountPolicy=new RateDisCountPolicy();

    @Test
    @DisplayName("VIP 회원은 10% 할인이 적용되어야 한다.")
    void vip_o(){
        //given
        Member member=new Member(1L,"memberVIP", Grade.VIP);
        //when
        int discount=disCountPolicy.discount(member,10000);
        //then
        //Assertions (core~~) : staitc import 하는게 좋다.
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x(){
        //given
        Member member=new Member(2L,"memberBASIC",Grade.BASIC);

        //when
        int discount= disCountPolicy.discount(member,10000);

        //then
        assertThat(discount).isEqualTo(0);
    }
}