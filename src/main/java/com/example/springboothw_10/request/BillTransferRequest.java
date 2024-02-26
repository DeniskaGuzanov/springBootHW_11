package com.example.springboothw_10.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillTransferRequest {

    private Long senderBillId;
    private Long receiverBillId;
    private BigDecimal bigDecimal;
}
