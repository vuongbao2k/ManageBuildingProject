<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="customerEditURL" value="/admin/customer-edit"/>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Thông tin khách hàng</title>
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
                    <c:if test="${not empty customerEdit.id}">
                        <li class="active">Chỉnh sửa khách hàng</li>
                    </c:if>
                    <c:if test="${empty customerEdit.id}">
                        <li class="active">Thêm khách hàng</li>
                    </c:if>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        Thông tin khách hàng
                    </h1>
                </div><!-- /.page-header -->
                <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                    <form:form modelAttribute="customerEdit" action="${customerEditURL}" id="listForm" method="post">
                        <div class="col-xs-12">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-xs-3">Tên khách hàng</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="fullName"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Số điện thoại</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="phone"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Email</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="email"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Tên công ty</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="companyName"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Nhu cầu</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="demand"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Tình trạng</label>
                                <div class="col-xs-9">
                                    <form:select class="form-control" path="status">
                                        <form:option value="">---Chọn tình trạng---</form:option>
                                        <form:options items="${statusCode}"/>
                                    </form:select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class="col-xs-9">
                                    <c:if test="${not empty customerEdit.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">Cập nhật thông tin</button>
                                        <button type="button" class="btn btn-primary" id="btnCancel">Huỷ</button>
                                    </c:if>
                                    <c:if test="${empty customerEdit.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">Thêm khách hàng</button>
                                        <button type="button" class="btn btn-primary" id="btnCancel">Huỷ</button>
                                    </c:if>
                                </div>
                            </div>
                            <form:hidden path="id" id="customerId"/>
<%--                            <form:hidden path="modifiedDate" id="modifiedDate"/>--%>
<%--                            <form:hidden path="modifiedBy" id="modifiedBy"/>--%>
                        </form>
                    </div>
                    </form:form>
                </div>



            </div><!-- /.page-content -->

            <c:forEach var="item" items="${transactionType}">
                <div class="col-xs-12">
                    <div class="col-sm-12">
                        <h3 class="header smaller lighter blue">${item.value}</h3>
                        <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}',${customerEdit.id})">
                            <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
                        </button>
                    </div>
                    <div class="col-xs-12">
                            <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Ngày tạo</th>
                                <th>Người tạo</th>
                                <th>Ngày sửa</th>
                                <th>Người sửa</th>
                                <th>Chi tiết giao dịch</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="list" items="${item.key == 'CSKH' ? CSKHList : DDXList}">
                                <tr>
                                    <td>${list.createdDate}</td>
                                    <td>${list.createdBy}</td>
                                    <td>${list.modifiedDate}</td>
                                    <td>${list.modifiedBy}</td>
                                    <td>${list.note}</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-xs btn-primary" title="Sửa chi tiết giao dịch"
                                                onclick="showFormUpdate(${list.id},'${item.key}')">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
<%--                                                <i class="bi bi-pencil-square bigger-120"></i> --%>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                            </table>
                     </div>
                  </div>
            </c:forEach>
        </div>
    </div><!-- /.main-content -->
    <div class="modal fade" id="transactionTypeModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>
            <div class="modal-body">
               <div class="form-group has-success" id="formSerial">
                    <label for="transactionDetail" class="col-xs-12 col-sm-3 control-label no-padding-right">Chi tiết giao dịch</label>
                    <div class="col-xs-12 col-sm-9">
                        <span class="block input-icon input-icon-right">
                            <input type="text" id="transactionDetail" value="" class="width-100">
                        </span>
                    </div>
                </div>
                <input type="hidden" name="customerId" id="customerId" value="">
                <input type="hidden" name="code" id="code" value="">
                <input type="hidden" name="id" id="id" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAddOrUpdateTransaction">Thêm giao dịch</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
    </div>

    <script>
        function transactionType(code, customerId){
            $('#transactionTypeModal').modal();
            $('#customerId').val(customerId);
            $('#code').val(code);
            $('#id').val(null);
            $('#transactionDetail').val(null);
        }

        function showFormUpdate(id,code){
            $('#transactionTypeModal').modal();
            $('#id').val(id);
            $('#code').val(code);
            loadTransactionDetail(id);
        }

        function loadTransactionDetail(id){
            $.ajax({
                type: "GET",
                url: "${customerAPI}/"+id+"/details",
                data: JSON.stringify(id),
                contentType: "application/json",
                dataType: "JSON",
                success: function(respond){
                    $('#transactionDetail').val(respond.data.note);
                },
                error: function(respond){
                    window.alert("Fail");
                }
            });
        }

        $('#btnAddOrUpdateTransaction').click(function(e){
            e.preventDefault();
            var data = {};
            var id = $('#id').val();
            data['id'] = id;
            let customerId = $('#customerId').val();
            data['customerId'] = customerId;
            data['code'] = $('#code').val()
            data['note'] = $('#transactionDetail').val();
            // var formData = $('#listForm').serializeArray();
            //call api
            if (id !== "") {
                updateTransaction(data, customerId);
            }
            addTransaction(data, customerId);
        });

        function updateTransaction(data, customerId){
            $.ajax({
                type: "PUT",
                url: "${customerAPI}/transaction",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function(respond){
                    alert("Update transaction successfully!")
                    window.location.href = "/admin/customer-edit-"+customerId;
                },
                error: function(respond){
                    console.log(respond);
                    <%--window.location.href = "<c:url value='/admin/customer-edit?message=error'/>";--%>
                }
            });
        }

        function addTransaction(data, customerId){
            $.ajax({
                type: "POST",
                url: "${customerAPI}/transaction",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function(respond){
                    alert("Add transaction successfully!")
                    window.location.href = "/admin/customer-edit-"+customerId;
                },
                error: function(respond){
                    console.log(respond);
                    <%--window.location.href = "<c:url value='/admin/customer-edit?message=error'/>";--%>
                }
            });
        }

        $('#btnAddOrUpdateCustomer').click(function(){
            var data = {};
            var formData = $('#listForm').serializeArray();
            $.each(formData,function(i,v){
                data[v.name] = v.value;
            });
            // data['modifiedDate'] = $('#modifiedDate').val().toString();
            // data['modifiedBy'] = $('#modifiedBy').val();
            //call api
            if ($('#customerId').val() !== "") {
                updateCustomer(data,$('#customerId').val());
            }
            else {
                addCustomer(data);
            }
        });


        function updateCustomer(data, customerId){
            $.ajax({
                type: "PUT",
                url: "${customerAPI}/"+ customerId,
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function(respond){
                    alert("Update customer successfully!")
                    window.location.href = "<c:url value='/admin/customer-list?message=success'/>";
                },
                error: function(respond){
                    console.log("Failed");
                    window.location.href = "<c:url value='/admin/customer-list?message=error'/>";
                }
            });
        }
        function addCustomer(data){
            $.ajax({
                type: "POST",
                url: "${customerAPI}",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function(respond){
                    window.location.href = "<c:url value='/admin/customer-list?message=success'/>";
                },
                error: function(respond){
                    console.log("Failed");
                    window.location.href = "<c:url value='/admin/customer-edit?message=error'/>";
                }
            });
        }
        $('#btnCancel').click(function() {
            window.location.href = "/admin/customer-list";
          //
        })
    </script>
</body>
</html>
