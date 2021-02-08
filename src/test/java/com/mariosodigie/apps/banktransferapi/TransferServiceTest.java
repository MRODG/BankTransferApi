package com.mariosodigie.apps.banktransferapi;

import com.mariosodigie.apps.banktransferapi.model.Account;
import com.mariosodigie.apps.banktransferapi.model.Transaction;
import com.mariosodigie.apps.banktransferapi.service.TransferServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransferServiceTest {

    @TestConfiguration
    static class AccountServiceTestContextConfiguration {
        @Bean
        public TransferServiceImpl accountServiceImplTest() {
            return new TransferServiceImpl();

        }
    }

    @Autowired
    private TransferServiceImpl transferService;

    @Test
    public void completeTransactionTest() {
        Account account1 = new Account(0L, "00361521", new BigDecimal(250));
        Account account2 = new Account(0L, "1836261", new BigDecimal(100));
        transferService.saveAll(List.of(account1, account2));

        Transaction transaction =
                new Transaction(1L,
                        account1.getAccountNumber(),
                        account2.getAccountNumber(),
                        new BigDecimal(100)
                );
        transferService.completeTransaction(transaction);
        assertThat(transferService.findByAccountNumber(account1.getAccountNumber())
                .getBalance())
                .isEqualTo(new BigDecimal(150));
        assertThat(transferService.findByAccountNumber(account2.getAccountNumber())
                .getBalance())
                .isEqualTo(new BigDecimal(200));

    }
}
