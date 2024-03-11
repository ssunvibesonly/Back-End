package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 인터페이스로만 생성하면 당연히 NullPointException이 발생하므로 꼭 구현체로 생성해주어야 한다.
    private final MemberRepository memberRepository=new MemoryMemberRepository();
    @Override
    public void join(Member member) {
         memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
