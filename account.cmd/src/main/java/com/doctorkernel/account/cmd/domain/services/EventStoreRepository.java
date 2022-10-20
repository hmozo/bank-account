package com.doctorkernel.account.cmd.domain.services;

import com.doctorkernel.account.cmd.domain.entities.EventModel;

import java.util.List;

public interface EventStoreRepository {
    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
    EventModel save(EventModel eventModel);
}
