package com.icici.apimngmnt.service;

import java.io.BufferedReader;

import org.springframework.stereotype.Service;

import com.icici.apimngmnt.model.RequestModel;

@Service
public interface AccountStatementConsolidateService {

	public RequestModel acStatementConsolidate(BufferedReader reader,String acNo);
}
