package com.doctorkernel.account.cmd.domain.commands;

import com.doctorkernel.cqrs.core.domain.commands.BaseCommand;
import lombok.Data;

@Data
public class CloseAccountCommand extends BaseCommand {
    public CloseAccountCommand(String id){
        super(id);
    }
}
