package com.doctorkernel.account.cmd.api.rest;

import com.doctorkernel.account.cmd.api.rest.dto.BaseResponse;
import com.doctorkernel.account.cmd.api.rest.dto.OpenAccountResponse;
import com.doctorkernel.account.cmd.domain.commands.CloseAccountCommand;
import com.doctorkernel.cqrs.core.application.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/close-account")
@Slf4j
public class CloseAccountController {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable("id") String id){
        try {
            commandDispatcher.send(new CloseAccountCommand(id));
            return new ResponseEntity<>(BaseResponse.builder()
                    .message("bank account closure request completed successfully").build(), HttpStatus.OK);
        }catch(IllegalStateException ex){
            log.warn(String.format("Client made a bad request - {0}", ex.toString()));
            return new ResponseEntity<>(BaseResponse.builder().message(ex.toString()).build(), HttpStatus.BAD_REQUEST);
        }catch(Exception ex){
            log.error(String.format("Error while processing request when closing bank-account id - {0}", id));
            return new ResponseEntity<>(BaseResponse.builder().message(ex.toString()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
