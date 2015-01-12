<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"       prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
<head>
	<title><s:message code="common.title" /></title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="title" content="<s:message code="common.title" />" />
	<meta name="description" content="Site" />
	<meta name="keywords" content="Search keywords">
<!-- 	<link rel="stylesheet" type="text/css" href="/web/css/common.css" /> -->
</head>
<body>
<t:insertAttribute name="content"/>
</body>
</html>