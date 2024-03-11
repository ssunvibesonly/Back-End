package hello.core.member;

public interface MemberRepository {
    // 회원가입
    void save(Member member);
    // id로 회원 조회
    Member findById(Long memberId);
}
