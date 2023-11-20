package com.example.trip.repository;

import com.example.trip.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByLoginId(String LoginId);
    Member findByMemberIndex(Long memberIndex);



}
