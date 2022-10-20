package com.doctorkernel.account.cmd.domain.services;

import com.doctorkernel.account.cmd.domain.entities.EventModel;
import com.doctorkernel.account.cmd.domain.model.AccountAggregate;
import com.doctorkernel.cqrs.core.domain.events.BaseEvent;
import com.doctorkernel.cqrs.core.domain.events.EventStore;
import com.doctorkernel.cqrs.core.domain.exceptions.AggregateNotFoundException;
import com.doctorkernel.cqrs.core.domain.services.EventProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class AccountEventStore implements EventStore {

    private final EventStoreRepository eventStoreRepository;
    private final EventProducer eventProducer;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream= eventStoreRepository.findByAggregateIdentifier(aggregateId);

        StreamSupport.stream(events.spliterator(), false).forEach(event->{
            var eventModel= EventModel.builder()
                    .timestamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(expectedVersion+1)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event).build();
            var persistedEvent= eventStoreRepository.save(eventModel);

            // [Producer] sent events to Kafka
            if(!persistedEvent.getId().isEmpty()){
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }

        });
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream= eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream==null || eventStream.isEmpty()){
            throw new AggregateNotFoundException("Incorrect acount ID provider");
        }
        return eventStream.stream().map(event-> event.getEventData()).collect(Collectors.toList());
    }

    @Override
    public List<String> getAggregateIds() {
        var eventStream= eventStoreRepository.findAll();
        if (eventStream==null || eventStream.isEmpty()){
            throw new IllegalStateException("could not retrieved event stream from the event store");
        }
        return eventStream.stream().map(EventModel::getAggregateIdentifier).distinct().collect(Collectors.toList());
    }
}
