<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../includes/header.jsp"%>

<div class="container">

    <div class="row">
        <div class="col-lg-12">
            <h2 class="page-header">
                <c:if test="${empty authInfo}">
                    <p>Welcome to HomeSmart!</p>
                </c:if>
                <c:if test="${! empty authInfo}">
                    <p>Welcome! ${authInfo.name}</p>
                </c:if>
            </h2>
            <h1 class="page-header">House For Rent
                <small>Look For Your Fantastic Place!</small>
                <c:if test="${! empty authInfo}">
                    <button class="btn btn-success" onclick="location.href='<c:url value='/board/step1'/>'">Register Your Place!</button>
                    <button class="btn btn-info" onclick="location.href='<c:url value='/board/myList?memberId=${authInfo.id}'/>'">My List</button>
                    <button class="btn btn-warning" onclick="location.href='<c:url value='/board/favourite?memberId=${authInfo.id}'/>'"><i class="fa fa-star" aria-hidden="true"></i> FAVOURITES</button>
                </c:if>
            </h1>
        </div>
    </div>
    <c:if test="${empty boards}">
        <div class="jumbotron" align="center">
            <p>No Result</p>
        </div>
    </c:if>
    <c:forEach var="board" items="${boards}">
        <c:set var="loop_flag" value="false" />
        <div class="row">

            <c:forEach var="file" items="${board.fileVOs}">
                <c:if test="${not loop_flag }">
                    <c:if test="${file.title == true}">
                        <div class="col-lg-7 col-md-7">
                            <a href="#">
                                <img class="img-responsive" src="<c:url value='/showImage?imgId=${file.fno}'/>" alt="" style="width:700px;height:300px;">
                            </a>
                        </div>
                        <c:set var="loop_flag" value="true" />
                    </c:if>
                </c:if>
            </c:forEach>

            <div class="col-lg-5 col-md-5">
                <h2>$${board.price}</h2>
                <h4>${board.type}</h4>
                <p>Bathroom: ${board.bathroom}</p>
                <p>Bedroom: ${board.bedroom}</p>
                <p>Den: ${board.den}</p>
                <a class="btn btn-primary" href="<c:url value='/board/${board.bno}'/>">View in Detail <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>

        <hr>
    </c:forEach>

    <div class="row text-center">
        <div class="col-lg-12">
            <ul class="pagination">
                <li><a href="">&laquo;</a>
                </li>
                <li class="active"><a href="">1</a>
                </li>
                <%--<li><a href="#">2</a>
                </li>
                <li><a href="#">3</a>
                </li>
                <li><a href="#">4</a>
                </li>
                <li><a href="#">5</a>
                </li>--%>
                <li><a href="">&raquo;</a>
                </li>
            </ul>
        </div>
     </div>

    <hr>

</div>
<!-- /.container -->

<%@ include file="../includes/footer.jsp"%>