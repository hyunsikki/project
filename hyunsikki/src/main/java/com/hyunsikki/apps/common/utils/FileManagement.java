package com.hyunsikki.apps.common.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hyunsikki.apps.common.model.CustomFiles;


/**
 * @author: John
 * @version: V1.0
 * @date: 2012. 11. 2.
 * @description: Modification history 2012. 11. 2. John 최초작성
 */
public class FileManagement {
	
	private static final Logger logger = LoggerFactory.getLogger(FileManagement.class);
	
	private List<String> imageExtensions = new ArrayList<String>();
	
	/** 다운로드 버퍼 크기 */
	private static final int BUFFER_SIZE = 8192; // 8kb

	/** 문자 인코딩 */
	private static final String CHARSET = "UTF-8";
	
	private String IMAGE_RATIO 		= "ratio";
	
	public FileManagement(){
		imageExtensions.addAll(Arrays.asList(new String[] { "gif", "jpg", "jpeg", "png", "bmp" }));
	}
	
	/**
	 * 파일을 업로드 한다. <xmp> 예) MultipartHttpServletRequest multipartReq =
	 * (MultipartHttpServletRequest)req; Map<String, MultipartFile> files =
	 * multipartReq.getFileMap();
	 * 
	 * List<MultipartFile> multiFiles = new ArrayList<MultipartFile>();
	 * List<String> saveFileNames = new ArrayList<String>();
	 * 
	 * String fileName = null; for (MultipartFile file : files.values()) {
	 * multiFiles.add(file); fileName = file.getOriginalFilename();
	 * saveFileNames.add(FileManagement.changeSaveFileName(fileName)); }
	 * FileManagement.uploadFile(req,"/imageFile",multiFiles,saveFileNames);
	 * </xmp>
	 * 
	 * @param req
	 * @param saveFileLocation
	 * @param multiFiles
	 *            MultipartFile 리스트
	 * @param saveFileNames
	 *            file이름들의 리스트
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws Exception
	 */
	public List<CustomFiles> uploadFile(HttpServletRequest req, String saveFileLocation, String dataFileLocation, List<MultipartFile> multiFiles, List<String> saveFileNames) throws Exception{
//        String applicationRealPath = applicationRealPath = req.getSession().getServletContext().getRealPath("");
        //String webInfoPath = webInfoPath = applicationRealPath + "/WEB-INF";
		List<CustomFiles> list	= new ArrayList<CustomFiles>();

		FileOutputStream fos =null;
		InputStream is = null;
		try{
        	File dir = null;

        	for (int i = 0; i < multiFiles.size(); i++) {
        		CustomFiles uploadFiles = new CustomFiles();
        		if(checkImageFile(multiFiles.get(i).getOriginalFilename())){   //image인경우
        			logger.debug("------------ check Image ------------- ");
        			dir = new File(saveFileLocation);
        			if (!dir.exists()) {
        				dir.mkdirs();
        			}
        			String src	= saveFileLocation + File.separator + saveFileNames.get(i);
        			
        			fos =  new FileOutputStream(src);
        			is = multiFiles.get(i).getInputStream();
        			FileCopyUtils.copy(is, fos);
        			
        			// TODO
        			/*
        			 * 1. file Upload 완료 후 이미지를 썸네일로 만들어 준다.
        			 * 2. 파일 업로드 후 임시 저장 테이블에 저장한다.
        			 */
        			uploadFiles.setOrgFileName(multiFiles.get(i).getOriginalFilename());
        			uploadFiles.setRenameFileName(saveFileNames.get(i));
        			
        			// 이미지 저장 경로, 이미지 가로, 세로
        			if(makeThumbnail(saveFileLocation, saveFileNames.get(i), IMAGE_RATIO)){
        				//TODO 
        				list.add(uploadFiles);
        			}else{
        				//TODO 
        			}
        			
        		}else{
        			logger.debug("------------ check File ------------- ");
        			dir = new File(dataFileLocation);
        			if (!dir.exists()) {
        				dir.mkdirs();
        			}        			
        			fos =  new FileOutputStream(dataFileLocation + File.separator + saveFileNames.get(i));
        			is  = multiFiles.get(i).getInputStream();
        			FileCopyUtils.copy(is, fos);

        			logger.debug("Zip file OrgFileName : {}", multiFiles.get(i).getOriginalFilename());
        			logger.debug("Zip file RenameFileName : {}",saveFileNames.get(i));
        			uploadFiles.setOrgFileName(multiFiles.get(i).getOriginalFilename());
        			uploadFiles.setRenameFileName(saveFileNames.get(i));
        			list.add(uploadFiles);
        		}
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	try { 
        			if(fos != null){
        				fos.close(); 
        				logger.debug("FileManagement... {}.....close","Fileoutput");
        			}
        			if(is != null){
        				is.close();
        				logger.debug("FileManagement... {}.....close","inputStream");
        			}
        		} catch (Exception e) {
        			
        		}
        }
		return list;
	}
	
	public boolean uploadAchive(HttpServletRequest req, String saveFileLocation, List<MultipartFile> multiFiles, List<String> saveFileNames) throws Exception{

		boolean result = false;
		
		FileOutputStream fos =null;
		InputStream is = null;
		int resultInt = 0;
		try{
			File dir = new File(saveFileLocation);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			for (int i = 0; i < multiFiles.size(); i++) {
				fos =  new FileOutputStream(saveFileLocation + File.separator + saveFileNames.get(i));
				is  = multiFiles.get(i).getInputStream();
				resultInt = FileCopyUtils.copy(is, fos);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try { 
				if(fos != null){
					fos.close(); 
				}
				if(is != null){
					is.close();
				}
			} catch (Exception e) {
				
			}
		}
		return result = resultInt >0 ? true: false;
	}
	/**
	 * 파일을 다운로드 한다. <xmp> 예)
	 * FileManagement.downloadFile(request,response,saveFileLocation
	 * ,request.getParameter
	 * ("originalFileName"),request.getParameter("saveFileName")); client <img
	 * src="http://localhost:8080/path/saveFileName/> 또는 <a href=
	 * "/download.do?originalFileName=originalFileName&saveFileName=saveFileName/>"
	 * </xmp>
	 * 
	 * @param req
	 * @param res
	 * @param path
	 *            다운로드할 위치 (image인경우는 cntext_root/saveFileLocation 에 다운로드 된다.)
	 * @param originalFileName
	 *            다운로드할 파일명
	 * @param saveFileName
	 *            실제 서버에 저장된 파일명
	 * @throws Exception
	 */
	public void downloadFile(HttpServletRequest req, HttpServletResponse res, String path, 
			String originalFileName,	String saveFileName) throws Exception {
		/** Build File Full Path */
		StringBuffer fileFullPath = new StringBuffer();
		if (checkImageFile(saveFileName)) { // image인경우
			String imageDownloadPath = path;
			fileFullPath.append(imageDownloadPath).append(File.separatorChar).append(saveFileName);
		} else {
			fileFullPath.append(path).append(File.separatorChar).append(saveFileName);
		}

		File dwldFile = new File(fileFullPath.toString());

		/** Set Response Header */
		res.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
		res.setContentLength((int) dwldFile.length());
		res.setHeader("Content-Disposition", "attachment; fileName=" + "\""
				+ new String(originalFileName.getBytes("MS949"), "8859_1")
				+ "\"" + ";");
		res.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream os = res.getOutputStream();
		InputStream is = null;
		is = new FileInputStream(dwldFile);
		FileCopyUtils.copy(is, os);
		os.flush();
	}

	/**
	 * 저장될 파일명의 이름을 변경해준다. <xmp> 예)dd.jpg -> 1121245206883.jpg (nano seconds)
	 * </xmp>
	 * 
	 * @param fileName
	 * @return
	 */
	public String changeSaveFileName(String fileName) {
		int pos = fileName.lastIndexOf(".");
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(System.nanoTime()));
		sb.append(fileName.substring(pos).toLowerCase());
		return sb.toString();
	}
	
	/**
	 * 이미지 파일인지를 체크한다.
	 * 
	 * @param file
	 * @return
	 */
	public boolean checkImageFile(String file) {
		logger.debug("checkImageFile Name :{}", file);
		int pos = file.lastIndexOf(".")+1;
		String ext = file.substring(pos).toLowerCase();
		logger.debug("checkImageFile ext :{}", ext);
		if (imageExtensions.contains(ext))
			return true;
		else
			return false;
	}

	public boolean makeThumbnail(String path, String fileName, String type)throws IOException{
		
		boolean returnvalue = true;
//		ImageScaling imageScaling = new ImageScaling();
		
		String src				= path + File.separator + fileName; 
//    	String thumbnailPath  	= path + File.separator + THUMBNAIL_PATH;
//    	String imageFormat 		= null;
//    	int destWidth1 			= 0;
//    	int destHeight1			= 0;
//    	int destWidth2 			= 0;
//    	int destHeight2			= 0;
//    	int destWidth3 			= 0;
//    	int destHeight3			= 0;
    	
    	FileInputStream fis = null;
    	FileInputStream fis2 = null;
    	FileInputStream fis3 = null;
    	ImageInputStream iis = null;
    	try{
//    		File file = new File(thumbnailPath);
//    		fis = new FileInputStream(src);
//    		
//    		if(!file.isDirectory())
//    			file.mkdirs();
//    		
//    		BufferedImage srcImage 	= ImageIO.read(fis);
//    		
//    		if(type.equals("fixed")){
//    			destWidth1 = fixedMaxWidth1;
//    			destHeight1 = fixedMaxHeith1;
//    			destWidth2 = fixedMaxWidth2;
//    			destHeight2 = fixedMaxHeith2;
//    			destWidth3 = fixedMaxWidth3;
//    			destHeight3 = fixedMaxHeith3;
//    		}else if(type.equals(IMAGE_RATIO)){
//    			fis3 =  new FileInputStream(src);
//    			BufferedImage im = ImageIO.read(fis3);
//    			
//    			// thumbnail1
//    			double scale1 = 0; 
//    			if (im.getWidth() < im.getHeight()) {
//    				if((double) ratioMaxHeith1 <(double) im.getHeight())
//    					scale1 = (double) ratioMaxHeith1 / (double) im.getHeight();
//    				else
//    					scale1 = 1;
//    					
//    			}else{
//    				if((double) ratioMaxWidth1 < (double) im.getWidth())
//    					scale1 = (double) ratioMaxWidth1 / (double) im.getWidth();
//    				else
//    					scale1 = 1;
//    			}
//    			
//    			destWidth1 = (int) (scale1 * im.getWidth());
//    			destHeight1 = (int) (scale1 * im.getHeight());
//    			
//    			// thumbnail2
//    			double scale2 = 0; 
//    			if (im.getWidth() < im.getHeight()) {
//    				if((double) ratioMaxHeith2 <(double) im.getHeight())
//    					scale2 = (double) ratioMaxHeith2 / (double) im.getHeight();
//    				else
//    					scale2 = 1;
//    				
//    			}else{
//    				if((double) ratioMaxWidth2 < (double) im.getWidth())
//    					scale2 = (double) ratioMaxWidth2 / (double) im.getWidth();
//    				else
//    					scale2 = 1;
//    			}
//    			
//    			destWidth2 = (int) (scale2 * im.getWidth());
//    			destHeight2 = (int) (scale2 * im.getHeight());
//    			
//    			// thumbnail3
//    			double scale3 = 0; 
//    			if (im.getWidth() < im.getHeight()) {
//    				if((double) ratioMaxHeith3 <(double) im.getHeight())
//    					scale3 = (double) ratioMaxHeith3 / (double) im.getHeight();
//    				else
//    					scale3 = 1;
//    				
//    			}else{
//    				if((double) ratioMaxWidth3 < (double) im.getWidth())
//    					scale3 = (double) ratioMaxWidth3 / (double) im.getWidth();
//    				else
//    					scale3 = 1;
//    			}
//    			
//    			destWidth3 = (int) (scale3 * im.getWidth());
//    			destHeight3 = (int) (scale3 * im.getHeight());
//    		}
//    		
//    		// Image Format 받아 오기
//    		fis2 = new FileInputStream(src);
//    		iis = ImageIO.createImageInputStream(fis2);
//    		Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
//    		while (readers.hasNext()) {
//    			ImageReader read = (ImageReader) readers.next();
//    			imageFormat = read.getFormatName();
//    			if (imageFormat != null) {
//    				imageFormat = imageFormat.toLowerCase();
//    				logger.debug("---- image format : {}", imageFormat);
//    			}
//    		}
//    		//thumbnail 1 pc web
//    		returnvalue = imageScaling.scale(srcImage, thumbnailPath+ File.separator + Files.THUMB1_PREFIX+ fileName, imageFormat, destWidth1, destHeight1);
//    		//thumbnail 2 mobile pad
//    		returnvalue = imageScaling.scale(srcImage, thumbnailPath+ File.separator + Files.THUMB2_PREFIX+ fileName, imageFormat, destWidth2, destHeight2);
//    		//thumbnail 3 mobile phone
//    		returnvalue = imageScaling.scale(srcImage, thumbnailPath+ File.separator + Files.THUMB3_PREFIX+ fileName, imageFormat, destWidth3, destHeight3);
    		
    		//파일 권한 변경
    		// apache server 외부에서 이미지 볼수 있게 설정
    		List<String> chPermList = new ArrayList<String>();
//    		chPermList.add("chmod");
//    		chPermList.add("644");
//    		chPermList.add(thumbnailPath+ File.separator + Files.THUMB1_PREFIX+ fileName);
//    		chPermList.add("chmod");
//    		chPermList.add("644");
//    		chPermList.add(thumbnailPath+ File.separator + Files.THUMB2_PREFIX+ fileName);
//    		chPermList.add("chmod");
//    		chPermList.add("644");
//    		chPermList.add(thumbnailPath+ File.separator + Files.THUMB3_PREFIX+ fileName);
    		chPermList.add("chmod");
    		chPermList.add("644");
    		chPermList.add(src);

    		changePermission(chPermList);
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if(iis!=null)
    			iis.close();
    		if(fis!=null)
    			fis.close();
    		if(fis2!=null)
    			fis2.close();
    		if(fis3!=null)
    			fis3.close();
    	}
		
		return returnvalue;
	}

    public void changePermission(List<String> chPermList) throws InterruptedException, IOException
    {
        ProcessBuilder pb = null;
        pb = new ProcessBuilder(chPermList);
                pb.redirectErrorStream(true);

                Process p = pb.start();

                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

                p.waitFor();

                if(p.exitValue()!=0)
                {
                   logger.debug("The permission change failed with exit value: {}" + p.exitValue());

                }
    }
    
	public void copyDirectory(File sourcelocation, File targetdirectory)
			throws IOException {
		// 디렉토리인 경우
		if (sourcelocation.isDirectory()) {
			// 복사될 Directory가 없으면 만듭니다.
			if (!targetdirectory.exists()) {
				targetdirectory.mkdir();
			}

			String[] children = sourcelocation.list();
			for (int i = 0; i < children.length; i++) {
				copyDirectory(new File(sourcelocation, children[i]), new File(
						targetdirectory, children[i]));
			}
		} else {
			// 파일인 경우
			InputStream in = new FileInputStream(sourcelocation);
			OutputStream out = new FileOutputStream(targetdirectory);

			// Copy the bits from instream to outstream
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
	}
	
	public void copyDirectory(String sourceLocation, String targetLocation)
			throws IOException {
		File sourcelocation = new File(sourceLocation);
		File targetdirectory = new File(targetLocation);
		// 디렉토리인 경우
		if (sourcelocation.isDirectory()) {
			// 복사될 Directory가 없으면 만듭니다.
			if (!targetdirectory.exists()) {
				targetdirectory.mkdir();
			}
			
			String[] children = sourcelocation.list();
			for (int i = 0; i < children.length; i++) {
				copyDirectory(new File(sourcelocation, children[i]), new File(
						targetdirectory, children[i]));
			}
		} else {
			// 파일인 경우
			InputStream in = new FileInputStream(sourcelocation);
			OutputStream out = new FileOutputStream(targetdirectory);
			
			// Copy the bits from instream to outstream
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
	}
	
	public boolean deleteFolder(File targetFolder) {

		File[] childFile = targetFolder.listFiles();
		boolean confirm = false;
		int size = childFile.length;

		if (size > 0) {

			for (int i = 0; i < size; i++) {
				if (childFile[i].isFile()) {
					confirm = childFile[i].delete();
				} else {
					deleteFolder(childFile[i]);
				}
			}
		}

		targetFolder.delete();

		return (!targetFolder.exists());
	}// deleteFolder

	public boolean deleteFolder(String targetSrc) {
		File targetFolder = new File(targetSrc);
		File[] childFile = targetFolder.listFiles();
		boolean confirm = false;
		int size = childFile.length;
		
		if (size > 0) {
			
			for (int i = 0; i < size; i++) {
				if (childFile[i].isFile()) {
					confirm = childFile[i].delete();
				} else {
					deleteFolder(childFile[i]);
				}
			}
		}
		
		targetFolder.delete();
		
		return (!targetFolder.exists());
	}// deleteFolder
	
	public List<String> listFile(String targetPath){
		List<String> list = new ArrayList<String>();
		File listFile = new File(targetPath);
		String[] fileArr = listFile.list();
		for(String str:fileArr){
			list.add(str);
		}
		return list;
	}
	
	public String replaceString(String str) {
		String str_imsi = "";
		String[] filter_word = { 
				"\\\\", "\\/", "\\:", "\\?", "\\*", "\\<", "\\>", "\\|", "\\&"
				};

		for (int i = 0; i < filter_word.length; i++) {
			str_imsi = str.replaceAll(filter_word[i], "_");
			str = str_imsi;
		}

		return str;
	}
	
	// 중복 파일 리네임용
	// 중복 파일 정책에 맞는 String return
	public String renamePolicyString (File file){
		
		String returnVal = "";
		String path		 = file.getPath();
		String orgFileName = file.getName().substring(0, file.getName().lastIndexOf("."));
		if(file.isFile()){
			for(int i=1; i< Integer.MAX_VALUE; i++){
				String rename	= orgFileName + "("+i+")";
				File checkFile = new File(path+rename);
				if(!checkFile.isFile()){
					returnVal = rename;
					break;
				}
			}
		}
			
		return returnVal;
	}
	
	public boolean moveFile(String srcPath, String srcFileName, String destPath, String destFileName){
		boolean result = false;
		File srcFile = new File(srcPath+File.separator+srcFileName);
		if(srcFile.isFile()){
			File destFolder = new File(destPath);
			if(!destFolder.isDirectory())
				destFolder.mkdirs();
			result = srcFile.renameTo(new File(destPath+File.separator+destFileName));
		}
		
		return result;
	}
	
	public boolean deleteFile(List<String> fileList, String path){
		boolean result = false;
		for(String fileName : fileList){
			File deleteFile = new File(path+File.separator+fileName);
			if(deleteFile.isFile()){
				System.out.println("Delete File : "+ deleteFile.getName());
				result= deleteFile.delete();
			}
		}
		return result;
	}

	public boolean deleteFile(String path, String file){
		logger.warn("Delete File : "+ path+File.separator+file);
		boolean result = false;
		File deleteFile = new File(path+File.separator+file);
		if(deleteFile.isFile()){
			result= deleteFile.delete();
		}
		return result;
	}
	
	public static void download(HttpServletRequest request,
			HttpServletResponse response, File file) throws ServletException,
			IOException {

		String mimetype = request.getSession().getServletContext()
				.getMimeType(file.getName());

		if (file == null || !file.exists() || file.length() <= 0
				|| file.isDirectory()) {
			throw new IOException(
					"파일 객체가 Null 혹은 존재하지 않거나 길이가 0, 혹은 파일이 아닌 디렉토리이다.");
		}

		InputStream is = null;

		try {
			is = new FileInputStream(file);
			download(request, response, is, file.getName(), file.length(),
					mimetype);
		} finally {
			try {
				is.close();
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * 해당 입력 스트림으로부터 오는 데이터를 다운로드 한다.
	 * 
	 * @param request
	 * @param response
	 * @param is
	 *            입력 스트림
	 * @param filename
	 *            파일 이름
	 * @param filesize
	 *            파일 크기
	 * @param mimetype
	 *            MIME 타입 지정
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void download(HttpServletRequest request,
			HttpServletResponse response, InputStream is, String filename,
			long filesize, String mimetype) throws ServletException,
			IOException {
		String mime = mimetype;

		if (mimetype == null || mimetype.length() == 0) {
			mime = "application/octet-stream;";
		}

		byte[] buffer = new byte[BUFFER_SIZE];

		response.setContentType(mime + "; charset=" + CHARSET);

		// 아래 부분에서 euc-kr 을 utf-8 로 바꾸거나 URLEncoding을 안하거나 등의 테스트를
		// 해서 한글이 정상적으로 다운로드 되는 것으로 지정한다.
		String userAgent = request.getHeader("User-Agent");

		// attachment; 가 붙으면 IE의 경우 무조건 다운로드창이 뜬다. 상황에 따라 써야한다.
		if (userAgent != null && userAgent.indexOf("MSIE 5.5") > -1) { // MS IE 5.5 이하
			response.setHeader("Content-Disposition","filename=" + URLEncoder.encode(filename, "UTF-8") + ";");
		} else if (userAgent != null && userAgent.indexOf("MSIE") > -1) { // MSIE (보통은 6.x 이상 가정)
			response.setHeader("Content-Disposition", "attachment; filename="
					+ java.net.URLEncoder.encode(filename, "UTF-8") + ";");
		} else { // 모질라나 오페라
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(filename.getBytes(CHARSET), "latin1") + ";");
		}

		// 파일 사이즈가 정확하지 않을때는 아예 지정하지 않는다.
		if (filesize > 0) {
			response.setHeader("Content-Length", "" + filesize);
		}

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
			fin = new BufferedInputStream(is);
			outs = new BufferedOutputStream(response.getOutputStream());
			int read = 0;

			while ((read = fin.read(buffer)) != -1) {
				outs.write(buffer, 0, read);
			}
		} catch (IOException ex) {
			// Tomcat ClientAbortException을 잡아서 무시하도록 처리해주는게 좋다.
		} finally {
			try {
				outs.close();
			} catch (Exception ex1) {
			}

			try {
				fin.close();
			} catch (Exception ex2) {

			}
		} // end of try/catch
	}
}
