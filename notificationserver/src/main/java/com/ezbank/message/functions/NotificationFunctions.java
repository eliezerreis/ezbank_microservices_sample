package com.ezbank.message.functions;

import com.ezbank.message.dto.AccountsMsgDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;

@Configuration
public class NotificationFunctions {

    private static final Logger log = Logger.getLogger(NotificationFunctions.class.getName());

    @Bean
    public Function<AccountsMsgDTO, Message<AccountsMsgDTO>> processEmail() {
        return accountsMsgDto -> {
            log.info("Email was sent {} through JavaMailSender API ..." + accountsMsgDto.email());
            return MessageBuilder.withPayload(accountsMsgDto)
                .setHeader("routing-key", "notifications-events.#")
                .build();
        };
    }

    @Bean
    public Consumer<Message<AccountsMsgDTO>> processSms(){
        return msg -> {
            AccountsMsgDTO accountsMsgDto = msg.getPayload();

            log.info("SMS was sent {} through Twilio API ..." + accountsMsgDto.mobileNumber());
        };
    }
}
