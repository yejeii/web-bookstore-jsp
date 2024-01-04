<%@ page import="vo.Book" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
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
	<link rel="stylesheet" href="/css/linearicons.css">
	<link rel="stylesheet" href="/css/font-awesome.min.css">
	<link rel="stylesheet" href="/css/themify-icons.css">
	<link rel="stylesheet" href="/css/bootstrap.css">
	<link rel="stylesheet" href="/css/owl.carousel.css">
	<link rel="stylesheet" href="/css/nice-select.css">
	<link rel="stylesheet" href="/css/nouislider.min.css">
	<link rel="stylesheet" href="/css/ion.rangeSlider.css" />
	<link rel="stylesheet" href="/css/ion.rangeSlider.skinFlat.css" />
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
					<h1>Product Details Page</h1>
					<nav class="d-flex align-items-center">
						<a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
						<a href="#">Shop<span class="lnr lnr-arrow-right"></span></a>
						<a href="single-product.html">product-details</a>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Banner Area -->

	<!--================Single Product Area =================-->
	<div class="product_image_area">
		<div class="container">
			<div class="row s_product_inner">
				<div class="col-lg-12 mb-4">
					<div class="s_product_catgy row col">
						<div class="default-select">
							<select class="main_select">
							</select>
						</div>
						<div style="display:flex; justify-content:center; align-items:center;">&nbsp;//&nbsp;</div>
						<div class="default-select">
							<select class="middle_select">
							</select>
						</div>
						<div style="display:flex; justify-content:center; align-items:center;">&nbsp;//&nbsp;</div>
						<div class="default-select">
							<select class="sub_select">
							</select>
						</div>	
					</div>
				
				</div>
				<div class="col-lg-6">
					<div class="s_Product_carousel">
						<div class="single-prd-item ">
							<img class="img-fluid" style="max-width:80%;" src="${request.getServletContext().getRealPath('/')}/bookImage/${book.b_image }" alt="">
						</div>
						<div class="single-prd-item">
							<img class="img-fluid" style="max-width:80%;" src="${request.getServletContext().getRealPath('/')}/bookImage/${book.b_image }" alt="">
						</div>
						<div class="single-prd-item">
							<img class="img-fluid" style="max-width:80%;" src="${request.getServletContext().getRealPath('/')}/bookImage/${book.b_image }" alt="">
						</div>
					</div>
				</div>
				<div class="col-lg-5 offset-lg-1" style="margin-left:5%;">
					<div class="s_product_text">
						<h3>${book.b_name }</h3>
						<h2><i class="bi bi-currency-dollar"></i>${book.b_price }</h2>
						<ul class="list">
							<li><a href="#"><span>지은이</span> : ${book.b_writer }</a></li>
							<c:if test="${not empty book.b_translator }">
							<li><a href="#"><span>옮긴이</span> : ${book.b_translator }</a></li>
							</c:if>
							<li><a href="javascript:void(0)"><span>출판일</span> : ${book.b_publish_date }</a></li>
							<li><a href="#"><span>재고 현황</span> : 재고 있음</a></li>
						</ul>
						<%-- <p>${book.b_content }</p> --%>
						<div class="product_count">
							<label for="qty">수량 :</label>
							<input type="text" name="qty" id="sst" maxlength="12" value="1" title="Quantity:" class="input-text qty">
							<button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"
							 				class="increase items-count" type="button" style="padding-top: 5px;"><span class="lnr lnr-chevron-up"></span></button>
							<button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"
											class="reduced items-count" type="button" style="padding-bottom: 7px;"><span class="lnr lnr-chevron-down"></span></button>
						</div>
						<div class="card_area d-flex align-items-center">
							<form name="addForm" action="${request.getContextPath() }/bookCartAdd.ok?page=${page }&b_id=${book.b_id }&sort=${sort}&limit=${limit}" method="post">
								<a class="primary-btn" href="#" onclick="javascript:addToCart()">장바구니 담기</a>
							</form>
							<a class="primary-btn" href="#">바로 구매</a>
							<a class="icon_btn pt-3" href="#"><i class="lnr lnr lnr-diamond"></i></a>
							<a class="icon_btn pt-3" href="#"><i class="lnr lnr lnr-heart"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--================End Single Product Area =================-->

	<!--================Product Description Area =================-->
	<section class="product_description_area">
		<div class="container">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item">
					<a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" 
							aria-selected="true">도서 설명</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
					 		aria-selected="false">도서 상세정보</a>
				</li>
				<li class="nav-item" onclick="getCommentList(${book.b_id});">
					<a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact"
					 		aria-selected="false">인상깊었던 글귀</a>
				</li>
				<li class="nav-item" onclick="getReviewList();">
					<a class="nav-link" id="review-tab" data-toggle="tab" href="#review" role="tab" aria-controls="review"
					 		aria-selected="false">도서 리뷰</a>
				</li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
					<p>${book.b_content }</p>
				</div>
				<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
					<div class="table-responsive">
						<table class="table">
							<tbody>
								<tr>
									<td>
										<h5>도서 분류</h5>
									</td>
									<td>
										<h5 class="mb-2"></h5>
										<c:if test="${book.bookSubCatgyList != null }">
											<c:forEach items="${book.bookSubCatgyList}" var="book_sub_catgy">
												<h5 class="mb-2">${book_sub_catgy.code_ref_mn_name } > ${book_sub_catgy.code_ref_md_name } > ${book_sub_catgy.name }</h5>
											</c:forEach>
										</c:if>
									</td>
								</tr>
								<tr>
									<td>
										<h5>전체 쪽수</h5>
									</td>
									<td>
										<h5>${book.b_page }&nbsp;쪽</h5>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
					<div class="row">
						<div class="col-lg-6">
							<div class="comment_list">
								<!-- 코멘트 출력 부분 -->
							</div>
						</div>
						<div class="col-lg-6">
							<div class="review_box">
								<h4>인상 깊었던 글귀를 올려주세요</h4>
								<form class="row contact_form" name="commentForm" method="post" id="commentForm" novalidate="novalidate">
									<input type="hidden" name="c_b_id" value="${book.b_id }">
									<div class="col-md-12">
										<div class="form-group">
											<input type="text" class="form-control" id="c_m_id" name="c_m_id" placeholder="ID">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<input type="text" class="form-control" id="c_title" name="c_title" placeholder="코멘트 제목을 입력하세요.">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<textarea class="form-control" name="c_text" id="c_text" rows="1" placeholder="인상깊었던 글귀를 적어주세요."></textarea>
										</div>
									</div>
									<div class="col-md-12 text-right">
										<button type="button" class="btn primary-btn registCommentBtn">등록하기</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane fade" id="review" role="tabpanel" aria-labelledby="review-tab">
					<div class="row">
						<div class="col-lg-6">
							<div class="row total_rate">
								<div class="col-6">
									<div class="box_total review_overall">
										<h5>Overall</h5>
										<h4>4.0</h4>
										<h6>(03 Reviews)</h6>
									</div>
								</div>
								<div class="col-6">
									<div class="rating_list review_rate">
										<h3>Based on 3 Reviews</h3>
										<ul class="list review_rate_list">
											<li><a href="#">5 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
													 class="fa fa-star"></i><i class="fa fa-star"></i> <span>01</span> </a></li>
											<li><a href="#">4 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
													 class="fa fa-star"></i><i class="fa fa-star-o"></i> <span>01</span> </a></li>
											<li><a href="#">3 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
													 class="fa fa-star-o"></i><i class="fa fa-star-o"></i> <span>01</span> </a></li>
											<li><a href="#">2 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i
													 class="fa fa-star-o"></i><i class="fa fa-star-o"></i> <span>01</span> </a></li>
											<li><a href="#">1 Star <i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i
													 class="fa fa-star-o"></i><i class="fa fa-star-o"></i> <span>01</span> </a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="review_list">
								<!-- 리뷰가 출력되는 부분(ajax) -->
							</div>
						</div>
						<div class="col-lg-6">
							<div class="review_box">
								<h4>리뷰 작성</h4>
								<form class="row contact_form" name="reviewForm" novalidate="novalidate">
									<input type="hidden" name="r_b_id" id="r_b_id" value="${book.b_id }">
									<input type="hidden" name="page" id="page" value="${page }">
									<div class="col-md-2">
										<div class="checkbox">
											<input type="checkbox" class="form-control" value="1" id="r_buy_opt" name="r_buy_opt" checked="checked">구매 여부
										</div>
									</div>
									<div class="col-md-10">
										<div class="form-group">
											<p>별점 : </p>
											<div class="radio">
												<label class="radio-inline"> <input type="radio" name="r_star" id="r_star" value="1" checked="checked">★☆☆☆☆</label>
												<label class="radio-inline"> <input type="radio" name="r_star" id="r_star" value="2">★★☆☆☆</label>
												<label class="radio-inline"> <input type="radio" name="r_star" id="r_star" value="3">★★★☆☆</label>
												<label class="radio-inline"> <input type="radio" name="r_star" id="r_star" value="4">★★★★☆</label>
												<label class="radio-inline"> <input type="radio" name="r_star" id="r_star" value="5">★★★★★</label>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<input type="text" class="form-control" id="r_m_id" name="r_m_id" placeholder="아이디">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<textarea class="form-control" name="r_text" id="r_text" rows="1" placeholder="리뷰" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Review'"></textarea></textarea>
										</div>
									</div>
									<div class="col-md-12 text-right">
										<button type="button" class="primary-btn" id="registBtn">리뷰 등록</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================End Product Description Area =================-->

	<!-- Start related-product Area -->
	<section class="related-product-area section_gap_bottom">
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
	<!-- yeji -->
	<script type="text/javascript">
		$(document).ready(function() {
			$.catgys = ${catgys};
			$.b_bc_code = ${book.b_bc_code };
		});
	
		function addToCart() {
			document.addForm.submit();
			alert('장바구니에 추가되었습니다.')
		}
		
		// b_id에 해당하는 모든 리뷰를 불러오는 script
		function getReviewList() {
			
			// 건네줄 데이터를 js 오브젝트로 만든다.  
			var data = {
				'b_id' : ${book.b_id},
				'page' : ${page}
			};
			console.log(data);
			
			$.ajax({
				url: "/bookReviewList.re",
				type: "POST",
				data: data,
				//contentType: "application/json; charset=utf-8",
				dataType: "TEXT",
				success: function(data) {
					// console.log(data);
					// console.log(typeof data);	
					
					var html = "";

					if(data === "no-review") {
						html += "<div class='review_item'>" + "등록된 리뷰가 없습니다. " + "</div>";
						$(".review_list").html(html);
						$(".review_overall").children("h4").html("0");
						$(".review_overall").children("h6").html("리뷰가 없습니다.");
						$(".review_rate").children("h3").html("Based on 0 Reviews");
						
					} else {
						
						// String타입의 jsArr(JSON으로 인코딩된 객체)를 자바스크립트 객체로 변환(convert a string containing JSON notatio)
						var toJsObj = JSON.parse(data);
						console.log(toJsObj);
						console.log(toJsObj.startCnt);
					
						if(toJsObj.univ != null) {
							var star = 0;
							
							// 리뷰 출력
							$.each(toJsObj["univ"], function(index, value) {
								console.log(index);
								console.log(value);
								
								star += value.r_star;
								
								html += "<div class='review_item'>";
									html += "<div class='media'>";
										html += "<div class='d-flex'>";
											html += "<img src='img/product/review-2.png' alt=''>";
										html += "</div>";
										html += "<div class='media-body row'>";
											html += "<div class='col-lg-8'>";
												html += "<h4>" + value.r_m_id + "</h4>";
											html += "</div>";
											html += "<div class='col-lg-4'>";
												html += "<h5>" + value.r_regdate + "</h5>";
											html += "</div>";
											html += "<div class='col-lg-12 star'>";
												for(let i = 1; i <= value.r_star; i++) {
													  html += "<i class='fa fa-star'></i>";
													}
											html += "</div>";
										html += "</div>";
									html += "</div> ";
									html += "<p>" + value.r_text + "</p>";
								html += "</div>";
		
								$(".review_list").html(html);
								
							});
							
							// 별점 출력
							$(".review_rate_list li:eq(0) span").html(toJsObj.startCnt.star5);
							$(".review_rate_list li:eq(1) span").html(toJsObj.startCnt.star4);
							$(".review_rate_list li:eq(2) span").html(toJsObj.startCnt.star3);
							$(".review_rate_list li:eq(3) span").html(toJsObj.startCnt.star2);
							$(".review_rate_list li:eq(4) span").html(toJsObj.startCnt.star1);
						
							$(".review_overall").children("h4").html(star/toJsObj.univ.length);
							$(".review_overall").children("h6").html("(" + toJsObj.univ.length + ") 리뷰들");
							$(".review_rate").children("h3").html("Based on " + toJsObj.univ.length  + " Reviews");
						}
					}
				},
				error: function(request, status, error) {
					console.log("code: "+request.status+"\n"+"message : "+request.responseText+"\n"+"error: "+error);
				}
			}); 
		}
		
		// 리뷰 등록
		$("#registBtn").click(function(){
			/* 
			if(${sessionScope.user == null}) {
				alert('로그인 후 이용가능합니다.');
				return;
			} 
			*/
			
			// data : 데이터로 가지고 가야할 값이 많을 때 form 태그 안에 있다면 
			// form태그의 선택자.serialize(); 을 해주면 form 태그 안의 모든 값들이 name값을 키값으로 만들어 보내준다.
			// data: {"r_b_id", 값}
			var queryString = $("form[name=reviewForm]").serialize();
			$.ajax({
				url: "/bookReviewRegistPro.re",
				type: "POST",
				data: queryString,
				success: function() {
					alert("댓글이 등록되었습니다");
					getReviewList();
					$("#r_text").val("").focus();
				}, 
				error: function(request, status, error) {
					console.log("code: "+request.status+"\n"+"message : "+request.responseText+"\n"+"error: "+error);
				}
			});
			
		});
		
		// 코멘트 등록
		$(".registCommentBtn").click(function() {
			/* 
			if(${sessionScope.user == null}) {
				alert('로그인 후 이용가능합니다.');
				return;
			} 
			*/
			
			var queryString = $("form[name=commentForm]").serialize();

			$.ajax({
				url: "/bookCommentRegistPro.re",
				type: "POST",
				data: queryString,
				dataType: "TEXT",	// 서버에서 DB insert에 대한 결과물을 받을 것
				success: function(text) {
					if(text === "success") {
						alert("등록되었습니다.");
						$("#c_title").attr("readonly", "readonly");
						$("#c_text").attr("readonly", "readonly");
						getCommentList(${book.b_id});
					} else {
						alert("등록되지 못했습니다.");
						return false;
					}
					
				}, 
				error: function(request, status, error) {
					console.log("code: "+request.status+"\n"+"message : "+request.responseText+"\n"+"error: "+error);
				} 
			});
			
		});
		
		// 등록된 코멘트를 출력하는 함수
		function getCommentList(b_id) {
			
			// Form 양식 정리
			$('input[name="c_ref"]').remove();
			$('input[name="c_lev"]').remove();
			$('input[name="c_seq"]').remove();
			
			$(".review_box").children("h4").html("인상 깊었던 글귀를 올려주세요.");
			$("#c_title").removeAttr("readonly");
			$("#c_text").removeAttr("readonly");
			$("#c_title").val("").focus();
			$("#c_text").val("");
			$("#c_title").attr("placeholder", "코멘트 제목을 입력하세요.");
			$("#c_text").attr("placeholder", "인상깊었던 글귀를 적어주세요.");
			
			$.ajax({
				url: "/bookCommentList.re",
				type: "POST",
				data: {"c_b_id": b_id},
				dataType: "TEXT",
				success: function(text) {
					// console.log(text);
					var html = "";
					
					if(text === "no-comments") {
						html += "<div class='review_item'><h4>" + "등록된 글귀가 없습니다. " + "</h4></div>";
						
						$(".comment_list").html(html);
					} else {
						
						// String타입의 jsArr(JSON으로 인코딩된 객체)를 자바스크립트 객체로 변환
						var toJsObj = JSON.parse(text);
						
						if(toJsObj.comments != null) {
							$.each(toJsObj["comments"], function(index, value) {
								
								html += "<div class='review_item' style='padding-left: " + value.c_lev*14 + "px;'>";
									html += "<div class='media row'>";
										html += "<div class='d-flex'>";
											html += "<img src='img/product/review-1.png' alt='' width=50>";
										html += "</div>";
										html += "<div class='media-body row'>";
											html +=	"<div class='col-lg-9'>";
												html +=	"<h4>" + value.c_title + "</h4>";
												html += "<span>" + value.c_m_id + "</span> &nbsp;&nbsp;";
												html += "<span>" + value.c_regdate + "</span> &nbsp;&nbsp;";
												html += "<a href='javascript:void(0)' onclick='clickEmpathy(\"" + value.c_id +"\");'>";
													html += "<span>공감(" + value.c_empathy + ")</span>";
												html += "</a> &nbsp;&nbsp;";
											html += "</div>";
											html += "<div class='col-lg-3' style='right: 15px;'>";
												html += "<a class='reply_btn' onclick='replyForm(\"" + value.c_ref + "\", \"" + value.c_lev + "\", \"" + value.c_seq + "\");'>댓글</a>"
											html += "</div>";
										html += "</div>";
									html += "</div>";
									html += "<div class='media'>";
										html += "<p>" + value.c_text + "</p>";
									html += "</div>";
								html += "</div>";
								
								$(".comment_list").html(html);
								
							});
						}
						
					}
				}, 
				error: function(request, status, error) {
					console.log("code: "+request.status+"\n"+"message : "+request.responseText+"\n"+"error: "+error);
				} 
			});
		}
		
		// 코멘트 댓글 버튼을 누를 때 호출되는 함수
		function replyForm(c_ref, c_lev, c_seq) {
			// alert(c_ref + c_lev + c_seq);
			
			// 코멘트 다는 Form 설정하기
			$("#commentForm").append($('<input/>', {type: 'hidden', name: 'c_ref'}));
			$("#commentForm").append($('<input/>', {type: 'hidden', name: 'c_lev'}));
			$("#commentForm").append($('<input/>', {type: 'hidden', name: 'c_seq'}));
			$('input[name="c_ref"]').val(c_ref);
			$('input[name="c_lev"]').val(c_lev);
			$('input[name="c_seq"]').val(c_seq);
			
			$(".review_box").children("h4").html("댓글을 달아주세요.");
			$("#c_title").removeAttr("readonly");
			$("#c_text").removeAttr("readonly");
			$("#c_title").val("").focus();
			$("#c_text").val("");
			$("#c_title").attr("placeholder", "댓글 제목을 적어주세요.");
			$("#c_text").attr("placeholder", "댓글을 달아주세요.");
			
		}
		
		// 공감 버튼을 누를 때 호출되는 함수
		function clickEmpathy(c_id) {
			alert(c_id);
			
			$.ajax({
				url: "/bookCommentEmpathy.re",
				type: "POST",
				data: {"c_id": c_id},
				dataType: "TEXT",
				success: function(text) {
					if(text === "success") {
						getCommentList(${book.b_id});
					} else {
						alert("공감되지 못했습니다.");
						return false;
					}
				}, 
				error: function(request, status, error) {
					console.log("code: "+request.status+"\n"+"message : "+request.responseText+"\n"+"error: "+error);
				}
			});
		}
	</script>
	<script language=JavaScript src="/js/yj/book.sort-b_bc_code.js"></script>
	

</body>

</html>