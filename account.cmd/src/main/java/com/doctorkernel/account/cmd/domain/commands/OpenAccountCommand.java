package com.doctorkernel.account.cmd.domain.commands;

import com.doctorkernel.account.common.valueobjects.AccountType;
import com.doctorkernel.cqrs.core.domain.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
