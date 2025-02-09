package com.ezbank.accounts.notifications;

import com.ezbank.accounts.dto.AccountsMsgDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.logging.Logger;

@Configuration
public class ConfirmationFunctions {

    private static final Logger log = Logger.getLogger(ConfirmationFunctions.class.getName());

    @Bean
    public Consumer<AccountsMsgDTO> processEmail() {
        return accountsMsgDTO -> {
            log.info("Email confirmation sent to {}." + accountsMsgDTO.email());
        };
    }
}
