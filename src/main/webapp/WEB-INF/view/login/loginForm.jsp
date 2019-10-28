<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../includes/header.jsp"%>

<form:form modelAttribute="loginCommand">
    <form:errors />

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <fieldset>
                                <div class="form-group">
                                    <form:input path="email" class="form-control" placeholder="E-mail" type="email" autofocus="true"/>
                                    <form:errors path="email"/>
                                </div>
                                <div class="form-group">
                                    <form:password path="password" class="form-control" placeholder="Password"/>
                                    <form:errors path="password"/>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <form:checkbox path="rememberEmail"/>Remember Me
                                    </label>
                                </div>
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="<spring:message code="login.btn" />">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form:form>

<%@ include file="../includes/footer.jsp"%>