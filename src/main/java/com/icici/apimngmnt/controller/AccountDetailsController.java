package com.icici.apimngmnt.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icici.apimngmnt.model.RequestModel;
import com.icici.apimngmnt.service.AccountStatementConsolidateService;


@RestController
@RequestMapping
public class AccountDetailsController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RequestModel requestModel;
	
	@Autowired
	AccountStatementConsolidateService consolidateService;
	
	@PostMapping(value = "${app.post.url}",consumes ="application/xml" ,produces = "application/xml")
	public  ResponseEntity<RequestModel> getAccountStatement(InputStream input,
			@PathVariable(required = true, name = "acNo") String accountNo) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
		requestModel = consolidateService.acStatementConsolidate(reader,accountNo);
		
		logger.info("Account No "+accountNo);
		return ResponseEntity.ok().body(requestModel);
	}

}
 