package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDisCountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; // => final이 있으면 기본으로 할당되든, 생성자로 할당되는 무조건 할당되야 한다.
    //private final DiscountPolicy discountPolicy=new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy=new RateDisCountPolicy();

    //DIP(의존 관계 역전 원칙)를 위반하지 않도록 인터페이스에만 의존하도록 의존관계 변경
    private  DiscountPolicy discountPolicy; //구체화에 의존하지 않고 추상화인 인터페이스에만 OrderServiceImpl이 의존

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member=memberRepository.findById(memberId);
        int discountPrice=discountPolicy.discount(member,itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    ///테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
