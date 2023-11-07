package com.example.trip.controller;

import com.example.trip.domain.Member;
import com.example.trip.dto.Member.LoginRequest;
import com.example.trip.dto.Member.SignInRequest;
import com.example.trip.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //test
    @GetMapping("/test")
    public ResponseEntity getTest(){
        return ResponseEntity.ok("success");
    }

    // 회원 가입 요청
    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody SignInRequest request) {
        if(memberService.allowedId(request)){
            memberService.saveMember(request);
            return ResponseEntity.ok("회원가입");
        }
        else
            return ResponseEntity.ok("중복된 아이디입니다.");

    }

    //login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        if(memberService.loginMember(loginRequest))
            return "successfully login. hello "+loginRequest.getLoginId();
        else
            return "login failed";
    }

    //회원 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteMember(@RequestBody LoginRequest request){
        if(memberService.loginMember(request)){
            memberService.deleteMember(request);
            return ResponseEntity.ok("탈퇴 완료");
        }
        else
            return ResponseEntity.ok("탈퇴 실패") ;
    }

    // 회원 정보 조회
    @PostMapping("/inform")
    public ResponseEntity<?> readMember(@RequestBody LoginRequest request){
        if(memberService.loginMember(request)){
            Member member = memberService.findByLoginID(request.getLoginId());
            return ResponseEntity.ok(member);
        }
        else{
            return ResponseEntity.ok("잘못된 비밀번호 입니다.");
        }
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        return ResponseEntity.ok("로그아웃");
    }

    //비밀번호 조회
    @PostMapping("/password")
    public ResponseEntity<?> findPass(@RequestHeader String loginId){
        return ResponseEntity.ok(memberService.findMemberPass(loginId));
    }

}
