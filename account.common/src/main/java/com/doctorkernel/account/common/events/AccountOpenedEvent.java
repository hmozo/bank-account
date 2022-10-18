package com.doctorkernel.account.common.events;

import com.doctorkernel.account.common.valueobjects.AccountType;
import com.doctorkernel.cqrs.core.domain.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {
    private String accountHolder;
    private AccountType accountType;
    private Date createDate;
    private double openingBalance;
}
