package com.doctorkernel.account.cmd.domain.services;

import com.doctorkernel.account.cmd.domain.commands.CloseAccountCommand;
import com.doctorkernel.account.cmd.domain.commands.DepositFundsCommand;
import com.doctorkernel.account.cmd.domain.commands.OpenAccountCommand;
import com.doctorkernel.account.cmd.domain.commands.WithdrawFundsCommand;

public interface CommandHandler {
    void handle(OpenAccountCommand openAccountCommand);
    void handle(DepositFundsCommand depositFundsCommand);
    void handle(WithdrawFundsCommand withdrawFundsCommand);
    void handle(CloseAccountCommand closeAccountCommand);
}
