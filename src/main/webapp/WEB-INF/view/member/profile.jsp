<%--
  Created by IntelliJ IDEA.
  User: lg
  Date: 2019-08-10
  Time: 오전 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../includes/header.jsp"%>

<br/>
<br/>

<!-- /.col-lg-4 -->
<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            User Profile
        </div>
        <div class="panel-body">
            <h4>Name</h4>
            <blockquote>
                <p>${user.name}</p>
            </blockquote>
            <h4>Email</h4>
            <blockquote>
                <p>${user.email}</p>
            </blockquote>
            <h4>Registration Date</h4>
            <blockquote>
                <p>${user.registerDateTime}</p>
            </blockquote>
            <h4>Password</h4>
            <blockquote>
                <button type="button" class="btn btn-default btn-lg" onclick="location.href='<c:url value='/user/changePassword'/>'">Change Password</button>
            </blockquote>
            <h4>Account</h4>
            <blockquote>
                <button type="button" class="btn btn-danger btn-lg" onclick="location.href='<c:url value='/user/delete'/>'">Delete Account</button>
                <i class="fa fa-exclamation-triangle fa-fw"></i> *This action will remove every records you have made in this web application, and they will not be recovered.
            </blockquote>
        </div>
        <!-- /.panel-body -->
    </div>
    <!-- /.panel -->
</div>

<%@ include file="../includes/footer.jsp"%>