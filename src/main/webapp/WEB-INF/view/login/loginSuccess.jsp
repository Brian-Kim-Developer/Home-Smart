<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../includes/header.jsp"%>

<p>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">You Have Been Successfully Logged in!</h1>
        </div>
        <div class="alert alert-success">
            <a href="<c:url value='/'/>" class="alert-link"><button type="button" class="btn btn-primary btn-info btn-lg">Go to Main</button></a>
        </div>
    </div>
</p>

<%@ include file="../includes/footer.jsp"%>