package com.doctorkernel.account.cmd.application;

import com.doctorkernel.account.cmd.domain.commands.*;

public interface CommandHandler {
    void handle(OpenAccountCommand openAccountCommand);
    void handle(DepositFundsCommand depositFundsCommand);
    void handle(WithdrawFundsCommand withdrawFundsCommand);
    void handle(CloseAccountCommand closeAccountCommand);
    void handle(RestoreReadDbCommand restoreReadDbCommand);;
}
