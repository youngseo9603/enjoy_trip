package com.example.trip.controller;

import com.example.trip.controller.constant.Message;
import com.example.trip.controller.constant.StatusCode;
import com.example.trip.dto.Wish.WishRequest;
import com.example.trip.dto.Wish.WishResponse;
import com.example.trip.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @GetMapping
    public ResponseEntity<?> getWishList(@RequestParam Long memberIndex){
        List<WishResponse> responses = wishService.readWishList(memberIndex);
        Message message = new Message(StatusCode.OK, "위시 리스트 조회 성공", responses);
        return ResponseEntity.ok(message);
    }

    @PostMapping
    public ResponseEntity<?> postWishList(@RequestParam Long memberIndex, @RequestBody WishRequest requests){
        wishService.createWishList(memberIndex, requests);
        Message message = new Message(StatusCode.OK, "위시 리스트 생성 성공");
        return ResponseEntity.ok(message);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteWish(@RequestParam Long memberIndex, @RequestBody Long wishIndex){
        wishService.deleteWish(memberIndex, wishIndex);
        Message message = new Message(StatusCode.OK, "위시 리스트 생성 성공");
        return ResponseEntity.ok(message);
    }


}
