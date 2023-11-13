package com.example.trip.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter
@Setter
@Data
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "boardIndex")
    private Long boardIndex;

    @ManyToOne
    @JoinColumn(name = "memberIndex")
    private Member member;

    @Column(name = "boardTitle")
    private String boardTitle;

    @Column(name = "boardContent")
    private String boardContent;

    @Column(name = "createTime")
    private LocalDateTime createTime;

}
