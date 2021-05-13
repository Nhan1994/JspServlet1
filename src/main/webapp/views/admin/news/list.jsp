<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>
	<div class="main-content">
	<form action="<c:url value='/admin-news-list'/>" id="formSubmit" method="get">
		
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
								chủ</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-right tableTools-container">
										<div class="dt-buttons btn-overlap btn-group">
											<a flag="info"
											   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
											   title='Thêm bài viết' href='<c:url value="/admin-news-list?type=edit"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
											</a>
											<button id="btnDelete" type="button"
													class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
											</button>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead class="thead-dark">
												<tr>
													<th></th>
													<th>News name</th>
													<th>Short description</th>
													<th>Thumbnail</th>
													<th>Content</th>
													<th>Action</th>
												</tr>
											</thead>
											<!-- tbody> -->
											<c:forEach var="item" items="${model.results}">
												<tr>
													<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
													<td>${item.title}</td>
													<td>${item.shortDescription}</td>
													<td>${item.thumbnail}</td>
													<td>${item.content}</td>
<%--													<tbody>--%>
<%--													<c:forEach var="item" items="${model.results}">--%>
<%--														<tr>--%>
													<td>
														<c:url var="editURL" value="/admin-news-list">
															<c:param name="type" value="edit"/>
															<c:param name="id" value="${item.id}"/>
														</c:url>
														<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
														   title="Cập nhật bài viết" href='${editURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
														</a>
													</td>
<%--														</tr>--%>
<%--													</c:forEach>--%>
<%--													</tbody>--%>
												</tr>
											</c:forEach>
											<!-- </tbody> -->
										</table>

									</div>
									<nav aria-label="Page navigation">
										<ul class="pagination" id="pagination"></ul>
									</nav>
									<input type="hidden" value="" id="page" name="page"/>
									<input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
									<input type="hidden" value="" id="sortName" name="sortName"/>
									<input type="hidden" value="" id="sortBy" name="sortBy"/>
									<input type="hidden" value="" id="type" name="type"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>		
		</form>
	</div>
	<!-- /.main-content -->
	<script type="text/javascript">
	    var currentPage = ${model.page};
		var totalPage = ${model.totalPage};
		var limit = 3;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPage,
				visiblePages : 10,
				startPage : currentPage,
				onPageClick : function(event, page) {
					if (currentPage != page){
						console.info(page + ' (from options)');
						$('#maxPageItem').val(limit);
						$('#page').val(page);
						$('#sortName').val('title');
						$('#sortBy').val('asc');
						$('#type').val('list');
						$('#formSubmit').submit();
					}				
				}
			});
		});
	</script>

</body>
</html>