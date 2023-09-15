package com.eteration.simplebanking.util.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class BaseDto {
    private Date date;
}
