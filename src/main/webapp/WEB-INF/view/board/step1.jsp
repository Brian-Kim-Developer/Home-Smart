<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/header.jsp"%>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Attract Home Seekers!</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">

            <div class="panel-heading">Board Registration Form</div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <form action="<c:url value='/board/step2'/>" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>Landlord Contact Information</label>
                        <input class="form-control" name='contactInfo' required>
                    </div>
                    <div class="form-group">
                        <label>House Description (Max: 500 characters)</label>
                        <textarea class="form-control" rows="16" name='content' maxlength="500" required></textarea>
                        <script>
                            $(function() {
                                $("textarea[maxlength]").bind('input propertychange', function() {
                                    var maxLength = $(this).attr('maxlength');
                                    if ($(this).val().length > maxLength) {
                                        $(this).val($(this).val().substring(0, maxLength));
                                    }
                                })
                            });
                        </script>
                    </div>
                    <div class="form-group">
                        <label>Location</label>
                        <input class="form-control" name='location' required>
                    </div>
                    <div class="form-group">
                        <label>House Type</label>
                        <select class="form-control" name="type" required>
                            <option value="House">House</option>
                            <option value="Town House">Town House</option>
                            <option value="Condo">Condo</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Baths</label>
                        <select class="form-control" name="bathroom" required>
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Beds</label>
                        <select class="form-control" name="bedroom" required>
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Den</label>
                        <select class="form-control" name="den" required>
                            <option value="0">0</option>
                            <option value="1">1</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Price($)</label>
                        <input class="form-control" name='price' required>
                    </div>
                    <div class="form-group">
                        <label>Title Image</label>
                        <input type="file" name="file" required>
                    </div>
                    <div class="form-group">
                        <label>Extra Images</label>
                        <input type="file" name="files" multiple required>
                    </div>

                    <input type="hidden" name="memberEmail" value="${authInfo.email}">

                    <button type="submit" class="btn btn-primary">Register</button>
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
<%@include file="../includes/footer.jsp"%>
