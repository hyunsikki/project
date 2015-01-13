package com.hyunsikki.apps.common.model;

import java.io.File;
import java.util.List;

public class Mail {

	public static String FROMUSER = "webmaster@usermail.com";
	
	/**
	 * 제목 
	 */
	private String subject;
	
	/**
	 * 내용
	 */
	private String text;
	
	/**
	 * 보내는사람
	 */
	private String fromUser;
	
	/**
	 * 받는사람
	 */
	private List<String> toUser;
	
	/**
	 * 참조
	 */
	private String toCC;
	
	/**
	 * 첨부파일명
	 */
	private List<String> attcheFileName;
	
	/**
	 * 첨부파일
	 */
	private List<File> file;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFromUser() {
		if(this.fromUser==null)
			this.fromUser = FROMUSER;
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public List<String> getToUser() {
		return toUser;
	}

	public void setToUser(List<String> toUser) {
		this.toUser = toUser;
	}

	public String getToCC() {
		return toCC;
	}

	public void setToCC(String toCC) {
		this.toCC = toCC;
	}

	public List<String> getAttcheFileName() {
		return attcheFileName;
	}

	public void setAttcheFileName(List<String> attcheFileName) {
		this.attcheFileName = attcheFileName;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}
}
