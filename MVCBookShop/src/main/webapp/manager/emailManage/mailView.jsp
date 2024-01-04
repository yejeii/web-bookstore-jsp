<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    <li class="breadcrumb-item active"><a href="javascript:void(0)">Compose</a></li>
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
                	<div class="row">
                  	<div class="col-12">
                    	<div class="right-box-padding">
                      	<div class="toolbar" role="toolbar">
                        	<div class="btn-group mb-4">
                          	<button type="button" class="btn btn-dark btn-lg"><i class="fa fa-archive"></i></button>
                            <button type="button" class="btn btn-dark"><i class="fa fa-exclamation-circle"></i></button>
                            <button type="button" class="btn btn-dark"><i class="fa fa-trash"></i></button>
                          </div>
                          <div class="btn-group mb-4">
                          	<button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown">
                          		<i class="fa fa-folder"></i> <b class="caret m-l-5"></b>
                            </button>
                            <div class="dropdown-menu">
                            	<a class="dropdown-item" href="javascript: void(0);">Social</a>
                              <a class="dropdown-item" href="javascript: void(0);">Promotions</a>
                              <a class="dropdown-item" href="javascript: void(0);">Updates</a>
                              <a class="dropdown-item" href="javascript: void(0);">Forums</a>
                            </div>
                          </div>
                          <div class="btn-group mb-4">
                          	<button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown">
                          		<i class="fa fa-tag"></i> <b class="caret m-l-5"></b>
                            </button>
                            <div class="dropdown-menu">
                            	<a class="dropdown-item" href="javascript: void(0);">Updates</a>
                              <a class="dropdown-item" href="javascript: void(0);">Social</a>
                              <a class="dropdown-item" href="javascript: void(0);">Promotions</a>
                              <a class="dropdown-item" href="javascript: void(0);">Forums</a>
                            </div>
                          </div>
                          <div class="btn-group mb-4">
                          	<button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown">More <span class="caret m-l-5"></span>
                            </button>
                            <div class="dropdown-menu">
                            	<a class="dropdown-item" href="javascript: void(0);">Mark as Unread</a>
                              <a class="dropdown-item" href="javascript: void(0);">Add to Tasks</a>
                              <a class="dropdown-item" href="javascript: void(0);">Add Star</a>
                              <a class="dropdown-item" href="javascript: void(0);">Mute</a>
                            </div>
                          </div>
                        </div>
                        <div class="read-content">
                        	<div class="media pt-3 wx-auto">
                          	<img class="mr-4 rounded-circle" alt="image" src="/img/manager/avatar/1.png">
                          	<div class="media-body w-100">
                          		<div class="m-1 mb-4">
	                            	<h3 class="text-black"><b>${mail.ml_title }</b></h3>
                          		</div>
  														<div class="m-1 mb-3">
	                            	<h5 class="text-primary d-inline mr-3">[보낸사람]</h5>
	                          		<div class="badge badge-rounded badge-outline-dark d-inline">
			                        		<input type="text" value="${mail.ml_sender}" style="border:none; cursor:pointer;"
	                        						onclick="${request.getContextPath() }/manager/memberManage/memberView.ma">
											      		</div>
										      		</div>
										      		<div class="m-1 my-3">
	                            	<h5 class="text-primary d-inline mr-3">[받는사람]</h5>
											      		<c:forEach var="recipient" items="${mail.ml_recipient}">
																  <div class="badge badge-rounded badge-outline-dark d-inline mr-3">
																    <input type="text" value="${recipient}" style="border:none; cursor:pointer;"
																           onclick="${request.getContextPath()}/manager/memberManage/memberView.ma">
																  </div>
																</c:forEach>
															</div>
                              <p class="mb-0"><fmt:formatDate value="${mail.ml_sendTime }" type="both" pattern="yyyy-MM-dd (E) a hh:mm"/></p>
                            </div>
                            <a href="javascript:void()" class="text-muted "><i class="fa fa-reply"></i> </a>
                            <a href="javascript:void()" class="text-muted ml-3"><i class="fa fa-long-arrow-right"></i> </a>
                            <a href="javascript:void()" class="text-muted ml-3"><i class="fa fa-trash"></i></a>
                          </div>
                          <hr>
                          <div class="read-content-body">
                          	${mail.ml_text }
                            <hr>
                          </div>

                        	<c:if test="${mail.ml_attachment != null }">
		                        <div class="read-content-attachment">
		                        	<h6><i class="fa fa-download mb-2"></i> 첨부파일	<span>(${mail.ml_attachment.size()})</span></h6>
		                          <div class="row attachment">
			                      		<c:forEach items="${mail.ml_attachment}" var="ml_attachment">
			                          	<div class="col-auto">
			                            	<a href="${request.getContextPath()}/manager/fileDownload.ma?filename=${ml_attachment }" class="text-muted">${ml_attachment }</a>
			                            </div>
			                      		</c:forEach>
		                          </div>
		                        </div>
		                        <hr>
                        	</c:if>
                        	
                        </div>
                        <div class="text-right">
                        	<button class="btn btn-primary btn-sl-sm mb-5" type="button">답변하기</button>
                        </div>
                      </div>
                    </div>
                  </div>
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
  	
  </script>
  <!-- Script -->

</body>
</html>