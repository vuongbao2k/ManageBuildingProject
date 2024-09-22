<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list"/>
<c:url var="buildingAPI" value="/api/building"/>
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
                    <li class="active">Quản lý toà nhà</li>
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
                                    <form:form id="listForm" modelAttribute="modelSearch" action="${buildingListURL}" method="GET">
                                     <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label class="name">Tên toà nhà</label>
<%--                                                    <input type="text" class="form-control" name="name" value="${modelSearch.name}">--%>
                                                    <form:input class="form-control" path="name"/>
                                                </div>
                                                <div class="col-xs-6">
                                                    <label class="name">Diện tích sàn</label>
<%--                                                    <input type="number" class="form-control" name="floorArea" value="${modelSearch.floorArea}">--%>
                                                    <form:input class="form-control" path="floorArea"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <label class="name">Quận</label>
                                                    <form:select class="form-control" path="district">
                                                        <form:option value="">---Chọn quận---</form:option>
                                                        <form:options items="${districts}"/>
                                                    </form:select>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label class="name">Phường</label>
                                                    <form:input class="form-control" path="ward"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label class="name">Đường</label>
                                                    <form:input class="form-control" path="street"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label class="name">Số tầng hầm</label>
                                                    <form:input class="form-control" path="numberOfBasement"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="name">Hướng</label>
                                                    <form:input class="form-control" path="direction"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="name">Hạng</label>
                                                    <form:input class="form-control" path="level"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <label class="name">Diện tích từ</label>
                                                    <form:input class="form-control" path="areaFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="name">Diện tích đến</label>
                                                    <form:input class="form-control" path="areaTo"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="name">Giá thuê từ</label>
                                                    <form:input class="form-control" path="rentPriceFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="name">Giá thuê đến</label>
                                                    <form:input class="form-control" path="rentPriceTo"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <label class="name">Tên quản lý</label>
                                                    <form:input class="form-control" path="managerName"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label class="name">SĐT quản lý</label>
                                                    <form:input class="form-control" path="managerPhone"/>
                                                </div>
                                                <div class="col-xs-2">
                                                    <label class="name">Nhân viên</label>
                                                    <form:select class="form-control" path="staffId">
                                                        <form:option value="">---Chọn nhân viên---</form:option>
                                                        <form:options items="${listStaff}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <form:checkboxes items="${buildingType}" path="typeCode"/>
<%--                                                    <label class="checkbox-inline">--%>
<%--                                                        <input name="typeCode" type="checkbox" value="noi-that">Nội thất--%>
<%--                                                    </label>--%>
<%--                                                    <label class="checkbox-inline">--%>
<%--                                                        <input name="typeCode" type="checkbox" value="nguyen-can">Nguyên căn--%>
<%--                                                    </label>--%>
<%--                                                    <label class="checkbox-inline">--%>
<%--                                                        <input name="typeCode" type="checkbox" value="tang-tret">Tầng trệt--%>
<%--                                                    </label>--%>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button type="button" class="btn btn-danger" id="btnSearchBuilding">
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
                                <a href="/admin/building-edit">
                                    <button class="btn btn-info" title="Thêm toà nhà">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                            <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                            <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                            <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                        </svg>
                                    </button>
                                </a>

                                <button class="btn btn-danger" title="Xoá toà nhà" id="btnDeleteBuilding">
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
                        <table id="tableList" style="margin: 3em 0 0;" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" name="checkList" value="">
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th>Tên toà nhà</th>
                                <th>Địa chỉ</th>
                                <th>Số tầng hầm</th>
                                <th>Tên quản lý</th>
                                <th>Số điện thoại</th>
                                <th>Diện tích sàn</th>
                                <th>Diện tích trống</th>
                                <th>Diện tích thuê</th>
                                <th>Phí môi giới</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="item" items="${buildingList}">
                                <tr>
                                <td class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" name="checkList" value="${item.id}">
                                        <span class="lbl"></span>
                                    </label>
                                </td>

                                <td>${item.name}</td>
                                <td>${item.address}</td>
                                <td>${item.numberOfBasement}</td>
                                <td>${item.managerName}</td>
                                <td>${item.managerPhone}</td>
                                <td>${item.floorArea}</td>
                                <td>${item.emptyArea}</td>
                                <td>${item.rentArea}</td>
                                <td>${item.brokerageFee}</td>
                                <td>
                                    <div class="hidden-sm hidden-xs btn-group">
                                        <button class="btn btn-xs btn-success" title="Giao toà nhà" onclick="assignmentBuilding(${item.id})">
                                            <i class="ace-icon glyphicon glyphicon-list"></i>
                                        </button>

                                        <a class="btn btn-xs btn-info" title="Sửa toà nhà" href="/admin/building-edit-${item.id}">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </a>

                                        <button class="btn btn-xs btn-danger" title="Xoá toà nhà" onclick="deleteBuilding(${item.id})">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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
<div class="modal fade" id="assignmentBuildingModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">

        <!-- Modal content-->
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
                <input type="hidden" name="buildingId" id="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnassignmentBuilding">Giao toà nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>
    <!-- basic scripts -->
    <script>
        function assignmentBuilding(buildingId){
            $('#assignmentBuildingModal').modal();
            loadStaff(buildingId);
            $('#buildingId').val(buildingId);
        }

        function loadStaff(buildingId) {
            $.ajax({
                type: "GET",
                url: "${buildingAPI}/"+ buildingId + "/staff",
                // data: JSON.stringify(data),
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
                    window.location.href = "<c:url value="/admin/building-list?message=error"/>";
                    console.log(response);
                }
            });
        }

        $('#btnassignmentBuilding').click(function(e){
            e.preventDefault();
            var data = {};
            data['buildingId'] = $('#buildingId').val();
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
                url: "${buildingAPI}/assignment",
                data: JSON.stringify(data),
                contentType: "application/json",
                // dataType: "JSON",
                success: function(response){
                    // confirm("Success");
                    window.location.href = "<c:url value="/admin/building-list?message=success"/>";
                },
                error: function(response){
                    // console.info("Failed");
                    window.location.href = "<c:url value="/admin/building-list?message=error"/>";
                    // console.log(response);
                }
            });
        }

        $('#btnSearchBuilding').click(function (e){
            e.preventDefault();
            $('#listForm').submit();
        })

        function deleteBuilding(id){
            var buildingId = [id];
            deleteBuildings(buildingId);
        }
        $('#btnDeleteBuilding').click(function(e){
            e.preventDefault();
            var buildingIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function(){
                return $(this).val();
            }).get();
            deleteBuildings(buildingIds);
        })
        function deleteBuildings(data){
            $.ajax({
                type: "DELETE",
                url: "${buildingAPI}/"+ data,
                data: JSON.stringify(data),
                contentType: "application/json",
                // dataType: "JSON",
                success: function(response){
                    console.log("Success");
                    window.location.href = "<c:url value='/admin/building-list?message=success'/>";
                },
                error: function(response){
                    console.log("Failed");
                    console.log(response);
                    window.location.href = "<c:url value='/admin/building-list?message=error'/>";
                }
            });
        }
    </script>
</body>
</html>
