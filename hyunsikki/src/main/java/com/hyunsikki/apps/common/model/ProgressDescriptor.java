/**
 * 
 */
package com.hyunsikki.apps.common.model;

/**
 * @author Hyunsik Jung
 *
 */
public class ProgressDescriptor {

	public long bytesRead;
	public long bytesTotal;

	public ProgressDescriptor() {
		bytesRead = bytesTotal = 0;
	}

	public ProgressDescriptor(long bytesRead, long bytesTotal) {
		this.bytesRead = bytesRead;
		this.bytesTotal = bytesTotal;
	}

	public long getBytesRead() {
		return bytesRead;
	}

	public void setBytesRead(long bytesRead) {
		this.bytesRead = bytesRead;
	}

	public long getBytesTotal() {
		return bytesTotal;
	}

	public void setBytesTotal(long bytesTotal) {
		this.bytesTotal = bytesTotal;
	}
	
}
