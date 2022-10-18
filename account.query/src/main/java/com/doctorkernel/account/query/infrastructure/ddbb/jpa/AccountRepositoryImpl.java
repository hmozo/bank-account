package com.doctorkernel.account.query.infrastructure.ddbb.jpa;

import com.doctorkernel.account.query.domain.entities.BankAccount;
import com.doctorkernel.account.query.domain.repositories.AccountRepository;
import com.doctorkernel.cqrs.core.domain.entities.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private AccountJPARepository accountJPARepository;

    @Override
    public void save(BankAccount bankAccount) {
        accountJPARepository.save(bankAccount);
    }

    @Override
    public Optional<BankAccount> findById(String id) {
        return accountJPARepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        accountJPARepository.deleteById(id);
    }

    @Override
    public Optional<BankAccount> findByAccountHolder(String accountHolder) {
        return accountJPARepository.findByAccountHolder(accountHolder);
    }

    @Override
    public List<BaseEntity> findByBalanceLessThan(double balance) {
        return accountJPARepository.findByBalanceLessThan(balance);
    }
}
