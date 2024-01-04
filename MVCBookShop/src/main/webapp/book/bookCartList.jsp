<%@ page import="vo.Book" %>
<%@ page import="vo.Cart" %>
<%@ page import="dao.BookDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
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

  <!-- CSS -->
  <link rel="stylesheet" href="/css/linearicons.css">
  <link rel="stylesheet" href="/css/owl.carousel.css">
  <link rel="stylesheet" href="/css/font-awesome.min.css">
  <link rel="stylesheet" href="/css/themify-icons.css">
  <link rel="stylesheet" href="/css/nice-select.css">
  <link rel="stylesheet" href="/css/nouislider.min.css">
  <link rel="stylesheet" href="/css/bootstrap.css">
  <link rel="stylesheet" href="/css/main.css">
  <style type="text/css">
		#cartImage {
			width:70px;
			height: 100px;
			border: none;
		}
  </style>
  
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
          <h1>Shopping Cart</h1>
          <nav class="d-flex align-items-center">
            <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
            <a href="category.html">Cart</a>
          </nav>
        </div>
      </div>
    </div>
  </section>
  <!-- End Banner Area -->

	<!-- Cart Search Function -->
	<c:if test="${startMoney != null }">
		<c:set var="startMoney" value="${startMoney }"/>
	</c:if>
	<c:if test="${endMoney != null }">
		<c:set var="endMoney" value="${endMoney }"/>
	</c:if>
	<!-- Cart Search Function -->

  <!--================Cart Area =================-->
  <section class="cart_area">
  	<c:if test="${cartList != null && cartList.size() > 0}">
  		<form method="post">
		    <div class="container">
		      <div class="cart_inner">
		        <div class="table-responsive">
							<select id="startMoney" name="startMoney">
								<option>=최하=</option>
								<c:choose>
									<c:when test="${startMoney == 10000 }">
										<option selected="selected">10000</option>
										<option>20000</option>
										<option>30000</option>
										<option>40000</option>
									</c:when>
									<c:when test="${startMoney == 20000 }">
										<option>10000</option>
										<option selected="selected">20000</option>
										<option>30000</option>
										<option>40000</option>
									</c:when>
									<c:when test="${startMoney == 30000 }">
										<option>10000</option>
										<option>20000</option>
										<option selected="selected">30000</option>
										<option>40000</option>
									</c:when>
									<c:when test="${startMoney == 40000 }">
										<option>10000</option>
										<option>20000</option>
										<option>30000</option>
										<option selected="selected">40000</option>
									</c:when>
									<c:otherwise>
										<option>10000</option>
										<option>20000</option>
										<option>30000</option>
										<option>40000</option>
									</c:otherwise>
								</c:choose>
							</select>
							
							<select id="endMoney" name="endMoney">
								<option>=최고=</option>
								<c:choose>
									<c:when test="${endMoney == 10000 }">
										<option selected="selected">10000</option>
										<option>20000</option>
										<option>30000</option>
										<option>40000</option>
									</c:when>
									<c:when test="${endMoney == 20000 }">
										<option>10000</option>
										<option selected="selected">20000</option>
										<option>30000</option>
										<option>40000</option>
									</c:when>
									<c:when test="${endMoney == 30000 }">
										<option>10000</option>
										<option>20000</option>
										<option selected="selected">30000</option>
										<option>40000</option>
									</c:when>
									<c:when test="${endMoney == 40000 }">
										<option>10000</option>
										<option>20000</option>
										<option>30000</option>
										<option selected="selected">40000</option>
									</c:when>
									<c:otherwise>
										<option>10000</option>
										<option>20000</option>
										<option>30000</option>
										<option>40000</option>
									</c:otherwise>
								</c:choose>
							</select>
							<input type="submit" value="검색" formaction="/bookCartSearch.ok"/>
							
		          <table class="table" id="cart-table">
		            <thead>
		              <tr>
		              	<th scope="col" style="width: 3px;">
		              		<input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll(this.form)"/>
		              	</th>
		                <th scope="col">상품</th>
		                <th scope="col">가격</th>
		                <th scope="col">수량</th>
		                <th scope="col">금액</th>
		              </tr>
		            </thead>
		            <tbody>
		            	<c:forEach var="cart" items="${cartList }" varStatus="status">
		                <tr>
		                	<td>
												<input type="checkbox" class="remove-id" name="remove" value="${cart.c_b_id }" />
											</td>
											
	                    <td>
                        <div class="media">
                          <div class="d-flex">
                            <img src="${request.getContextPath() }/bookImage/${cart.c_b_image }" alt="${cart.c_b_name }" id="cartImage">
                          </div>
                          <div class="media-body">
                            <p>${cart.c_b_name }</p>
                          </div>
                        </div>
	                    </td>
	                    
	                    <td>
                        <h5 class="product-price"><i class="bi bi-currency-dollar"></i>${cart.c_b_price }</h5>
	                    </td>
	                    
	                    <td>
	                    	<div class="product_count">
	                      	<div class="qty-up-a" style="cursor:pointer;">
														<i class="lnr lnr-chevron-up"></i>
													</div>
													<div class="qty-div d-inline">
														<span class="product-qty d-block">${cart.c_b_qty }</span>
													</div>
													<div class="qty-down-a" style="cursor:pointer;"> 
														<i class="lnr lnr-chevron-down"></i>
													</div>
									      </div>
	                    </td>
	                    
	                    <td>
	                    	<h5 class="product-qty-total"><i class="bi bi-currency-dollar"></i>${cart.c_b_qty * cart.c_b_price }</h5>
	                    </td>
		                </tr>
		            	</c:forEach>
		                
		                <tr class="bottom_button">
		                    <td>
		                    	<input type="submit" class="gray_btn" value="삭제" formaction="/bookCartRemove.ok"/>
		                    </td>
		                    <td>
		                    </td>
		                    <td>
		                    </td>
		                    
												</td>               
		                    <td>
		                        <div class="cupon_text d-flex align-items-center">
		                            <input type="text" placeholder="Coupon Code">
		                            <a class="primary-btn" href="#">Apply</a>
		                            <a class="gray_btn" href="#">Close Coupon</a>
		                        </div>
		                    </td>
		                </tr>
		                <tr>
		                    <td>
		
		                    </td>
		                    <td>
		
		                    </td>
		                    <td>
		                        <h5>총금액</h5>
		                    </td>
		                    <td>
		                        <h5 class="cart-total-price"><i class="bi bi-currency-dollar"></i>${totalMoney }</h5>
		                    </td>
		                </tr>
		                <!-- <tr class="shipping_area">
		                    <td>
		
		                    </td>
		                    <td>
		
		                    </td>
		                    <td>
		                        <h5>Shipping</h5>
		                    </td>
		                    <td>
		                        <div class="shipping_box">
		                            <ul class="list">
		                                <li><a href="#">Flat Rate: $5.00</a></li>
		                                <li><a href="#">Free Shipping</a></li>
		                                <li><a href="#">Flat Rate: $10.00</a></li>
		                                <li class="active"><a href="#">Local Delivery: $2.00</a></li>
		                            </ul>
		                            <h6>Calculate Shipping <i class="fa fa-caret-down" aria-hidden="true"></i></h6>
		                            <select class="shipping_select">
		                                <option value="1">Bangladesh</option>
		                                <option value="2">India</option>
		                                <option value="4">Pakistan</option>
		                            </select>
		                            <select class="shipping_select">
		                                <option value="1">Select a State</option>
		                                <option value="2">Select a State</option>
		                                <option value="4">Select a State</option>
		                            </select>
		                            <input type="text" placeholder="Postcode/Zipcode">
		                            <a class="gray_btn" href="#">Update Details</a>
		                        </div>
		                    </td>
		                </tr> -->
		                <tr class="out_button_area">
		                    <td>
		
		                    </td>
		                    <td>
		
		                    </td>
		                    <td>
		
		                    </td>
		                    <td>
		                        <div class="checkout_btn_inner d-flex align-items-center">
		                            <a class="gray_btn" href="${request.getContextPath() }/bookList.ok">쇼핑 계속하기</a>
		                            <a class="primary-btn" href="javascript:orderForm();">주문하기</a>
		                        </div>
		                    </td>
		                </tr>
		            </tbody>
		          </table>
		        </div>
		      </div>
		    </div>
		    </form>
    </c:if>
    
    <c:if test="${cartList == null || cartList.size() eq 0 }">
			 <h1>장바구니에 담은 책이 없습니다.</h1>
		</c:if>
  </section>
  <!--================End Cart Area =================-->

  <!-- start footer Area -->
	<%@ include file="/common/footer.jsp" %>
  <!-- End footer Area -->

  <script src="js/vendor/jquery-2.2.4.min.js"></script>
  <script src="js/vendor/jquery.cookie.js"></script>
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
	<!-- yj JS -->
	<script type="text/javascript">
	
		function checkAll(theForm) {
				if(theForm.remove.length == undefined) {
					theForm.remove.checked = theForm.allCheck.checked;
				} else {
					for(var i=0; i<theForm.remove.length; i++) {
						theForm.remove[i].checked = theForm.allCheck.checked;
					}
				}
		}
		
		$(document).on('click', '.qty-up-a, .qty-down-a', function(e) {
			  e.preventDefault();
			  
				// 클릭한 요소의 클래스를 확인 후 처리를 다르게 해주기 위한 설정
			  var $clickedElement = $(this);	
			  var $row = $clickedElement.closest('tr');

			  // 확인용
				console.log("Matched row : " + $row.html());
				console.log("Matched row Index: " + $row.index());
			  
		    console.log("Book ID: " + $row.find('.remove-id').val());
		    console.log("Book price: " + $row.find('.product-price').text());
		    console.log("Book Qty: " + $row.find('.product-qty').text());
		    console.log("Book * Qty Total: " + $row.find('.product-qty-total').text());
		    
			  /* .hasClass() 함수를 사용하여 클래스를 확인 */
			  if ($clickedElement.hasClass('qty-up-a')) {
				  
			    var how = 'up';
			    console.log("Clicked qty-up-a");
			    
			 		// DB 또는 세션 처리
			 		formQty($row, how);
			  } else if ($clickedElement.hasClass('qty-down-a')) {
			    
			    var how = 'down';
			    console.log("Clicked qty-down-a");
			    
			 		// 수량이 1보다 큰 경우에만 DB 또는 세션 처리
			    if(parseInt($row.find('.product-qty').text()) > 1) formQty($row, how);
			  }
			});


		function formQty(trQueryObj, how) {
		  
		  var productId = trQueryObj.find('.remove-id').val();
		  var productPriceText = trQueryObj.find('.product-price').text();
		  var $productQty = trQueryObj.find('.product-qty');
		  var $productQtyTotal = trQueryObj.find('.product-qty-total');
		  
			$.ajax({
				url: '/bookCartFormQty.ax',
				type: 'POST',
				data: {b_id:productId, how:how},
				dataType: "TEXT",
				success: function(response) {
					var intValue = parseInt(response);
					// alert(intValue);
					
					switch (intValue) {
					  case -1:
					    // 세션이 존재하지 않는 경우
					    alert('허용되지 못한 접근입니다.');
							location.href='/bookShopMain.ok;';
					    break;
					    
					  case -2:
					    // DB 저장에 실패한 경우
					    alert('처리에 실패했습니다.');
							history.back(-1);
					    break;
					    
					  default:
					    // 위 case에 매치되지 않을 경우
					    // DB에서 성공적으로 수정되거나 장바구니 세션에 성공적으로 수정된 경우
					    var productQtyText = parseInt($productQty.text());
					    if(how == 'up') {
					    	// 수량 1 증가
					    	productQtyText++;
					    } else {
					    	productQtyText--;
					    }

					    /* 브라우저 화면 값 변경
					     * 1. 수량 변경
					     * 2. 가격 * 수량 계산 
					     * 3. 총 금액 변경 */
					  	// alert(productQtyText);
					  	$productQty.text(productQtyText);
					  	$productQtyTotal.text(productQtyText * parseInt(productPriceText));
							
							// 총금액 계산
							$('.cart-total-price').text(getSubTotal);
					  	break;
					}
				}, 
				error:function(data,textStatus){
        	alert("처리되지 못했습니다.");ㅣ
        }
			}); 		
		}
		
		/* 장바구니 총 금액 계산 */
	  function getSubTotal() {
		  var sum = 0; // 누적 변수 초기화

		  $('.product-qty-total').each(function() {
		    var textValue = $(this).text(); // 텍스트 값을 가져옴
		    var numericValue = parseInt(textValue, 10); // 정수로 변환
		    sum += numericValue; // 누적 변수에 더함
		  });

		  console.log("Cart Total Price: " + sum); // 합산된 결과 출력
		  return sum;
	  }
	  
		function orderForm() {
			
			alert($.cookie('c_session'));
			var $form = $('<form>').attr({
				action: '/bookShippingCheckOut.ok',
				method: 'post'
			});
			
/* 			var $input = $('<input>').attr({
				type: 'hidden',
				name: 'c_m_id',
				value: m_id
			}); */
			
			/* var $input2 = $('<input>').attr({
				type: 'hidden',
				name: 'c_session',
				value: $.cookie('c_session')
			});
			
			$form.append($input2); */
			$form.appendTo('body'); // Append the form to the document body 
			$form.submit();
			
		}
	</script>
</body>

</html>