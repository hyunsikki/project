/**
 * 
 */
package com.hyunsikki.apps.common.model;

/**
 * @author Hyunsik Jung
 *
 */
public class CustomFiles {

	public static enum FILE_TYPE {IMG, HTML, ZIP};
	private int no;
	private String tableType;
	private int postNo;
	private String orgFileName;
	private String renameFileName;
	private String fileType;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String html;
	private String FILE_TYPE;
	public CustomFiles(){
		
	}
	public CustomFiles(String orgFileName, String renameFileName, String fileType){
		this.orgFileName = orgFileName;
		this.renameFileName = renameFileName;
		this.fileType = fileType;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getRenameFileName() {
		return renameFileName;
	}
	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getThumb1() {
		return thumb1;
	}
	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}
	public String getThumb2() {
		return thumb2;
	}
	public void setThumb2(String thumb2) {
		this.thumb2 = thumb2;
	}
	public String getThumb3() {
		return thumb3;
	}
	public void setThumb3(String thumb3) {
		this.thumb3 = thumb3;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getFILE_TYPE() {
		return FILE_TYPE;
	}
	public void setFILE_TYPE(String fILE_TYPE) {
		FILE_TYPE = fILE_TYPE;
	}
}
