package com.example.springboothw_10.services;

import com.example.springboothw_10.exception.BillNotFoundException;
import com.example.springboothw_10.model.Bill;
import com.example.springboothw_10.repositories.BillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillServices {

    private final BillRepository billRepository;

    public BillServices(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Transactional
    public void billTransferMoney(Long idSender, Long idReceiver, BigDecimal bigDecimal){
        Bill sender = billRepository.findById(idSender)
                .orElseThrow(() -> new BillNotFoundException());

        Bill receiver = billRepository.findById(idReceiver)
                .orElseThrow(() -> new BillNotFoundException());

        BigDecimal senderNewBill = sender.getBigDecimal().subtract(bigDecimal);
        BigDecimal receiverNewBill = receiver.getBigDecimal().add(bigDecimal);

        billRepository.changeBill(idSender, senderNewBill);
        billRepository.changeBill(idReceiver, senderNewBill);

    }

    public Iterable<Bill> getAllBill(){
        return billRepository.findAll();
    }

    public List<Bill> findBillByName(String name){
        return billRepository.findBillByName(name);
    }
}
