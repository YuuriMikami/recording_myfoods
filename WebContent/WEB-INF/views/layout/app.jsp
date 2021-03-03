<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>食事管理記録</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
    	<div id="wrapper">
    		<div id="header">
    			<div id="header_menu">
    				<h1><a href="<c:url value='/' />">食事管理記録</a></h1>&nbsp;&nbsp;&nbsp;
    		 <c:if test="${sessionScope.login_record != null}">
                        <c:if test="${sessionScope.login_record.admin_flag == 1}">
                            <a href="<c:url value='/records/index' />">管理</a>&nbsp;
                        </c:if>
                        <a href="<c:url value='/reports/index' />">MEMO</a>&nbsp;
                    </c:if>
                </div>
                <c:if test="${sessionScope.login_record != null}">
                    <div id="record_name">
                        <c:out value="${sessionScope.login_record.name}" />&nbsp;さん&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
                </c:if>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Y.M.
            </div>
        </div>
    </body>
</html>