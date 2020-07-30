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

		//logger.info(maskedAcNo);
		String specialCharSt = "&#13;";
		//String specialCharEnd = ";&#13;";
		String fixedString ="Statement of Transactions in SavingsNumber: " + maskedAcNo
				+ acNo.substring(acNo.length() - 4, acNo.length());
		String fixedString1="Statement of Transactions in CurrentNumber: "+maskedAcNo+acNo.substring(acNo.length() - 4, acNo.length());

		String elseCaseEndString = "Statement";
		String response = "";
		//logger.info("fixedString "+fixedString);

		reader.lines().forEach(line -> {
			builder.append(line);
			builder.append("\n");
		});

		//logger.info("builder "+builder.toString());
		//logger.info("builder.lastIndexOf(fixedString1) "+builder.lastIndexOf(fixedString1));
		if (builder.indexOf(fixedString) != -1 || builder.lastIndexOf(fixedString1)!=-1) {
			
			//logger.info("Inside builder op" +builder);
			if(builder.lastIndexOf(specialCharSt)!=-1) {
				
				response = builder.substring(builder.indexOf(fixedString), builder.lastIndexOf(specialCharSt));
				response = response.substring(response.indexOf(fixedString), response.indexOf("\n"+specialCharSt));
				
			}else {
				//logger.info("builder.indexOf(fixedString1) "+builder.indexOf(fixedString1));
				response = builder.substring(builder.indexOf(fixedString1), builder.lastIndexOf(elseCaseEndString));
				
			}
			
			logger.info(response);

		}

		return response;

	}

}
