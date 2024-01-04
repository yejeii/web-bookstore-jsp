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
              <h4>등록된 회원 목록</h4>
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
                <h4 class="card-title">회원 목록</h4>
              	<button type="button" class="btn btn-outline-secondary" id="sendMailBtn">메일 전송
              		<span class="btn-icon-right"><i class="fa fa-envelope"></i></span>
                </button>
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table id="example" class="display" style="min-width: 845px; padding:1rem;">
                    <thead>
                      <tr>
                      	<th style="width:3px !important;">
                      		<input type="checkbox" class="form-check-input checkAll">
                      	</th>
                        <th>아이디</th>
                        <th>이름</th>
                        <th>나이</th>
                        <th>이메일</th>
                        <th>연락처</th>
                        <th>메일 전송</th>
                      </tr>
                    </thead>
                      <tbody>
                      	<c:forEach var="member" items="${members }" varStatus="status">
		                      <tr>
		                      	<td>
		                      		<input type="checkbox" name="chk" id="check${status.index }">
		                      	</td>
		                        <td>
		                        	<c:choose>
			                        	<c:when test="${member.m_gender eq 'M' }">
			                        		<img alt="man" src="${request.getContextPath() }/img/manager/avatar/1.png" class="mr-2" width="30" height="30">
			                        	</c:when>
			                        	<c:otherwise>
			                        		<img alt="woman" src="${request.getContextPath() }/img/manager/avatar/2.png" class="mr-2" width="30" height="30">
			                        	</c:otherwise>
		                        	</c:choose>
		                        	<a href="${request.getContextPath() }/manager/memberManage/memberView.ma?isLogin=true&m_id=${member.m_id }"
		                        			id="m_id${status.index }">
		                        		${member.m_id }
		                        	</a>
		                        </td>
		                        <td>${member.m_name }</td>
		                        <td>${member.m_age }</td>
		                        <td>
		                        	<a href="#" class="sendMail" id="m_email${status.index }">
		                        		${member.m_email }
		                        	</a>
		                        </td>
		                        <td>010&nbsp;-&nbsp;${member.m_phone1 }&nbsp;-&nbsp;${member.m_phone2 }</td>
		                        <td>
		                        	<button type="button" class="btn btn-rounded btn-outline-warning btn-xs" 
		                        					onclick="window.location.href='/manager/bookManage/bookDeletePro.ma?b_id=${book.b_id}'">Remove&nbsp;
		                        		<i class="fa fa-close"></i>
                              </button>
                            </td>
		                      </tr>
                        </c:forEach>
                      </tbody>
                                    
                      <tfoot>
                        <tr>
                          <th><input type="checkbox" class="form-check-input checkAll" value=""></th>
                          <th>아이디</th>
	                        <th>이름</th>
	                        <th>나이</th>
	                        <th>이메일</th>
	                        <th>연락처</th>
	                        <th>메일 전송</th>
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
  <script src="/js/vendor/jquery-2.2.4.min.js"></script>
  <script src="/vendor/global/global.min.js"></script>
  <script src="/js/manager/quixnav-init.js"></script>
  <script src="/js/manager/custom.min.js"></script>
  <script src="/vendor/manager/datatables/js/jquery.dataTables.min.js"></script>
  <script src="/js/manager/plugins-init/datatables.init.js"></script>
  <!-- YJ -->
  <script type="text/javascript">
  $(document).ready(function() {
	    
	  $('.checkAll').click(function() {
		  if($('.checkAll').is(":checked")) $('input[name=chk]').prop('checked', true);
			else $('input[name=chk]').prop('checked', false);
	  });
	  
	  $('input[name=chk]').click(function() {
			var total = $('input[name=chk]').length;
			var checked = $('input[name=chk]:checked').length;
			
			if(total != checked) $('.checkAll').prop('checked', false);
			else $('.checkAll').prop('checked', true); 
		});
	  
	  $('#sendMailBtn').click(function() {
	  
		  // alert(emails);
			// Create a form with hidden input fields for each selected email address
		  var $form = $('<form>', {
		    'method': 'POST',
		    'action': '/manager/emailManage/sendMail.ma?userId=true'
		  });
			
		  $('input[name=chk]:checked').each(function() {
		    $('<input>').attr({
		      'type': 'hidden',
		      'name': 'emails[]',
		      'value': $(this).closest('tr').find('td:eq(4)').text().trim()
		    }).appendTo($form);
		  });
		  
		  // Submit the form to the server
		  $form.appendTo('body').submit();
		});

	  
	  $('.sendMail').click(function(event) {
	      event.preventDefault(); // prevent default behavior of hyperlink
	      var url = $(this).attr('href');
	      
//	      alert("click");
	      
	      var index = $(this).attr('id').substring(7);
	      var id = 'm_id'+index;
	      var m_id = $('#'+id).text().trim();
//	      alert(index);
//	      alert(m_id);
	      
	      var m_email = $(this).text().trim();
	      
	      const form = $('<form>', {
	        method: 'post',
	        action: '/manager/emailManage/sendMail.ma?userId=true'
	      });
	      const memberIdInput = $('<input>', {
	        type: 'hidden',
	        name: 'm_id',
	        value: m_id
	      });
	      const memberEmailInput = $('<input>', {
	        type: 'hidden',
	        name: 'm_email',
	        value: m_email
	      });
	      
	      form.append(memberIdInput);
	      form.append(memberEmailInput);
	      $('body').append(form);
	      
	      form.submit();
	    });
	  });
  </script>
  <!-- Script -->
</body>
</html>