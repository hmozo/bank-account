package com.doctorkernel.account.query.service;

import com.doctorkernel.account.common.events.AccountClosedEvent;
import com.doctorkernel.account.common.events.AccountOpenedEvent;
import com.doctorkernel.account.common.events.FundsDepositedEvent;
import com.doctorkernel.account.common.events.FundsWithdrawnEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
