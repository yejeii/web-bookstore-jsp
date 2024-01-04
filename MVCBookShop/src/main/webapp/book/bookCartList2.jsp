<%@ page import="vo.Book" %>
<%@ page import="vo.Cart" %>
<%@ page import="dao.BookDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC Book Shop</title>
<script src="https://kit.fontawesome.com/a888c9b95b.js" crossorigin="anonymous"></script>
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
	
	function checkQty(b_id, qty) {
		if(qty != 1) {
			location.href="/bookCartQtyDown.ok?b_id="+b_id;
		}
	}
</script>
<style type="text/css">
	#listForm {
		width: 640px;
		border: 1px solid red;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 550px;
	}
	
	.tr_top {
		background-color: lime;
	}
	
	.div_empty {
		text-align: center;
		background-color: orange;
	}
	
	.td_command {
		text-align: right;	
	}
	
	#todayImageList {
		text-align: center;
	}
	
	#productImage {
		width: 150px;
		height: 150px;
		border: none;
	}
	
	#cartImage {
		width: 70px;
		height: 70px;
		border: none;
	}
	
	#select {
		text-align: right;
	}
	
	#commandList {
		text-align: center;
	}
	
	#upImage {
		width: 15px;
	}
	
	#downImage {
		width: 15px;
	}
	
</style>
</head>
<body>
	<c:if test="${startMoney != null }">
		<c:set var="startMoney" value="${startMoney }"/>
	</c:if>
	<c:if test="${endMoney != null }">
		<c:set var="endMoney" value="${endMoney }"/>
	</c:if>
	
	<section id="listForm">
		<c:if test="${cartList != null && cartList.size() > 0}">
			<h2>장바구니 목록</h2>
			<form method="post">
				<table>
					<tr id="select">
						<td colspan="6">
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
						</td>
					</tr>
					
					<tr class="tr_top">
						<td>
							<input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll(this.form)"/>
						</td>
						<td>번호</td>
						<td>상품 이미지</td>
						<td>가격</td>
						<td>수량</td>
					</tr>

					<c:forEach var="cart" items="${cartList }" varStatus="status">
					
						<tr>
							<td>
								<input type="checkbox" id="remove" name="remove" value="${cart.b_id }"/>
							</td>
							
							<td>
								${status.index+1 }	<!-- 번호값 계산 -->
							</td>
							
							<td>
								<img src="images/${cart.b_image }" id="cartImage"/>
							</td>
							
							<td>
								${cart.b_price }&nbsp;원
							</td>
							
							<td>
								<a href="/bookCartQtyUp.ok?b_id=${cart.b_id }">
									<i class="fa-solid fa-chevron-up"></i>
								</a>
								<br>
								${cart.b_qty }
								<br>
								<a href="javascript:checkQty(${cart.b_id }, ${cart.b_qty })">
									<i id="downIcon" class="fa-solid fa-chevron-down"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
					
					<tr>
						<td colspan="5" style="text-align: center;">
							총금액: ${totalMoney }원
						</td>
					</tr>
	
					<tr>
						<td colspan="5" style="text-align: center;">
							<input type="submit" value="삭제" formaction="/bookCartRemove.ok"/>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		
		<c:if test="${cartList == null }">
			<section class="div_empty">
			 책 정보가 없습니다.
			</section>
		</c:if>
		
		<nav id="commandList">
			<a href="/bookList.ok">쇼핑 계속하기</a>
		</nav>
	</section>
</body>
</html>