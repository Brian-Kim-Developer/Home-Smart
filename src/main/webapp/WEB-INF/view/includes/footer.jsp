<footer>
    <div class="row">
        <div class="col-lg-12" align="center">
            <p >Copyright &copy; Gyeongwon Kim</p>
        </div>
    </div>
</footer>

</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- Core Scripts - Include with every page -->
<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/resources/js/plugins/metisMenu/jquery.metisMenu.js' />"></script>

<!-- Page-Level Plugin Scripts - Tables -->
<script src="<c:url value='/resources/js/plugins/dataTables/jquery.dataTables.js' />"></script>
<script src="<c:url value='/resources/js/plugins/dataTables/dataTables.bootstrap.js' />"></script>

<!-- SB Admin Scripts - Include with every page -->
<script src="<c:url value='/resources/js/sb-admin.js' />"></script>

<script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
</script>

<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
        $(".sidebar-nav")
            .attr("class", "sidebar-nav navbar-collapse collapse")
            .attr("aria-expanded", 'false')
            .attr("style", "height:1px");
    });
</script>

</body>

</html>
