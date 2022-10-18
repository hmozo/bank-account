package com.doctorkernel.account.cmd.infrastructure.ddbb.mongo;

import com.doctorkernel.account.cmd.domain.entities.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoreRepository extends MongoRepository<EventModel, String> {
    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}
