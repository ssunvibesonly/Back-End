package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    //엔티티를 직접 호출하게 되면 엔티티 내부에 있는 모든 정보들이 외부에 노출된다.
    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();
    }

    //API 스펙을 위한 DTO를 만들면 엔티티가 변경이 되도 API 스펙이 변하지 않는 장점이 있다. + 유연성
    @GetMapping("/api/v2/members")
    public Result memberV2(){
        
        //List<Member>를 List<MemberDto>로 변환
        List<Member> findMembers=memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect);

    }
    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        // List collection으로 바로 내버리면 json타입으로 나가서 유연성이 확 떨어진다.
        private T data;
    }

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    
    //엔티티는 굉장히 여러 곳에서 사용되므로 바뀔 확률이 높기에 API 스펙을 위한 별도의 DTO 생성 필요
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMember2(@RequestBody @Valid CreateMemberRequest request){

        Member member=new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMember2(@PathVariable("id") Long id,
                                              @RequestBody @Valid UpdateMemberRequest request){

        memberService.update(id,request.getName());

        //업데이트가 되고, 트랜잭션이 다 끝난 후 변경이 잘 되었는지 확인하기 위해 findMember로 가져옴
        //그리고 response를 만들어서 responseDTO 반환
        Member findMember=memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(),findMember.getName());
    }

    @Data
    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }

    @Data
    static class CreateMemberRequest{
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
