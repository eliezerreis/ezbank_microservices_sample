package com.ezbank.accounts.mapper;

import com.ezbank.accounts.dto.CustomerDTO;
import com.ezbank.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface CustomerMapper {
    void updateFromDTO(CustomerDTO customerDTO, @MappingTarget Customer customer);

    CustomerDTO toDTO(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);
}
