package com.doctorkernel.account.query.infrastructure.ddbb.jpa;

import com.doctorkernel.account.query.domain.entities.BankAccount;
import com.doctorkernel.cqrs.core.domain.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountJPARepository extends JpaRepository<BankAccount, String> {
    Optional<BankAccount> findByAccountHolder(String accountHolder);
    List<BaseEntity> findByBalanceLessThan(double balance);
}
