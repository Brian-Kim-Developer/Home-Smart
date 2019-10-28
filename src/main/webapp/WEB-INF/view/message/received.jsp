<%@ include file="../includes/header.jsp"%>

<div class="container">

    <div class="row">

        <div class="col-lg-12">
            <h1 class="page-header">
                Received Items
            </h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <c:if test="${empty messages}">
        <div class="jumbotron" align="center">
            <p>No Result</p>
        </div>
    </c:if>
    <c:forEach var="message" items="${messages}">
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        From ${message.senderDTO.name}
                    </div>
                    <div class="panel-heading">
                        Date: ${message.registerDateTime}
                    </div>
                    <div class="panel-body">
                        <p>${message.message}</p>
                    </div>
                    <div class="panel-footer">
                        <form action="<c:url value='/message/step1'/>" method="post">
                            <input type="hidden" name="senderId" value="${authInfo.id}">
                            <input type="hidden" name="recipientId" value="${message.senderDTO.id}">
                            <button type="submit" class="btn btn-info">Send Message</button>
                        </form>
                    </div>
                </div>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </c:forEach>
</div>

<%@ include file="../includes/footer.jsp"%>