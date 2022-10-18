package com.doctorkernel.account.query.domain.repositories;

import com.doctorkernel.account.query.domain.entities.BankAccount;
import com.doctorkernel.cqrs.core.domain.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    void save(BankAccount bankAccount);
    Optional<BankAccount> findById(String id);
    void deleteById(String id);
    Optional<BankAccount> findByAccountHolder(String accountHolder);
    List<BaseEntity> findByBalanceLessThan(double balance);
}
