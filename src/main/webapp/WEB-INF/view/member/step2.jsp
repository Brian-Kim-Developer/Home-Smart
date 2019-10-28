<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../includes/header.jsp"%>

<c:url var="addUrl" value="/member/step3"/>
<form:form action="${addUrl}" modelAttribute="registerMemberRequest">
    <div class="container">
        <div class="row">
            <br/>
            <br/>
            <br/>
            <br/>
            <div class="col-lg-6 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-heading" align="center">
                        Registration Form
                    </div>
                    <div class="panel-body">
                        <p>
                            <label><spring:message code="email" />:</label>
                            <form:input class="form-control" path="email" placeholder="Email"/>
                            <form:errors path="email" />
                        </p>
                        <p>
                            <label><spring:message code="name" />:</label>
                            <form:input class="form-control" path="name" placeholder="Name"/>
                            <form:errors path="name" />
                        </p>
                        <p>
                            <label><spring:message code="password" />:</label>
                            <form:password class="form-control" path="password" placeholder="Password"/>
                            <form:errors path="password" />
                        </p>
                        <p>
                            <label><spring:message code="password.confirm" />:</label>
                            <form:password class="form-control" path="confirmPassword" placeholder="Confirm Password"/>
                            <form:errors path="confirmPassword" />
                        </p>
                    </div>
                    <div class="panel-footer" align="center">
                        <button type="submit" class="btn btn-default btn-lg" ><spring:message code="register.btn" /></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form:form>

<%--
    <form action="step3" method="post">
    <p>
        <label>이메일:<br>
        <input type="text" name="email" id="email" value="${registerRequest.email}">
        </label>
    </p>
    <p>
        <label>이름:<br>
        <input type="text" name="name" id="name" value="${registerRequest.name}">
        </label>
    </p>
    <p>
        <label>비밀번호:<br>
        <input type="password" name="password" id="password">
        </label>
    </p>
    <p>
        <label>비밀번호 확인:<br>
        <input type="password" name="confirmPassword" id="confirmPassword">
        </label>
    </p>
    <input type="submit" value="가입 완료">
    </form>
 --%>

<%@ include file="../includes/footer.jsp"%>