<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../includes/header.jsp"%>

<form:form>
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="col-lg-4 text-center col-md-6 col-md-offset-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                Delete Account Format
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <h3>
                        <label>Please Enter Password
                            <br/>
                            <form:input class="form-control" path="password" />
                            <form:errors class="text-danger" path="password"/>
                        </label>
                    </h3>
                </div>
                <input type="submit" class="btn btn-default btn-lg" value="<spring:message code="submit.btn" />">
            </div>
        </div>
    </div>
    </div>
</form:form>

<%@ include file="../includes/footer.jsp"%>