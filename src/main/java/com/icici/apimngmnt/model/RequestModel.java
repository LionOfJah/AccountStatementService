package com.icici.apimngmnt.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement
public class RequestModel {
	
	private String messageType;

	private String procCode;

	private String actCode;

	private String acStatement;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getProcCode() {
		return procCode;
	}

	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public String getAcStatement() {
		return acStatement;
	}

	public void setAcStatement(String acStatement) {
		this.acStatement = acStatement;
	}

	public RequestModel() {
		
	}

	public RequestModel(String messageType, String procCode, String actCode, String acStatement) {
		
		this.messageType = messageType;
		this.procCode = procCode;
		this.actCode = actCode;
		this.acStatement = acStatement;
	}

	@Override
	public String toString() {
		return "RequestModel [messageType=" + messageType + ", procCode=" + procCode + ", actCode=" + actCode
				+ ", acStatement=" + acStatement + "]";
	}
	
	

	
	
	

}
