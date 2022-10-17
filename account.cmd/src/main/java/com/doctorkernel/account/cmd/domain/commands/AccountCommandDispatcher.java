package com.doctorkernel.account.cmd.domain.commands;

import com.doctorkernel.cqrs.core.domain.commands.BaseCommand;
import com.doctorkernel.cqrs.core.domain.commands.CommandHandlerMethod;
import com.doctorkernel.cqrs.core.domain.commands.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {
    private final Map<Class<?extends BaseCommand>, List<CommandHandlerMethod>> routes= new HashMap<>();

    @Override
    public void registerHandler(Class type, CommandHandlerMethod handler) {
        var handlers= routes.computeIfAbsent(type, c->new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        var handlers= routes.get(command.getClass());
        if(handlers==null && handlers.size()==0){
            throw new RuntimeException("No command handler was registered!");
        }
        if(handlers.size()>1){
            throw new RuntimeException("Cannot send the command to more than 1 handler");
        }

        handlers.get(0).handle(command);
    }
}
