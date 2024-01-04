<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="header_area sticky-header">
	<div class="main_menu">
		<nav class="navbar navbar-expand-lg navbar-light main_box">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<a class="navbar-brand logo_h" href="${request.getContextPath() }/bookShopMain.ok"><img src="/img/logo.png" alt=""></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
				 aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
					<ul class="nav navbar-nav menu_nav ml-auto">
						<li class="nav-item active"><a class="nav-link" href="${request.getContextPath() }/bookShopMain.ok">홈</a></li>
						<li class="nav-item"><a class="nav-link" id="bookList_nav" role="button" href="javascript:void(0);">최신 도서</a></li>
						<li class="nav-item submenu dropdown">
							<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							 aria-expanded="false">Blog</a>
							<ul class="dropdown-menu">
								<li class="nav-item"><a class="nav-link" href="cart.html">Shopping Cart</a></li>
								<li class="nav-item"><a class="nav-link" href="blog.html">Blog</a></li>
								<li class="nav-item"><a class="nav-link" href="single-blog.html">Blog Details</a></li>
							</ul>
						</li>
						<li class="nav-item"><a class="nav-link" href="contact.html">Contact</a></li>
						<li class="nav-item"><a class="nav-link" href="${request.getContextPath() }/cscenter.ok">고객센터</a></li>
						<c:choose>
							<c:when test="${sessionScope != null and sessionScope.isLogin eq true}">
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
									 aria-expanded="false"><c:out value="${sessionScope.userId }"/>&nbsp;님</a>
									<ul class="dropdown-menu">
										<li class="nav-item">
											<a class="nav-link" href="${request.getContextPath() }/memberView.me?isLogin=true&userId=${sessionScope.userId }">개인정보</a>
										</li>
										<li class="nav-item"><a class="nav-link" href="${request.getContextPath() }/memberLogOutPro.me">로그아웃</a></li>
									</ul>
								</li>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="nav-link" href="${request.getContextPath() }/memberLogin.me">로그인</a></li>
							</c:otherwise>
						</c:choose>
						<!-- 관리자만 접근 -->
						<c:if test="${sessionScope != null and sessionScope.isLogin eq true and sessionScope.userId eq 'manager' }">
							<li class="nav-item"><a class="nav-link" href="${request.getContextPath() }/manager/dashboard.ma">관리</a></li>
						</c:if>
						<!-- 관리자만 접근 -->
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="nav-item"><a href="${request.getContextPath() }/bookCartList.ok" class="cart"><span class="ti-bag"></span></a></li>
						<li class="nav-item">
							<button class="search"><span class="lnr lnr-magnifier" id="search"></span></button>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div class="search_input" id="search_input_box">
		<div class="container">
			<form class="d-flex justify-content-between">
				<input type="text" class="form-control" id="search_input" placeholder="Search Here">
				<button type="submit" class="btn"></button>
				<span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
			</form>
		</div>
	</div>
</header>
