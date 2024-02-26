package com.example.springboothw_10.repositories;

import com.example.springboothw_10.model.Bill;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Long> {

    @Query("SELECT * FROM bill WHERE name = :name ")
    List<Bill> findBillByName(String name);

    @Modifying
    @Query("UPDATE bill SET bigDecimal = :bigDecimal WHERE id = :id")
    void changeBill(Long id, BigDecimal bigDecimal);
}
