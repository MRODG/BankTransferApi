package com.mariosodigie.apps.banktransferapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long transactionId;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;

}
