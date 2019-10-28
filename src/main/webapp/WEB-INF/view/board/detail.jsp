<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<%@ include file="../includes/header.jsp"%>

<div class="container">

    <div class="row">

        <div class="col-lg-12">
            <h1 class="page-header">View in Detail</h1>
        </div>

    </div>

    <div class="row">

        <div class="col-lg-7 col-md-3">
            <c:forEach var="file" items="${board.fileVOs}">
                <c:if test="${file.title == true}">
                    <div class="panel panel-info">
                        <a href="#">
                            <img class="img-responsive" src="<c:url value='/showImage?imgId=${file.fno}'/>" alt="" style="width:700px;height:300px;">
                        </a>
                    </div>
                </c:if>
            </c:forEach>
            <h3>Description</h3>
            <div class="jumbotron">
                <p>${board.content}</p>
            </div>
        </div>

        <div class="col-md-4">

            <c:if test="${ authInfo.email == board.memberDTO.email }">
                <button class="btn btn-primary" onclick="location.href='<c:url value='/board/update/${board.bno}'/>'">Update</button>
                <button class="btn btn-danger" onclick="location.href='<c:url value='/board/delete/${board.bno}'/>'">Delete</button>
            </c:if>

            <c:if test="${ !empty authInfo && authInfo.id != board.memberDTO.id }">
                <form action="<c:url value='/message/step1'/>" method="post">
                    <input type="hidden" name="senderId" value="${authInfo.id}">
                    <input type="hidden" name="recipientId" value="${board.memberDTO.id}">
                    <button type="submit" class="btn btn-info">Send Message</button>
                </form>
                </br>
                <form action="<c:url value='/board/add/favourite'/>" method="post">
                    <input type="hidden" name="memberId" value="${authInfo.id}">
                    <input type="hidden" name="bno" value="${board.bno}">
                    <button type="submit" class="btn btn-warning"><i class="fa fa-star" aria-hidden="true"></i> ADD to FAVOURITES</button>
                </form>
            </c:if>

            <h3>Place Details</h3>
            <div class="form-group">
                <label>Landlord Contact Information</label>
                <div class="form-control" name='contactInfo'>${board.contactInfo}</div>
            </div>
            <div class="form-group">
                <label>Location</label>
                <div class="form-control" name='location'>${board.location}</div>
            </div>
            <div class="form-group">
                <label>Type</label>
                <div class="form-control" name='type'>${board.type}</div>
            </div>
            <div class="form-group">
                <label>Bathroom</label>
                <div class="form-control" name='bathroom'>${board.bathroom}</div>
            </div>
            <div class="form-group">
                <label>Bedroom</label>
                <div class="form-control" name='bedroom'>${board.bedroom}</div>
            </div>
            <div class="form-group">
                <label>Den</label>
                <div class="form-control" name='den'>${board.den}</div>
            </div>
            <div class="form-group">
                <label>Price($)</label>
                <div class="form-control" name='price'>${board.price}</div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">Extra Images</h3>
        </div>

        <c:forEach var="file" items="${board.fileVOs}">
            <c:if test="${file.title == false}">
                <div class="col-lg-4">
                    <div class="panel panel-info">
                        <a href="#">
                            <img class="img-responsive" src="<c:url value='/showImage?imgId=${file.fno}'/>" alt="" style="width:700px;height:220px;">
                        </a>
                    </div>
                </div>
                <!-- /.col-lg-4 -->
            </c:if>
        </c:forEach>
    </div>

</div>
<!-- /.container -->

<!-- JavaScript -->
<script src="js/jquery-1.10.2.js"></script>
<script src="js/bootstrap.js"></script>

<%@ include file="../includes/footer.jsp"%>