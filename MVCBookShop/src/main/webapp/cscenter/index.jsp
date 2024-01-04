<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<h4><b>자주 묻는 질문</b></h4>
			</div>
			<div class="features-inner text-center" style="padding: 20px 0 !important;"> <!-- features-inner CSS 변경 -->
				<div class="row row-cols-4 mx-2">	<!-- mx-2 : margin left&right 0.5rem -->
					<div class="col-3 p-2 border">	<!-- p-2 : padding: 0.5rem !important;-->
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok"><h5>Best 10</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=1"><h5>회원</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=2"><h5>도서/상품정보/교과서</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=3"><h5>주문/결제</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=4"><h5>배송/수령일 안내</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=5"><h5>반품/교환/환불</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=6"><h5>세금계산서/증빙</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=7"><h5>서비스</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=8"><h5>eBook</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=9"><h5>중고장터</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=10"><h5>POD 주문형출판</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=11"><h5>PubPle(퍼플)</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=12"><h5>sam</h5></a>
						</li>
					</div>
					<div class="col-3 p-2 border">
						<li class="mt-2" style="list-style-type : none; ">
							<a href="${request.getContextPath() }/cscenter/faq.ok?fcode=13"><h5>북모닝</h5></a>
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
	
	<section class="container mb-5">
		<div class="container-header" style="padding:10px 0;">
			<h4><b>1:1 문의</b></h4>
		</div>
		<div class="row">
		  <div class="col-sm-4">
		    <div class="card">
		      <div class="card-body">
		        <h5 class="card-title">1:1 문의 접수</h5>
		        <p class="card-text">상담사와 1:1 문의 접수를 원하시면 클릭하세요</p>
		        <a href="#" class="btn btn-white">접수</a>
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-4">
		    <div class="card">
		      <div class="card-body">
		        <h5 class="card-title">전화 상담</h5>
		        <h5 class="card-title"><b>1544-1234</b></h5>
		        <p class="card-text">평일 09:00 ~ 18:00 (주말 및 공휴일 휴무)</p>
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-4">
		    <div class="card">
		      <div class="card-body">
		        <h5 class="card-title">보이는 ARS</h5>
		        <p class="card-text">평일 09:00 ~ 18:00 (주말 및 공휴일 휴무)</p>
		        <a href="#" class="btn btn-white">전화상담서비스 안내도</a>
		      </div>
		    </div>
		  </div>
		</div>
	</section>
	
	<section class="container mb-5">
		<div class="container-header" style="padding:10px 0;">
			<h4><b>공지사항</b></h4>
		</div>
		<hr>
		<table class="table table-hover">
	    <tr>
	      <th scope="row">1</th>
	      <td>MVCBook Shop 영상정보처리기기 운영관리방침(CCTV) 변경 안내(3/22)</td>
	      <td>고객센터</td>
	      <td>2023.03.30</td>
	    </tr>
	    <tr>
	      <th scope="row">2</th>
	      <td>배송비 정책 변경 안내 (2/20~)</td>
	      <td>고객센터</td>
	      <td>2023.02.13</td>
	    </tr>
	    <tr>
	      <th scope="row">3</th>
	      <td>마케팅 정보 수신동의 확인 안내</td>
	      <td>고객센터</td>
	      <td>2022.11.14</td>
	    </tr>
		</table>
	</section>
	

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
</body>

</html>