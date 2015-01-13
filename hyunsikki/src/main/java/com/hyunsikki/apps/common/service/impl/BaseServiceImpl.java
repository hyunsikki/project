/**
 * 
 */
package com.hyunsikki.apps.common.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hyunsik Jung
 *
 */
public class BaseServiceImpl {

	/**
	 * 모든 상속 클래스를 위한 로그
	 */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * UUID Generator
	 * @return
	 */
	public String getUUID() {
		return UUID.randomUUID().toString();
	}
}
