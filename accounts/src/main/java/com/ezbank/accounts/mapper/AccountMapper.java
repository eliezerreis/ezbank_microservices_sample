package com.ezbank.accounts.mapper;

import com.ezbank.accounts.dto.AccountDTO;
import com.ezbank.accounts.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    void updateFromDTO(AccountDTO accountDTO, @MappingTarget Account account);

    AccountDTO toDTO(Account account);

    Account toEntity(AccountDTO accountDTO);
}
