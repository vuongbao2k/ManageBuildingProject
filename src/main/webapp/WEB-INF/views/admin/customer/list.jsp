<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerListURL" value="/admin/customer-list"/>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="formUrl" value="/admin/customer-list"/>
<c:url var="formAjax" value="/api/customer"/>
<html>
<head>
    <title>Danh sách toà nhà</title>
</head>
<body>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Trang chủ</a>
                    </li>
                    <li class="active">Quản lý khách hàng</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box ui-sortable-handle">
                            <div class="widget-header">
                                <h5 class="widget-title">Tìm kiếm</h5>

                                <div class="widget-toolbar">
                                    <a href="#" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>

                            <div class="widget-body" style="font-family: 'Times New Roman', Times, serif;">
                                <div class="widget-main">
                                    <form:form id="listForm" modelAttribute="customerSearch" action="${customerListURL}" method="GET">
                                     <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label class="name">Tên khách hàng</label>
<%--                                                    <input type="text" class="form-control" name="name" value="${modelSearch.name}">--%>
                                                    <form:input class="form-control" path="fullName"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="name">Di động</label>
<%--                                                    <input type="number" class="form-control" name="floorArea" value="${modelSearch.floorArea}">--%>
                                                    <form:input class="form-control" path="phone"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="name">Email</label>
<%--                                                    <input type="number" class="form-control" name="floorArea" value="${modelSearch.floorArea}">--%>
                                                    <form:input class="form-control" path="email"/>
                                                </div>
                                            </div>
                                        </div
                                        <security:authorize access="hasRole('MANAGER')">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label class="name">Nhân viên</label>
                                                    <form:select class="form-control" path="staffId">
                                                        <form:option value="">---Chọn nhân viên---</form:option>
                                                        <form:options items="${staffmaps}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        </security:authorize>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button type="button" class="btn btn-danger" id="btnSearchCustomer">
                                                        <i class="ace-icon glyphicon glyphicon-search"></i>Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </form:form>
                                </div>
                            </div>
                            <div class="pull-right">
                                <a href="/admin/customer-edit">
                                    <button class="btn btn-info" title="Thêm khách hàng">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                            <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                            <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                            <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                        </svg>
                                    </button>
                                </a>

                                <button class="btn btn-danger" title="Xoá khách hàng" id="btnDeleteCustomer">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                                        <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                        <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                        <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                    </svg>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Bảng danh sách -->
                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                                    <display:table name="customerSearch.listResult" cellspacing="0" cellpadding="0"
                                                   requestURI="${formUrl}" partialList="true" sort="external"
                                                   size="${customerSearch.totalItems}" defaultsort="2" defaultorder="ascending"
                                                   id="tableList" pagesize="${customerSearch.maxPageItems}"
                                                   export="false"
                                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                                   style="margin: 3em 0 1.5em;">
                                        <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                        headerClass="center select-cell">
                                            <fieldset>
                                                <input type="checkbox" name="checkList" value="${tableList.id}"
                                                       id="checkbox_${tableList.id}" class="check-box-element"/>
                                            </fieldset>
                                        </display:column>
                                        <display:column headerClass="text-left" property="fullName" title="Tên khách hàng"/>
                                        <display:column headerClass="text-left" property="phone" title="Di động"/>
                                        <display:column headerClass="text-left" property="email" title="Email"/>
                                        <display:column headerClass="text-left" property="demand" title="Nhu cầu"/>
                                        <display:column headerClass="text-left" property="createdBy" title="Người thêm"/>
                                        <display:column headerClass="text-left" property="createdDate" title="Ngày thêm"/>
                                        <display:column headerClass="text-left" property="status" title="Tình trạng"/>

                                        <display:column headerClass="col-actions" title="Thao tác">
<%--                                            <c:if test="${tableList.roleCode != 'MANAGER'}">--%>
                                                <div class="hidden-sm hidden-xs btn-group">
                                                    <security:authorize access="hasRole('MANAGER')">
                                                    <button class="btn btn-xs btn-success" title="Giao khách hàng" onclick="assignmentCustomer(${tableList.id})">
                                                        <i class="ace-icon glyphicon glyphicon-list"></i>
                                                    </button>
                                                    </security:authorize>

                                                    <a class="btn btn-xs btn-info" title="Sửa khách hàng" href="/admin/customer-edit-${tableList.id}">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                    </a>

                                                    <button class="btn btn-xs btn-danger" title="Xoá khách hàng" onclick="deleteCustomer(${tableList.id})">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </button>
                                                </div>
<%--                                            </c:if>--%>
<%--                                            <c:if test="${tableList.roleCode == 'MANAGER'}">--%>
<%--                                                <p>Không đươc thao tác</p>--%>
<%--                                            </c:if>--%>
                                        </display:column>
                                    </display:table>
                                </div>
<%--                        <table id="tableList" style="margin: 3em 0 0;" class="table table-striped table-bordered table-hover">--%>
<%--                            <thead>--%>
<%--                            <tr>--%>
<%--                                <th class="center">--%>
<%--                                    <label class="pos-rel">--%>
<%--                                        <input type="checkbox" class="ace" name="checkList" value="">--%>
<%--                                        <span class="lbl"></span>--%>
<%--                                    </label>--%>
<%--                                </th>--%>
<%--                                <th>Tên khách hàng</th>--%>
<%--                                <th>Di động</th>--%>
<%--                                <th>Email</th>--%>
<%--                                <th>Nhu cầu</th>--%>
<%--                                <th>Người thêm</th>--%>
<%--                                <th>Ngày thêm</th>--%>
<%--                                <th>Tình trạng</th>--%>
<%--                                <th>Thao tác</th>--%>
<%--                            </tr>--%>
<%--                            </thead>--%>

