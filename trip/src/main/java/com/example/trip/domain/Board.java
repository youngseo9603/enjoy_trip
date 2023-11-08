package com.example.trip.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "userIndex")
    private Member member;

    @Column(name = "boardTitle")
    private String boardTitle;

    @Column(name = "boardContent")
    private Long boardContent;

    @Column(name = "createTime")
    private LocalDateTime createTime;

}
