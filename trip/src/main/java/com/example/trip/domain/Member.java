package com.example.trip.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "members")
@Getter @Setter @Data
@ConstructorBinding
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "memberIndex")
    private Long memberIndex;

    @OneToMany(mappedBy = "member")
    private List<Board> boards;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<WholePlan> wholePlans;

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "member")
    private List<Wish> wish;
}
