package com.doctorkernel.account.cmd.application;

import com.doctorkernel.account.cmd.domain.commands.*;
import com.doctorkernel.account.cmd.domain.model.AccountAggregate;
import com.doctorkernel.cqrs.core.domain.services.EventSourcingHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountCommandHandler implements CommandHandler{

    private final EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    @Override
    public void handle(OpenAccountCommand openAccountCommand) {
        var aggregate= new AccountAggregate(openAccountCommand);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DepositFundsCommand depositFundsCommand) {
        var aggregate= eventSourcingHandler.getById(depositFundsCommand.getId());
        aggregate.depositFunds(depositFundsCommand.getAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(WithdrawFundsCommand withdrawFundsCommand) {
        var aggregate= eventSourcingHandler.getById(withdrawFundsCommand.getId());
        if (withdrawFundsCommand.getAmount() > aggregate.getBalance()){
            throw new IllegalStateException("Withdrawal declined, insufficient funds");
        }
        aggregate.withdrawFunds(withdrawFundsCommand.getAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(CloseAccountCommand closeAccountCommand) {
        var aggregate= eventSourcingHandler.getById(closeAccountCommand.getId());
        aggregate.closeAccount();
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(RestoreReadDbCommand restoreReadDbCommand) {
        eventSourcingHandler.republishEvents();
    }
}
