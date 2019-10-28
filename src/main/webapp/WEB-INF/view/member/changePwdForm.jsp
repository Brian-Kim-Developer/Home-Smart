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
                Password Change Format
            </div>
            <div class="panel-body">
                    <div class="form-group">
                        <h3>
                            <label><spring:message code="currentPassword" />:
                                <br/>
                                <form:input class="form-control" path="currentPassword" />
                                <form:errors class="text-danger" path="currentPassword"/>
                            </label>
                        </h3>
                    </div>
                    <div class="form-group">
                        <h3>
                            <label><spring:message code="newPassword" />:
                                <br/>
                                <form:password class="form-control" path="newPassword" />
                                <form:errors class="text-danger" path="newPassword"/>
                            </label>
                        </h3>
                    </div>
                    <input type="submit" class="btn btn-default btn-lg" value="<spring:message code="change.btn" />">
                </div>
            </div>
        </div>
    </div>
</form:form>

<%@ include file="../includes/footer.jsp"%>