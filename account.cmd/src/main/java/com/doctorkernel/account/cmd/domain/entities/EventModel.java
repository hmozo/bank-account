package com.doctorkernel.account.cmd.domain.entities;

import com.doctorkernel.cqrs.core.domain.events.BaseEvent;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document (collection = "eventStore")
public class EventModel {
    @Id
    private String id;
    private Date timestamp;
    private String aggregateIdentifier;
    private String aggregateType;
    private int version;
    private String eventType;
    private BaseEvent eventData;
}
