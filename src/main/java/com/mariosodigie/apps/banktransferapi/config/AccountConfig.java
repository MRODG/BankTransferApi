package com.mariosodigie.apps.banktransferapi.config;

import com.mariosodigie.apps.banktransferapi.model.Account;
import com.mariosodigie.apps.banktransferapi.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository){
        return args -> {
            Account account1 = new Account(
                0L, "00361521", new BigDecimal(100)
            );

            Account account2 = new Account(
                    0L, "1836261", new BigDecimal(55)
            );

            accountRepository.saveAll(List.of(account1, account2));
        };
    }
}
