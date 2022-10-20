package com.doctorkernel.account.common.events;

import com.doctorkernel.cqrs.core.domain.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class AccountClosedEvent extends BaseEvent{

}
