<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
              	<div class="email-left-box px-0 mb-5">
                	<div class="p-0">
                  	<a href="email-compose.html" class="btn btn-primary btn-block">Compose</a>
                  </div>
                  <div class="mail-list mt-4">
                    <a href="email-inbox.html" class="list-group-item active">
                    	<i class="fa fa-inbox font-18 align-middle mr-2"></i> Inbox 
                    	<span class="badge badge-primary badge-sm float-right">198</span> 
                    </a>
                    <a href="javascript:void()" class="list-group-item">
                      <i class="fa fa-paper-plane font-18 align-middle mr-2"></i>Sent
                    </a> 
                    <a href="javascript:void()" class="list-group-item">
                      <i class="fa fa-star-o font-18 align-middle mr-2"></i>Important 
                      <span class="badge badge-danger text-white badge-sm float-right">47</span>
                    </a>
                    <a href="javascript:void()" class="list-group-item">
                      <i class="mdi mdi-file-document-box font-18 align-middle mr-2"></i>Draft
                    </a>
                    <a href="javascript:void()" class="list-group-item">
                      <i class="fa fa-trash font-18 align-middle mr-2"></i>Trash
                    </a>
                  </div>
                  <div class="intro-title d-flex justify-content-between">
                    <h5>Categories</h5>
                    <i class="fa fa-chevron-down" aria-hidden="true"></i>
                  </div>
                  <div class="mail-list mt-4">
                    <a href="email-inbox.html" class="list-group-item">
                      <span class="icon-warning"><i class="fa fa-circle" aria-hidden="true"></i></span>Work 
										</a>
                    <a href="email-inbox.html" class="list-group-item">
                      <span class="icon-primary"><i class="fa fa-circle" aria-hidden="true"></i></span>Private 
                    </a>
                    <a href="email-inbox.html" class="list-group-item">
                      <span class="icon-success"><i class="fa fa-circle" aria-hidden="true"></i></span>Support 
                    </a>
                    <a href="email-inbox.html" class="list-group-item">
                      <span class="icon-dpink"><i class="fa fa-circle" aria-hidden="true"></i></span>Social 
                    </a>
                  </div>
                </div>
                <div class="email-right-box ml-0 ml-sm-4 ml-sm-0">
                  <div class="toolbar mb-4" role="toolbar">
                    <div class="btn-group mb-1">
                      <button type="button" class="btn btn-dark"><i class="fa fa-archive"></i></button>
                      <button type="button" class="btn btn-dark"><i class="fa fa-exclamation-circle"></i></button>
                      <button type="button" class="btn btn-dark"><i class="fa fa-trash"></i></button>
                    </div>
                    <div class="btn-group mb-1">
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
                    <div class="btn-group mb-1">
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
                    <div class="btn-group mb-1">
                      <button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown">More 
                      	<span class="caret m-l-5"></span>
                      </button>
                      <div class="dropdown-menu"> 
                        <a class="dropdown-item" href="javascript: void(0);">Mark as Unread</a> 
                        <a class="dropdown-item" href="javascript: void(0);">Add to Tasks</a>
                        <a class="dropdown-item" href="javascript: void(0);">Add Star</a> 
                        <a class="dropdown-item" href="javascript: void(0);">Mute</a>
                      </div>
                    </div>
                  </div>
                  <!-- 메일 작성 영역 -->
                  <div class="compose-content">
                  	<form name="mailForm" action="${request.getContextPath() }/manager/emailManage/sendMailPro.ma" method="post" enctype="multipart/form-data">
										  <div class="form-group row">
										    <label class="col-sm-2 col-form-label">받는사람</label>
										    <div class="col-sm-10">
										    	<c:choose>
										    		<c:when test="${emails != null}">
												      <c:forEach items="${emails }" var="m_email"> 
												      	<div class="d-inline mailDiv">
												      		<div class="badge badge-rounded badge-outline-dark">
			                        		<input type="text" name="recipient_to[]" value="${m_email }" style="border:none;"
			                        						onclick="${request.getContextPath() }/manager/memberManage/memberView.ma">
				                        		<span class="deleteSpn">
																				<i class="fa fa-close"></i>
																		</span>
												      		</div>
												      	</div>
			                      	</c:forEach>
										    		</c:when>
										    		<c:otherwise>
										    			<input type="text" name="recipient_to[]" style="border:none;">
										    		</c:otherwise>
										    	</c:choose>
										    </div>
										  </div>
										  <div class="form-group row">
										    <label for="title" class="col-sm-2 col-form-label">제목</label>
										    <div class="col-sm-10">
										      <input type="text" class="form-control" name="title" placeholder="제목을 입력하세요">
										    </div>
										  </div>
										  <div class="form-group row">
										    <label for="file" class="col-sm-2 col-form-label"><i class="fa fa-paperclip"></i>첨부파일</label>
										    <div class="col-sm-10">
										    	<button class="btn btn-info btn-sl-sm mb-1" id="fileBtn" type="button">
										    		<span class="text-info mr-1">
										    			<i class="fa fa-plus text-white"></i>
										    		</span>파일 추가
										    	</button>
										    	<div class="d-flex flex-column rounded border border-info shadow bg-white w-100 overflow-auto p-2 fileDiv" 
										    				style="height: 100px;" data-spy="scroll" data-target="#fileBtn" data-offset="0">
										    		<!-- 파일 추가 -->
										    		<!-- onclick="deleteFile(this);" : 클릭된 span 요소를 가리키기 위해 this를 deleteFile() 함수에 매개변수로 전달 -->
										    		<!-- <div>
										    			<input type="file" class="mt-1 w-90 inputFile" name="files[]">
										    			<span class="text-danger" style="cursor:pointer;" onclick="deleteFile(this);">취소<i class="fa fa-close text-danger"></i></span> 
										    		</div> -->
										    		<!-- 파일 추가 -->
										    	</div>
										    </div>
										  </div>
										  <div class="form-group row">
                        <textarea id="email-compose-editor" name="text"
                        					class="textarea_editor form-control bg-transparent" rows="15" placeholder="내용을 입력하세요"></textarea>
                      </div>
										</form>
                  </div>
                  
                  <div class="text-left mt-4 mb-5">
                    <button class="btn btn-primary btn-sl-sm mr-3" type="button" onclick="sendForm();">
                      <span class="mr-2"><i class="fa fa-paper-plane"></i></span> 전송
                    </button>
                    <button class="btn btn-warning btn-sl-sm mr-3" type="button">
                      <span class="mr-2"><i class="fa fa-times" aria-hidden="true"></i></span> 임시저장
                    </button>
                    <button class="btn btn-dark btn-sl-sm mr-3" type="button">
                      <span class="mr-2"><i class="fa fa-times" aria-hidden="true"></i></span> 취소
                    </button>
                    <button class="btn btn-dark btn-sl-sm" type="button">
                      <span class="mr-2"><i class="fa fa-times" aria-hidden="true"></i></span> 돌아가기
                    </button>
                  </div>
                  <!-- 메일 작성 영역 -->
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
  	
		var index = 0;
		
  	// 배지(이메일) 제거
  	$('.deleteSpn').click(function(){
  		$(this).closest('div').remove();
  	});
  	
  	// 파일 추가
  	$('#fileBtn').on("click", function(e) {
  		
			// create a div element
			var div = $('<div>');
		
			// create an input element with type "file" and class "mt-1 w-90 inputFile" and name "files[]"
			var input = $('<input>').attr({
			  type: 'file',
			  class: 'mt-1 w-90 inputFile',
			  name: 'file'+index
			});
			
			index++;
		
			// create a span element with class "text-danger" and onclick event that calls a function named "deleteFile"
			var span = $('<span>').addClass('text-danger').css('cursor', 'pointer').click(deleteFile).text('삭제');
			
			// add an onclick event that calls a function named "deleteFile" with the "this" keyword as the argument
			span.on('click', function() {
			  deleteFile(this);
			});
		
			// create an "i" element with class "fa fa-close text-danger"
			var i = $('<i>').addClass('fa fa-close text-danger');
		
			// add the "i" element to the span element
			span.append(i);
		
			// add the input element and span element to the div element
			div.append(input, span);
		
			// add the div element to the .fileDiv
			div.appendTo($('.fileDiv'));

  		// select the last input element that was appended to .fileDiv
  		// $(this).last('input').click(); ERROR : $(this) is not a collection
  		$('.fileDiv input:last').click();
  	});
  	
  	// 파일 취소
  	// 전달된 매개변수로 $(element)가 클릭된 span 요소를 가리키게 됨.
  	function deleteFile(element) {
			$(element).closest('div').remove();
		}
  	
  	// form 전송
  	function sendForm() {
  		
  		$('form[name="mailForm"]').submit();
  	}
  	
  </script>
  <!-- Script -->

</body>
</html>