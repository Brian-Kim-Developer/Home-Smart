<%@ include file="../includes/header.jsp"%>



<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">To ${recipientName}</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">

            <div class="panel-heading">Message Form</div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <form action="<c:url value='/message/step2'/>" method="post">
                    <div class="form-group">
                        <textarea class="form-control" rows="16" name='message'></textarea>
                    </div>

                    <input type="hidden" name="senderId" value="${senderId}">
                    <input type="hidden" name="recipientId" value="${recipientId}">

                    <button type="submit" class="btn btn-primary">Send</button>
                    <button type="reset" class="btn btn-default">Reset</button>
                </form>

            </div>
            <!--  end panel-body -->

        </div>
        <!--  end panel-body -->
    </div>
    <!-- end panel -->
</div>
<!-- /.row -->

<%@ include file="../includes/footer.jsp"%>