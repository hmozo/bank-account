package com.doctorkernel.account.common.events;

import com.doctorkernel.cqrs.core.domain.events.BaseEvent;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AccountClosedEvent extends BaseEvent{

}
