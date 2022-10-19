package com.doctorkernel.account.query.domain.services;

import com.doctorkernel.account.query.domain.queries.FindAccountByHolderQuery;
import com.doctorkernel.account.query.domain.queries.FindAccountByIdQuery;
import com.doctorkernel.account.query.domain.queries.FindAccountWithBalanceQuery;
import com.doctorkernel.account.query.domain.queries.FindAllAccountsQuery;
import com.doctorkernel.cqrs.core.domain.entities.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllAccountsQuery query);
    List<BaseEntity> handle(FindAccountByIdQuery query);
    List<BaseEntity> handle(FindAccountByHolderQuery query);
    List<BaseEntity> handle(FindAccountWithBalanceQuery query);
}
