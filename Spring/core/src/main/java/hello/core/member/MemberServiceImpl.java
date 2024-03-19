package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class MemberServiceImpl implements MemberService{

    // 생성자를 통해 AppConfig에서 MemberRepository를 파라메터로 받아온다.
    // 따라서 MemberServiceImpl은 인터페이스에만 의존하게 되므로 DIP 원칙을 지킨다.
    private final MemberRepository memberRepository;
    //마치 ac.getBean(MemberRepository.class) 처럼 동작한다고 이해!
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
         memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    ///테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
