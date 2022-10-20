package com.doctorkernel.account.cmd.infrastructure.brokers.kafka;

import com.doctorkernel.cqrs.core.domain.events.BaseEvent;
import com.doctorkernel.cqrs.core.domain.services.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountEventProducer implements EventProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topic, BaseEvent event) {
        this.kafkaTemplate.send(topic, event);
    }
}
