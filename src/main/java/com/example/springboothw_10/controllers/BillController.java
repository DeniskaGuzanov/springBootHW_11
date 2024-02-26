package com.example.springboothw_10.controllers;

import com.example.springboothw_10.model.Bill;
import com.example.springboothw_10.request.BillTransferRequest;
import com.example.springboothw_10.services.BillServices;
import io.micrometer.core.instrument.Metrics;
import io.prometheus.client.Counter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class BillController {

    private final BillServices billServices;
    //private final Counter requestCounter = (Counter) Metrics.counter("request_count");

    @PostMapping("/billTransfer")
    public void BillTransferMoney(@RequestBody BillTransferRequest request){

        //requestCounter.inc();
        billServices.billTransferMoney(
                request.getSenderBillId(),
                request.getReceiverBillId(),
                request.getBigDecimal()
        );
    }

    @GetMapping("/bill")
    public Iterable<Bill> getAllBill(@RequestBody(required = false)
                                     String name){
        if (name == null){
            return billServices.getAllBill();
        } else {
            return billServices.findBillByName(name);
        }
    }
}
