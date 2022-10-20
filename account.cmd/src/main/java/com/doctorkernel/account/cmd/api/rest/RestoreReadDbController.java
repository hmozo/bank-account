package com.doctorkernel.account.cmd.api.rest;

import com.doctorkernel.account.cmd.api.rest.dto.BaseResponse;
import com.doctorkernel.account.cmd.api.rest.dto.OpenAccountResponse;
import com.doctorkernel.account.cmd.domain.commands.OpenAccountCommand;
import com.doctorkernel.account.cmd.domain.commands.RestoreReadDbCommand;
import com.doctorkernel.cqrs.core.application.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/restore-readdb")
@Slf4j
public class RestoreReadDbController {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> restoreReadDb(){
        try {
            commandDispatcher.send(new RestoreReadDbCommand());
            return new ResponseEntity<>(BaseResponse.builder()
                    .message("Restoration of read ddbb completed successfully").build(), HttpStatus.CREATED);
        }catch(IllegalStateException ex){
            log.warn(String.format("Client made a bad request - {0}", ex.toString()));
            return new ResponseEntity<>(BaseResponse.builder().message(ex.toString()).build(), HttpStatus.BAD_REQUEST);
        }catch(Exception ex){
            log.error("Error while processing request when restoring read ddbb");
            return new ResponseEntity<>(BaseResponse.builder().message(ex.toString()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
