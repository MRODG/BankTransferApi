package com.mariosodigie.apps.banktransferapi.repository;

import com.mariosodigie.apps.banktransferapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
