package com.example.springboothw_10;

import com.example.springboothw_10.model.Bill;
import com.example.springboothw_10.repositories.BillRepository;
import com.example.springboothw_10.services.BillServices;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpringIntegrationTest {

 @MockBean
    private BillRepository billRepository;

 private final BillServices billServices;

    public SpringIntegrationTest(BillServices billServices) {
        this.billServices = billServices;
    }

    @Test
    void transferServiceTransferBillTest(){

        Bill sender = new Bill();
        sender.setId(1L);
        sender.setBigDecimal(new BigDecimal(3000));

        Bill receiver = new Bill();
        receiver.setId(2L);
        receiver.setBigDecimal(new BigDecimal(3000));

        when(billRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(billRepository.findById(2L)).thenReturn(Optional.of(receiver));

        billServices.billTransferMoney(1L, 2L, new BigDecimal(300));

        verify(billRepository).changeBill(1L, new BigDecimal(2500));
        verify(billRepository).changeBill(2L, new BigDecimal(3500));

    }
}
