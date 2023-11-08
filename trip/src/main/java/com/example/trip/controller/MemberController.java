package com.example.trip.controller;

import com.example.trip.controller.constant.Message;
import com.example.trip.controller.constant.StatusCode;
import com.example.trip.domain.Member;
import com.example.trip.dto.Member.*;
import com.example.trip.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
    public ResponseEntity login(HttpSession session, @RequestBody LoginRequest loginRequest){

        if(memberService.loginMember(loginRequest)) {
            Long memberIndex = memberService.findUserIndexByLoginId(loginRequest.getLoginId());
            UserIndexResponse response = new UserIndexResponse(memberIndex);

            session.setAttribute("memberIndex", memberIndex);

            Message message = new Message(StatusCode.OK, "로그인 성공", response);
            return ResponseEntity.ok(message);
        }
        else {
            Message message = new Message(StatusCode.BAD_REQUEST, "아이디/비밀번호에 해당하는 값이 유효하지 않습니다.");
            return ResponseEntity.ok(message);
        }
    }

    //회원 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteMember(@RequestBody LoginRequest request){
        if(memberService.loginMember(request)){
            memberService.deleteMember(request);
            Message message = new Message(StatusCode.OK, "회원 탈퇴 완료");
            return ResponseEntity.ok(message);
        }
        else {
            Message message = new Message(StatusCode.BAD_REQUEST, "잘못된 아이디 또는 비밀번호 입니다.");
            return ResponseEntity.ok(message);
        }
    }

    // 회원 정보 조회
    @PostMapping("/mypage")
    public ResponseEntity<?> readMember(@RequestBody Long memberIndex){
        Member member = memberService.findByID(memberIndex);
        MyPageResponse response = new MyPageResponse(member.getMemberIndex(),member.getLoginId(),
                member.getNickname(), member.getName(), member.getEmail());

        Message message = new Message(StatusCode.OK, "마이페이지 조회 성공", response);
        return ResponseEntity.ok(message);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){
        session.invalidate();
        Message message = new Message(StatusCode.OK, "로그아웃");
        return ResponseEntity.ok(message);
    }

    //비밀번호 분실
    @PutMapping("/password")
    public ResponseEntity<?> modifyPass(@RequestBody UpdatePasswordRequest request){
        Member member = memberService.findByID(request.getMemberIndex());
        memberService.updateMemberPass(member, request);
        Message message = new Message(StatusCode.OK, "비밀번호 변경 성공");
        return ResponseEntity.ok(message);
    }

}
