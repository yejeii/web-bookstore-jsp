<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c-rt" uri="http://java.sun.com/jstl/core_rt"%>
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
					<h1>Confirmation</h1>
					<nav class="d-flex align-items-center">
						<a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
						<a href="category.html">Confirmation</a>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Banner Area -->

	<!--================Order Details Area =================-->
	<section class="order_details section_gap">
		<div class="container">
			<h3 class="title_confirmation">Thank you. Your order has been received.</h3>
			<div class="row order_d_inner">
				<div class="col-lg-4">
					<div class="details_item">
						<h4>Order Info</h4>
						<ul class="list">
							<li><a href="#"><span>Order number</span> : 60235</a></li>
							<li><a href="#"><span>Date</span> : ${shippingDate} </a></li>
							<li><a href="#"><span>Total</span> : $ ${totalMoney}</a></li>
							<li><a href="#"><span>Payment method</span> : Check payments</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="details_item">
						<h4>주문자 정보</h4>
						<ul class="list">
									<li><a href="#"><span>주문자</span> : <span id="shippingName"></span></a></li>
								<li>
									<a href="#">
										<span>연락처</span> 
										: <i>010</i> - <i id="shippingPhone1"></i> - <i id="shippingPhone2"></i>
									</a>
								</li>
								<li><a href="#"><span>이메일 주소</span> : <span id="shippingEmail"></span></a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="details_item">
						<h4>배송 주소</h4>
						<ul class="list">
								<li><a href="#"><span>국가명</span> : <span id="shippingCountry"></span></a></li>
								<li><a href="#"><span>도로명 주소</span> : <span id="shippingAddr1"></span></a></li>
								<li><a href="#"><span>상세 주소</span> : <span id="shippingAddr2"></span></a></li>
								<li><a href="#"><span>우편번호</span> : <span id="shippingPostcode"></span></a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="order_details_table">
				<h2>Order Details</h2>
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Product</th>
								<th scope="col">Quantity</th>
								<th scope="col">Total</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cartList }" var="cart">
								<tr>
									<td>
										<p>${cart.c_b_name }</p>
									</td>
									<td>
										<h5>x ${cart.c_b_qty}</h5>
									</td>
									<td>
										<p>$ ${cart.c_b_qty * cart.c_b_price }</p>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td>
									<h4>Subtotal</h4>
								</td>
								<td>
									<h5></h5>
								</td>
								<td>
									<p>$ ${totalMoney }</p>
								</td>
							</tr>
							<tr>
								<td>
									<h4>Shipping</h4>
								</td>
								<td>
									<h5></h5>
								</td>
								<td>
									<p>Flat rate: $00.00</p>
								</td>
							</tr>
							<tr>
								<td>
									<h4>Total</h4>
								</td>
								<td>
									<h5></h5>
								</td>
								<td>
									<p>$ ${totalMoney }</p>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>
	<!--================End Order Details Area =================-->
	
  <!-- start footer Area -->
  <%@ include file="/common/footer.jsp" %>
  <!-- End footer Area -->


  <script src="/js/vendor/jquery-2.2.4.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" 
  		integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
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
  	window.onload = function() {
  		
  		var shipping_name = "shipping_name";
  		var shipping_phone1 = "shipping_phone1";
  		var shipping_phone2 = "shipping_phone2";
  		var shipping_email = "shipping_email";
  		var shipping_country = "shipping_country";
  		var shipping_addr1 = "shipping_addr1";
  		var shipping_postcode = "shipping_postcode";
  		
      // Function to get the value of a specific cookie
      // Split the cookies into an array
      const cookies = document.cookie.split(";");

      // Loop through the cookies to find the one with the specified name
      for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i].trim();
				// console.log(cookie);
        
        // Check if the cookie starts with the name we're looking for
        if (cookie.startsWith(shipping_name + "=")) {
          // Return the value of the cookie
          shipping_name = decodeURIComponent(cookie.substring(shipping_name.length + 1));
          document.getElementById("shippingName").innerHTML = shipping_name;
        }
        if (cookie.startsWith(shipping_phone1 + "=")) {
        	shipping_phone1 = decodeURIComponent(cookie.substring(shipping_phone1.length + 1));
        	document.getElementById("shippingPhone1").innerHTML = shipping_phone1;
        }
        if (cookie.startsWith(shipping_phone2 + "=")) {
        	shipping_phone2 = decodeURIComponent(cookie.substring(shipping_phone2.length + 1));
        	document.getElementById("shippingPhone2").innerHTML = shipping_phone2;
        }
        if (cookie.startsWith(shipping_email + "=")) {
        	shipping_email = decodeURIComponent(cookie.substring(shipping_email.length + 1));
        	document.getElementById("shippingEmail").innerHTML = shipping_email;
        }
        if (cookie.startsWith(shipping_country + "=")) {
        	shipping_country = decodeURIComponent(cookie.substring(shipping_country.length + 1));
        	document.getElementById("shippingCountry").innerHTML = shipping_country;
        }
        if (cookie.startsWith(shipping_addr1 + "=")) {
        	shipping_addr1 = decodeURIComponent(cookie.substring(shipping_addr1.length + 1));
        	// '+'을 빈공백으로 치환
        	shipping_addr1 = shipping_addr1.split('+').join(' ');
        	console.log(shipping_addr1);
        	document.getElementById("shippingAddr1").innerHTML = shipping_addr1;
        	document.getElementById("shippingAddr2").innerHTML = shipping_addr1;
        }
        if (cookie.startsWith(shipping_postcode + "=")) {
        	shipping_postcode = decodeURIComponent(cookie.substring(shipping_postcode.length + 1));
        	document.getElementById("shippingPostcode").innerHTML = shipping_postcode;
        }
      }
  	}
  </script>
</body>

</html>