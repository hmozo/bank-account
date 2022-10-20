package com.doctorkernel.account.cmd.infrastructure.ddbb.mongo;

import com.doctorkernel.account.cmd.domain.entities.EventModel;
import com.doctorkernel.account.cmd.domain.services.EventStoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class EventStoreRepositoryImpl implements EventStoreRepository {

    @Autowired
    private final EventStoreMongoRepository eventStoreMongoRepository;

    @Override
    public List<EventModel> findByAggregateIdentifier(String aggregateIdentifier) {
        return eventStoreMongoRepository.findByAggregateIdentifier(aggregateIdentifier);
    }

    @Override
    public EventModel save(EventModel eventModel) {
        return eventStoreMongoRepository.save(eventModel);
    }

    @Override
    public List<EventModel> findAll() {
        return eventStoreMongoRepository.findAll();
    }
}
