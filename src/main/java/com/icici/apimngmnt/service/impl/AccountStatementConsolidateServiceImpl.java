package com.icici.apimngmnt.service.impl;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;

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

		// requestModel = new RequestModel();

		StringBuilder builder = new StringBuilder();
		String maskedAcNo = acNo.substring(0, acNo.length() - 4);
		maskedAcNo = maskedAcNo.replaceAll("\\d", "X");

		String specialCharSt = "&#13;";
		// String specialCharEnd = ";&#13;";
		String fixedString = "Statement of Transactions in SavingsNumber: " + maskedAcNo
				+ acNo.substring(acNo.length() - 4, acNo.length());
		String fixedString1 = "Statement of Transactions in CurrentNumber: " + maskedAcNo
				+ acNo.substring(acNo.length() - 4, acNo.length());

		String elseCaseEndString = "EZQ";
		String response = "";
		// logger.info("fixedString "+fixedString);

		reader.lines().forEach(line -> {
			if (line.isEmpty()) {
				builder.append(line);
				builder.append("EZQ\n");

			} else {
				builder.append(line);
				builder.append("\n");
				// logger.info("Line Appended is "+line);
			}
		});

		if (builder.indexOf(fixedString) != -1 || builder.indexOf(fixedString1) != -1) {

			if (builder.indexOf(fixedString) != -1) {

				 logger.info(builder.toString());
				response = builder.substring(builder.indexOf(fixedString), builder.lastIndexOf(specialCharSt));
				response = response.substring(response.indexOf(fixedString), response.indexOf("\n" + specialCharSt));
				logger.info("Inside response builder op if" + response);
				StringBuilder responseBuilder = new StringBuilder();

				StringReader stringReader = new StringReader(response);
				BufferedReader buffStrReader = new BufferedReader(stringReader);

				buffStrReader.lines().forEach(line -> {

					boolean flag = line.startsWith("Statement of");
					if (!flag) {
						String[] strArray = line.split("\\,");
						if (strArray[0].equalsIgnoreCase("DATE")) {
							strArray[0] = "transactionTimestamp";
							String headerLine = "";
							for (String string : strArray) {
								headerLine = headerLine.concat(string + ",");

							}
							headerLine = headerLine.replaceAll("&#13;,", "");
							headerLine = headerLine.concat(",CBSRFERECNCE,txnId");
							responseBuilder.append(headerLine);
							responseBuilder.append("\n");
							logger.info("Respone Builder " + responseBuilder.toString());
						} else {
							strArray[0] = strArray[0].concat("T12:00:00-00:00");
							if (strArray[1].equals(""))
								strArray[1] = "OTHERS";
							String headerLine = "";
							for (String string : strArray) {
								headerLine = headerLine.concat(string + ",");
							}
							headerLine = headerLine.replaceAll("&#13;,", "");
							headerLine = headerLine.concat(", ,OTHERS");
							responseBuilder.append(headerLine);
							responseBuilder.append("\n");
							logger.info("Respone Builder " + responseBuilder.toString());

						}

					} else {
						responseBuilder.append(line);
						responseBuilder.append("\n");
					}
				});
				response = responseBuilder.toString();
				logger.info("response " + response);
			} else {

				if(builder.indexOf("EZQ")!=-1) {
					response = builder.substring(builder.indexOf(fixedString1), builder.lastIndexOf(elseCaseEndString));
					response = response.substring(response.indexOf(fixedString1), response.indexOf(elseCaseEndString));
						
				}else {
					
					response = builder.substring(builder.indexOf(fixedString1), builder.lastIndexOf(specialCharSt));
					response = response.substring(response.indexOf(fixedString1), response.indexOf("\n"+specialCharSt));
					
				}
				
				logger.info("Inside response builder op else" + response);
				StringBuilder responseBuilder = new StringBuilder();

				StringReader stringReader = new StringReader(response);
				BufferedReader buffStrReader = new BufferedReader(stringReader);

				buffStrReader.lines().forEach(line -> {

					boolean flag = line.startsWith("Statement of");
					if (!flag) {
						
						String[] strArray = line.split("\\,");
						if (strArray[0].equalsIgnoreCase("DATE")) {
							strArray[0] = "transactionTimestamp";
							String headerLine = "";
							for (String string : strArray) {
								headerLine = headerLine.concat(string + ",");

							}
							headerLine = headerLine.replaceAll("&#13;,", "");
							headerLine = headerLine.concat(",CBSRFERECNCE,txnId");
							responseBuilder.append(headerLine);
							responseBuilder.append("\n");
							// logger.info("Respone Builder " + responseBuilder.toString());
						} else {
							strArray[0] = strArray[0].concat("T12:00:00-00:00");
							
							if (strArray[1].equals(""))
								strArray[1] = "OTHERS";
							String headerLine = "";
							for (String string : strArray) {
								headerLine = headerLine.concat(string + ",");
							}
							headerLine = headerLine.replaceAll("&#13;,", "");
							headerLine = headerLine.concat(", ,OTHERS");
							responseBuilder.append(headerLine);
							responseBuilder.append("\n");
							// logger.info("Respone Builder " + responseBuilder.toString());

						}

					} else {
						responseBuilder.append(line);
						responseBuilder.append("\n");
					}
				});
				response = responseBuilder.toString();
				logger.info("response " + response);
			}

			// logger.info(response);

		}

		return response;

	}

}
