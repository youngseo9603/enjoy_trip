package com.example.trip.repository;

import com.example.trip.domain.Member;
import com.example.trip.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findAllByMember(Member member);
    Wish findByWishIndex(Long wishIndex);
}
