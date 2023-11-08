package com.example.trip.controller;

import com.example.trip.controller.constant.Message;
import com.example.trip.controller.constant.StatusCode;
import com.example.trip.domain.Member;
import com.example.trip.dto.Member.LoginRequest;
import com.example.trip.dto.Member.SignInRequest;
import com.example.trip.dto.Member.UserIndexResponse;
import com.example.trip.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //test
    @GetMapping("/test")
    public ResponseEntity getTest(){
        Message message = new Message(StatusCode.OK, "test success");
        return ResponseEntity.ok(message);
    }

    // 회원 가입 요청
    @PostMapping("/join")
    public ResponseEntity<?> signUp(@RequestBody SignInRequest request) {
        if(memberService.allowedId(request)){
            memberService.saveMember(request);
            Long memberIndex = memberService.findUserIndexByLoginId(request.getLoginId());
            UserIndexResponse response = new UserIndexResponse(memberIndex);
            Message message = new Message(StatusCode.OK, "회원 가입 성공", response);
            return ResponseEntity.ok(message);
        }
        else {
            Message message = new Message(StatusCode.BAD_REQUEST, "아이디가 중복되었습니다.");
            return ResponseEntity.ok(message);
        }

    }

    //login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        if(memberService.loginMember(loginRequest)) {
            Long memberIndex = memberService.findUserIndexByLoginId(loginRequest.getLoginId());
            UserIndexResponse response = new UserIndexResponse(memberIndex);
            Message message = new Message(StatusCode.OK, "로그인 성공", response);
            return "successfully login. hello " + loginRequest.getLoginId();
        }
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
