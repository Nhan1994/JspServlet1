<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/news"/>
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa bài viết</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert}">
                                ${messageResponse}
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="categoryCode" name="categoryCode">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="title" name="title" value="${model.title}"/>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="thumbnail" name="thumbnail"
                                   value="${model.thumbnail}"/>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="shortDescription" name="shortDescription"
                                   value="${model.shortDescription}"/>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                        <div class="col-sm-9">
                            <textarea rows="" cols="" id="content" name="content"
                                      style="width: 820px;height: 175px">${model.content}</textarea>
                        </div>
                    </div>
                    <br/>
                    <br/>
<%--                    <div class="form-group">--%>
<%--                        <div class="col-sm-12">--%>
<%--                            <c:if test="${not empty model.id}">--%>
<%--                                <input type="button" class="btn btn-white btn-warning btn-bold"--%>
<%--                                       value="Cập nhật bài viết" id="btnAddOrUpdateNew"/>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${empty model.id}">--%>
<%--                                <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm bài viết"--%>
<%--                                       id="btnAddOrUpdateNew"/>--%>
<%--                            </c:if>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <input type="hidden" value="${model.id}" id="id" name="id"/>--%>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>