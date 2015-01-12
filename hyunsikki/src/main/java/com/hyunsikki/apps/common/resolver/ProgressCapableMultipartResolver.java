package com.hyunsikki.apps.common.resolver;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.hyunsikki.apps.common.model.ProgressDescriptor;

public class ProgressCapableMultipartResolver extends CommonsMultipartResolver {

	private static final Logger log = LoggerFactory.getLogger(ProgressCapableMultipartResolver.class);

	public final static String PROGRESS_PREFIX = "progressUpdateSession";

	private void initProgressProcessor(HttpServletRequest request) {
		request.getSession().removeAttribute(PROGRESS_PREFIX);
		request.getSession().setAttribute(PROGRESS_PREFIX, new Gson().toJson(new ProgressDescriptor()).toString());
	}

	private void clearProgressProcessor(HttpServletRequest request) {
		request.getSession().removeAttribute(PROGRESS_PREFIX);
	}

	private void processProgress(long bytesRead, long bytesTotal, HttpServletRequest request) {
		request.getSession().setAttribute(PROGRESS_PREFIX, new Gson().toJson((new ProgressDescriptor(bytesRead, bytesTotal))).toString());
	}

	protected MultipartParsingResult parseRequest(
			final HttpServletRequest request) throws MultipartException {
		String encoding = determineEncoding(request);
		ServletFileUpload fileUpload = (ServletFileUpload) prepareFileUpload(encoding);

		initProgressProcessor(request);
		fileUpload.setProgressListener(new ProgressListener() {
			public void update(long pBytesRead, long pContentLength, int pItems) {
				processProgress(pBytesRead, pContentLength, request);
			}
		});

		MultipartParsingResult result = null;
		try {
			result = parseFileItems(fileUpload.parseRequest(request), encoding);

		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(),
					ex);

		} catch (FileUploadException ex) {
			throw new MultipartException("Could not parse "
					+ "multipart servlet request", ex);
		}

		clearProgressProcessor(request);
		return result;
	}

}
