<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
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
	<link rel="stylesheet" href="/css/owl.carousel.css">
	<link rel="stylesheet" href="/css/themify-icons.css">
	<link rel="stylesheet" href="/css/font-awesome.min.css">
	<link rel="stylesheet" href="/css/nice-select.css">
	<link rel="stylesheet" href="/css/nouislider.min.css">
	<link rel="stylesheet" href="/css/bootstrap.css">
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
					<h1>비밀번호 찾기</h1>
					<nav class="d-flex align-items-center">
						<a href="${request.getContextPath() }/bookShopMain.ok">홈<span class="lnr lnr-arrow-right"></span></a>
	          <a href="${request.getContextPath() }/memberLogin.me">로그인<span class="lnr lnr-arrow-right"></span></a>
	          <a href="#">비밀번호 찾기</a>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Banner Area -->

	<!--================Login Box Area =================-->
	<section class="login_box_area section_gap">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="login_box_img">
						<img class="img-fluid" src="img/login.jpg" alt="">
						<div class="hover">
							<h4>New to our website?</h4>
							<p>세상에 휘둘리지 않고 마음을 지킬 수 있다면.</p>
							<a class="primary-btn" href="${request.getContextPath() }/memberJoin.me">Create an Account</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="login_form_inner">
						<h3>인증코드 발급하기</h3>
						<form class="row login_form" action="${request.getContextPath() }/memberForgotPwPro.me" method="post" 
									id="contactForm" name="emailForm" novalidate="novalidate">
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="m_id" name="m_id" placeholder="아이디를 입력하세요" onfocus="this.placeholder = ''" 
												onblur="this.placeholder = 'Username'">
							</div>
							<div class="col-md-12 form-group">
								<input type="email" class="form-control" id="m_email" name="m_email" placeholder="이메일을 입력하세요" onfocus="this.placeholder = ''" 
												onblur="this.placeholder = 'Username'">
							</div>
							<div class="col-md-12 form-group">
								<button type="submit" value="임시 비밀번호 발급받기" class="primary-btn">전송하기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================End Login Box Area =================-->

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
	<script src="/js/jquery.magnific-popup.min.js"></script>
	<script src="/js/owl.carousel.min.js"></script>
	<!--gmaps Js-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="/js/gmaps.min.js"></script>
	<script src="/js/main.js"></script>
</body>

</html>