<%--                            <tbody>--%>
<%--                            <c:forEach var="item" items="${customers}">--%>
<%--                                <tr>--%>
<%--                                <td class="center">--%>
<%--                                    <label class="pos-rel">--%>
<%--                                        <input type="checkbox" class="ace" name="checkList" value="${item.id}">--%>
<%--                                        <span class="lbl"></span>--%>
<%--                                    </label>--%>
<%--                                </td>--%>

<%--                                <td>${item.fullName}</td>--%>
<%--                                <td>${item.phone}</td>--%>
<%--                                <td>${item.email}</td>--%>
<%--                                <td>${item.demand}</td>--%>
<%--                                <td>${item.createdBy}</td>--%>
<%--                                <td>${item.createdDate}</td>--%>
<%--                                <td>${item.status}</td>--%>
<%--                                <td>--%>
<%--                                    <div class="hidden-sm hidden-xs btn-group">--%>
<%--                                        <button class="btn btn-xs btn-success" title="Giao khách hàng" onclick="assignmentCustomer(${item.id})">--%>
<%--                                            <i class="ace-icon glyphicon glyphicon-list"></i>--%>
<%--                                        </button>--%>

<%--                                        <a class="btn btn-xs btn-info" title="Sửa khách hàng" href="/admin/customer-edit-${item.id}">--%>
<%--                                            <i class="ace-icon fa fa-pencil bigger-120"></i>--%>
<%--                                        </a>--%>

<%--                                        <button class="btn btn-xs btn-danger" title="Xoá khách hàng" onclick="deleteCustomer(${item.id})">--%>
<%--                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>--%>
<%--                                        </button>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            </c:forEach>--%>
<%--                            </tbody>--%>
<%--                        </table>--%>
                    </div><!-- /.span -->
                </div>

            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->
<!-- Modal -->
<div class="modal fade" id="assignmentCustomerModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">

<%--        <!-- Modal content-->--%>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table id="staffList" class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th class="center">Chọn</th>
                            <th>Tên nhân viên</th>
                        </tr>
                    </thead>

                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" name="customerId" id="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnassignmentCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>
    <!-- basic scripts -->
    <script>
        function assignmentCustomer(customerId){
            $('#assignmentCustomerModal').modal();
            loadStaff(customerId);
            $('#customerId').val(customerId);
        }

        function loadStaff(customerId) {
            $.ajax({
                type: "GET",
                url: "${customerAPI}/"+ customerId + "/staff",
                contentType: "application/json",
                dataType: "JSON",
                success: function(response){
                    var row = ''
                    $.each(response.data, function (index, it) {
                        row += '<tr>';
                        row += '<td class="center"> <input type="checkbox" value="' + it.staffId + '" id="checkbox_' + it.staffId + '" '+ it.checked + '></td>';
                        row += '<td class="center">' + it.fullName + '</td>';
                        row += '</tr>';
                    })
                    $('#staffList tbody').html(row);
                    console.info("Success");
                },
                error: function(response){
                    console.log("Failed");
                    window.location.href = "<c:url value="/admin/customer-list?message=error"/>";
                    console.log(response);
                }
            });
        }

        $('#btnassignmentCustomer').click(function(e){
            e.preventDefault();
            var data = {};
            data['customerId'] = $('#customerId').val();
            var staff = $('#staffList').find('tbody input[type = checkbox]:checked').map(function(){
                return $(this).val();
            }).get();
            data['staffs'] = staff;
            if(data['staffs'] != '') {
                assignmentStaff(data);
            }
            console.log("OK");
        })

        function assignmentStaff(data) {
            $.ajax({
                type: "POST",
                url: "${customerAPI}/assignment",
                data: JSON.stringify(data),
                contentType: "application/json",
                // dataType: "JSON",
                success: function(response){
                    // confirm("Success");
                    window.location.href = "<c:url value="/admin/customer-list?message=success"/>";
                },
                error: function(response){
                    // console.info("Failed");
                    window.location.href = "<c:url value="/admin/customer-list?message=error"/>";
                    // console.log(response);
                }
            });
        }

        $('#btnSearchCustomer').click(function (e){
            e.preventDefault();
            $('#listForm').submit();
        })

        function deleteCustomer(customerId){
            showAlertBeforeDelete(function (){
                handleDeleteCustomer([customerId]);
            });
        }

        $('#btnDeleteCustomer').click(function(e){
            e.preventDefault();
            var customerIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function(){
                return $(this).val();
            }).get();
            handleDeleteCustomer(customerIds);
        })

        function handleDeleteCustomer(data){
            $.ajax({
                type: "DELETE",
                url: "${customerAPI}",
                data: JSON.stringify(data),
                contentType: "application/json",
                // dataType: "JSON",
                success: function(response){
                    alert("Delete customer successfully!");
                    window.location.href = "<c:url value='/admin/customer-list?message=success'/>";
                },
                error: function(response){
                    console.log("Failed");
                    console.log(response);
                    window.location.href = "<c:url value='/admin/customer-list?message=error'/>";
                }
            });
        }
    </script>
</body>
</html>
