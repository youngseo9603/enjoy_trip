package com.example.trip.service;

import com.example.trip.domain.Member;
import com.example.trip.domain.Wish;
import com.example.trip.dto.Wish.WishRequest;
import com.example.trip.dto.Wish.WishResponse;
import com.example.trip.repository.MemberRepository;
import com.example.trip.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WishService {

    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;

    public List<WishResponse> readWishList(Long memberIndex){
        List<Wish> wishes = wishRepository.findAllByMember(memberRepository.findByMemberIndex(memberIndex));
        List<WishResponse> response = new ArrayList<>();
        for(Wish wish : wishes){
            WishResponse w = WishResponse.builder()
                    .wishIndex(wish.getWishIndex())
                    .placeName(wish.getPlaceName())
                    .category(wish.getCategory())
                    .address(wish.getAddress())
                    .build();
            response.add(w);
        }
        return response;
    }

    public void createWishList(Long memberIndex, WishRequest request){
        Member member = memberRepository.findByMemberIndex(memberIndex);
        Wish wish = Wish.builder()
                .member(member)
                .placeName(request.getPlaceName())
                .category(request.getCategory())
                .address(request.getAddress())
                .build();
        wishRepository.save(wish);
    }

}
