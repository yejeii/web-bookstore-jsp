<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zxx" class="no-js">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Mobile Specific Meta -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <!-- Favicon-->
  <link rel="shortcut icon" href="/img/fav.png">
  <!-- Author Meta -->
  <meta name="author" content="CodePixar">
  <!-- Meta Description -->
  <meta name="description" content="">
  <!-- Meta Keyword -->
  <meta name="keywords" content="">
  <!-- meta character set -->
  <meta charset="UTF-8">
  <!-- Site Title -->
	<title>MVCBook Shop</title>
	
	<!-- CSS -->
	<!-- Datatable -->
  <link href="${request.getContextPath() }/vendor/manager/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
  <!-- Custom Stylesheet -->
	<link href="${request.getContextPath() }/css/manager/style.css" rel="stylesheet">
</head>
<body>
	<!-- Preloader Start -->
	<div id="preloader">
	    <div class="sk-three-bounce">
	        <div class="sk-child sk-bounce1"></div>
	        <div class="sk-child sk-bounce2"></div>
	        <div class="sk-child sk-bounce3"></div>
	    </div>
	</div>
	<!-- Preloader End -->
	
	<!-- Main wrapper start -->
	<div id="main-wrapper">

    <!-- Nav-Header / Header start -->
		<%@ include file="/common/manager/header.jsp" %>
    <!-- Nav-Header / Header end -->

	  <!-- Sidebar start -->
		<%@ include file="/common/manager/sidebar.jsp" %>
	  <!-- Sidebar end -->

		<!-- Content body start -->
    <div class="content-body">
    	<div class="container-fluid">
      	<div class="row page-titles mx-0">
        	<div class="col-sm-6 p-md-0">
          	<div class="welcome-text">
              <h4>등록된 도서 목록</h4>
              <span class="ml-1">Datatable</span>
            </div>
        	</div>
	        <div class="col-sm-6 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="javascript:void(0)">Table</a></li>
              <li class="breadcrumb-item active"><a href="javascript:void(0)">Datatable</a></li>
            </ol>
	        </div>
      	</div>
        
        <!-- row -->
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
              	<div class="col-10">
                	<h4 class="card-title">도서 목록</h4>
              	</div>
              	<div class="col-2">
              		<button type="button" class="btn btn btn-outline-dark btn-lg"
              						onclick="window.location.href='/manager/bookManage/bookRegistForm.ma'">새로운 도서 등록</button>
              	</div>
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table id="example" class="display" style="min-width: 845px">
                    <thead>
                      <tr>
                        <th>도서명</th>
                        <th>저자 및 옮긴이</th>
                        <th>분류</th>
                        <th>출판사</th>
                        <th>출판일</th>
                        <th>정가</th>
                        <th>삭제</th>
                      </tr>
                    </thead>
                    <%-- <c:if test="${requestScope.bookList != null } "> --%>
                      <tbody>
                      	<c:forEach var="book" items="${bookList }" varStatus="status">
		                      <tr>
		                        <td>
		                        	<a href="${request.getContextPath() }/manager/bookManage/bookView.ma?b_id=${book.b_id }">${book.b_name }</a>
		                        </td>
		                        <td>${book.b_writer } &nbsp;/&nbsp; ${book.b_translator }</td>
		                        <td>${book.b_bc_code }</td>
		                        <td>${book.b_publisher }</td>
		                        <td>${book.b_publish_date }</td>
		                        <td>${book.b_price }</td>
		                        <td>
		                        	<button type="button" class="btn btn-rounded btn-outline-warning btn-xs" 
		                        					onclick="window.location.href='/manager/bookManage/bookDeletePro.ma?b_id=${book.b_id}'">Remove&nbsp;
		                        		<i class="fa fa-close"></i>
                              </button>
                            </td>
		                      </tr>
                        </c:forEach>
                      </tbody>
                   	<%-- </c:if> --%>
                                    
                      <tfoot>
                        <tr>
                          <th>도서명</th>
                          <th>저자 및 옮긴이</th>
                          <th>분류</th>
                          <th>출판사</th>
                          <th>출판일</th>
                          <th>정가</th>
                        </tr>
                      </tfoot>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- row -->
    	</div>
    </div>
    <!-- Content body end -->
        
  	<!-- start footer Area -->
		<div class="footer">
		  <div class="copyright">
		    <p>Copyright © Designed &amp; Developed by <a href="#" target="_blank">Quixkit</a> 2019</p>
		  </div>
		</div>
		<!-- End footer Area -->
	
	</div>
	<!-- Main Wrapper End -->
  
  <!-- Script -->
  <script src="/vendor/global/global.min.js"></script>
  <script src="/js/manager/quixnav-init.js"></script>
  <script src="/js/manager/custom.min.js"></script>
  <script src="/vendor/manager/datatables/js/jquery.dataTables.min.js"></script>
  <script src="/js/manager/plugins-init/datatables.init.js"></script>
  <!-- Script -->
</body>
</html>