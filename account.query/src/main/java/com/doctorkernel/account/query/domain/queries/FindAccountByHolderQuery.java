package com.doctorkernel.account.query.domain.queries;

import com.doctorkernel.cqrs.core.domain.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByHolderQuery extends BaseQuery {
    private String accountHolder;
}