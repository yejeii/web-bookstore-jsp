<%@ page import="java.util.HashMap" %>
<%@ page import="vo.Book" %>
<%@ page import="vo.PageInfo" %>
<%@ page import="java.util.ArrayList" %>
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
	<title>MVC Book Shop</title>

	<!-- CSS  -->
	<link rel="stylesheet" href="../css/linearicons.css">
	<link rel="stylesheet" href="../css/owl.carousel.css">
	<link rel="stylesheet" href="../css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/themify-icons.css">
	<link rel="stylesheet" href="../css/nice-select.css">
	<link rel="stylesheet" href="../css/nouislider.min.css">
	<link rel="stylesheet" href="../css/bootstrap.css">
	<link rel="stylesheet" href="../css/main.css">
	<link rel="stylesheet" href="../css/yj/recentBook.css">
</head>

<body id="category">

	<!-- Start Header Area -->
	<%@ include file="/common/header.jsp" %>
	<!-- End Header Area -->

	<!-- Start Top Banner Area -->
	<section class="banner-area organic-breadcrumb">
		<div class="container">
			<div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
				<div class="col-first">
					<h1>최신 도서</h1>
					<nav class="d-flex align-items-center">
						<a href="index.html">홈<span class="lnr lnr-arrow-right"></span></a>
						<a href="#">도서<span class="lnr lnr-arrow-right"></span></a>
						<a href="category.html">최신 도서</a>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Top Banner Area -->
	
	<!-- Start Main Area -->
	<div class="container">
		<div class="row">
			<!-- Start Main Catgy Area -->
			<div class="col-xl-3 col-lg-4 col-md-5">
				<div class="sidebar-categories">
					<div class="head">최신 도서</div>
					<ul class="main-categories">
						<!-- 대분류 출력 -->
					</ul>
				</div>
				<div class="sidebar-filter mt-50">
					<div class="top-filter-head">Product Filters</div>
					<div class="common-filter">
						<div class="head">Brands</div>
						<form action="#">
							<ul>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="apple" name="brand"><label for="apple">Apple<span>(29)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="asus" name="brand"><label for="asus">Asus<span>(29)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="gionee" name="brand"><label for="gionee">Gionee<span>(19)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="micromax" name="brand"><label for="micromax">Micromax<span>(19)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung" name="brand"><label for="samsung">Samsung<span>(19)</span></label></li>
														<!-- 확인용 -->
								<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung" name="brand"><label for="samsung">mnName : ${mnName }</label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung" name="brand"><label for="samsung">mdCode : ${mdCode }</label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung" name="brand"><label for="samsung">page : ${pageInfo.page }</label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung" name="brand"><label for="samsung">limit : ${limit }</label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung" name="brand"><label for="samsung">sort : ${filter.sort }</label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung" name="brand"><label for="samsung">year : ${filter.year }</label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung" name="brand"><label for="samsung">month : ${filter.month}</label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="samsung" name="brand"><label for="samsung">week : ${filter.week }</label></li>
							</ul>
						</form>
					</div>
					<div class="common-filter">
						<div class="head">Color</div>
						<form action="#">
							<ul>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="black" name="color"><label for="black">Black<span>(29)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="balckleather" name="color"><label for="balckleather">Black
										Leather<span>(29)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="blackred" name="color"><label for="blackred">Black
										with red<span>(19)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="gold" name="color"><label for="gold">Gold<span>(19)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="spacegrey" name="color"><label for="spacegrey">Spacegrey<span>(19)</span></label></li>
							</ul>
						</form>
					</div>
					<div class="common-filter">
						<div class="head">Price</div>
						<div class="price-range-area">
							<div id="price-range"></div>
							<div class="value-wrapper d-flex">
								<div class="price">Price:</div>
								<span>$</span>
								<div id="lower-value"></div>
								<div class="to">to</div>
								<span>$</span>
								<div id="upper-value"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- End Main Catgy Area -->
			
			<div class="col-xl-9 col-lg-8 col-md-7">
				<!-- Middle Catgy Bar -->
				<div class="filter-bar d-flex flex-wrap align-items-center" style="background: #fff;">
					<a href="javascript:void(0);" class="genric-btn default circle medium" id="md_0">전체</a>
				</div>	
				<!-- Middle Catgy Bar -->
				<!-- Start Filter Bar -->
				<div class="filter-bar d-flex flex-wrap align-items-center">
					<div class="sorting">
						<select class="filter-select" id="filter-year">
							<option value="2023" <c:if test='${filter.year eq 2023 }'>selected="selected"</c:if>>2023년</option>
							<option value="2022" <c:if test='${filter.year eq 2022 }'>selected="selected"</c:if>>2022년</option>
							<option value="2021" <c:if test='${filter.year eq 2021 }'>selected="selected"</c:if>>2021년</option>
							<option value="2020" <c:if test='${filter.year eq 2020 }'>selected="selected"</c:if>>2020년</option>
							<option value="2019" <c:if test='${filter.year eq 2019 }'>selected="selected"</c:if>>2019년</option>
							<option value="2018" <c:if test='${filter.year eq 2018 }'>selected="selected"</c:if>>2018년</option>
						</select>
					</div>
					<div class="sorting">
						<select class="filter-select" id="filter-month">
							<option value="01" <c:if test='${filter.month eq 01 }'>selected="selected"</c:if>>1월</option>
							<option value="02" <c:if test='${filter.month eq 02 }'>selected="selected"</c:if>>2월</option>
							<option value="03" <c:if test='${filter.month eq 03 }'>selected="selected"</c:if>>3월</option>
							<option value="04" <c:if test='${filter.month eq 04 }'>selected="selected"</c:if>>4월</option>
							<option value="05" <c:if test='${filter.month eq 05 }'>selected="selected"</c:if>>5월</option>
							<option value="06" <c:if test='${filter.month eq 06 }'>selected="selected"</c:if>>6월</option>
							<option value="07" <c:if test='${filter.month eq 07 }'>selected="selected"</c:if>>7월</option>
							<option value="08" <c:if test='${filter.month eq 08 }'>selected="selected"</c:if>>8월</option>
							<option value="09" <c:if test='${filter.month eq 09 }'>selected="selected"</c:if>>9월</option>
							<option value="10" <c:if test='${filter.month eq 10 }'>selected="selected"</c:if>>10월</option>
							<option value="11" <c:if test='${filter.month eq 11 }'>selected="selected"</c:if>>11월</option>
							<option value="12" <c:if test='${filter.month eq 12 }'>selected="selected"</c:if>>12월</option>
						</select>
					</div>
					<div class="sorting mr-auto">
						<select class="filter-select" id="filter-week">
							<option value="01" <c:if test='${filter.week eq 01 }'>selected="selected"</c:if>>1주</option>
							<option value="02" <c:if test='${filter.week eq 02 }'>selected="selected"</c:if>>2주</option>
							<option value="03" <c:if test='${filter.week eq 03 }'>selected="selected"</c:if>>3주</option>
							<option value="04" <c:if test='${filter.week eq 04 }'>selected="selected"</c:if>>4주</option>
							<option value="05" <c:if test='${filter.week eq 05 }'>selected="selected"</c:if>>5주</option>
						</select>
					</div>
					<div class="sorting">
						<select class="filter-select" id="filter-sort">
							<option value="sel" <c:if test='${filter.sort eq "sel" }'>selected="selected"</c:if>>판매량 순</option>
							<option value="new" selected="selected">신규등록 순</option>
							<option value="mxv" <c:if test='${filter.sort eq "mxv" }'>selected="selected"</c:if>>높은 가격순</option>
							<option value="minv" <c:if test='${filter.sort eq "minv" }'>selected="selected"</c:if>>>낮은 가격순</option>
						</select>
					</div>
					<div class="sorting">
						<select class="filter-select" id="filter-limit">
							<option value="9">9개씩 보기</option>
							<option value="15"<c:if test='${limit eq 15 }'>selected="selected"</c:if>>15개씩 보기</option>
							<option value="21"<c:if test='${limit eq 21 }'>selected="selected"</c:if>>21개씩 보기</option>
						</select>
					</div>
					
					<div class="sorting">
						<a href="javascript:void(0);" class="genric-btn default-border" id="filter_search">검색</a>
					</div>
					
				</div>
				<!-- End Filter Bar -->
				
