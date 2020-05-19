package com.icici.apimngmnt.model;


import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

@Component
@XmlRootElement
public class RequestModel {
	
	private String status;

	private String acStatement;

	

	public String getAcStatement() {
		return acStatement;
	}

	public void setAcStatement(String acStatement) {
		this.acStatement = acStatement;
	}

	public RequestModel() {
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RequestModel(String status, String acStatement) {
		super();
		this.status = status;
		this.acStatement = acStatement;
	}

	@Override
	public String toString() {
		return "RequestModel [status=" + status + ", acStatement=" + acStatement + "]";
	}



}
