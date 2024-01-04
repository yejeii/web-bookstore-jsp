<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

  <!-- CSS -->
  <link rel="stylesheet" href="/css/linearicons.css">
  <link rel="stylesheet" href="/css/owl.carousel.css">
  <link rel="stylesheet" href="/css/themify-icons.css">
  <link rel="stylesheet" href="/css/font-awesome.min.css">
  <link rel="stylesheet" href="/css/nice-select.css">
  <link rel="stylesheet" href="/css/nouislider.min.css">
  <link rel="stylesheet" href="/css/bootstrap.css">
  <link rel="stylesheet" href="/css/main.css">
  <link rel="stylesheet" href="/css/manager/style.css">
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
                  <h1>Your Info</h1>
                  <nav class="d-flex align-items-center">
                      <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                      <a href="single-product.html">Login/Your Info</a>
                  </nav>
              </div>
          </div>
      </div>
  </section>
  <!-- End Banner Area -->

  <!-- Member Info Area -->
  <section class="checkout_area section_gap">
      <div class="container">
          <div class="billing_details">
              <div class="row">
              		<div class="col-lg-4">
                      <div class="order_box">
                          <h2>회원 관리</h2>
                          <ul class="list">
                          		<li><a href="${request.getContextPath() }/memberView.me?isLogin=true&userId=${m_id}">회원 정보</a></li>
                              <li><a href="${request.getContextPath() }/memberModifyPw.me?isLogin=true&userId=${m_id}">비밀번호 관리</a></li>
                          </ul>
                          <div class="payment_item">
                              <div class="radion_btn">
                                  <input type="radio" id="f-option5" name="selector">
                                  <label for="f-option5">Check payments</label>
                                  <div class="check"></div>
                              </div>
                              <p>Please send a check to Store Name, Store Street, Store Town, Store State / County,
                                  Store Postcode.</p>
                          </div>
                          <div class="payment_item active">
                              <div class="radion_btn">
                                  <input type="radio" id="f-option6" name="selector">
                                  <label for="f-option6">Paypal </label>
                                  <img src="img/product/card.jpg" alt="">
                                  <div class="check"></div>
                              </div>
                              <p>Pay via PayPal; you can pay with your credit card if you donât have a PayPal
                                  account.</p>
                          </div>
                          <div class="creat_account">
                              <input type="checkbox" id="f-option4" name="selector">
                              <label for="f-option4">Iâve read and accept the </label>
                              <a href="#">terms & conditions*</a>
                          </div>
                          <a class="primary-btn" href="#">Proceed to Paypal</a>
                      </div>
                  </div>
              
                  <div class="col-lg-8">
                  	<div class="mx-auto" style="width: 400px;">
                    <h3>비밀번호 변경</h3>
                    <h5 class="mb-3">안전한 비밀번호로 내정보를 보호하세요.</h5>
                    <h6 class="text-danger">&spades;&nbsp;다른 아이디/사이트에서 사용한 적 없는 비밀번호</h6>
                    <h6 class="text-danger">&spades;&nbsp;이전에 사용한 적 없는 비밀번호가 안전합니다.</h6>
                    
                    <div class="mt-4">
	                    <form class="row contact_form" id="infoForm" action="/memberModifyPwPro.me" method="post" novalidate="novalidate">
	                        <input type="hidden" name="m_id" value="${m_id }">
	                        <div class="form-group mx-sm-3 mb-4 w-100"> <!-- w-100 : style="width:100%" -->
												    <label for="oldPw" class="sr-only">Password</label>
												    <input type="password" class="form-control" id="oldPw" name="oldPw" placeholder="현재 비밀번호를 입력하세요.">
												  </div>
												  <div class="form-group mx-sm-3 mb-2 w-100">
												    <label for="newPw" class="sr-only">Password</label>
												    <input type="password" class="form-control" id="newPw" name="newPw" placeholder="변경할 비밀번호를 입력하세요.">
												  </div>
												  <div class="form-group mx-sm-3 mb-2 w-100">
												    <label for="checkPw" class="sr-only">Password</label>
												    <input type="password" class="form-control" id="checkPw" placeholder="변경할 비밀번호를 한번 더 입력하세요.">
												  </div>
	                    </form>
                    </div>
                    <div class="w-100 mt-5" style="height: 50px;"><!-- div 영역의 전체 높이ㅡㄹ 50px로 고정 -->
                    	<button type="button" class="h-100 w-100 btn btn-outline-warning" id="modifyBtn" onclick="javascript:modifyForm();">변경하기</button>
                    	<!-- h-100 : 부모 높이처럼. w-100 : 부모 너비처럼. -->
                    </div>
                    </div>
								  </div>
							</div>
					</div>
  		</div>
  </section>
  <!--================End Checkout Area =================-->

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
  <script type="text/javascript">
	  
		  
		  function modifyForm() {
			  if (checkValue()) {
		  		$('#infoForm').submit();			
				}
		  }
		  
		  function checkValue() {
			  var oldPw = $('#oldPw').val();
			  var newPw = $('#newPw').val();
			  var checkPw = $('#checkPw').val();
			  
			  // only calls the checkPw() function if all three inputs are non-empty
			  if (checkNull(oldPw) && checkNull(newPw) && checkNull(checkPw)) {
				  if(validatePassword(newPw, checkPw)) {
				      return true;
				    }
				}
			  return false;
		  }
				
		  function checkNull(value) {
			  if(value == null || value === '') {
  				alert('모든 칸을 입력하세요');
  				return false;
  			}
			  return true;
		  }
			  
		  function validatePassword(pw, pw2) {
			  if(pw !== pw2) {
				  alert('변경할 비밀번호가 서로 맞지 않습니다.');
				  return false;
			  }
				return true;
		  }
			  

	  
  </script>
</body>

</html>