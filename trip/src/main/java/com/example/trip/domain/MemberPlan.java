package com.example.trip.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "memberPlan")
@Getter
@Setter
@Data
@ConstructorBinding
public class MemberPlan {

    @Id
    @GeneratedValue
    @Column(name = "memberPlanIndex")
    private Long memberPlanIndex;

    @OneToMany(mappedBy = "memberPlan")
    private List<Member> members ;

    @OneToMany(mappedBy = "wholePlanIndex")
    private List<WholePlan> wholePlans;

}
