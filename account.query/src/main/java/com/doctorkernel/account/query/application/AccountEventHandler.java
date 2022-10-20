package com.doctorkernel.account.query.application;

import com.doctorkernel.account.common.events.AccountClosedEvent;
import com.doctorkernel.account.common.events.AccountOpenedEvent;
import com.doctorkernel.account.common.events.FundsDepositedEvent;
import com.doctorkernel.account.common.events.FundsWithdrawnEvent;
import com.doctorkernel.account.query.domain.entities.BankAccount;
import com.doctorkernel.account.query.domain.services.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountEventHandler implements EventHandler {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void on(AccountOpenedEvent event) {
        var bankAccount= BankAccount.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(new Date())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance()).build();
        accountRepository.save(bankAccount);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        var bankAccount= accountRepository.findById(event.getId());
        if(bankAccount.isEmpty()){
            return;
        }
        var currentBalance= bankAccount.get().getBalance();
        var latestBalance= currentBalance + event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(FundsWithdrawnEvent event) {
        var bankAccount= accountRepository.findById(event.getId());
        if(bankAccount.isEmpty()){
            return;
        }
        var currentBalance= bankAccount.get().getBalance();
        var latestBalance= currentBalance - event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
