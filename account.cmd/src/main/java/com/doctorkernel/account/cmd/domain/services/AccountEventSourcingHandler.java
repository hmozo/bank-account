package com.doctorkernel.account.cmd.domain.services;

import com.doctorkernel.account.cmd.domain.model.AccountAggregate;
import com.doctorkernel.cqrs.core.domain.events.EventStore;
import com.doctorkernel.cqrs.core.domain.model.AggregateRoot;
import com.doctorkernel.cqrs.core.domain.services.EventProducer;
import com.doctorkernel.cqrs.core.domain.services.EventSourcingHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@AllArgsConstructor
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    private final EventStore eventStore;
    private final EventProducer eventProducer;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public AccountAggregate getById(String id) {
        var aggregate= new AccountAggregate();
        var events= eventStore.getEvents(id);
        if (events!=null && !events.isEmpty()){
            aggregate.replayEvents(events);
            var latestVersionOpt= events.stream().map(event->event.getVersion()).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersionOpt.get());
        }
        return aggregate;
    }

    @Override
    public void republishEvents() {
        var aggregateIds= eventStore.getAggregateIds();
        aggregateIds.forEach(aggregateid->{
            var aggregate= getById(aggregateid);
            if (aggregate!=null && aggregate.getActive()){
                var events= eventStore.getEvents(aggregateid);
                events.stream().forEach(event->{
                    eventProducer.produce(event.getClass().getSimpleName(), event);
                });
            }
        });
    }
}
