package com.eteration.simplebanking.util.base;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@SuperBuilder
@Getter @Setter @NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @PrePersist
    protected void createDate() {
        date = new Date();
    }
}
