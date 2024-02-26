package com.example.springboothw_10.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class Bill {

    @Id
    private Long id;

    private String name;
    private BigDecimal bigDecimal;
}
