package com.icici.apimngmnt.service.impl;

import java.io.BufferedReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icici.apimngmnt.model.RequestModel;
import com.icici.apimngmnt.service.AccountStatementConsolidateService;

@Service
public class AccountStatementConsolidateServiceImpl implements AccountStatementConsolidateService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	String response;

	@Override
	public String acStatementConsolidate(BufferedReader reader, String acNo) {

		//requestModel = new RequestModel();

		StringBuilder builder = new StringBuilder();
		String maskedAcNo = acNo.substring(0, acNo.length() - 4);
		maskedAcNo = maskedAcNo.replaceAll("\\d", "X");

		logger.info(maskedAcNo);
		String specialCharSt = "&#13;";
		String specialCharEnd = ";&#13;";
		String fixedString = specialCharSt + "Statement of Transactions in SavingsNumber: " + maskedAcNo
				+ acNo.substring(acNo.length() - 4, acNo.length());

		String response = "";
		logger.info(fixedString);

		reader.lines().forEach(line -> {
			builder.append(line);
			// builder.append("\n");
		});

		if (builder.indexOf(fixedString) != -1) {
			
			response = builder.substring(builder.indexOf(fixedString), builder.lastIndexOf("&#13"));
			response = response.substring(response.indexOf(fixedString), response.indexOf(specialCharEnd));
			
			logger.info(response);

		}

		return response;

	}

}
