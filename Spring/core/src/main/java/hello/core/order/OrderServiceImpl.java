package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDisCountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository=new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy=new FixDiscountPolicy(); => final은 무조건 값이 할당 되야 된다.
    //private final DiscountPolicy discountPolicy=new RateDisCountPolicy();

    //DIP(의존 관계 역전 원칙)를 위반하지 않도록 인터페이스에만 의존하도록 의존관계 변경
    private  DiscountPolicy discountPolicy; //구체화에 의존하지 않고 추상화인 인터페이스에만 OrderServiceImpl이 의존


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member=memberRepository.findById(memberId);
        int discountPrice=discountPolicy.discount(member,itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
