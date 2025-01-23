package com.ezbank.loans.mapper;

import com.ezbank.loans.dto.LoanDTO;
import com.ezbank.loans.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    void updateFromDTO(LoanDTO loanDTO, @MappingTarget Loan loan);

    LoanDTO toDTO(Loan loan);

    Loan toEntity(LoanDTO loanDTO);
}
