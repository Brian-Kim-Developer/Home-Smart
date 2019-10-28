<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../includes/header.jsp"%>

	<form action="<c:url value='/member/step2'/>" method="post">
		<div class="container">
			<div class="row">
				<br/>
				<br/>
				<br/>
				<br/>
				<div class="col-lg-6 col-md-offset-3" align="center">
					<div class="panel panel-default">
						<div class="panel-heading">
							<spring:message code="term.agree" />
						</div>
						<div class="panel-body">
							<p>
                                Basic features (Search, View Full List, View in Detail) are supported before sign up.
                                Email verification using real email is not supported in this website for easier approach but should follow general email format (xxx@xxx.com).
                                Once you register and sign in, you will find additional features (User Profile, Change Password, Delete Account, Register&Update&Delete Board, My List, My Favourites, Message Service, Sign out).
                                Make yourself at home!
							</p>
						</div>
						<div class="panel-footer">
							<label>
                                I accept the Terms of Service.
								<input type="checkbox" name="agree" value="true">
							</label>
							</br>
							<label>
								<button type="submit" class="btn btn-default btn-lg" >Next Step</button>
							</label>
						</div>
					</div>
				</div>
				<!-- /.col-lg-4 -->
			</div>
		</div>
	</form>

<%@ include file="../includes/footer.jsp"%>