<%-- 				<h4>Limit : <c:out value="${limit }"></c:out></h4>
				<h4>sort : <c:out value="${sort }"></c:out></h4>
				<h4>현재 페이지 번호 : <c:out value="${pageInfo.page }"></c:out></h4>
				<h4>필요한 페이지 수(sort 및 Limit에 따라 달라짐) : <c:out value="${pageInfo.maxPage }"></c:out></h4>
				<h4>이번 섹션의 시작 번호 : <c:out value="${pageInfo.startPage }"></c:out></h4>
				<h4>이번 섹션의 끝 번호 : <c:out value="${pageInfo.endPage }"></c:out></h4>
				<h4>sort에 따른 도서 개수 : <c:out value="${pageInfo.listCount }"></c:out></h4> --%>
					
				<section class="lattest-product-area pb-40 category-list">
					<%-- <c:if test="${bookList != null} ">  --%>
						<div class="row">
							<c:forEach var="book" items="${bookList }" varStatus="status">
								<div class="col-lg-4 col-md-6">
									<div class="single-product">
										<div class="product-image">
											<img class="img-fluid d-block" src="${request.getServletContext().getRealPath('/')}/bookImage/${book.b_image }" alt="" 
													id="productImage" style="width:100%; height:auto; cursor: pointer;" 
													onclick="location.href='${request.getContextPath() }/bookView.ok?page=${pageInfo.page }&b_id=${book.b_id }&sort=${filter.sort}&limit=${limit}';">
										</div>
										<div class="product-details">
											<h6>${book.b_name }</h6>
											<p>${book.b_writer }(지은이) | ${book.b_translator }(옮긴이)</p>
											<div class="price">
												<h6><i class="bi bi-currency-dollar"></i>7000(할인)</h6>
												<h6 class="l-through"><i class="bi bi-currency-dollar"></i>${book.b_price }</h6>
											</div>
											<div class="prd-bottom">
												<a href="" class="social-info">
													<span class="ti-bag"></span>
													<p class="hover-text">장바구니 추가</p>
												</a>
												<a href="" class="social-info">
													<span class="lnr lnr-heart"></span>
													<p class="hover-text">위시리스트</p>
												</a>
												<a href="" class="social-info">
													<span class="lnr lnr-sync"></span>
													<p class="hover-text">비교하기</p>
												</a>
												<a href="${request.getContextPath() }/bookView.ok?b_id=${book.b_id }" class="social-info">
													<span class="lnr lnr-move"></span>
													<p class="hover-text">더 알아보기</p>
												</a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					<%-- </c:if> --%>
					
					<c:if test="${empty bookList }">
						<h1>책 상품이 없습니다. </h1>
					</c:if>
				</section>
				<!-- End Best Seller -->
				
				<!-- Start Paging Bar -->
				<div class="filter-bar d-flex flex-wrap align-items-center">
					<c:if test="${pageInfo.listCount != null }">
						<div class="pagination mx-auto">
							<!-- 이전 화살표 -->							
							<c:if test="${pageInfo.page > 10 }">
								<a href="/bookList.ok?page=${pageInfo.page-1}&mnName=${mnName}&mdCode=${mdCode}&year=${filter.year}&month=${filter.month}&week=${filter.week}&sort=${filter.sort}&limit=${limit}" 
										class="prev-arrow"><i class="fa fa-long-arrow-left pt-3" aria-hidden="true"></i></a>
							</c:if>
							
							<c:forEach var="a" end="${pageInfo.endPage }" step="1" begin="${pageInfo.startPage }">
								<c:if test="${a == pageInfo.page}">
									<a>${a }</a>
								</c:if>
								<c:if test="${a != pageInfo.page}">
									<a href="/bookList.ok?page=${a}&mnName=${mnName}&mdCode=${mdCode}&year=${filter.year}&month=${filter.month}&week=${filter.week}&sort=${filter.sort}&limit=${limit}">${a}</a>
								</c:if>
							</c:forEach>
							
							<!-- 다음 화살표 -->		
							<c:if test="${pageInfo.endPage < pageInfo.maxPage }">
								<a href="/bookList.ok?page=${pageInfo.endPage+1}&mnName=${mnName}&mdCode=${mdCode}&year=${filter.year}&month=${filter.month}&week=${filter.week}&sort=${filter.sort}&limit=${limit}" class="next-arrow">
									<i class="fa fa-long-arrow-right pt-3" aria-hidden="true"></i>
								</a>
							</c:if>
						</div>
					</c:if>
				</div>
				<!-- End Paging Bar -->
			</div>
			
			<!-- Right Side Banner -->
			<div class="sideBanner" style="top: 500px;">	<!-- position:absolute; -->
				<div >
					<h4>오늘 본 상품</h4>
				</div>
		    <div id="todayCarousel" class="carousel slide" data-ride="carousel">
				  <ol class="carousel-indicators">
				  	<c:forEach var="todayImage" items="${todayImageList }" varStatus="status">
				    <li class="item-li" data-target="#todayCarousel" data-slide-to="${status.index }" ${status.first ? 'class="active"' : ''}></li>
				  	</c:forEach>
				  </ol>
				  <div class="carousel-inner">
		    	<c:forEach var="todayImage" items="${todayImageList }" varStatus="status">
				    <div class="carousel-item ${status.first ? 'active' : ''}">
				      <img src="${request.getContextPath()}/bookImage/${todayImage }" class="book-img" alt="최근 본 책"
				      			style="width:inherit; height:inherit; object-fit: cover;">
				    </div>
		    	</c:forEach>
				  </div>
				  <button class="carousel-control-prev" type="button" data-target="#todayCarousel" data-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				  </button>
				  <button class="carousel-control-next" type="button" data-target="#todayCarousel" data-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				  </button>
				</div>
		  </div>
			<!-- Right Side Banner -->
		  
		</div>
	</div>

	<!-- Start related-product Area -->
	<section class="related-product-area section_gap">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-6 text-center">
					<div class="section-title">
						<h1>Deals of the Week</h1>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore
							magna aliqua.</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-9">
					<div class="row">
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r1.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r2.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r3.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r5.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r6.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r7.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r9.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r10.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="single-related-product d-flex">
								<a href="#"><img src="img/r11.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="ctg-right">
						<a href="#" target="_blank">
							<img class="img-fluid d-block mx-auto" src="img/category/c5.jpg" alt="">
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End related-product Area -->

	<!-- start footer Area -->
	<%@ include file="/common/footer.jsp" %>
	<!-- End footer Area -->

	<!-- Modal Quick Product View -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="container relative">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<div class="product-quick-view">
					<div class="row align-items-center">
						<div class="col-lg-6">
							<div class="quick-view-carousel">
								<div class="item" style="background: url(img/organic-food/q1.jpg);">

								</div>
								<div class="item" style="background: url(img/organic-food/q1.jpg);">

								</div>
								<div class="item" style="background: url(img/organic-food/q1.jpg);">

								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="quick-view-content">
								<div class="top">
									<h3 class="head">Mill Oil 1000W Heater, White</h3>
									<div class="price d-flex align-items-center"><span class="lnr lnr-tag"></span> <span class="ml-10">$149.99</span></div>
									<div class="category">Category: <span>Household</span></div>
									<div class="available">Availibility: <span>In Stock</span></div>
								</div>
								<div class="middle">
									<p class="content">Mill Oil is an innovative oil filled radiator with the most modern technology. If you are
										looking for something that can make your interior look awesome, and at the same time give you the pleasant
										warm feeling during the winter.</p>
									<a href="#" class="view-full">View full Details <span class="lnr lnr-arrow-right"></span></a>
								</div>
								<div class="bottom">
									<div class="color-picker d-flex align-items-center">Color:
										<span class="single-pick"></span>
										<span class="single-pick"></span>
										<span class="single-pick"></span>
										<span class="single-pick"></span>
										<span class="single-pick"></span>
									</div>
									<div class="quantity-container d-flex align-items-center mt-15">
										Quantity:
										<input type="text" class="quantity-amount ml-15" value="1" />
										<div class="arrow-btn d-inline-flex flex-column">
											<button class="increase arrow" type="button" title="Increase Quantity"><span class="lnr lnr-chevron-up"></span></button>
											<button class="decrease arrow" type="button" title="Decrease Quantity"><span class="lnr lnr-chevron-down"></span></button>
										</div>

									</div>
									<div class="d-flex mt-20">
										<a href="#" class="view-btn color-2"><span>Add to Cart</span></a>
										<a href="#" class="like-btn"><span class="lnr lnr-layers"></span></a>
										<a href="#" class="like-btn"><span class="lnr lnr-heart"></span></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<script src="js/vendor/jquery-2.2.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	 crossorigin="anonymous"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<!--gmaps Js-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="js/gmaps.min.js"></script>
	<script src="js/main.js"></script>
	<!-- YJ -->
	<script src="/js/yj/book.calculate-weekOfMonth.js"></script>
	<script src="/js/yj/book.sort-catgys.js"></script>
	<script type="text/javascript">
	  $(document).ready(function() {
		  $.main_catgy_arr = new Array();
		  $.middle_catgy_arr = new Array();
		  $.sub_catgy_arr = new Array();
		  
		  sort_catgys(${catgys}, $.main_catgy_arr, $.middle_catgy_arr, $.sub_catgy_arr);
	  });
	</script> 
	<script src="/js/yj/book.bookList.js"></script>
	<script src="/js/yj/book.bookListURL.js"></script>
</body>

</html>