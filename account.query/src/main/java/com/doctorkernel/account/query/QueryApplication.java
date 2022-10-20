package com.doctorkernel.account.query;

import com.doctorkernel.account.query.domain.queries.FindAccountByHolderQuery;
import com.doctorkernel.account.query.domain.queries.FindAccountByIdQuery;
import com.doctorkernel.account.query.domain.queries.FindAccountWithBalanceQuery;
import com.doctorkernel.account.query.domain.queries.FindAllAccountsQuery;
import com.doctorkernel.account.query.domain.services.QueryHandler;
import com.doctorkernel.cqrs.core.application.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class QueryApplication {

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers(){
		queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountWithBalanceQuery.class, queryHandler::handle);
	}
}
