package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 생성자를 통해 AppConfig에서 MemberRepository를 파라메터로 받아온다.
    // 따라서 MemberServiceImpl은 인터페이스에만 의존하게 되므로 DIP 원칙을 지킨다.
    private final MemberRepository memberRepository;

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
}
