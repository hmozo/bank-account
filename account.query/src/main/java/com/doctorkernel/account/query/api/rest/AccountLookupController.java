package com.doctorkernel.account.query.api.rest;

import com.doctorkernel.account.query.api.rest.dto.AccountLookupResponse;
import com.doctorkernel.account.query.domain.entities.BankAccount;
import com.doctorkernel.account.query.domain.queries.FindAccountByHolderQuery;
import com.doctorkernel.account.query.domain.queries.FindAccountByIdQuery;
import com.doctorkernel.account.query.domain.queries.FindAccountWithBalanceQuery;
import com.doctorkernel.account.query.domain.queries.FindAllAccountsQuery;
import com.doctorkernel.account.query.domain.valueobjects.EqualityType;
import com.doctorkernel.cqrs.core.application.QueryDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/api/v1/bank-accounts")
@Slf4j
public class AccountLookupController {

    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping
    public ResponseEntity<AccountLookupResponse> getAllAccounts(){
        try{
            List<BankAccount> accounts= queryDispatcher.send(new FindAllAccountsQuery());
            if (accounts==null || accounts.size()==0){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            var response= AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message(MessageFormat.format("Successfully returned {0} bank account(s)", accounts.size())).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            var safeErrorMessage= "Failed to complete get all accounts request";
            log.error(Level.SEVERE + ": " + safeErrorMessage, ex);
            return new ResponseEntity<AccountLookupResponse>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("byid/{id}")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable("id") String id){
        try{
            List<BankAccount> accounts= queryDispatcher.send(new FindAccountByIdQuery(id));
            if (accounts==null || accounts.size()==0){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            var response= AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message("Successfully returned bank account").build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            var safeErrorMessage= "Failed to complete get account by ID request";
            log.error(Level.SEVERE + ": " + safeErrorMessage, ex);
            return new ResponseEntity<AccountLookupResponse>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("byholder/{account-holder}")
    public ResponseEntity<AccountLookupResponse> getAccountByHolder(@PathVariable("account-holder") String accountHolder){
        try{
            List<BankAccount> accounts= queryDispatcher.send(new FindAccountByHolderQuery(accountHolder));
            if (accounts==null || accounts.size()==0){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            var response= AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message("Successfully returned bank account").build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            var safeErrorMessage= "Failed to complete get account by Holder request";
            log.error(Level.SEVERE + ": " + safeErrorMessage, ex);
            return new ResponseEntity<AccountLookupResponse>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("withbalance/{equality-type}/{balance}")
    public ResponseEntity<AccountLookupResponse> getAccountWithBalance(@PathVariable("equality-type") EqualityType equalityType, @PathVariable("balance") double balance){
        try{
            List<BankAccount> accounts= queryDispatcher.send(new FindAccountWithBalanceQuery(equalityType, balance));
            if (accounts==null || accounts.size()==0){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            var response= AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message(MessageFormat.format("Successfully returned {0} bank account(s)", accounts.size())).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            var safeErrorMessage= "Failed to complete get account with balance request";
            log.error(Level.SEVERE + ": " + safeErrorMessage, ex);
            return new ResponseEntity<AccountLookupResponse>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
