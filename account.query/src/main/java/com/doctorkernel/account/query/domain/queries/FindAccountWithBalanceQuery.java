package com.doctorkernel.account.query.domain.queries;

import com.doctorkernel.account.query.domain.valueobjects.EqualityType;
import com.doctorkernel.cqrs.core.domain.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
    private EqualityType equalityType;
    private double balance;
}
