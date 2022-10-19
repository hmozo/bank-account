package com.doctorkernel.account.query.domain.services;

import com.doctorkernel.account.query.domain.entities.BankAccount;
import com.doctorkernel.account.query.domain.queries.FindAccountByHolderQuery;
import com.doctorkernel.account.query.domain.queries.FindAccountByIdQuery;
import com.doctorkernel.account.query.domain.queries.FindAccountWithBalanceQuery;
import com.doctorkernel.account.query.domain.queries.FindAllAccountsQuery;
import com.doctorkernel.account.query.domain.valueobjects.EqualityType;
import com.doctorkernel.cqrs.core.domain.entities.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountQueryHandler implements QueryHandler{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<BaseEntity> handle(FindAllAccountsQuery query) {
        Iterable<BankAccount> bankAccounts= accountRepository.findAll();
        List<BaseEntity> bankAccountsList= new ArrayList<>();
        bankAccounts.forEach(bankAccountsList::add);
        return bankAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQuery query) {
        var bankAccount= accountRepository.findById(query.getId());
        if (bankAccount.isEmpty()){
            return null;
        }
        List<BaseEntity> bankAccountList= new ArrayList<>();
        bankAccountList.add(bankAccount.get());
        return bankAccountList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery query) {
        var bankAccounts= accountRepository.findByAccountHolder(query.getAccountHolder());
        if (bankAccounts.isEmpty()){
            return null;
        }
        List<BaseEntity> bankAccountList= new ArrayList<>();
        bankAccounts.forEach(bankAccountList::add);

        return bankAccountList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountWithBalanceQuery query) {
        List<BaseEntity> bankAccountList= query.getEqualityType()== EqualityType.GREATER_THAN?
                accountRepository.findByBalanceGreaterThan(query.getBalance())
                :accountRepository.findByBalanceLessThan(query.getBalance());
        return bankAccountList;
    }
}
