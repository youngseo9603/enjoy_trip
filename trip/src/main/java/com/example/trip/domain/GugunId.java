package com.example.trip.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class GugunId implements Serializable {
    private static final long serialVersionUID = -5573989186663653179L;
    @Column(name = "gugun_code", nullable = false)
    private Integer gugunCode;

    @Column(name = "sido_code", nullable = false)
    private Integer sidoCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GugunId entity = (GugunId) o;
        return Objects.equals(this.gugunCode, entity.gugunCode) &&
                Objects.equals(this.sidoCode, entity.sidoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gugunCode, sidoCode);
    }

}