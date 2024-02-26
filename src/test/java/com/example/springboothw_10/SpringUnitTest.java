package com.example.springboothw_10;

import com.example.springboothw_10.model.Bill;
import com.example.springboothw_10.repositories.BillRepository;
import com.example.springboothw_10.services.BillServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SpringUnitTest {

    @Test
    @DisplayName("Test the bigDecimal is transfered from one bill to another if no exception occurs.")
    public void BillTransfwrMoney(){

        BillRepository billRepository = mock(BillRepository.class);
        BillServices billServices = new BillServices(billRepository);

        Bill sender = new Bill();
        sender.setId(1L);
        sender.setBigDecimal(new BigDecimal(3000));

        Bill destination = new Bill();
        destination.setId(2L);
        destination.setBigDecimal(new BigDecimal(3000));

        given(billRepository.findById(sender.getId()))
                .willReturn(Optional.of(sender));

        given(billRepository.findById(destination.getId()))
                .willReturn(Optional.of(destination));

        billServices.billTransferMoney(1L, 2L, new BigDecimal(300));
        verify(billRepository).changeBill(1L, new BigDecimal(2700));
        verify(billRepository).changeBill(2L, new BigDecimal(3300));

    }
}
