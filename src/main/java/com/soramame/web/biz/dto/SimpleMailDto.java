/**
 * Copyright 2015 Plat.
 * 
 * This file is part of Pj Platform.
 *
 *  Pj Platform is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Pj Platform is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Pj Platform.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.soramame.web.biz.dto;

import java.util.Map;

/**
 * mail dto
 * @version 1.0
 * @author Plat.
 */
public class SimpleMailDto {
	
	/** from address. */
	private String fromAddress;
	
	/** to address. */
	private String toAddress;
	
	/** to address array. */
	private String[] toAddressArray;
	
	/** cc address. */
	private String ccAddress;
	
	/** cc address array. */
	private String[] ccAddressArray;
	
	/** bcc address. */
	private String bccAddress;
	
	/** bcc address array. */
	private String[] bccAddressArray;
	
	/** subject. */
	private String subject;
	
	/** contents. */
	private String contents;
	
	/** contetType. */
	private String contentType;
	
	/** template name. */
	private String templateName;
	
	/** mail contents map. */
	private Map<String, String> mailMap;
	
	/** constructor */
	public SimpleMailDto() {
		this.contentType = "text/html";
	}

	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * @return the toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}

	/**
	 * @param to the to toAddress set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * @return the ccAddress
	 */
	public String getCcAddress() {
		return ccAddress;
	}

	/**
	 * @param ccAddress the ccAddress to set
	 */
	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	/**
	 * @return the bccAddress
	 */
	public String getBccAddress() {
		return bccAddress;
	}

	/**
	 * @param bccAddress the bccAddress to set
	 */
	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	/**
	 * @return the toAddressArray
	 */
	public String[] getToAddressArray() {
		return toAddressArray;
	}

	/**
	 * @param toAddressArray the toAddressArray to set
	 */
	public void setToAddressArray(String[] toAddressArray) {
		this.toAddressArray = toAddressArray;
	}

	/**
	 * @return the ccAddressArray
	 */
	public String[] getCcAddressArray() {
		return ccAddressArray;
	}

	/**
	 * @param ccAddressArray the ccAddressArray to set
	 */
	public void setCcAddressArray(String[] ccAddressArray) {
		this.ccAddressArray = ccAddressArray;
	}

	/**
	 * @return the bccAddressArray
	 */
	public String[] getBccAddressArray() {
		return bccAddressArray;
	}

	/**
	 * @param bccAddressArray the bccAddressArray to set
	 */
	public void setBccAddressArray(String[] bccAddressArray) {
		this.bccAddressArray = bccAddressArray;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @return the mailMap
	 */
	public Map<String, String> getMailMap() {
		return mailMap;
	}

	/**
	 * @param mailMap the mailMap to set
	 */
	public void setMailMap(Map<String, String> mailMap) {
		this.mailMap = mailMap;
	}

}
