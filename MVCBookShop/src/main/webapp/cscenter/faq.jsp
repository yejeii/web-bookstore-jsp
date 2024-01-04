<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zxx" class="no-js">

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
	<link rel="stylesheet" href="/css/linearicons.css">
	<link rel="stylesheet" href="/css/font-awesome.min.css">
	<link rel="stylesheet" href="/css/themify-icons.css">
	<link rel="stylesheet" href="/css/bootstrap.css">
	<link rel="stylesheet" href="/css/owl.carousel.css">
	<link rel="stylesheet" href="/css/nice-select.css">
	<link rel="stylesheet" href="/css/nouislider.min.css">
	<link rel="stylesheet" href="/css/ion.rangeSlider.css" />
	<link rel="stylesheet" href="/css/ion.rangeSlider.skinFlat.css" />
	<link rel="stylesheet" href="/css/magnific-popup.css">
	<link rel="stylesheet" href="/css/main.css">
</head>

<body>

	<!-- Start Header Area -->
	<%@ include file="/common/header.jsp" %>
	<!-- End Header Area -->

	<!-- Start Banner Area -->
	<section class="banner-area organic-breadcrumb">
		<div class="container">
			<div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
				<div class="col-first">
					<h1>Shop Category page</h1>
					<nav class="d-flex align-items-center">
						<a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
						<a href="#">Shop<span class="lnr lnr-arrow-right"></span></a>
						<a href="${request.getContextPath() }/cscenter.ok">고객 센터</a>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Banner Area -->
	
	<section class="features-area section_gap">
		<div class="container">
			<div class="container-header">
				<h3><b>자주 묻는 질문</b></h3>
			</div>
			<div class="features-inner text-center" style="padding: 20px 0 !important;"> <!-- features-inner CSS 변경 -->
				<div class="row row-cols-4 mx-2 faqRow">	<!-- mx-2 : margin left&right 0.5rem -->
					<div class="col-3 p-2 border">	<!-- p-2 : padding: 0.5rem !important;-->
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f0"><h5>Best 10</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f1"><h5>회원</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f2"><h5>도서/상품정보/교과서</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f3"><h5>주문/결제</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f4"><h5>배송/수령일 안내</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f5"><h5>반품/교환/환불</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f6"><h5>세금계산서/증빙</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f7"><h5>서비스</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f8"><h5>eBook</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f9"><h5>중고장터</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f10"><h5>POD 주문형출판</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f11"><h5>PubPle(퍼플)</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f12"><h5>sam</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="#" onclick="return false" class="fcode" id="f13"><h5>북모닝</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-1" style="list-style-type : none; ">
							<a href="#" onclick="return false"><h5></h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-1" style="list-style-type : none; ">
							<a href="#" onclick="return false"><h5></h5></a>
						</li>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<!-- faq.ok?fcode=n에 따른 데이터 출력되는 영역 -->
	<section class="container mb-5 faqSection">
		<div class="container-header faqTabArea" style="padding:10px 0;">
			<c:if test="${fkList != null and fn:length(fkList) eq 1}">
				<h3><b>${fkList[0].fc_value}</b></h3>	
			</c:if>
			<c:if test="${fkList != null and fn:length(fkList) > 1}">
				<h3><b>${fkList[0].fc_value}</b></h3>	
				<div style="height: 50px;">
				  <ul class="nav h-100 faqkeywordUl">
				  	<li class="nav-item my-2 mr-2">
				      <button type="button" class="fkbtn mh-49 p-2 border border-warning bg-white text-dark" id="tab00" 
				      				style="border-radius: 20px !important; font-size: 1rem; line-height: 1.5;">
				        <span class="text-center">전체</span>
				      </button>
				    </li>
				  	<c:forEach var="fk" items="${fkList}" varStatus="status">
					    <li class="nav-item my-2 mr-2">
					      <button type="button" class="fkbtn mh-49 p-2 border border-warning bg-white text-dark" id="tab0${status.index+1 }" 
					      				style="border-radius: 20px !important; font-size: 1rem; line-height: 1.5;">
					        <span class="text-center">${fk.fk_value }</span>
					      </button>
					    </li>
				    </c:forEach>
				  </ul>
				</div>
			</c:if>
			<hr class="my-3">
		</div>
		
		<div class="accordion faqCardList" id="accordionExample">
	    <c:forEach var="faq" items="${faqList}" varStatus="status">
        <div class="card">
          <div class="card-header" id="heading${status.index}" style="background-color:white !important;"> 
            <h2 class="mb-0">
              <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" 
                      data-target="#collapse${status.index}" aria-expanded="true" aria-controls="collapse${status.index}" 
                      style="color:black !important;">
                  [${faq.fk_value}] ${faq.f_title}
              </button>
            </h2>
          </div>
          <div id="collapse${status.index}" class="collapse" aria-labelledby="heading${status.index}" data-parent="#accordionExample">
            <div class="card-body" style="background-color:rgba(0,0,0,.03);">
              ${faq.f_text}
            </div>
          </div>
        </div>
	    </c:forEach>
		</div>
	</section>
	<!-- faq.ok?fcode=n에 따른 데이터 출력되는 영역 -->
	
	<!-- start footer Area -->
	<%@ include file="/common/footer.jsp" %>
	<!-- End footer Area -->

	<script src="/js/vendor/jquery-2.2.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	 crossorigin="anonymous"></script>
	<script src="/js/vendor/bootstrap.min.js"></script>
	<script src="/js/jquery.ajaxchimp.min.js"></script>
	<script src="/js/jquery.nice-select.min.js"></script>
	<script src="/js/jquery.sticky.js"></script>
	<script src="/js/nouislider.min.js"></script>
	<script src="/js/countdown.js"></script>
	<script src="/js/jquery.magnific-popup.min.js"></script>
	<script src="/js/owl.carousel.min.js"></script>
	<!--gmaps Js-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="/js/gmaps.min.js"></script>
	<script src="/js/main.js"></script>
	<!-- Yeji  -->
	<script type="text/javascript">
		$(document).ready(function(){
			
			$(".fcode").click(function() {
				
				var fcode = $(this).attr('id');
				fcode = fcode.substring(1);	
					
					// ajax로 보내서 출력
					$.ajax({
						url: '/cscenter/faq.ax',
						type: "POST",
						data: {"fcode": fcode},
						dataType: "TEXT",
						success: function(text) {
								
							// String타입의 text를 자바스크립트 객체로 변환
							var toJsObj = JSON.parse(text);
							
							var html = "";
							
							html = '<div class="container-header faqTabArea" style="padding:10px 0;">';
								html += '<h3><b>'+toJsObj["fc_value"]+'</b></h3>';
								html += '<input type="hidden" id="fc_code" value="'+toJsObj["fc_code"]+'">';
								
							// fks 출력부분
				    	if(toJsObj["fks"] != "no-fks") {
								html += '<div style="height: 50px;">';
							  	html += '<ul class="nav h-100 faqkeywordUl">';
							  		html += '<li class="nav-item my-2 mr-2">';
									      html += '<button type="button" class="fkbtn mh-49 p-2 border border-warning bg-white text-dark" id="tab00" '; 
							      		html += 'style="border-radius: 20px !important; font-size: 1rem; line-height: 1.5;">';
							        		html += '<span class="text-center">전체</span>';
							     		  html += '</button>';
							    	html += '</li>';
							    	
					    	$.each(toJsObj["fks"], function(index, value) {
									html += '<li class="nav-item my-2 mr-2">';
										html += '<button type="button" class="fkbtn mh-49 p-2 border border-warning bg-white text-dark" id="tab0'+value.fk_code+'"'; 
							      html += ' style="border-radius: 20px !important; font-size: 1rem; line-height: 1.5;">';
							      	html += '<span class="text-center">'+value.fk_value+'</span>';
							      html += '</button>';
							    html += '</li>';
								});
					    	
									html += '</ul>';
								html += '</div>';
							
				    	} 	
							
								html += '<hr class="my-3">';
							html += '</div>';
							
							html += '<div class="accordion faqCardList" id="accordionExample">';	
							
							// faqs가 없을 때
							if(toJsObj["faqs"] === "no-faqs"){
								html += '<div>';
									html += '<h3><b>등록된 문의가 없습니다.</b></h3>';
								html += '</div>';
								
							} 
							
							// faqs 출력
							else {
								$.each(toJsObj["faqs"], function(index, value) {
						      html += '<div class="card">';
						      	html += '<div class="card-header" id="heading"'+index+'" style="background-color:white !important;">'; 
						        	html += '<h2 class="mb-0">';
						        		html += '<button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse"';
						        		html += ' data-target="#collapse' + index + '" aria-expanded="true" aria-controls="collapse' + index + '"';
						          	html += 'style="color:black !important;">';
						            	html += '['+value.fk_value+']'+ value.f_title;
						            html += '</button>';
						          html += '</h2>';
						        html += '</div>';
						        
						        html += '<div id="collapse'+index+'" class="collapse" aria-labelledby="heading'+index+'" data-parent="#accordionExample">';
						        	html += '<div class="card-body" style="background-color:rgba(0,0,0,.03);">';
						          	html += value.f_text;
						          html += '</div>';
						        html += '</div>';
						      html += '</div>';
								});
								
								html += '</div>';
								
							}
							
							// Faq Section 아래에 붙이기
							$(".faqSection").html(html);
						
						}, 
						error: function(request, status, error) {
							console.log("code: "+request.status+"\n"+"message : "+request.responseText+"\n"+"error: "+error);
						} 
					}); 
				});
			
    });
		
		// 키워드 버튼 클릭 시
		$(document).on('click', '.fkbtn', function() {
			 var fkbtnID = $(this).attr('id').substring(4);
			 var fc_code = $('#fc_code').val();
			 // alert(fc_code);
			  
			 $.ajax({
			 		url: '/cscenter/fkcode/faq.ax',
			    method: 'POST',
			    data: { "fk_code" : fkbtnID, "fc_code" : fc_code},
			    success: function(text) {
			      // console.log(text);
			    	var toJsObj = JSON.parse(text);
					
						var html = "";
						
						// faqs가 없을 때
						if(toJsObj["faqs"] === "no-faqs"){
							html += '<div>';
								html += '<h4><b>등록된 문의가 없습니다.</b></h4>';
							html += '</div>';
						} else {
							$.each(toJsObj["faqs"], function(index, value) {
					      html += '<div class="card">';
					      	html += '<div class="card-header" id="heading"'+index+'" style="background-color:white !important;">'; 
					        	html += '<h2 class="mb-0">';
					        		html += '<button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse"';
					        		html += ' data-target="#collapse' + index + '" aria-expanded="true" aria-controls="collapse' + index + '"';
					          	html += 'style="color:black !important;">';
					            	html += '['+value.fk_value+']'+ value.f_title;
					            html += '</button>';
					          html += '</h2>';
					        html += '</div>';
					        
					        html += '<div id="collapse'+index+'" class="collapse" aria-labelledby="heading'+index+'" data-parent="#accordionExample">';
					        	html += '<div class="card-body" style="background-color:rgba(0,0,0,.03);">';
					          	html += value.f_text;
					          html += '</div>';
					        html += '</div>';
					      html += '</div>';
							});
									
						}
						
						// class="faqCardList" 아래에 붙이기
						$(".faqCardList").html(html);
			    },
			    error: function(jqXHR, textStatus, errorThrown) {
			      console.error(errorThrown);
			    }
			 });
		});
	</script>
</body>

</html>