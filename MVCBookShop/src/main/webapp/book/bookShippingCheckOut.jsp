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
  <title>MVC Book Shop</title>

  <!-- CSS -->
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
                  <h1>Checkout</h1>
                  <nav class="d-flex align-items-center">
                      <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                      <a href="single-product.html">Checkout</a>
                  </nav>
              </div>
          </div>
      </div>
  </section>
  <!-- End Banner Area -->

  <!--================Checkout Area =================-->
  <section class="checkout_area section_gap">
      <div class="container">
          <!-- <div class="returning_customer">
              <div class="check_title">
                  <h2>Returning Customer? <a href="#">Click here to login</a></h2>
              </div>
              <p>If you have shopped with us before, please enter your details in the boxes below. If you are a new
                  customer, please proceed to the Billing & Shipping section.</p>
              <form class="row contact_form" action="#" method="post" novalidate="novalidate">
                  <div class="col-md-6 form-group p_star">
                      <input type="text" class="form-control" id="name" name="name">
                      <span class="placeholder" data-placeholder="Username or Email"></span>
                  </div>
                  <div class="col-md-6 form-group p_star">
                      <input type="password" class="form-control" id="password" name="password">
                      <span class="placeholder" data-placeholder="Password"></span>
                  </div>
                  <div class="col-md-12 form-group">
                      <button type="submit" value="submit" class="primary-btn">login</button>
                      <div class="creat_account">
                          <input type="checkbox" id="f-option" name="selector">
                          <label for="f-option">Remember me</label>
                      </div>
                      <a class="lost_pass" href="#">Lost your password?</a>
                  </div>
              </form>
          </div>
          <div class="cupon_area">
              <div class="check_title">
                  <h2>Have a coupon? <a href="#">Click here to enter your code</a></h2>
              </div>
              <input type="text" placeholder="Enter coupon code">
              <a class="tp_btn" href="#">Apply Coupon</a>
          </div> -->
          <div class="billing_details">
              <div class="row">
                  <div class="col-lg-8">
                      <h3>배송 정보</h3>
                      <form class="row contact_form" action="${request.getContextPath()}/bookShippingConfirm.ok" method="post" 
                      			name="shippingForm" novalidate="novalidate">
                      	<%-- <input type="hidden" name="cartId" value="${sessionCookie}"/> --%> 
                        <div class="col-md-12 form-group p_star">
                            <input type="text" class="form-control" id="m_name" name="m_name" placeholder="성명">
                            <!-- <span class="placeholder" data-placeholder="성명"></span> -->
                        </div>
                        <div class="col-md-4 form-group p_star">
                            <input type="text" class="form-control" id="m_phone0" name="m_phone0" readonly="readonly" placeholder="010">
                            <!-- <span class="placeholder" data-placeholder="010"></span> -->
                        </div>
                        <div class="col-md-4 form-group p_star">
                            <input type="text" class="form-control" id="m_phone1" name="m_phone1" placeholder="가운데 연락처">
                            <!-- <span class="placeholder" data-placeholder="가운데 연락처"></span> -->
                        </div>
                        <div class="col-md-4 form-group p_star">
                            <input type="text" class="form-control" id="m_phone2" name="m_phone2" placeholder="연락처 마지막 4자리">
                            <!-- <span class="placeholder" data-placeholder="연락처 마지막 4자리"></span> -->
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="m_email" name="m_email" placeholder="이메일">
                            <!-- <span class="placeholder" data-placeholder="yeji@naver.com"></span> -->
                        </div>
                        <div class="col-md-12 form-group p_star">
                            <select class="country_select" name="m_country">
                                <option value="choose" disabled="disabled">선택하세요</option>
                                <option value="한국" selected="selected">한국</option>
                                <option value="U.S.A">U.S.A</option>
                            </select>
                        </div>
                        <div class="col-md-12 form-group p_star">
                            <input type="text" class="form-control" id="m_postcode" name="m_postcode" placeholder="우편 번호">
                            <!-- <span class="placeholder" data-placeholder="우편 번호"></span> -->
                        </div>
                        <div class="col-md-12 form-group p_star">
                            <input type="text" class="form-control" id="m_addr1" name="m_addr1" placeholder="주소">
                            <!-- <span class="placeholder" data-placeholder="주소"></span> -->
                        </div>
                        <div class="col-md-12 form-group">
                            <div class="creat_account">
                                <input type="checkbox" id="f-option2" name="selector">
                                <label for="f-option2">Create an account?</label>
                            </div>
                        </div>
                        <div class="col-md-12 form-group">
                            <div class="creat_account">
                                <h3>Shipping Details</h3>
                                <input type="checkbox" id="f-option3" name="selector">
                                <label for="f-option3">Ship to a different address?</label>
                            </div>
                            <textarea class="form-control" name="message" id="message" rows="1" placeholder="Order Notes"></textarea>
                        </div>
                      </form>
                  </div>
                  <div class="col-lg-4">
                      <div class="order_box">
                          <h2>Your Order</h2>
                          <ul class="list">
                              <li><a href="#">Product <span>Total</span></a></li>
                              <c:forEach items="${cartList }" var="cart">
	                              <li>
	                              	<a href="#">${cart.c_b_name} <span class="middle">x ${cart.c_b_qty}</span> <span class="last">${cart.c_b_price * cart.c_b_qty}</span>
	                              	</a>
	                              </li>
                              </c:forEach>
                          </ul>
                          <ul class="list list_2">
                              <li><a href="#">Subtotal <span>$ ${totalMoney}</span></a></li>
                              <li><a href="#">Shipping <span>Flat rate: $0.00</span></a></li>
                              <li><a href="#">Total <span>$ ${totalMoney}</span></a></li>
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
                          <input type="submit" class="primary-btn" value="결제하기" onclick="javascript:sendShippingForm()"/>
                          <input type="button" class="primary-btn" value="이전으로" onclick="javascript:history.back(-1);">
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
  <script src="js/vendor/jquery.cookie.js"></script>
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
  	function sendShippingForm() {
  		
/* 			alert($.cookie('c_session'));
			
			var $input2 = $('<input>').attr({
				type: 'hidden',
				name: 'c_session',
				value: $.cookie('c_session')
			});
			
			$('form[name="shippingForm"]').append($input2); */
			//$form.appendTo('body'); // Append the form to the document body
			
			// 순수 JS 코드
			document.shippingForm.submit(); 
			
	}
  </script>
</body>

</html>