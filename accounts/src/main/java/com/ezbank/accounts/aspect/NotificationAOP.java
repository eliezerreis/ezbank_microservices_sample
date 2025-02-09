package com.ezbank.accounts.aspect;


import com.ezbank.accounts.dto.AccountsMsgDTO;
import com.ezbank.accounts.dto.CustomerDTO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotificationAOP {
    Logger logger = LoggerFactory.getLogger(NotificationAOP.class);

    private final StreamBridge streamBridge;

    @Autowired
    public NotificationAOP(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Pointcut("execution(* com.ezbank.accounts.service.impl.CustomerServiceImpl.create(..))")
    public void createCustomer() {}

    @AfterReturning(value = "createCustomer()", returning = "result")
    public void afterCreateCustomer(CustomerDTO result) {
        AccountsMsgDTO  account = new AccountsMsgDTO(
            result.getAccount().getAccountNumber(),
            result.getName(),
            result.getEmail(),
            result.getMobileNumber());

        streamBridge.send("welcome-email", account);
        streamBridge.send("welcome-sms", account);
        logger.info("Customer notifications sent ...");
    }

}
