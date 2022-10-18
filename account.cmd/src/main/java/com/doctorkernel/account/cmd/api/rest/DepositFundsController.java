package com.doctorkernel.account.cmd.api.rest;

import com.doctorkernel.account.cmd.api.rest.dto.BaseResponse;
import com.doctorkernel.account.cmd.domain.commands.DepositFundsCommand;
import com.doctorkernel.cqrs.core.domain.commands.CommandDispatcher;
import com.doctorkernel.cqrs.core.domain.exceptions.AggregateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/deposit-funds")
@Slf4j
public class DepositFundsController {

    @Autowired
    CommandDispatcher commandDispatcher;

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable(value = "id") String id, @RequestBody DepositFundsCommand command){
        try{
            command.setId(id);
            commandDispatcher.send(command);

            return new ResponseEntity<>(BaseResponse.builder()
                    .message("Deposit funds request completed successfully").build(), HttpStatus.OK);
        }catch (IllegalStateException | AggregateNotFoundException ex){
            log.warn(String.format("Client made a bad request - {0}", ex.toString()));
            return new ResponseEntity<>(BaseResponse.builder().message(ex.toString()).build(), HttpStatus.BAD_REQUEST);
        }catch(Exception ex){
            log.error(String.format("Error while processing request to deposit funds to bank-account id - {0}", id));
            return new ResponseEntity<>(BaseResponse.builder().message(ex.toString()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
