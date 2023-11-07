package com.example.trip.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name = "loginId")
    private Member member;

    @Column(name = "boardId")
    private String boardId;

    @Column(name = "boardTitle")
    private String boardTitle;

    @Column(name = "boardContent")
    private Long boardContent;

}
