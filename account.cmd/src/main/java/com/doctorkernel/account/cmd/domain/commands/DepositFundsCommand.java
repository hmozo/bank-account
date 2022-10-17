package com.doctorkernel.account.cmd.domain.commands;

import com.doctorkernel.cqrs.core.domain.commands.BaseCommand;
import lombok.Data;

@Data
public class DepositFundsCommand extends BaseCommand {
    private double amount;
}
