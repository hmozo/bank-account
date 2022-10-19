package com.doctorkernel.account.query.api.rest.dto;

import com.doctorkernel.account.common.dto.BaseResponse;
import com.doctorkernel.account.query.domain.entities.BankAccount;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class AccountLookupResponse extends BaseResponse {
    private List<BankAccount> accounts;

    public AccountLookupResponse(String message){
        super(message);
    }
}
