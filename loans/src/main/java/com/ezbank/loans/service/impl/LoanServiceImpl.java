package com.ezbank.loans.service.impl;

import com.ezbank.loans.constant.LoanType;
import com.ezbank.loans.dto.LoanDTO;
import com.ezbank.loans.entity.Loan;
import com.ezbank.loans.exception.LoanExistsException;
import com.ezbank.loans.exception.ResourceNotFoundException;
import com.ezbank.loans.mapper.LoanMapper;
import com.ezbank.loans.repository.LoanDAO;
import com.ezbank.loans.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanMapper loanMapper;
    private LoanDAO loanDAO;

    @Override
    public LoanDTO create(String mobileNumber) {
        Optional<Loan> optionalLoan= loanDAO.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent()){
            throw new LoanExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        Loan loan = createDefaultLoan(mobileNumber);
        loan = loanDAO.save(loan);

        return loanMapper.toDTO(loan);
    }

    private Loan createDefaultLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanType.HOME);
        newLoan.setTotalLoan(1000);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(2000);
        return newLoan;
    }


    @Override
    public LoanDTO fetch(String mobileNumber) {
        Loan loan = loanDAO.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Loan doesn't exist on the database.")
        );
        return loanMapper.toDTO(loan);
    }

    @Override
    public LoanDTO update(LoanDTO loanDTO) {
        Loan loan = loanDAO.findByLoanNumber(loanDTO.getLoanNumber()).orElseThrow(
            () -> new ResourceNotFoundException("LOan doesn't exist on the database."));
        loanMapper.updateFromDTO(loanDTO, loan);
        loanDAO.save(loan);

        return loanMapper.toDTO(loan);
    }

    @Override
    public void delete(String mobileNumber) {
        Loan cards = loanDAO.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Loan doesn't exist on the database.")
        );
        loanDAO.deleteById(cards.getLoanId());
    }

}