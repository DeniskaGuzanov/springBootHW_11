package com.example.springboothw_10;


import com.example.springboothw_10.exception.BillNotFoundException;
import com.example.springboothw_10.model.Bill;
import com.example.springboothw_10.repositories.BillRepository;
import com.example.springboothw_10.services.BillServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class SpringAnnotationsUnitTest {

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillServices billServices;

    @Test
    public void transferMoneyBill(){

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

    @Test
    public void transferMoneyDestination(){

        Bill sender = new Bill();
        sender.setId(1L);
        sender.setBigDecimal(new BigDecimal(3000));

        given(billRepository.findById(1L))
                .willReturn(Optional.of(sender));

        given(billRepository.findById(2L))
                .willReturn(Optional.empty());

        assertThrows(BillNotFoundException.class, () -> billServices.billTransferMoney(1L, 2L,
                new BigDecimal(300)));

        verify(billRepository, never())
                .changeBill(anyLong(), any());
    }
}
