<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="content-wrapper">
    <div class="card">
        <div class="card-header">
            <div class="caption">
                <i class="fas fa-layer-group font-green"></i>
                <span class="caption-subject font-green sbold uppercase">Danh sách blog</span>

                <div class="form_config_test" style="float: right">
                    <div class="input-group-addon">
                        <div class="tag_action" data-toggle="dropdown">
                            <button>Tác vụ&nbsp;<i class="fa fa-angle-down"></i></button>
                            <%--<i class="fas fa-share"></i></i>&nbsp;Tác vụ&nbsp;<i class="fa fa-angle-down"></i>--%>
                        </div>
                        <ul class="dropdown-menu">
                            <li class="dropdown-item"><a href="/admin/course-plan/create"><i class="fas fa-plus"></i>&nbsp;Tạo mới</a></li>
                            <li class="dropdown-item"><a id="btnDelete"><i class="fas fa-trash-alt"></i>&nbsp;Xóa</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="card-header">
        <div class="container-fluid">

            <form id="search">
                <div class="d-flex justify-content-end">
                    <div class="p-3 flex-grow-1">
                        <input type="text"  class="form-control" placeholder="Từ khóa">
                    </div>
                    <div class="p-3">
                        <select  class="form-control" >
                            <option selected value="">-Tìm kiếm-</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                        </select>
                    </div>
                    <div class="p-3">
                        <select  class="form-control" >
                            <option selected value="">-Tìm kiếm-</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                        </select>
                    </div>
                    <div class="p-3">
                        <select  class="form-control" >
                            <option selected value="">-Tìm kiếm-</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                            <option value="">Test</option>
                        </select>
                    </div>

                    <div class="p-3">
                        <button id="btn_search" type="button" title="Tìm kiếm" class="btn btn-outline-info btn-sm" style="font-size: 26px"><i class="fas fa-search"></i></button>
                    </div>

                </div>
            </form>





            <!-- /.card-header -->
            <div class="card-body " style="overflow: auto">
                <table class="table table-hover table_competition table-bordered " id="dtHorizontalVerticalExample" cellspacing="0" style="min-width: 1400px">
                    <thead>
                    <tr>
                        <th> <input type='checkbox' value="0" class='check-box-element'></th>
                        <th style="width: 260px" >Nội dung</th>
                        <th style="width: 100px">Tiêu đề</th>
                        <th >Từ khóa</th>
                        <th >Trạng thái</th>
                        <th >Mô tả</th>
                        <th >Người viết</th>
                    </tr>
                    </thead>
                    <tbody id="data-list">

                    </tbody>
                </table>
            </div>
            <!-- /.card-body -->

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Lý do từ chối kế hoạch</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <textarea style="width: 100%;" name="changeStatusToNotDoneReason" rows="4" cols="53" ></textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                            <button type="button" class="btn btn-primary" id="btn-refuse">Xác nhận</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <div class="col-sm-12 col-xs-12">
        <ul id="pagination-test" class="pagination"></ul>
    </div>



    <%--<div class="position-absolute loader " style="">--%>
        <%--<div class="spinner-border " style="position:fixed;width: 3rem; height: 3rem;top: 50%;left: 50%"--%>
             <%--role="status">--%>
            <%--<span class="sr-only">Loading...</span>--%>
        <%--</div>--%>
    <%--</div>--%>

</div>

<script>

    $( document ).ready(function() {
        showUnitParent();
    });


    $("#checkAllCustomAdd").change(function(){  //"select all" change
        var status = this.checked; // "select all" checked status
        $('#data-list input[type=checkbox]').each(function(){ //iterate all listed checkbox items
            this.checked = status; //change ".checkbox" checked status
        });
    });

    $('#data-list input[type=checkbox]').change(function(){ //".checkbox" change
        //uncheck "select all", if one of the listed checkbox item is unchecked
        if(this.checked == false){ //if this item is unchecked
            $("#checkAllCustomAdd")[0].checked = false; //change "select all" checked status to false
        }
        //check "select all" if all checkbox items are checked
        if ($('#data-list input[type=checkbox]:checked').length == $('#data-list input[type=checkbox]').length ){
            $("#checkAllCustomAdd")[0].checked = true; //change "select all" checked status to true
        }
    });

</script>