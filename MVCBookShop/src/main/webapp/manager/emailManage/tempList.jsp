<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="img/fav.png">
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
	<!-- CSS  -->
	<link rel="stylesheet" href="/css/manager/style.css">
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

		<!-- Content body Area -->
		<div class="content-body">
    	<div class="container-fluid">
      	<div class="row page-titles mx-0">
        	<div class="col-sm-6 p-md-0">
          	<div class="welcome-text">
            	<h4>Hi, welcome back!</h4>
              <span class="ml-1">Email</span>
            </div>
          </div>
          <div class="col-sm-6 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex">
          	<ol class="breadcrumb">
            	<li class="breadcrumb-item"><a href="javascript:void(0)">Email</a></li>
              <li class="breadcrumb-item active"><a href="javascript:void(0)">Inbox</a></li>
            </ol>
          </div>
        </div>
        <!-- row -->
        <div class="row">
        	<div class="col-lg-12">
          	<div class="card">
            	<div class="card-body">
              	
              	<!-- sidebar -->
								<%@ include file="/common/manager/email_sidebar.jsp"%>              	
              	<!-- sidebar -->
              	
                <div class="email-right-box ml-0 ml-sm-4 ml-sm-0">
                	<div role="toolbar" class="toolbar ml-4 ml-sm-0">
                  	<div class="btn-group mb-4">
                    	<span class="btn btn-dark ml-3">
                      	<input type="checkbox">
                      </span>
                      <button class="btn btn-dark btn-lg" type="button"><i class="ti-reload"></i>
                      </button>
                    </div>
                    <div class="btn-group mb-4">
                    	<button aria-expanded="false" data-toggle="dropdown" class="btn btn-dark dropdown-toggle" type="button">More 
                    		<span class="caret"></span>
                    	</button>
                    	<div class="dropdown-menu"> 
                    		<a href="javascript: void(0);" class="dropdown-item">Mark as Unread</a> 
                    		<a href="javascript: void(0);" class="dropdown-item">Add to Tasks</a>
                        <a href="javascript: void(0);" class="dropdown-item">Add Star</a> 
                        <a href="javascript: void(0);" class="dropdown-item">Mute</a>
                      </div>
                    </div>
                  </div>
                  
                  <!-- 이메일 리스트 출력 영역 -->
                  <div class="email-list mt-4">
                  	<c:if test="${session.mailList != null}">
	                  	<c:forEach items="${session.mailList }" var="mail" varStatus="${mail.status }">
		                  	<div class="d-flex justify-content-between bd-highlight mb-3 message mx-auto" style="width:100%;">
		                      	<div class="d-flex flex-row message-single w-10 mt-3">
		                        	<div class="custom-control custom-checkbox pl-4">
		                          	<input type="checkbox">
		                          </div>
		                          <div class="ml-2">
						                		<c:choose>
			                        		<c:when test="${mail.ml_importance eq 0}" >
				                          	<button class="border-0 bg-transparent align-middle pl-1 starBtn"><i class="fa fa-star-o" aria-hidden="true"></i></button>
			                        		</c:when>
			                        		<c:otherwise>
				                          	<button class="border-0 bg-transparent align-middle pl-1 starBtn"><i class="fa fa-star" aria-hidden="true"></i></button>
			                        		</c:otherwise>
			                        	</c:choose>
		                          </div>
		                        </div>
		                        <div class="px-3 w-25 font-italic" style="line-height:3.5;">
															<c:choose>
		                        		<c:when test="${fn:length(mail.ml_recipient) > 1 }" >
			                        		${mail.ml_recipient[0] } &nbsp;...
		                        		</c:when>
		                        		<c:when test="${mail.ml_recipient != null }" >
			                        		${mail.ml_recipient[0] }
		                        		</c:when>
		                        		<c:otherwise>
			                        		(수신자 없음)
		                        		</c:otherwise>
		                        	</c:choose>
		                        </div>
	                        	<div class="subject px-3 w-55 font-weight-bold" style="line-height:3.5;">
			                        <a href="${request.getContextPath() }/manager/emailManage/tempView.ma?ml=${mail.status }" class="mailA">
			                        	<c:choose>
			                        		<c:when test="${fn:length(mail.ml_title) > 35 }" >
				                        		<c:out value="${fn:substring(mail.ml_title, 0, 34)}${'...'}" />
			                        		</c:when>
			                        		<c:when test="${mail.ml_title != null }" >
			                        			${mail.ml_title}
			                        		</c:when>
			                        		<c:otherwise>
			                        			(제목 없음)
			                        		</c:otherwise>
			                        	</c:choose>
			                        </a>
	                        	</div>
	                        	<div class="date w-10" style="line-height:3.5;">
	                        		<fmt:formatDate value="${mail.ml_sendTime }" type="time" timeStyle="short"/>
	                        	</div>
		                    </div>
	                  	</c:forEach>
	                  </c:if>
                  </div>
                  <!-- 이메일 리스트 출력 영역 -->

                  <!-- panel -->
                  <div class="row mt-4 m-4 mx-sm-4">
                  	<div class="col-7">
                    	<div class="text-left">1 - ${listCount } of 1</div>
                    </div>
                    <div class="col-5">
                    	<div class="btn-group float-right">
                      	<button class="btn btn-dark" type="button"><i class="fa fa-angle-left"></i></button>
                        <button class="btn btn-dark" type="button"><i class="fa fa-angle-right"></i></button>
                      </div>
                    </div>
                  </div>
                  <!-- panel -->
                  
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
		<!-- Content body Area -->

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
  <!-- YJ -->
  <script type="text/javascript">
  	$('.starBtn').on('click', function(e){
  		
  		// context of $(this) being different than what you expect it to be. 
  		// To fix this issue, you can store the reference to $(this) in a variable before making the AJAX request.
  		var $this = $(this);
  		var ml_index = $(this).siblings('input[name="ml_index"]').val();
  		var star_class = $(this).children('i').attr('class');
  		var star = 0;
  		if(star_class == 'fa fa-star-o') star = 1;
  		
//  		alert($this);
// 		alert(ml_index+','+star);
  		
			$.ajax({
				url: '/manager/emailManage/updateStar.ax',
				type: 'POST',
				data: {'ml_index': ml_index, 'star': star},
				dataType: 'TEXT',
				success: function(text) {
					if(text === 'commit') {
						if(star_class == 'fa fa-star') {
//							$(this).children('i').removeClass('fa-star').addClass('fa-star-o');
							$this.children('i').removeClass('fa-star').addClass('fa-star-o');
			  		} else {
							$this.children('i').removeClass('fa-star-o').addClass('fa-star');
			  		}
					} else {
						alert('정상적이지 못합니다. 이전 페이지로 돌아갑니다.');
						history.back(-1);
					}
				},
				error: function(request, status, error) {
					console.log("code: "+request.status+"\n"+"message : "+request.responseText+"\n"+"error: "+error);
				}
			});
  	});
  </script>
  <!-- Script -->

</body>
</html>